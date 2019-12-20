package br.com.sacHelp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import br.com.sacHelp.model.entity.Lembrete;
import br.com.sacHelp.model.service.LembreteService;

@Transactional
@Controller
public class LembreteController {

	@Autowired
	LembreteService lembreteService;
	
	public String adicionarLembrete(Lembrete lembrete){
		
		lembreteService.adicionar(lembrete);
		
		return "Lembretes";
	}
}
