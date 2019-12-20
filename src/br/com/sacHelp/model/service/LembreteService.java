package br.com.sacHelp.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sacHelp.model.entity.Lembrete;
import br.com.sacHelp.model.percistence.interfaces.IRepositorioLembrete;

@Service
public class LembreteService {

	@Autowired
	IRepositorioLembrete repLembrete;
	
	public void adicionar(Lembrete lembrete){
		repLembrete.adicionar(lembrete);
		
	}
}
