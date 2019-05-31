package br.com.sacHelp.model.percistence.interfaces;

import java.sql.SQLException;
import java.util.List;

import br.com.sacHelp.model.entity.Chamados;

public interface IRepositorioChamados {

	void adicionar(Chamados chamado) throws SQLException;
	List<Chamados> consultar() throws SQLException;
	List<Chamados> consultarOrdenado(String coluna, String ordem) throws SQLException;
	List<Chamados> consultarPorDescricaoOuTitulo(Chamados chamado) throws SQLException;
	List<Chamados> consultarPorPrazoSolucaoHoje(Chamados chamado) throws SQLException;
	List<Chamados> consultarAbertosOuEmAndamento() throws SQLException;
	List<Chamados> consultarAbertos() throws SQLException;
	List<Chamados> consultarEmAndamento() throws SQLException;
	Chamados consultarChamadoProId(int id) throws SQLException;
	void editar(Chamados chamado) throws SQLException;
}
