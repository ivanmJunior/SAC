package br.com.sacHelp.model.percistence;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.sacHelp.model.entity.Lembrete;
import br.com.sacHelp.model.percistence.interfaces.IRepositorioLembrete;

@Repository
public class LembreteDAO implements IRepositorioLembrete {

	@PersistenceContext
	private EntityManager manager;
	
	
	public void adicionar(Lembrete lembrete) {
		manager.persist(lembrete);

	}

}
