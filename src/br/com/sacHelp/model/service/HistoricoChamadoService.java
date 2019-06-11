package br.com.sacHelp.model.service;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sacHelp.model.entity.Chamados;
import br.com.sacHelp.model.entity.HistoricoChamado;
import br.com.sacHelp.model.percistence.interfaces.IRepositorioHistoricoChamado;

@Service
public class HistoricoChamadoService {

	@Autowired
	IRepositorioHistoricoChamado repHistoricoChamadoDAO;
	
	public void adicionar(HistoricoChamado historicoChamado) throws SQLException{
		repHistoricoChamadoDAO.adicionar(historicoChamado);
	}
	
	public List<HistoricoChamado> consultar() throws SQLException{
		return repHistoricoChamadoDAO.consultar();
	}
	
	public List<HistoricoChamado> consultar(int idChamado) throws SQLException{
		return repHistoricoChamadoDAO.consultar(idChamado);
	}

	public HistoricoChamado consultarHistoricoPorId(int id) throws SQLException {
		return repHistoricoChamadoDAO.consultarHistoricoProId(id);
	}
	
	public void editar(HistoricoChamado historicoChamado) throws SQLException{
		repHistoricoChamadoDAO.editar(historicoChamado);
	}

	public void registrarNovoPrazo(Chamados chamadoAnterior, Chamados chamado) throws SQLException {
		SimpleDateFormat sDFormat = new SimpleDateFormat("HH:mm:ss");
		DateFormat formatarData = DateFormat.getDateInstance();
		HistoricoChamado historicoChamado = new HistoricoChamado();
		historicoChamado.setChamado(chamado);
		historicoChamado.setOcorrencia("Atualização do prazo. ");
		historicoChamado.getData().setTime(new Date());
		historicoChamado.setHora(sDFormat.format(new Date()));
		
		historicoChamado.setOcorrencia(historicoChamado.getOcorrencia()+
				"Anterior: "+formatarData.format(chamadoAnterior.getPrazoSolucao().getTime())+
				" | Novo Prazo: "+formatarData.format(chamado.getPrazoSolucao().getTime()));
		
		adicionar(historicoChamado);
	}
	
	public void registrarAlteracao(Chamados chamadoAnterior, Chamados chamado) throws SQLException {
		SimpleDateFormat sDFormat = new SimpleDateFormat("HH:mm:ss");
		DateFormat formatarData = DateFormat.getDateInstance();
		HistoricoChamado historicoChamado = new HistoricoChamado();
		historicoChamado.setChamado(chamado);
		historicoChamado.setOcorrencia("Alteração ");
		historicoChamado.getData().setTime(new Date());
		historicoChamado.setHora(sDFormat.format(new Date()));
		
		if(!chamadoAnterior.getContato().equals(chamado.getContato())){
			historicoChamado.setOcorrencia(historicoChamado.getOcorrencia()+
					"no campo Contato de: "+chamadoAnterior.getContato()+
					" - PARA: "+chamado.getContato()+" - ");
		}
		if(!chamadoAnterior.getLoja().equals(chamado.getLoja())){
			historicoChamado.setOcorrencia(historicoChamado.getOcorrencia()+
					"no campo Loja de: "+chamadoAnterior.getLoja()+
					" - PARA: "+chamado.getLoja()+" - ");
		}
		if(!chamadoAnterior.getProtocolo().equals(chamado.getProtocolo())){
			historicoChamado.setOcorrencia(historicoChamado.getOcorrencia()+
					"no campo Protocolo de: "+chamadoAnterior.getProtocolo()+
					" - PARA: "+chamado.getProtocolo()+" - ");
		}
		if(!chamadoAnterior.getTecResponsavel().equals(chamado.getTecResponsavel())){
			historicoChamado.setOcorrencia(historicoChamado.getOcorrencia()+
					"no campo TecResponsavel de: "+chamadoAnterior.getTecResponsavel()+
					" - PARA: "+chamado.getTecResponsavel()+" - ");
		}
		if(!chamadoAnterior.getSetorResponsavel().equals(chamado.getSetorResponsavel())){
			historicoChamado.setOcorrencia(historicoChamado.getOcorrencia()+
					"no campo Setor/Responsavel de: "+chamadoAnterior.getSetorResponsavel()+
					" - PARA: "+chamado.getSetorResponsavel()+" - ");
		}
		if(!chamadoAnterior.getStatus().equals(chamado.getStatus())){
			historicoChamado.setOcorrencia(historicoChamado.getOcorrencia()+
					"no campo Status de: "+chamadoAnterior.getStatus()+
					" - PARA: "+chamado.getStatus()+" - ");
		}
		if(!chamadoAnterior.getTitulo().equals(chamado.getTitulo())){
			historicoChamado.setOcorrencia(historicoChamado.getOcorrencia()+
					"no campo Título de: "+chamadoAnterior.getTitulo()+
					" - PARA: "+chamado.getTitulo()+" - ");
		}
		if(!chamadoAnterior.getTipoAtendimento().equals(chamado.getTipoAtendimento())){
			historicoChamado.setOcorrencia(historicoChamado.getOcorrencia()+
					"no campo Tipo Atendimento de: "+chamadoAnterior.getTipoAtendimento()+
					" - PARA: "+chamado.getTipoAtendimento()+" - ");
		}
		if(!chamadoAnterior.getPrazoSolucao().equals(chamado.getPrazoSolucao())){
			historicoChamado.setOcorrencia(historicoChamado.getOcorrencia()+
					"no campo Prazo Solução de: "+formatarData.format(chamadoAnterior.getPrazoSolucao().getTime())+
					" - PARA: "+formatarData.format(chamado.getPrazoSolucao().getTime())+" - ");
			
		}
		if(!chamadoAnterior.getDescricao().equals(chamado.getDescricao())){
			historicoChamado.setOcorrencia(historicoChamado.getOcorrencia()+
					"no campo Descrição de: "+chamadoAnterior.getDescricao()+
					" - PARA: "+chamado.getDescricao()+" - ");
		}
		if(!chamadoAnterior.getSolucao().equals(chamado.getSolucao())){
			historicoChamado.setOcorrencia(historicoChamado.getOcorrencia()+
					"no campo Solução de: "+chamadoAnterior.getSolucao()+
					" - PARA: "+chamado.getSolucao()+" - ");
		}
		if(!chamadoAnterior.getObservacao().equals(chamado.getObservacao())){
			historicoChamado.setOcorrencia(historicoChamado.getOcorrencia()+
					"no campo Observação de: "+chamadoAnterior.getObservacao()+
					" - PARA: "+chamado.getObservacao()+" - ");
		}
		
		adicionar(historicoChamado);
	}
	
}
