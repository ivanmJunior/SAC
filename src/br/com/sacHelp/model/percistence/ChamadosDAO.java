package br.com.sacHelp.model.percistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.sacHelp.model.entity.Chamados;
import br.com.sacHelp.model.percistence.interfaces.IRepositorioChamados;

@Repository
public class ChamadosDAO implements IRepositorioChamados {

	@PersistenceContext
	private EntityManager manager;
	
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

}
