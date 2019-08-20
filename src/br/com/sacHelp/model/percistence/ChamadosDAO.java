package br.com.sacHelp.model.percistence;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.LinkedHashSet;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.sacHelp.model.entity.Chamados;
import br.com.sacHelp.model.entity.HistoricoChamado;
import br.com.sacHelp.model.percistence.interfaces.IRepositorioChamados;

@Repository
public class ChamadosDAO implements IRepositorioChamados {

	@PersistenceContext
	private EntityManager manager;
	
	private SimpleDateFormat sdfFomatarData = new SimpleDateFormat("yyyy-MM-dd");
	
	@Override
	public void adicionar(Chamados chamado) {
		manager.persist(chamado);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Chamados> consultar() {
		List<Chamados> listaDaConsulta = null;
		listaDaConsulta = manager.createQuery("select c from Chamados c").getResultList();
		
		return listaDaConsulta;
	}

	@Override
	public Chamados consultarChamadoProId(int id) throws SQLException{
		
		try{
			return (Chamados) manager.createQuery("select c from Chamados c join fetch c.historicosChamado where c.id="+id).getSingleResult();
		}catch(NoResultException e){
			
			Chamados chamadoDaConsulta = manager.find(Chamados.class, id);
			chamadoDaConsulta.setHistoricosChamado(new LinkedHashSet<HistoricoChamado>());
			return chamadoDaConsulta;
		}
	
	}

	@Override
	public void editar(Chamados chamado) throws SQLException {
		manager.merge(chamado);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Chamados> consultarAbertosOuEmAndamentoERP() throws SQLException {
		List<Chamados> listaDaConsulta = null;
		listaDaConsulta = manager.createQuery("select c from Chamados as c where (c.status='ABERTO' or c.status='EM ANDAMENTO') and c.setorResponsavel = 'BM' order by c.id desc").getResultList();
		return listaDaConsulta;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Chamados> consultarAbertosERP() throws SQLException {
		List<Chamados> listaDaConsulta = null;
		listaDaConsulta = manager.createQuery("select c from Chamados as c where c.status='ABERTO' and c.setorResponsavel = 'BM' order by c.id desc").getResultList();
		return listaDaConsulta;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Chamados> consultarEmAndamentoERP() throws SQLException {
		List<Chamados> listaDaConsulta = null;
		listaDaConsulta = manager.createQuery("select c from Chamados as c where c.status='EM ANDAMENTO' and c.setorResponsavel = 'BM' order by c.id desc").getResultList();
		return listaDaConsulta;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Chamados> consultarOrdenado(String coluna, String ordem) throws SQLException {
		List<Chamados> listaDaConsulta = null;
		listaDaConsulta = manager.createQuery("select c from Chamados as c order by c."+coluna +" "+ordem).getResultList();
		return listaDaConsulta;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Chamados> consultarPorDescricaoOuTitulo(Chamados chamado) throws SQLException {
		List<Chamados> listaDaConsulta = null;
		listaDaConsulta = manager.createQuery("select c from Chamados as c where c.descricao like '%"+ 
				chamado.getDescricao() +"%' or c.titulo like '%"+ chamado.getTitulo() + "%'" ).getResultList();
		return listaDaConsulta;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Chamados> consultarPorPrazoSolucaoHojeERP(Chamados chamado) throws SQLException {
		List<Chamados> listaDaConsulta = null;
		listaDaConsulta = manager.createQuery("select c from Chamados as c where (c.prazoSolucao = '"+
				sdfFomatarData.format(chamado.getPrazoSolucao().getTime())+"' and c.status<>'FINALIZADO') and c.setorResponsavel = 'BM'").getResultList(); 
		return listaDaConsulta;
	}

	//Metodo para tela TI
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Chamados> consultarPorPrazoSolucaoHojeTI(Chamados chamado) throws SQLException {
		List<Chamados> listaDaConsulta = null;
		listaDaConsulta = manager.createQuery("select c from Chamados as c where (c.prazoSolucao = '"+
				sdfFomatarData.format(chamado.getPrazoSolucao().getTime())+"' and c.status<>'FINALIZADO') and c.setorResponsavel <> 'BM'").getResultList(); 
		return listaDaConsulta;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Chamados> consultarAbertosOuEmAndamentoTI() throws SQLException {
		List<Chamados> listaDaConsulta = null;
		listaDaConsulta = manager.createQuery("select c from Chamados as c where (c.status='ABERTO' or c.status='EM ANDAMENTO') and c.setorResponsavel <> 'BM' order by c.id desc").getResultList();
		return listaDaConsulta;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Chamados> consultarAbertosTI() throws SQLException {
		List<Chamados> listaDaConsulta = null;
		listaDaConsulta = manager.createQuery("select c from Chamados as c where c.status='ABERTO' and c.setorResponsavel <> 'BM' order by c.id desc").getResultList();
		return listaDaConsulta;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Chamados> consultarEmAndamentoTI() throws SQLException {
		List<Chamados> listaDaConsulta = null;
		listaDaConsulta = manager.createQuery("select c from Chamados as c where c.status='EM ANDAMENTO' and c.setorResponsavel <> 'BM' order by c.id desc").getResultList();
		return listaDaConsulta;
	}

}
