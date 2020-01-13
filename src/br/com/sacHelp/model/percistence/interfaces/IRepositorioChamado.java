package br.com.sacHelp.model.percistence.interfaces;

import java.sql.SQLException;
import java.util.List;

import br.com.sacHelp.model.entity.Chamado;

public interface IRepositorioChamado {

	void adicionar(Chamado chamado) throws SQLException;
	List<Chamado> consultar() throws SQLException;
	List<Chamado> consultarOrdenado(String coluna, String ordem) throws SQLException;
	List<Chamado> consultarPorDescricaoOuTitulo(Chamado chamado) throws SQLException;
	List<Chamado> consultarPorPrazoSolucaoHojeERP(Chamado chamado) throws SQLException;
	List<Chamado> consultarAbertosOuEmAndamentoERP() throws SQLException;
	List<Chamado> consultarAbertosERP() throws SQLException;
	List<Chamado> consultarEmAndamentoERP() throws SQLException;
	List<Chamado> consultarPorPrazoSolucaoHojeTI(Chamado chamado) throws SQLException;
	List<Chamado> consultarAbertosOuEmAndamentoTI() throws SQLException;
	List<Chamado> consultarAbertosTI() throws SQLException;
	List<Chamado> consultarEmAndamentoTI() throws SQLException;
	Chamado consultarChamadoProId(int id) throws SQLException;
	void editar(Chamado chamado) throws SQLException;
}
