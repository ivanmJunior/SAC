package br.com.sacHelp.controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
	public static boolean checkAbaIndex;
	private static ContaPendentes contaPendentes;
	private static int diasCertificadoDigital;
	
	@RequestMapping("index")
	public String openHome(Model modelo){
		try {
			checkAbaIndex = true;
			List<Chamados> listaChamados = chamadosService.consultarAbertosOuEmAndamento();
			contaPendentes = chamadosService.contarChamadosPendentes(listaChamados);
			diasCertificadoDigital = calcularValidadeCertificado();
			modelo.addAttribute("listaChamados", listaChamados);
			modelo.addAttribute("contaPendentes", contaPendentes);
			modelo.addAttribute("certificadoValidade", diasCertificadoDigital);
			modelo.addAttribute("checkAbaIndex", checkAbaIndex);
			return "index";
		} catch (SQLException e) {
			msg.setMensagemErro("Erro ao listar chamados: " + e.getMessage());
			return "redirect:mostraMensagemChamado";
		}
	}
	
	@RequestMapping("indexAbertos")
	public String openHomeAbertos(Model modelo){
		try {
			checkAbaIndex = true;
			List<Chamados> listaChamados = chamadosService.consultarAbertos();
			modelo.addAttribute("listaChamados", listaChamados);
			modelo.addAttribute("contaPendentes", contaPendentes);
			modelo.addAttribute("certificadoValidade", diasCertificadoDigital);
			modelo.addAttribute("checkAbaIndex", checkAbaIndex);
			return "index";
		} catch (SQLException e) {
			msg.setMensagemErro("Erro ao listar chamados: " + e.getMessage());
			return "redirect:mostraMensagemChamado";
		}
	}
	
	@RequestMapping("indexEmAndamento")
	public String openHomeEmAndamento(Model modelo){
		try {
			checkAbaIndex = true;
			List<Chamados> listaChamados = chamadosService.consultarEmAndamento();
			modelo.addAttribute("listaChamados", listaChamados);
			modelo.addAttribute("contaPendentes", contaPendentes);
			modelo.addAttribute("certificadoValidade", diasCertificadoDigital);
			modelo.addAttribute("checkAbaIndex", checkAbaIndex);
			return "index";
		} catch (SQLException e) {
			msg.setMensagemErro("Erro ao listar chamados: " + e.getMessage());
			return "redirect:mostraMensagemChamado";
		}
	}
	
	
	@RequestMapping("indexAtrasados")
	public String openHomeAtrasados(Model modelo){
		try {
			checkAbaIndex = true;
			List<Chamados> listaChamados = chamadosService.consultarAtrasados();
			modelo.addAttribute("listaChamados", listaChamados);
			modelo.addAttribute("contaPendentes", contaPendentes);
			modelo.addAttribute("certificadoValidade", diasCertificadoDigital);
			modelo.addAttribute("checkAbaIndex", checkAbaIndex);
			return "index";
		} catch (SQLException e) {
			msg.setMensagemErro("Erro ao listar chamados: " + e.getMessage());
			return "redirect:mostraMensagemChamado";
		}
	}
	
	
	@RequestMapping("indexPraHoje")
	public String openHomePrazoSolucaoPraHoje(Model modelo){
		try {
			checkAbaIndex = true;
			Chamados chamado = new Chamados();
			chamado.setPrazoSolucao(new GregorianCalendar());
			List<Chamados> listaChamados = chamadosService.consultarPorPrazoSolucaoHoje(chamado);
			modelo.addAttribute("listaChamados", listaChamados);
			modelo.addAttribute("contaPendentes", contaPendentes);
			modelo.addAttribute("certificadoValidade", diasCertificadoDigital);
			modelo.addAttribute("checkAbaIndex", checkAbaIndex);
			return "index";
		} catch (SQLException e) {
			msg.setMensagemErro("Erro ao listar chamados: " + e.getMessage());
			return "redirect:mostraMensagemChamado";
		}
	}
	
	//----------------------------------------------------------------------------------
	
	@RequestMapping("indexTI")
	public String openHomeTI(Model modelo){
		try {
			checkAbaIndex = false;
			List<Chamados> listaChamados = chamadosService.consultarAbertosOuEmAndamentoTI();
			contaPendentes = chamadosService.contarChamadosPendentes(listaChamados);
			diasCertificadoDigital = calcularValidadeCertificado();
			modelo.addAttribute("listaChamados", listaChamados);
			modelo.addAttribute("contaPendentes", contaPendentes);
			modelo.addAttribute("certificadoValidade", diasCertificadoDigital);
			modelo.addAttribute("checkAbaIndex", checkAbaIndex);
			return "index";
		} catch (SQLException e) {
			msg.setMensagemErro("Erro ao listar chamados: " + e.getMessage());
			return "redirect:mostraMensagemChamado";
		}
	}
	
	@RequestMapping("indexAbertosTI")
	public String openHomeAbertosTI(Model modelo){
		try {
			checkAbaIndex = false;
			List<Chamados> listaChamados = chamadosService.consultarAbertosTI();
			modelo.addAttribute("listaChamados", listaChamados);
			modelo.addAttribute("contaPendentes", contaPendentes);
			modelo.addAttribute("certificadoValidade", diasCertificadoDigital);
			modelo.addAttribute("checkAbaIndex", checkAbaIndex);
			return "index";
		} catch (SQLException e) {
			msg.setMensagemErro("Erro ao listar chamados: " + e.getMessage());
			return "redirect:mostraMensagemChamado";
		}
	}
	
	@RequestMapping("indexEmAndamentoTI")
	public String openHomeEmAndamentoTI(Model modelo){
		try {
			checkAbaIndex = false;
			List<Chamados> listaChamados = chamadosService.consultarEmAndamentoTI();
			modelo.addAttribute("listaChamados", listaChamados);
			modelo.addAttribute("contaPendentes", contaPendentes);
			modelo.addAttribute("certificadoValidade", diasCertificadoDigital);
			modelo.addAttribute("checkAbaIndex", checkAbaIndex);
			return "index";
		} catch (SQLException e) {
			msg.setMensagemErro("Erro ao listar chamados: " + e.getMessage());
			return "redirect:mostraMensagemChamado";
		}
	}
	
	
	@RequestMapping("indexAtrasadosTI")
	public String openHomeAtrasadosTI(Model modelo){
		try {
			checkAbaIndex = false;
			List<Chamados> listaChamados = chamadosService.consultarAtrasadosTI();
			modelo.addAttribute("listaChamados", listaChamados);
			modelo.addAttribute("contaPendentes", contaPendentes);
			modelo.addAttribute("certificadoValidade", diasCertificadoDigital);
			modelo.addAttribute("checkAbaIndex", checkAbaIndex);
			return "index";
		} catch (SQLException e) {
			msg.setMensagemErro("Erro ao listar chamados: " + e.getMessage());
			return "redirect:mostraMensagemChamado";
		}
	}
	
	
	@RequestMapping("indexPraHojeTI")
	public String openHomePrazoSolucaoPraHojeTI(Model modelo){
		try {
			checkAbaIndex = false;
			Chamados chamado = new Chamados();
			chamado.setPrazoSolucao(new GregorianCalendar());
			List<Chamados> listaChamados = chamadosService.consultarPorPrazoSolucaoHojeTI(chamado);
			modelo.addAttribute("listaChamados", listaChamados);
			modelo.addAttribute("contaPendentes", contaPendentes);
			modelo.addAttribute("certificadoValidade", diasCertificadoDigital);
			modelo.addAttribute("checkAbaIndex", checkAbaIndex);
			return "index";
		} catch (SQLException e) {
			msg.setMensagemErro("Erro ao listar chamados: " + e.getMessage());
			return "redirect:mostraMensagemChamado";
		}
	}
	
	//----------------------------------------------------------------------------------
	
	
	//CALCULA QUANTOS DIAS RESTA PARA O VENCIMENTO DO CERTIFICADO DIGITAL DA PORTOLIVRE COM BASE NA DATA INFORMADA.
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
			return 99999;
		}
		
	}
}
