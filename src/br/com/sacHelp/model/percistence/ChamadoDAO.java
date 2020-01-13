package br.com.sacHelp.model.percistence;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.sacHelp.model.entity.Chamado;
import br.com.sacHelp.model.percistence.interfaces.IRepositorioChamado;

/**Classe ChamadoDAO implementa a interface IRepositorioChamado.
 * É responsavel por manter a comunicação com o Banco de Dados.*/
@Repository
public class ChamadoDAO implements IRepositorioChamado {

	@PersistenceContext
	private EntityManager manager;
	
	private SimpleDateFormat sdfFomatarData = new SimpleDateFormat("yyyy-MM-dd");
	
	@Override
	public void adicionar(Chamado chamado) throws SQLException{
		manager.persist(chamado);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Chamado> consultar() throws SQLException{
		List<Chamado> listaDaConsulta = null;
		listaDaConsulta = manager.createQuery("select c from Chamado c").getResultList();
		
		return listaDaConsulta;
	}

	@Override
	public Chamado consultarChamadoProId(int id) throws SQLException{
		return (Chamado) manager.createQuery("select c from Chamado c join fetch c.historicosChamado where c.id="+id).getSingleResult();
		
	}

	@Override
	public void editar(Chamado chamado) throws SQLException {
		manager.merge(chamado);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Chamado> consultarAbertosOuEmAndamentoERP() throws SQLException {
		List<Chamado> listaDaConsulta = null;
		listaDaConsulta = manager.createQuery("select c from Chamado as c where (c.status='ABERTO' or c.status='EM ANDAMENTO') and c.setorResponsavel = 'BM' order by c.id desc").getResultList();
		return listaDaConsulta;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Chamado> consultarAbertosERP() throws SQLException {
		List<Chamado> listaDaConsulta = null;
		listaDaConsulta = manager.createQuery("select c from Chamado as c where c.status='ABERTO' and c.setorResponsavel = 'BM' order by c.id desc").getResultList();
		return listaDaConsulta;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Chamado> consultarEmAndamentoERP() throws SQLException {
		List<Chamado> listaDaConsulta = null;
		listaDaConsulta = manager.createQuery("select c from Chamado as c where c.status='EM ANDAMENTO' and c.setorResponsavel = 'BM' order by c.id desc").getResultList();
		return listaDaConsulta;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Chamado> consultarOrdenado(String coluna, String ordem) throws SQLException {
		List<Chamado> listaDaConsulta = null;
		listaDaConsulta = manager.createQuery("select c from Chamado as c order by c."+coluna +" "+ordem).getResultList();
		return listaDaConsulta;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Chamado> consultarPorDescricaoOuTitulo(Chamado chamado) throws SQLException {
		List<Chamado> listaDaConsulta = null;
		listaDaConsulta = manager.createQuery("select c from Chamado as c where c.descricao like '%"+ 
				chamado.getDescricao() +"%' or c.titulo like '%"+ chamado.getTitulo() + "%'" ).getResultList();
		return listaDaConsulta;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Chamado> consultarPorPrazoSolucaoHojeERP(Chamado chamado) throws SQLException {
		List<Chamado> listaDaConsulta = null;
		listaDaConsulta = manager.createQuery("select c from Chamado as c where (c.prazoSolucao = '"+
				sdfFomatarData.format(chamado.getPrazoSolucao().getTime())+"' and c.status<>'FINALIZADO') and c.setorResponsavel = 'BM'").getResultList(); 
		return listaDaConsulta;
	}

	//Metodo para tela TI
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Chamado> consultarPorPrazoSolucaoHojeTI(Chamado chamado) throws SQLException {
		List<Chamado> listaDaConsulta = null;
		listaDaConsulta = manager.createQuery("select c from Chamado as c where (c.prazoSolucao = '"+
				sdfFomatarData.format(chamado.getPrazoSolucao().getTime())+"' and c.status<>'FINALIZADO') and c.setorResponsavel <> 'BM'").getResultList(); 
		return listaDaConsulta;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Chamado> consultarAbertosOuEmAndamentoTI() throws SQLException {
		List<Chamado> listaDaConsulta = null;
		listaDaConsulta = manager.createQuery("select c from Chamado as c where (c.status='ABERTO' or c.status='EM ANDAMENTO') and c.setorResponsavel <> 'BM' order by c.id desc").getResultList();
		return listaDaConsulta;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Chamado> consultarAbertosTI() throws SQLException {
		List<Chamado> listaDaConsulta = null;
		listaDaConsulta = manager.createQuery("select c from Chamado as c where c.status='ABERTO' and c.setorResponsavel <> 'BM' order by c.id desc").getResultList();
		return listaDaConsulta;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Chamado> consultarEmAndamentoTI() throws SQLException {
		List<Chamado> listaDaConsulta = null;
		listaDaConsulta = manager.createQuery("select c from Chamado as c where c.status='EM ANDAMENTO' and c.setorResponsavel <> 'BM' order by c.id desc").getResultList();
		return listaDaConsulta;
	}

}
