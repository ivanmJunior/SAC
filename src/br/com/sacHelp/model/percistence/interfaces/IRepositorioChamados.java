package br.com.sacHelp.model.percistence.interfaces;

import java.util.List;

import br.com.sacHelp.model.entity.Chamados;

public interface IRepositorioChamados {

	void adicionar(Chamados chamado);
	List<Chamados> consultar();
}
