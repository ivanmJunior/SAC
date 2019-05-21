package br.com.sacHelp.controller;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.sacHelp.model.entity.HistoricoChamado;
import br.com.sacHelp.model.service.ChamadosService;
import br.com.sacHelp.model.service.HistoricoChamadoService;
import br.com.sacHelp.util.Mensagem;

@Transactional
@Controller
public class HistoricoChamadoController {

	@Autowired
	HistoricoChamadoService historicoChamadoService;
	
	@Autowired
	ChamadosService chamadosService;
	
	Mensagem msg = new Mensagem();
	SimpleDateFormat sDFormat = new SimpleDateFormat("HH:mm:ss");
	
	@RequestMapping("adicionarHistoricoChamado")
	public String adicionarHistoricoChamado(HistoricoChamado historicoChamado){
		
		historicoChamado.getData().setTime(new Date());
		historicoChamado.setHora(sDFormat.format(new Date()));
		
		try {
			historicoChamadoService.adicionar(historicoChamado);
			return "redirect:index";
		} catch (SQLException e) {
			msg.setMensagemErro("Erro ao Adicionar Historico. "+e.getMessage());
			return "redirect:mostraMensagemHistorico";
		}
	}
	
	/*
	@RequestMapping("listarChamados")
	public String litarChamados(Model modelo){
		
		try {
			List<Chamados> listaChamados = chamadosService.consultar();
			modelo.addAttribute("listaChamados", listaChamados);
			return "chamado/listarChamados";
		} catch (SQLException e) {
			msg.setMensagemErro("Erro ao listar chamados: " + e.getMessage());
			e.printStackTrace();
			return "redirect:mostraMensagemChamado";
		}
	}*/
	
	@RequestMapping("abrirEditarHistoricoChamado")
	public String abrirEditarHistoricoChamado(int id, Model modelo){
		
		try {
			HistoricoChamado historicochamadoDaConsulta = historicoChamadoService.consultarHistoricoPorId(id);
			modelo.addAttribute("historicoChamado", historicochamadoDaConsulta);
			return "chamado/editarHistoricoChamado";
		} catch (SQLException e) {
			msg.setMensagemErro("Erro! "+e.getMessage());
			return "redirect:mostraMensagemHistorico";
		}
	}
	
	@RequestMapping("editarHistorico")
	public String editarHistoricoChamado(HistoricoChamado historicoChamado){
		
		try {
			historicoChamadoService.editar(historicoChamado);
			return "redirect:abrirLinhaDoTempo?id="+historicoChamado.getChamado().getId();
		} catch (SQLException e) {
			msg.setMensagemErro(e.getMessage());
			return "redirect:mostraMensagemHistorico";
		}
		
	}

	
	@RequestMapping("mostraMensagemHistorico")
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
