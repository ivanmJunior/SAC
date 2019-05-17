package br.com.sacHelp.controller;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.sacHelp.model.entity.Chamados;
import br.com.sacHelp.model.entity.HistoricoChamado;
import br.com.sacHelp.model.exception.CampoVazioException;
import br.com.sacHelp.model.exception.PrazoInvadoException;
import br.com.sacHelp.model.service.ChamadosService;
import br.com.sacHelp.model.service.HistoricoChamadoService;
import br.com.sacHelp.util.Mensagem;

@Transactional
@Controller
public class ChamadosController {

	@Autowired
	ChamadosService chamadosService;
	
	@Autowired
	HistoricoChamadoService historicoChamadoService;
	
	Mensagem msg = new Mensagem();
	SimpleDateFormat sDFormat = new SimpleDateFormat("HH:mm:ss");
	
	@RequestMapping("novoChamado")
	public String novoChamado(){
		return "chamado/novoChamado";
	}
	
	@RequestMapping("adicionarChamado")
	public String adicionaChamado(Chamados chamado){
		chamado.setContato(chamado.getContato().replace(",", ""));// verificar depois
		chamado.getDataAbertura().setTime(new Date());
		chamado.setHoraAbertura(sDFormat.format(new Date()));
		chamado.setSolucao("");
		
		Set<HistoricoChamado> listaPrimeiroHistorico = new HashSet<>();
		
		HistoricoChamado primeiroHistoricoChamado = new HistoricoChamado();
		
		primeiroHistoricoChamado.setChamado(chamado);
		primeiroHistoricoChamado.setOcorrencia("ABERTO");
		primeiroHistoricoChamado.setHora(sDFormat.format(new Date()));
		
		listaPrimeiroHistorico.add(primeiroHistoricoChamado);
		
		chamado.setHistoricosChamado(listaPrimeiroHistorico);
		
		try {
			chamadosService.adicionar(chamado);
			
			return "redirect:index";
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
			modelo.addAttribute("registros", listaChamados.size());
			return "chamado/listarChamados";
		} catch (SQLException e) {
			msg.setMensagemErro("Erro ao listar chamados: " + e.getMessage());
			e.printStackTrace();
			return "redirect:mostraMensagemChamado";
		}
	}
	
	@RequestMapping("selecionarChamado")
	public String selecionarChamado(int id, Model modelo){
		
		try {
			Chamados chamadoDaConsulta = chamadosService.consultarChamadoPorId(id);
			modelo.addAttribute("chamado", chamadoDaConsulta);
			return "index";
		} catch (SQLException e) {
			msg.setMensagemErro("Erro! "+e.getMessage());
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
			Chamados chamadoAnterior = new Chamados();
			chamadoAnterior = chamadosService.consultarChamadoPorId(chamado.getId());
			historicoChamadoService.pegarAlteracao(chamadoAnterior, chamado);
			chamadosService.editar(chamado);
			return "redirect:index";
		} catch (SQLException e) {
			msg.setMensagemErro(e.getMessage());
			e.printStackTrace();
			return "redirect:mostraMensagemChamado";
		}
		
	}
	
	@RequestMapping("abrirLinhaDoTempo")
	public String abrirLinhaDoTempo(int id, Model modelo){
		
		try {
			Chamados chamado = chamadosService.consultarChamadoPorId(id);
			if(!chamado.getHistoricosChamado().isEmpty()){
				modelo.addAttribute("chamado", chamado);
				Set<HistoricoChamado> listaHistoricoChamado = chamado.getHistoricosChamado();
				modelo.addAttribute("listaHistoricoChamado", listaHistoricoChamado);
				return "chamado/linhaDoTempo";
			}else{
				msg.setMensagemErro("Não existe registro na linha do tempo.");
				return "redirect:mostraMensagemChamado";
			}
		} catch (SQLException e) {
			msg.setMensagemErro("Erro ao gerar Linha do tempo: "+e.getMessage());
			return "redirect:mostraMensagemChamado";
		}
		
	}
	
	@RequestMapping("finalizarChamado")
	public String finalizarChamado(Chamados chamado){

		try {
			Chamados chamadoDaConsulta = chamadosService.consultarChamadoPorId(chamado.getId());
			chamadoDaConsulta.setSolucao(chamado.getSolucao());
			chamadoDaConsulta.setDataFechamento(chamado.getDataFechamento());
			chamadoDaConsulta.setHoraFechamento(sDFormat.format(new Date()));
			chamadoDaConsulta.setStatus("FINALIZADO");
			
			HistoricoChamado historicoChamado = new HistoricoChamado();
			historicoChamado.setChamado(chamadoDaConsulta);
			historicoChamado.setOcorrencia(chamadoDaConsulta.getStatus()+
					" - Solução: "+chamadoDaConsulta.getSolucao());
			historicoChamado.setHora(sDFormat.format(new Date()));
			
			chamadosService.editar(chamadoDaConsulta);
			historicoChamadoService.adicionar(historicoChamado);
			return "redirect:index";
		} catch (SQLException e) {
			msg.setMensagemErro("Erro ao Finalizar: "+e.getMessage());
			return "redirect:mostraMensagemChamado";
		}
		
	}
	
	@RequestMapping("classificarLitaChamados")
	public String classificarLitaChamados(Model modelo, String coluna){
		
		try {
			List<Chamados> listaChamadosOrdemDesc = chamadosService.classificarAscDesc(coluna);
			modelo.addAttribute("listaChamados", listaChamadosOrdemDesc);
			modelo.addAttribute("registros", listaChamadosOrdemDesc.size());
			return "chamado/listarChamados";
		} catch (SQLException e) {
			msg.setMensagemErro("Erro ao listar chamados Ordenados em Descrescente: " + e.getMessage());
			return "redirect:mostraMensagemChamado";
		}
	}
	
	@RequestMapping("filtrarPorDescricaoOuTitulo")
	public String filtrarPorDescricaoOuTitulo(Model modelo, Chamados chamado){
		
		try {
			chamado.setTitulo(chamado.getDescricao());
			List<Chamados> listaChamados = chamadosService.consultarPorDescricaoOuTitulo(chamado);
			modelo.addAttribute("listaChamados", listaChamados);
			modelo.addAttribute("registros", listaChamados.size());
			return "chamado/listarChamados";
		} catch (SQLException e) {
			msg.setMensagemErro("Erro ao filtrar chamados: " + e.getMessage());
			return "redirect:mostraMensagemChamado";
		}
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
