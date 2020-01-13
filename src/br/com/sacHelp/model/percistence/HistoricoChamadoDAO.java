package br.com.sacHelp.model.percistence;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.sacHelp.model.entity.HistoricoChamado;
import br.com.sacHelp.model.percistence.interfaces.IRepositorioHistoricoChamado;

@Repository
public class HistoricoChamadoDAO implements IRepositorioHistoricoChamado {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public void adicionar(HistoricoChamado historicoChamado) throws SQLException {
		manager.persist(historicoChamado);
		
	}

	@Override
	public List<HistoricoChamado> consultar() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<HistoricoChamado> consultar(int idChamado) throws SQLException {
		List<HistoricoChamado> listaDaConsulta = null;
		listaDaConsulta = manager.createQuery("select hc HistoricoChamado as hc where ="+idChamado).getResultList();
		return listaDaConsulta;
	}

	@Override
	public HistoricoChamado consultarHistoricoProId(int id) throws SQLException {
		return manager.find(HistoricoChamado.class, id);
	}

	@Override
	public void editar(HistoricoChamado historicoChamado) throws SQLException {
		manager.merge(historicoChamado);
		
	}
	

}

