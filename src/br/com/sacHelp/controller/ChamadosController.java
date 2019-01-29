package br.com.sacHelp.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.sacHelp.model.entity.Chamados;
import br.com.sacHelp.model.exception.PrazoInvadoException;
import br.com.sacHelp.model.service.ChamadosService;
import br.com.sacHelp.util.Mensagem;

@Transactional
@Controller
public class ChamadosController {

	@Autowired
	ChamadosService chamadosService;
	
	Mensagem msg = new Mensagem();
	
	@RequestMapping("novoChamado")
	public String novoChamado(){
		return "chamado/novoChamado";
	}
	
	@RequestMapping("adicionarChamado")
	public String adicionaChamado(Chamados chamado){
		SimpleDateFormat sDFormat = new SimpleDateFormat("hh:mm:ss");
		
		chamado.getDataAbertura().setTime(new Date());
		chamado.setHoraAbertura(sDFormat.format(new Date()));
		try {
			chamadosService.adicionar(chamado);
			return "chamado/novoChamado";
		} catch (PrazoInvadoException e) {
			msg.setMensagemErro(e.getMessage());
			e.printStackTrace();
			return "redirect:mostraMensagemChamado";
		}
		
	}
	
	@RequestMapping("listarChamados")
	public String litarChamados(Model modelo){
		
		List<Chamados> listaChamados = chamadosService.consultar();
		modelo.addAttribute("listaChamados", listaChamados);
		return "chamado/listarChamados";
	}
	
	@RequestMapping("mostraMensagemChamado")
	public ModelAndView execMensagens(){
		String paginaMensagem = "";
		
		if(!msg.getMensagemSucesso().equals("")){
			paginaMensagem = "/mensagemSucesso";
		}else{
			paginaMensagem = "/mensagemErro";
		}
		
		ModelAndView modelo = new ModelAndView(paginaMensagem);
		modelo.addObject("msg", msg);
		return modelo;
		
	}
}
