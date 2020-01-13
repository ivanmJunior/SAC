package br.com.sacHelp.controller;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.sacHelp.model.entity.HistoricoChamado;
import br.com.sacHelp.model.exception.CampoVazioException;
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

	/**
	 * ID_ULTIMO_HISTORICO armazena o id do ultimo historico adicionado. Este é
	 * usado como parâmetro do método consultarHistoricoPorId(int id) da classe
	 * HistoricoChamadoService chamado no método mostrarNovoHistorico(Model modelo),
	 * este serve para mostrar o ultimo historico do chamado adicionado.
	 */
	private static int ID_ULTIMO_HISTORICO;

	@RequestMapping("adicionarHistoricoChamado")
	public String adicionarHistoricoChamado(HistoricoChamado historicoChamado) {

		historicoChamado.getData().setTime(new Date());
		historicoChamado.setHora(sDFormat.format(new Date()));

		try {
			historicoChamadoService.adicionar(historicoChamado);
			ID_ULTIMO_HISTORICO = historicoChamado.getId();
			return "redirect:mostrarNovoHistorico";
		} catch (NoResultException | SQLException | NullPointerException e) {
			msg.setMensagemErro("Erro ao Adicionar Historico. " + e.getMessage());
			e.printStackTrace();
			return "redirect:mostraMensagemHistorico";
		} catch (CampoVazioException e) {
			msg.setMensagemErro("Erro: " + e.getMessage());
			e.printStackTrace();
			return "redirect:mostraMensagemHistorico";
		}
	}

	/** MOSTRA A PÁGINA DA LINHA DO TEMPO COM O NOVO HISTÓRICO ADICIONADO */
	@RequestMapping("mostrarNovoHistorico")
	public String mostrarNovoHistorico(Model modelo) {

		try {
			HistoricoChamado historicoDaConsulta = historicoChamadoService.consultarHistoricoPorId(ID_ULTIMO_HISTORICO);
			return "redirect:abrirLinhaDoTempo?id=" + historicoDaConsulta.getChamado().getId();
		} catch (NoResultException | SQLException | NullPointerException e) {
			msg.setMensagemErro("Erro ao listar chamados: " + e.getMessage());
			e.printStackTrace();
			return "redirect:mostraMensagemChamado";
		}
	}

	@RequestMapping("abrirEditarHistoricoChamado")
	public String abrirEditarHistoricoChamado(int id, Model modelo) {

		try {
			HistoricoChamado historicochamadoDaConsulta = historicoChamadoService.consultarHistoricoPorId(id);
			modelo.addAttribute("historicoChamado", historicochamadoDaConsulta);
			return "chamado/editarHistoricoChamado";
		} catch (SQLException | NullPointerException e) {
			msg.setMensagemErro(
					"Erro ao abrir editar historico do chamado: " + e.getMessage() + ". Erro: " + e.getClass());
			e.printStackTrace();
			return "redirect:mostraMensagemHistorico";
		} catch (NoResultException e) {
			msg.setMensagemErro(
					"Historico não emcontrado no Banco de Dados: " + e.getMessage() + ". Erro: " + e.getClass());
			e.printStackTrace();
			return "redirect:mostraMensagemHistorico";
		}
	}

	@RequestMapping("editarHistorico")
	public String editarHistoricoChamado(HistoricoChamado historicoChamado) {

		try {
			historicoChamadoService.editar(historicoChamado);
			return "redirect:abrirLinhaDoTempo?id=" + historicoChamado.getChamado().getId();
		} catch (NoResultException | SQLException | NullPointerException e) {
			msg.setMensagemErro("Erro ao editar historico do chamado: " + e.getMessage() + ". Erro: " + e.getClass());
			e.printStackTrace();
			return "redirect:mostraMensagemHistorico";
		}

	}

	@RequestMapping("mostraMensagemHistorico")
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
