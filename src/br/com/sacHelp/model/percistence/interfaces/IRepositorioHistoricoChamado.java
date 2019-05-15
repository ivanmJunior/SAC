package br.com.sacHelp.model.percistence.interfaces;

import java.sql.SQLException;
import java.util.List;

import br.com.sacHelp.model.entity.HistoricoChamado;

public interface IRepositorioHistoricoChamado {

	void adicionar(HistoricoChamado historicoChamado) throws SQLException;
	List<HistoricoChamado> consultar() throws SQLException;
	List<HistoricoChamado> consultar(int idChamado) throws SQLException;
	HistoricoChamado consultarHistoricoProId(int id) throws SQLException;
	void editar(HistoricoChamado historicoChamado) throws SQLException;
}
