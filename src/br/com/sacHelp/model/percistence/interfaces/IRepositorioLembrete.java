package br.com.sacHelp.model.percistence.interfaces;

import java.sql.SQLException;
import java.util.List;

import br.com.sacHelp.model.entity.Lembrete;

public interface IRepositorioLembrete {

	void adicionar(Lembrete lembrete) throws SQLException;
	List<Lembrete> consultar() throws SQLException;
	Lembrete consultarPorId(int id) throws SQLException;
	void editar(Lembrete lembreteAlterado) throws SQLException;
}
