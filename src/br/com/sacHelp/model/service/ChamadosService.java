package br.com.sacHelp.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sacHelp.model.entity.Chamados;
import br.com.sacHelp.model.exception.PrazoInvadoException;
import br.com.sacHelp.model.percistence.interfaces.IRepositorioChamados;

@Service
public class ChamadosService {

	@Autowired
	IRepositorioChamados repChamadosDAO;
	
	public void adicionar(Chamados chamado) throws PrazoInvadoException{
		if(chamado.getPrazoSolucao().before(chamado.getDataAbertura())){
			throw new PrazoInvadoException("Data prazo não pode ser ANTERIOR à data de Abertura!");
		}
		repChamadosDAO.adicionar(chamado);
	}
	
	public List<Chamados> consultar(){
		return repChamadosDAO.consultar();
	}
}
