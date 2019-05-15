package br.com.sacHelp.controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.sacHelp.model.entity.Chamados;
import br.com.sacHelp.model.service.ChamadosService;
import br.com.sacHelp.util.ContaPendentes;
import br.com.sacHelp.util.Mensagem;

@Controller
public class HomeController {

	@Autowired
	ChamadosService chamadosService;
	
	Mensagem msg = new Mensagem();
	private static ContaPendentes contaPendentes;
	
	@RequestMapping("index")
	public String openHome(Model modelo){
		try {
			List<Chamados> listaChamados = chamadosService.consultarAbertosOuEmAndamento();
			contaPendentes = chamadosService.contarChamadosPendentes(listaChamados);
			modelo.addAttribute("listaChamados", listaChamados);
			modelo.addAttribute("contaPendentes", contaPendentes);
			modelo.addAttribute("certificadoValidade", calcularValidadeCertificado());
			return "index";
		} catch (SQLException e) {
			msg.setMensagemErro("Erro ao listar chamados: " + e.getMessage());
			return "redirect:mostraMensagemChamado";
		}
	}
	
	@RequestMapping("indexAbertos")
	public String openHomeAbertos(Model modelo){
		try {
			List<Chamados> listaChamados = chamadosService.consultarAbertos();
			modelo.addAttribute("listaChamados", listaChamados);
			modelo.addAttribute("contaPendentes", contaPendentes);
			return "index";
		} catch (SQLException e) {
			msg.setMensagemErro("Erro ao listar chamados: " + e.getMessage());
			return "redirect:mostraMensagemChamado";
		}
	}
	
	@RequestMapping("indexEmAndamento")
	public String openHomeEmAndamento(Model modelo){
		try {
			List<Chamados> listaChamados = chamadosService.consultarEmAndamento();
			modelo.addAttribute("listaChamados", listaChamados);
			modelo.addAttribute("contaPendentes", contaPendentes);
			return "index";
		} catch (SQLException e) {
			msg.setMensagemErro("Erro ao listar chamados: " + e.getMessage());
			return "redirect:mostraMensagemChamado";
		}
	}
	
	@RequestMapping("indexAtrasados")
	public String openHomeAtrasados(Model modelo){
		try {
			List<Chamados> listaChamados = chamadosService.consultarAtrasados();
			modelo.addAttribute("listaChamados", listaChamados);
			modelo.addAttribute("contaPendentes", contaPendentes);
			return "index";
		} catch (SQLException e) {
			msg.setMensagemErro("Erro ao listar chamados: " + e.getMessage());
			return "redirect:mostraMensagemChamado";
		}
	}
	
	public int calcularValidadeCertificado(){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date dData;
		try {
			dData = sdf.parse("07/04/2020");
			Calendar dataAtual = Calendar.getInstance();
			Calendar dataValidade = Calendar.getInstance();
			dataValidade.setTime(dData);
			dataAtual.setTime(new Date());
			int ano1 = dataAtual.get(Calendar.YEAR);
			int ano2 = dataValidade.get(Calendar.YEAR);
			int dias = dataValidade.get(Calendar.DAY_OF_YEAR) - dataAtual.get(Calendar.DAY_OF_YEAR);
			return (ano2 - ano1)*365 + (dias);
		} catch (ParseException e) {
			
			e.printStackTrace();
			return 99999;
		}
		
	}
}