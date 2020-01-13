package br.com.sacHelp.controller;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.sacHelp.model.entity.Chamado;
import br.com.sacHelp.model.entity.HistoricoChamado;
import br.com.sacHelp.model.exception.CampoVazioException;
import br.com.sacHelp.model.exception.PrazoInvadoException;
import br.com.sacHelp.model.service.ChamadosService;
import br.com.sacHelp.model.service.HistoricoChamadoService;
import br.com.sacHelp.util.Mensagem;

/**
 * Classe ChamadosController. Serve para fazer o controle do acesso à regra de
 * negócio do Chamado. Contém os métodos que receberam as requisições da tela do
 * usuário. Os métodos são invocados através das anotações
 * (Annotations). @RequestMapping. Contém os tratamentos de Exceções.
 */
@Transactional
@Controller
public class ChamadosController {

	@Autowired
	ChamadosService chamadosService;

	@Autowired
	HomeController homeController;

	@Autowired
	HistoricoChamadoService historicoChamadoService;

	Mensagem msg = new Mensagem();
	SimpleDateFormat sDFormat = new SimpleDateFormat("HH:mm:ss");

	@RequestMapping("novoChamado")
	public String novoChamado() {
		return "chamado/novoChamado";
	}

	@RequestMapping("adicionarChamado")
	public String adicionaChamado(Chamado chamado) {
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

			if (HomeController.checkAbaIndex) {
				return "redirect:index";
			} else {
				return "redirect:indexTI";
			}
		} catch (NoResultException | SQLException | NullPointerException e) {
			msg.setMensagemErro("Algo deu errado. Chamado não foi salvo no Banco de Dados: " + e.getMessage()
					+ ". Erro: " + e.getClass());
			e.printStackTrace();
			return "redirect:mostraMensagemChamado";
		} catch (PrazoInvadoException e) {
			msg.setMensagemErro(e.getMessage());
			e.printStackTrace();
			return "redirect:mostraMensagemChamado";
		} catch (CampoVazioException e) {
			msg.setMensagemErro(e.getMessage());
			e.printStackTrace();
			return "redirect:mostraMensagemChamado";
		}
	}

	@RequestMapping("listarChamados")
	public String litarChamados(Model modelo) {

		try {
			List<Chamado> listaChamados = chamadosService.consultar();
			modelo.addAttribute("listaChamados", listaChamados);
			modelo.addAttribute("registros", listaChamados.size());
			return "chamado/listarChamados";
		} catch (NoResultException | SQLException | NullPointerException e) {
			msg.setMensagemErro(
					"Erro ao consultar chamados no Banco de Dados: " + e.getMessage() + ". Erro: " + e.getClass());
			e.printStackTrace();
			return "redirect:mostraMensagemChamado";
		}
	}

	@RequestMapping("abrirEditarChamado")
	public String abrirEditarChamado(int id, Model modelo) {

		try {
			Chamado chamadoDaConsulta = chamadosService.consultarChamadoPorId(id);
			modelo.addAttribute("chamado", chamadoDaConsulta);
			return "chamado/editarChamado";
		} catch (NoResultException | SQLException | NullPointerException e) {
			msg.setMensagemErro(
					"Chamado não encontrado no Banco de Dados: " + e.getMessage() + ". Erro: " + e.getClass());
			e.printStackTrace();
			return "redirect:mostraMensagemChamado";
		}
	}

	@RequestMapping("editarChamado")
	public String editarChamado(Chamado chamado) {

		try {
			Chamado chamadoAnterior = new Chamado();
			chamadoAnterior = chamadosService.consultarChamadoPorId(chamado.getId());
			historicoChamadoService.registrarAlteracao(chamadoAnterior, chamado);
			chamadosService.editar(chamado);
			if (HomeController.checkAbaIndex) {
				return "redirect:index";
			} else {
				return "redirect:indexTI";
			}
		} catch (SQLException | NullPointerException e) {
			msg.setMensagemErro(
					"Erro ao editar chamado no Banco de Dados: " + e.getMessage() + ". Erro: " + e.getClass());
			e.printStackTrace();
			return "redirect:mostraMensagemChamado";
		} catch (CampoVazioException e) {
			msg.setMensagemErro(e.getMessage());
			e.printStackTrace();
			return "redirect:mostraMensagemChamado";
		} catch (NoResultException e) {
			msg.setMensagemErro("Chamado não encontrado para a edição: " + e.getMessage() + ". Erro: " + e.getClass());
			e.printStackTrace();
			return "redirect:mostraMensagemChamado";
		}

	}

	@RequestMapping("abrirLinhaDoTempo")
	public String abrirLinhaDoTempo(int id, Model modelo) {

		try {
			Chamado chamado = chamadosService.consultarLinhaTempoChamado(id);
			modelo.addAttribute("chamado", chamado);
			Set<HistoricoChamado> listaHistoricoChamado = chamado.getHistoricosChamado();
			modelo.addAttribute("listaHistoricoChamado", listaHistoricoChamado);
			return "chamado/linhaDoTempo";

		} catch (SQLException | NullPointerException e) {
			msg.setMensagemErro(
					"Chamado não possui dados na Linha do tempo: " + e.getMessage() + ". Erro: " + e.getClass());
			e.printStackTrace();
			return "redirect:mostraMensagemChamado";
		} catch (NoResultException e) {
			msg.setMensagemErro(
					"Chamado não encontrado no banco de dados: " + e.getMessage() + ". Erro: " + e.getClass());
			e.printStackTrace();
			return "redirect:mostraMensagemChamado";
		}

	}

	@RequestMapping("finalizarChamado")
	public String finalizarChamado(Chamado chamado) {

		try {
			Chamado chamadoDaConsulta = chamadosService.consultarChamadoPorId(chamado.getId());
			chamadoDaConsulta.setSolucao(chamado.getSolucao());
			chamadoDaConsulta.setDataFechamento(new GregorianCalendar());
			chamadoDaConsulta.setHoraFechamento(sDFormat.format(new Date()));
			chamadoDaConsulta.setStatus("FINALIZADO");

			HistoricoChamado historicoChamado = new HistoricoChamado();
			historicoChamado.setChamado(chamadoDaConsulta);
			historicoChamado
					.setOcorrencia(chamadoDaConsulta.getStatus() + " - Solução: " + chamadoDaConsulta.getSolucao());
			historicoChamado.setHora(sDFormat.format(new Date()));

			chamadosService.editar(chamadoDaConsulta);
			historicoChamadoService.adicionar(historicoChamado);
			if (HomeController.checkAbaIndex) {
				return "redirect:index";
			} else {
				return "redirect:indexTI";
			}
		} catch (SQLException | NullPointerException e) {
			msg.setMensagemErro("Erro ao Finalizar: " + e.getMessage());
			e.printStackTrace();
			return "redirect:mostraMensagemChamado";
		} catch (CampoVazioException e) {
			msg.setMensagemErro(e.getMessage() + ". Erro: " + e.getClass());
			e.printStackTrace();
			return "redirect:mostraMensagemChamado";
		} catch (NoResultException e) {
			msg.setMensagemErro("Chamado não encontrado para finalizar: " + e.getMessage() + ". Erro: " + e.getClass());
			e.printStackTrace();
			return "redirect:mostraMensagemChamado";
		}

	}

	@RequestMapping("classificarLitaChamados")
	public String classificarLitaChamados(Model modelo, String coluna) {

		try {
			List<Chamado> listaChamadosOrdemDesc = chamadosService.classificarAscDesc(coluna);
			modelo.addAttribute("listaChamados", listaChamadosOrdemDesc);
			modelo.addAttribute("registros", listaChamadosOrdemDesc.size());
			return "chamado/listarChamados";
		} catch (NoResultException | SQLException | NullPointerException e) {
			msg.setMensagemErro(
					"Erro ao listar chamados Ordenados em Descrescente: " + e.getMessage() + ". Erro: " + e.getClass());
			e.printStackTrace();
			return "redirect:mostraMensagemChamado";
		}
	}

	@RequestMapping("filtrarPorDescricaoOuTitulo")
	public String filtrarPorDescricaoOuTitulo(Model modelo, Chamado chamado) {

		try {
			chamado.setTitulo(chamado.getDescricao());
			List<Chamado> listaChamados = chamadosService.consultarPorDescricaoOuTitulo(chamado);
			modelo.addAttribute("listaChamados", listaChamados);
			modelo.addAttribute("registros", listaChamados.size());
			return "chamado/listarChamados";
		} catch (NoResultException | SQLException | NullPointerException e) {
			msg.setMensagemErro("Erro ao filtrar chamados: " + e.getMessage() + ". Erro: " + e.getClass());
			e.printStackTrace();
			return "redirect:mostraMensagemChamado";
		}
	}

	@RequestMapping("atualizarPrazo")
	public String atualizarPrazo(Chamado chamadoNovoPrazo) {

		try {
			Chamado chamadoPrazoAnterior = new Chamado();
			chamadoPrazoAnterior = chamadosService.consultarChamadoPorId(chamadoNovoPrazo.getId());
			historicoChamadoService.registrarNovoPrazo(chamadoPrazoAnterior, chamadoNovoPrazo);
			chamadoPrazoAnterior.setPrazoSolucao(chamadoNovoPrazo.getPrazoSolucao());
			chamadoNovoPrazo = chamadoPrazoAnterior;
			chamadosService.editar(chamadoNovoPrazo);
			if (HomeController.checkAbaIndex) {
				return "redirect:index";
			} else {
				return "redirect:indexTI";
			}
		} catch (SQLException | NullPointerException e) {
			msg.setMensagemErro("Erro ao atualizar prazo: " + e.getMessage() + ". Erro: " + e.getClass());
			e.printStackTrace();
			return "redirect:mostraMensagemChamado";
		} catch (CampoVazioException e) {
			msg.setMensagemErro(e.getMessage());
			e.printStackTrace();
			return "redirect:mostraMensagemChamado";
		} catch (NoResultException e) {
			msg.setMensagemErro(
					"Chamado não encontrado para atualizar o prazo: " + e.getMessage() + ". Erro: " + e.getClass());
			e.printStackTrace();
			return "redirect:mostraMensagemChamado";
		}

	}

	@RequestMapping("atualizarStatus")
	public String atualizarStatus(Chamado chamadoNovoStatus) {

		try {
			Chamado chamadoStatusAnterior = new Chamado();
			chamadoStatusAnterior = chamadosService.consultarChamadoPorId(chamadoNovoStatus.getId());

			historicoChamadoService.registrarNovoStatus(chamadoStatusAnterior, chamadoNovoStatus);

			chamadoStatusAnterior.setStatus(chamadoNovoStatus.getStatus());
			chamadoNovoStatus = chamadoStatusAnterior;
			chamadosService.editar(chamadoNovoStatus);
			if (HomeController.checkAbaIndex) {
				return "redirect:index";
			} else {
				return "redirect:indexTI";
			}
		} catch (SQLException | NullPointerException e) {
			msg.setMensagemErro("Erro ao atualizar Status: " + e.getMessage());
			e.printStackTrace();
			return "redirect:mostraMensagemChamado";
		} catch (CampoVazioException e) {
			msg.setMensagemErro(e.getMessage());
			e.printStackTrace();
			return "redirect:mostraMensagemChamado";
		} catch (NoResultException e) {
			msg.setMensagemErro(
					"Chamado não encontrado para atualizar o status: " + e.getMessage() + ". Erro: " + e.getClass());
			e.printStackTrace();
			return "redirect:mostraMensagemChamado";
		}

	}

	@RequestMapping("mostraMensagemChamado")
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
