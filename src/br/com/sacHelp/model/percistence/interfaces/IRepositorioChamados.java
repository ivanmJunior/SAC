package br.com.sacHelp.model.percistence.interfaces;

import java.sql.SQLException;
import java.util.List;

import br.com.sacHelp.model.entity.Chamados;

public interface IRepositorioChamados {

	void adicionar(Chamados chamado) throws SQLException;
	List<Chamados> consultar() throws SQLException;
	List<Chamados> consultarOrdenado(String coluna, String ordem) throws SQLException;
	List<Chamados> consultarPorDescricaoOuTitulo(Chamados chamado) throws SQLException;
	List<Chamados> consultarPorPrazoSolucaoHojeERP(Chamados chamado) throws SQLException;
	List<Chamados> consultarAbertosOuEmAndamentoERP() throws SQLException;
	List<Chamados> consultarAbertosERP() throws SQLException;
	List<Chamados> consultarEmAndamentoERP() throws SQLException;
	List<Chamados> consultarPorPrazoSolucaoHojeTI(Chamados chamado) throws SQLException;
	List<Chamados> consultarAbertosOuEmAndamentoTI() throws SQLException;
	List<Chamados> consultarAbertosTI() throws SQLException;
	List<Chamados> consultarEmAndamentoTI() throws SQLException;
	Chamados consultarChamadoProId(int id) throws SQLException;
	void editar(Chamados chamado) throws SQLException;
}
