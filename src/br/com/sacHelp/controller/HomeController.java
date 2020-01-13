package br.com.sacHelp.controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Timer;

import javax.persistence.NoResultException;

import org.hibernate.QueryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.sacHelp.model.entity.Chamado;
import br.com.sacHelp.model.service.ChamadosService;
import br.com.sacHelp.util.ContaPendentes;
import br.com.sacHelp.util.Mensagem;

/**
 * Classe HomeController. Tem com finalidade principal manter os métodos que
 * contrlam as atualizações dos dados da tabela de chamados pendentes que fica
 * na Index. Serve para fazer o controle do acesso à regra de negócio referentes
 * a dados exibidos na Index do Sistema. Contém os métodos que receberam as
 * requisições da tela do usuário e faram atualizações na Index. Os métodos são
 * invocados através das anotações (Annotations). @RequestMapping. Contém os
 * tratamentos de Exceções.
 */
@Controller
public class HomeController {

	@Autowired
	ChamadosService chamadosService;

	Mensagem msg = new Mensagem();
	public static boolean checkAbaIndex;
	private static ContaPendentes contaPendentes;
	private static int diasCertificadoDigital;
	// private final long SEGUNDOS = (5000);
	Timer timer = new Timer();

	@RequestMapping("index")
	public String openHome(Model modelo) {
		try {
			checkAbaIndex = true;
			List<Chamado> listaChamados = chamadosService.consultarAbertosOuEmAndamento();
			contaPendentes = chamadosService.contarChamadosPendentes(listaChamados);
			diasCertificadoDigital = calcularValidadeCertificado();
			modelo.addAttribute("listaChamados", listaChamados);
			modelo.addAttribute("contaPendentes", contaPendentes);
			modelo.addAttribute("certificadoValidade", diasCertificadoDigital);
			modelo.addAttribute("checkAbaIndex", checkAbaIndex);
			// Processos processo = new Processos();
			// timer.schedule(processo, 0, SEGUNDOS);
			return "index";
		} catch (QueryException | IllegalArgumentException | SQLException
				| NullPointerException e) {
			msg.setMensagemErro("Erro ao carregar o index: " + e.getMessage()+ ". Erro: "+ e.getClass());
			e.printStackTrace();
			return "redirect:mostraMensagemHome";
		}catch(NoResultException e) {
			msg.setMensagemErro("Ao consultar chamados ERP no Banco: " + e.getMessage()+ ". Erro: "+ e.getClass());
			e.printStackTrace();
			return "redirect:mostraMensagemHome";
		}
	}

	@RequestMapping("indexAbertos")
	public String openHomeAbertos(Model modelo) {
		try {
			checkAbaIndex = true;
			List<Chamado> listaChamados = chamadosService.consultarAbertos();
			modelo.addAttribute("listaChamados", listaChamados);
			modelo.addAttribute("contaPendentes", contaPendentes);
			modelo.addAttribute("certificadoValidade", diasCertificadoDigital);
			modelo.addAttribute("checkAbaIndex", checkAbaIndex);
			return "index";
		} catch (NoResultException | SQLException | NullPointerException e) {
			msg.setMensagemErro("Erro ao listar chamados Abertos: " + e.getMessage()+ ". Erro: "+ e.getClass());
			e.printStackTrace();
			return "redirect:mostraMensagemHome";
		}
	}

	@RequestMapping("indexEmAndamento")
	public String openHomeEmAndamento(Model modelo) {
		try {
			checkAbaIndex = true;
			List<Chamado> listaChamados = chamadosService.consultarEmAndamento();
			modelo.addAttribute("listaChamados", listaChamados);
			modelo.addAttribute("contaPendentes", contaPendentes);
			modelo.addAttribute("certificadoValidade", diasCertificadoDigital);
			modelo.addAttribute("checkAbaIndex", checkAbaIndex);
			return "index";
		} catch (NoResultException | SQLException | NullPointerException e) {
			msg.setMensagemErro("Erro ao listar chamados Em Andamento: " + e.getMessage()+ ". Erro: "+ e.getClass());
			e.printStackTrace();
			return "redirect:mostraMensagemHome";
		}
	}

	@RequestMapping("indexAtrasados")
	public String openHomeAtrasados(Model modelo) {
		try {
			checkAbaIndex = true;
			List<Chamado> listaChamados = chamadosService.consultarAtrasados();
			modelo.addAttribute("listaChamados", listaChamados);
			modelo.addAttribute("contaPendentes", contaPendentes);
			modelo.addAttribute("certificadoValidade", diasCertificadoDigital);
			modelo.addAttribute("checkAbaIndex", checkAbaIndex);
			return "index";
		} catch (NoResultException | SQLException | NullPointerException e) {
			msg.setMensagemErro("Erro ao listar chamados Atrasados: " + e.getMessage()+ ". Erro: "+ e.getClass());
			e.printStackTrace();
			return "redirect:mostraMensagemHome";
		}
	}

	@RequestMapping("indexPraHoje")
	public String openHomePrazoSolucaoPraHoje(Model modelo) {
		try {
			checkAbaIndex = true;
			Chamado chamado = new Chamado();
			chamado.setPrazoSolucao(new GregorianCalendar());
			List<Chamado> listaChamados = chamadosService.consultarPorPrazoSolucaoHoje(chamado);
			modelo.addAttribute("listaChamados", listaChamados);
			modelo.addAttribute("contaPendentes", contaPendentes);
			modelo.addAttribute("certificadoValidade", diasCertificadoDigital);
			modelo.addAttribute("checkAbaIndex", checkAbaIndex);
			return "index";
		} catch (NoResultException | SQLException | NullPointerException e) {
			msg.setMensagemErro("Erro ao listar chamados de Hoje: " + e.getMessage()+ ". Erro: "+ e.getClass());
			e.printStackTrace();
			return "redirect:mostraMensagemHome";
		}
	}

	// ----------------------------------------------------------------------------------

	@RequestMapping("indexTI")
	public String openHomeTI(Model modelo) {
		try {
			checkAbaIndex = false;
			List<Chamado> listaChamados = chamadosService.consultarAbertosOuEmAndamentoTI();
			contaPendentes = chamadosService.contarChamadosPendentes(listaChamados);
			diasCertificadoDigital = calcularValidadeCertificado();
			modelo.addAttribute("listaChamados", listaChamados);
			modelo.addAttribute("contaPendentes", contaPendentes);
			modelo.addAttribute("certificadoValidade", diasCertificadoDigital);
			modelo.addAttribute("checkAbaIndex", checkAbaIndex);
			return "index";
		} catch (NoResultException | QueryException | IllegalArgumentException | SQLException
				| NullPointerException e) {
			msg.setMensagemErro("Erro ao listar chamados TI no Banco: " + e.getMessage()+ ". Erro: "+ e.getClass());
			e.printStackTrace();
			return "redirect:mostraMensagemHome";
		}
	}

	@RequestMapping("indexAbertosTI")
	public String openHomeAbertosTI(Model modelo) {
		try {
			checkAbaIndex = false;
			List<Chamado> listaChamados = chamadosService.consultarAbertosTI();
			modelo.addAttribute("listaChamados", listaChamados);
			modelo.addAttribute("contaPendentes", contaPendentes);
			modelo.addAttribute("certificadoValidade", diasCertificadoDigital);
			modelo.addAttribute("checkAbaIndex", checkAbaIndex);
			return "index";
		} catch (NoResultException | SQLException | NullPointerException e) {
			msg.setMensagemErro("Erro ao listar chamados TI Abertos: " + e.getMessage()+ ". Erro: "+ e.getClass());
			e.printStackTrace();
			return "redirect:mostraMensagemHome";
		}
	}

	@RequestMapping("indexEmAndamentoTI")
	public String openHomeEmAndamentoTI(Model modelo) {
		try {
			checkAbaIndex = false;
			List<Chamado> listaChamados = chamadosService.consultarEmAndamentoTI();
			modelo.addAttribute("listaChamados", listaChamados);
			modelo.addAttribute("contaPendentes", contaPendentes);
			modelo.addAttribute("certificadoValidade", diasCertificadoDigital);
			modelo.addAttribute("checkAbaIndex", checkAbaIndex);
			return "index";
		} catch (NoResultException | SQLException | NullPointerException e) {
			msg.setMensagemErro("Erro ao listar chamados TI Em Andamento: " + e.getMessage()+ ". Erro: "+ e.getClass());
			e.printStackTrace();
			return "redirect:mostraMensagemHome";
		}
	}

	@RequestMapping("indexAtrasadosTI")
	public String openHomeAtrasadosTI(Model modelo) {
		try {
			checkAbaIndex = false;
			List<Chamado> listaChamados = chamadosService.consultarAtrasadosTI();
			modelo.addAttribute("listaChamados", listaChamados);
			modelo.addAttribute("contaPendentes", contaPendentes);
			modelo.addAttribute("certificadoValidade", diasCertificadoDigital);
			modelo.addAttribute("checkAbaIndex", checkAbaIndex);
			return "index";
		} catch (NoResultException | SQLException | NullPointerException e) {
			msg.setMensagemErro("Erro ao listar chamados TI Atrasados: " + e.getMessage()+ ". Erro: "+ e.getClass());
			e.printStackTrace();
			return "redirect:mostraMensagemHome";
		}
	}

	@RequestMapping("indexPraHojeTI")
	public String openHomePrazoSolucaoPraHojeTI(Model modelo) {
		try {
			checkAbaIndex = false;
			Chamado chamado = new Chamado();
			chamado.setPrazoSolucao(new GregorianCalendar());
			List<Chamado> listaChamados = chamadosService.consultarPorPrazoSolucaoHojeTI(chamado);
			modelo.addAttribute("listaChamados", listaChamados);
			modelo.addAttribute("contaPendentes", contaPendentes);
			modelo.addAttribute("certificadoValidade", diasCertificadoDigital);
			modelo.addAttribute("checkAbaIndex", checkAbaIndex);
			// timer.cancel();
			return "index";
		} catch (NoResultException | SQLException | NullPointerException e) {
			msg.setMensagemErro("Erro ao listar chamados TI de Hoje: " + e.getMessage()+ ". Erro: "+ e.getClass());
			e.printStackTrace();
			return "redirect:mostraMensagemHome";
		}
	}

	@RequestMapping("mostraMensagemHome")
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

	// ----------------------------------------------------------------------------------

	// CALCULA QUANTOS DIAS RESTA PARA O VENCIMENTO DO CERTIFICADO DIGITAL DA
	// PORTOLIVRE COM BASE NA DATA INFORMADA.
	public int calcularValidadeCertificado() {
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
			return (ano2 - ano1) * 365 + (dias);
		} catch (ParseException e) {
			return 99999;
		}

	}
}
