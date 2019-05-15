package br.com.sacHelp.model.service;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sacHelp.model.entity.Chamados;
import br.com.sacHelp.model.exception.CampoVazioException;
import br.com.sacHelp.model.exception.PrazoInvadoException;
import br.com.sacHelp.model.percistence.interfaces.IRepositorioChamados;
import br.com.sacHelp.util.ContaPendentes;

@Service
public class ChamadosService {

	@Autowired
	IRepositorioChamados repChamadosDAO;
	
	SimpleDateFormat sDFormat = new SimpleDateFormat("dd/MM/yyyy");
	
	public void adicionar(Chamados chamado) throws PrazoInvadoException, CampoVazioException, SQLException{
		if(chamado.getDescricao().equals("")||
				chamado.getLoja().equals("")||
				chamado.getContato().equals("")){
			throw new CampoVazioException("Atenção: Os Campos 'Descrição, Loja e Contato são obrigatórios!");
		}
		if(sDFormat.format(chamado.getPrazoSolucao().getTime()).equals(sDFormat.format(chamado.getDataAbertura().getTime()))){
			repChamadosDAO.adicionar(chamado);
		}else if(chamado.getPrazoSolucao().before(chamado.getDataAbertura())){
			throw new PrazoInvadoException("Data prazo não pode ser ANTERIOR à data de Abertura!");
		}else{
			repChamadosDAO.adicionar(chamado);
		}
	}
	
	public List<Chamados> consultar() throws SQLException{
		return repChamadosDAO.consultar();
	}

	public Chamados consultarChamadoPorId(int id) throws SQLException {
		return repChamadosDAO.consultarChamadoProId(id);
	}
	
	public List<Chamados> consultarAbertosOuEmAndamento() throws SQLException{
		return repChamadosDAO.consultarAbertosOuEmAndamento();
	}
	
	public List<Chamados> consultarAbertos() throws SQLException{
		return adicionarCalcAtrasoNaLista(repChamadosDAO.consultarAbertos());
	}
	
	public List<Chamados> consultarEmAndamento() throws SQLException{
		return adicionarCalcAtrasoNaLista(repChamadosDAO.consultarEmAndamento());
	}
	
	public List<Chamados> consultarAtrasados() throws SQLException{
		return filtrarAtrasados(repChamadosDAO.consultarAbertosOuEmAndamento());
	}
	
	public void editar(Chamados chamado) throws SQLException{
		repChamadosDAO.editar(chamado);
	}
	
	public ContaPendentes contarChamadosPendentes(List<Chamados> listaChamadosPendentes){
		ContaPendentes contaPendentes = new ContaPendentes();
		Calendar dataAtual = new GregorianCalendar();
		int dias = 0;
		for(Chamados chamadosDaConsulta : listaChamadosPendentes){
			if(chamadosDaConsulta.getStatus().equals("ABERTO")){
				contaPendentes.setQtdAbertos(contaPendentes.getQtdAbertos()+1);
			}else if(chamadosDaConsulta.getStatus().equals("EM ANDAMENTO")){
				contaPendentes.setQtdEmAndamento((contaPendentes.getQtdEmAndamento()+1));
			}
			
			if(chamadosDaConsulta.getPrazoSolucao().before(dataAtual)){
				contaPendentes.setQtdAtrasados(contaPendentes.getQtdAtrasados()+1);
			}
			
			contaPendentes.setTotalPendentes(((contaPendentes.getQtdEmAndamento()+contaPendentes.getQtdAbertos())));
			
			dias = calcularAtraso(chamadosDaConsulta);
			if(dias > 0){
				chamadosDaConsulta.setDiferencaTempoDeEntrega(dias+" Atra");
			}else{
				chamadosDaConsulta.setDiferencaTempoDeEntrega(dias+" Rest");
			}
		}
		return contaPendentes;
	}
	
	public List<Chamados> adicionarCalcAtrasoNaLista(List<Chamados> listaChamadosPendentes){
		List<Chamados> novaLista = new LinkedList<>();
		int dias = 0;
		for(Chamados chamadoDaLista : listaChamadosPendentes){
			
			dias = calcularAtraso(chamadoDaLista);
			if(dias > 0){
				chamadoDaLista.setDiferencaTempoDeEntrega(dias+" Atra");
			}else{
				chamadoDaLista.setDiferencaTempoDeEntrega(dias+" Rest");
			}
			
			novaLista.add(chamadoDaLista);
		}
		
		return novaLista;
		
	}
	
	public int calcularAtraso(Chamados chamado){
		Calendar dataAtual = Calendar.getInstance();
		dataAtual.setTime(new Date());
		int dias = dataAtual.get(Calendar.DAY_OF_YEAR) - chamado.getPrazoSolucao().get(Calendar.DAY_OF_YEAR);
		
		return dias;
	}
	
	public List<Chamados> filtrarAtrasados(List<Chamados> listaChamadosPendentes){
		List<Chamados> novaLista = new LinkedList<>();
		int dias = 0;
		for(Chamados chamadoDaLista : listaChamadosPendentes){
			dias = calcularAtraso(chamadoDaLista);
			if(dias > 0){
				chamadoDaLista.setDiferencaTempoDeEntrega(dias+" Atra");
				novaLista.add(chamadoDaLista);
			}
		}
		return novaLista;
	}
}
