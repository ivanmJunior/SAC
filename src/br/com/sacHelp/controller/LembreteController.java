package br.com.sacHelp.controller;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.sacHelp.model.entity.Lembrete;
import br.com.sacHelp.model.service.LembreteService;
import br.com.sacHelp.util.Mensagem;

/**
 * Classe LembreteController. Serve para fazer o controle do acesso � regra de
 * neg�cio do Lembrete. Cont�m os m�todos que receberam as requisi��es da tela
 * do usu�rio. Os m�todos s�o invocados atrav�s das anota��es
 * (Annotations). @RequestMapping. Cont�m os tratamentos de Exce��es.
 */
@Transactional
@Controller
public class LembreteController {

	@Autowired
	LembreteService lembreteService;
	SimpleDateFormat sdFormatHora = new SimpleDateFormat("HH:mm:ss");
	Mensagem msg = new Mensagem();
	
	@RequestMapping("adicionarLembrete")
	public String adicionarLembrete(Lembrete lembrete) {

		try {
			lembrete.getDataRegistro().setTime(new Date());
			lembrete.setHoraRegistro(sdFormatHora.format(new Date()));
			
			lembreteService.adicionar(lembrete);
			return "redirect:index";
		} catch (SQLException e) {
			msg.setMensagemErro("Erro ao adicionar novo lembrete! "+ e.getMessage() + ". Erro: "+ e.getClass());
			e.printStackTrace();
			return "redirect:mostraMensagemLembrete";
		}

	}
	
	@RequestMapping("listarLembretes")
	public String litarlembretes(Model modelo) {

		try {
			List<Lembrete> listaLembretes = lembreteService.consultar();
			modelo.addAttribute("listaLembretes", listaLembretes);
			modelo.addAttribute("registros", listaLembretes.size()); // Manda o total de registros que � exibido no rodap� da p�gina.
			return "lembrete/lembretes";
		} catch (NoResultException | SQLException | NullPointerException e) {
			msg.setMensagemErro(
					"Erro ao consultar lembretes no Banco de Dados: " + e.getMessage() + ". Erro: " + e.getClass());
			e.printStackTrace();
			return "redirect:mostraMensagemLembrete";
		}
	}
	
	@RequestMapping("abrirEditar")
	public String abrirEditaLembrete(int id, Model modelo) {
		try {
			Lembrete lembrete = lembreteService.consultarPorId(id);
			modelo.addAttribute("lembrete", lembrete);
			return "lembrete/editar";
		} catch (NoResultException | SQLException e) {
			msg.setMensagemErro(
					"Erro ao consultar lembretes no Banco de Dados: " + e.getMessage() + ". Erro: " + e.getClass());
			e.printStackTrace();
			return "redirect:mostraMensagemLembrete";
		}
		
	}
	
	@RequestMapping("editarLembrete")
	public String editarLembrete(Lembrete lembrete) {
		
		try {
			System.out.println(lembrete);
			lembreteService.editar(lembrete);
			return "redirect:listarLembretes";
		} catch (SQLException e) {
			msg.setMensagemErro("Erro ao editar lembrete! "+ e.getMessage() +". Erro: "+ e.getClass());
			e.printStackTrace();
			return "redirect:mostraMensagemLembrete";
		}
		
		
	}
	
	@RequestMapping("mostraMensagemLembrete")
	public ModelAndView execMensagens() {
		String paginaMensagem = "";

		if (!msg.getMensagemSucesso().equals("")) {
			paginaMensagem = "/mensagemSucesso";
		} else {
			paginaMensagem = "/mensagemErro";
		}

		ModelAndView modelo = new ModelAndView(paginaMensagem);
		modelo.addObject("msg", msg);
		return modelo;

	}

}
