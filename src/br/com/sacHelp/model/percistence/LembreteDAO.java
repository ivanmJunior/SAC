package br.com.sacHelp.model.percistence;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.sacHelp.model.entity.Lembrete;
import br.com.sacHelp.model.percistence.interfaces.IRepositorioLembrete;

/**Classe LembreteDAO implementa a interface IRepositorioLembrete.
 * É responsavel por manter a comunicação com o Banco de Dados.*/
@Repository
public class LembreteDAO implements IRepositorioLembrete {

	@PersistenceContext
	private EntityManager manager;
	
	/**Método adicionar não tem retorno e é responsável por persistir um objeto Lembrete no Banco de Dados.*/
	public void adicionar(Lembrete lembrete) throws SQLException{
		manager.persist(lembrete);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Lembrete> consultar() throws SQLException {
		List<Lembrete> listaDaConsulta = null;
		listaDaConsulta = manager.createQuery("select l from Lembrete l").getResultList();
		return listaDaConsulta;
	}

	@Override
	public Lembrete consultarPorId(int id) throws SQLException {
		return (Lembrete) manager.createQuery("select l from Lembrete l where l.id="+id).getSingleResult();
	}

	@Override
	public void editar(Lembrete lembreteAlterado) throws SQLException {
		manager.merge(lembreteAlterado);
		System.out.println(lembreteAlterado);
		
	}

}
