package br.com.sacHelp.controller;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.sacHelp.model.entity.HistoricoChamado;
import br.com.sacHelp.model.service.HistoricoChamadoService;
import br.com.sacHelp.util.Mensagem;

@Transactional
@Controller
public class HistoricoChamadoController {

	@Autowired
	HistoricoChamadoService historicoChamadoService;
	
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

	
	
	/*@RequestMapping("adicionarHistorico")
	public String adicionaChamado(Chamados chamado){
		chamado.setContato(chamado.getContato().replace(",", ""));// verificar depois
		SimpleDateFormat sDFormat = new SimpleDateFormat("HH:mm:ss");
		
		chamado.getDataAbertura().setTime(new Date());
		chamado.setHoraAbertura(sDFormat.format(new Date()));
		try {
			historicoChamadoService.adicionar(chamado);
			return "chamado/novoChamado";
		} catch (PrazoInvadoException | CampoVazioException | SQLException e) {
			msg.setMensagemErro(e.getMessage());
			return "redirect:mostraMensagemChamado";
		}
	}
	
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
	}
	
	@RequestMapping("abrirEditarChamado")
	public String abrirEditarChamado(int id, Model modelo){
		
		try {
			Chamados chamadoDaConsulta = chamadosService.consultarChamadoPorId(id);
			modelo.addAttribute("chamado", chamadoDaConsulta);
			return "chamado/editarChamado";
		} catch (SQLException e) {
			msg.setMensagemErro("Erro! "+e.getMessage());
			return "redirect:mostraMensagemChamado";
		}
	}
	
	@RequestMapping("editarChamado")
	public String editarChamado(Chamados chamado){
		
		try {
			chamadosService.editar(chamado);
			return "redirect:index";
		} catch (SQLException e) {
			msg.setMensagemErro(e.getMessage());
			e.printStackTrace();
			return "redirect:mostraMensagemChamado";
		}
		
	}*/
	
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
