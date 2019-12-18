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

	private static String ordem = "asc";
	private static String colunaAnteridor = "";
	private List<Chamados> listaDaConsulta;
	
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
	
	//CLASSIFICA EM CRESCENTE O DECRESCENTE A PARTIR DE UMA COLUNA DA TELA LISTAR CHAMADOS
	public List<Chamados> classificarAscDesc(String coluna) throws SQLException {
		
		if(ordem.equals("asc") && colunaAnteridor.equals(coluna)){
			ordem = "desc";
			listaDaConsulta = repChamadosDAO.consultarOrdenado(coluna, ordem);
		}else{
			ordem = "asc";
			colunaAnteridor = coluna;
			listaDaConsulta = repChamadosDAO.consultarOrdenado(coluna, ordem);
		}
		
		return listaDaConsulta;
		
	}
	
	public List<Chamados> consultarPorDescricaoOuTitulo(Chamados chamado) throws SQLException{
		return repChamadosDAO.consultarPorDescricaoOuTitulo(chamado);
	}
	
	public List<Chamados> consultarPorPrazoSolucaoHoje(Chamados chamado) throws SQLException {
		return adicionarCalcAtrasoNaLista(repChamadosDAO.consultarPorPrazoSolucaoHojeERP(chamado));
	}
	
	public List<Chamados> consultarAbertosOuEmAndamento() throws SQLException{
		return repChamadosDAO.consultarAbertosOuEmAndamentoERP();
	}
	
	public List<Chamados> consultarAbertos() throws SQLException{
		return adicionarCalcAtrasoNaLista(repChamadosDAO.consultarAbertosERP());
	}
	
	public List<Chamados> consultarEmAndamento() throws SQLException{
		return adicionarCalcAtrasoNaLista(repChamadosDAO.consultarEmAndamentoERP());
	}
	
	//CONSULTA CHAMADOS PENDENTES E FILTRA OS ATRAZADOS
	public List<Chamados> consultarAtrasados() throws SQLException{
		return filtrarAtrasados(repChamadosDAO.consultarAbertosOuEmAndamentoERP());
	}
	
	//----------------------------------------------------
	
	public List<Chamados> consultarPorPrazoSolucaoHojeTI(Chamados chamado) throws SQLException {
		return adicionarCalcAtrasoNaLista(repChamadosDAO.consultarPorPrazoSolucaoHojeTI(chamado));
	}
	
	public List<Chamados> consultarAbertosOuEmAndamentoTI() throws SQLException{
		return repChamadosDAO.consultarAbertosOuEmAndamentoTI();
	}
	
	public List<Chamados> consultarAbertosTI() throws SQLException{
		return adicionarCalcAtrasoNaLista(repChamadosDAO.consultarAbertosTI());
	}
	
	public List<Chamados> consultarEmAndamentoTI() throws SQLException{
		return adicionarCalcAtrasoNaLista(repChamadosDAO.consultarEmAndamentoTI());
	}
	
	public List<Chamados> consultarAtrasadosTI() throws SQLException{
		return filtrarAtrasados(repChamadosDAO.consultarAbertosOuEmAndamentoTI());
	}
	
	//----------------------------------------------------
	
	public void editar(Chamados chamado) throws SQLException{
		repChamadosDAO.editar(chamado);
	}
	
	//CONTA CHAMADOS PENDENTES SEPARANDO ABERTOS, EM ANDAMENTO E ATRASADOS
	public ContaPendentes contarChamadosPendentes(List<Chamados> listaChamadosPendentes){
		ContaPendentes contaPendentes = new ContaPendentes();
		Calendar dataAtual = new GregorianCalendar();
		int dias = 0;
		
		for(Chamados chamadoDaConsulta : listaChamadosPendentes){
			if(chamadoDaConsulta.getStatus().equals("ABERTO")){
				contaPendentes.setQtdAbertos(contaPendentes.getQtdAbertos()+1);
			}else if(chamadoDaConsulta.getStatus().equals("EM ANDAMENTO")){
				contaPendentes.setQtdEmAndamento((contaPendentes.getQtdEmAndamento()+1));
			}
			
			if(chamadoDaConsulta.getPrazoSolucao().before(dataAtual) &&
					!sDFormat.format(chamadoDaConsulta.getPrazoSolucao().getTime()).equals(sDFormat.format(dataAtual.getTime()))){
				contaPendentes.setQtdAtrasados(contaPendentes.getQtdAtrasados()+1);
			}
			
			if(sDFormat.format(chamadoDaConsulta.getPrazoSolucao().getTime()).equals(sDFormat.format(dataAtual.getTime()))){
				contaPendentes.setQtdPrazoPraHoje(contaPendentes.getQtdPrazoPraHoje()+1);
			}
			
			
			contaPendentes.setTotalPendentes(((contaPendentes.getQtdEmAndamento()+contaPendentes.getQtdAbertos())));
			
			dias = calcularAtraso(chamadoDaConsulta);
			if(dias > 0){
				chamadoDaConsulta.setDiferencaTempoDeEntrega(String.valueOf(dias));
			}else{
				chamadoDaConsulta.setDiferencaTempoDeEntrega(String.valueOf(dias));
			}
		}
		return contaPendentes;
	}
	
	//ADICIONA APENAS O CALCULO DO ATRASO EM CADA CHAMADO DA LISTA PASSADA
	public List<Chamados> adicionarCalcAtrasoNaLista(List<Chamados> listaChamadosPendentes){
		List<Chamados> novaLista = new LinkedList<>();
		int dias = 0;
		for(Chamados chamadoDaLista : listaChamadosPendentes){
			
			dias = calcularAtraso(chamadoDaLista);
			if(dias > 0){
				chamadoDaLista.setDiferencaTempoDeEntrega(String.valueOf(dias));
			}else{
				chamadoDaLista.setDiferencaTempoDeEntrega(String.valueOf(dias));
			}
			
			novaLista.add(chamadoDaLista);
		}
		
		return novaLista;
		
	}
	
	//CALCULA A QUANTIDADE DE DIAS EM ATRASO DO CHAMADO
	public int calcularAtraso(Chamados chamado){
		
		Date dData;
		dData = chamado.getPrazoSolucao().getTime();
		Calendar dataAtual = Calendar.getInstance();
		Calendar dataValidade = Calendar.getInstance();
		dataValidade.setTime(dData);
		dataAtual.setTime(new Date());
		int ano1 = dataAtual.get(Calendar.YEAR);
		int ano2 = dataValidade.get(Calendar.YEAR);
		int dias = dataValidade.get(Calendar.DAY_OF_YEAR) - dataAtual.get(Calendar.DAY_OF_YEAR);
		return (ano2 - ano1)*365 + (dias);
		
	}
	
	//FILTRA DE UMA LISTA DE CHAMADOS APENAS OS ATRASADOS
	public List<Chamados> filtrarAtrasados(List<Chamados> listaChamadosPendentes){
		List<Chamados> novaLista = new LinkedList<>();
		int dias = 0;
		for(Chamados chamadoDaLista : listaChamadosPendentes){
			dias = calcularAtraso(chamadoDaLista);
			if(dias > 0){
				chamadoDaLista.setDiferencaTempoDeEntrega(String.valueOf(dias));
				novaLista.add(chamadoDaLista);
			}
		}
		return novaLista;
	}

	
}
