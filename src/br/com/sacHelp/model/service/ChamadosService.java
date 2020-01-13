package br.com.sacHelp.model.service;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sacHelp.model.entity.Chamado;
import br.com.sacHelp.model.exception.CampoVazioException;
import br.com.sacHelp.model.exception.PrazoInvadoException;
import br.com.sacHelp.model.percistence.interfaces.IRepositorioChamado;
import br.com.sacHelp.util.ContaPendentes;

/**ChamadoService � uma classe respons�vel por valida��es e tratamentos
 * de regras do chamado.*/
@Service
public class ChamadosService {

	/**ORDEM � um atributo usado especificamente no m�todo classificarAscDesc().
	 * Este � passado como par�metro no m�toodo consultarOrdenado(coluna, ORDEM).*/
	private static String ORDEM = "asc";
	
	/**COLUNA � um atributo usado especificamente no m�todo classificarAscDesc().
	 * Este � passado como par�metro no m�toodo consultarOrdenado(coluna, ORDEM) 
	 * e armazena a descri��o da coluna anterior da ultima classifica��o.*/
	private static String COLUNA_ANTERIOR = "";
	
	/**Este atributo � usado em quase todos os m�todos apenas nessa classe. 
	 * Serve para armazenar temporariamnete uma lista consutada no atrv�s do reposit�rio*/
	private List<Chamado> listaDaConsulta;
	
	@Autowired
	IRepositorioChamado repChamadosDAO;
	
	/**O objeto sDFormat � usado sempre que se faz necess�rio formatar uma data.*/
	SimpleDateFormat sDFormat = new SimpleDateFormat("dd/MM/yyyy");
	
	/**Faz a valida��o do chamado e chama o reposit�rio do chamado para realizar a persist�ncia*/
	public void adicionar(Chamado chamado) throws PrazoInvadoException, CampoVazioException, SQLException{
		if(chamado.getDescricao().equals("")||
				chamado.getLoja().equals("")||
				chamado.getContato().equals("")){
			throw new CampoVazioException("Aten��o: Os Campos 'Descri��o, Loja e Contato s�o obrigat�rios!");
		}
		if(sDFormat.format(chamado.getPrazoSolucao().getTime()).equals(sDFormat.format(chamado.getDataAbertura().getTime()))){
			repChamadosDAO.adicionar(chamado);
		}else if(chamado.getPrazoSolucao().before(chamado.getDataAbertura())){
			throw new PrazoInvadoException("Data prazo n�o pode ser ANTERIOR � data de Abertura!");
		}else{
			repChamadosDAO.adicionar(chamado);
		}
	}
	
	public List<Chamado> consultar() throws SQLException{
		return repChamadosDAO.consultar();
	}

	public Chamado consultarChamadoPorId(int id) throws SQLException {
		return repChamadosDAO.consultarChamadoProId(id);
	}
	
	public Chamado consultarLinhaTempoChamado(int id) throws SQLException{
		Chamado chamadoDaConsulta = consultarChamadoPorId(id);
		if(chamadoDaConsulta.getHistoricosChamado().isEmpty()) {
			return null;
		}else {
			return chamadoDaConsulta;
		}
		
	}
	
	/**O m�todo classificarAscDesc(String coluna), serve para ordenar uma lista de chamados 
	 * em crescente ou decrescente a partir de uma atributo (coluna) do chamado e retorna 
	 * a lista classificada*/
	public List<Chamado> classificarAscDesc(String coluna) throws SQLException {
		
		if(ORDEM.equals("asc") && COLUNA_ANTERIOR.equals(coluna)){
			ORDEM = "desc";
			listaDaConsulta = repChamadosDAO.consultarOrdenado(coluna, ORDEM);
		}else{
			ORDEM = "asc";
			COLUNA_ANTERIOR = coluna;
			listaDaConsulta = repChamadosDAO.consultarOrdenado(coluna, ORDEM);
		}
		
		return listaDaConsulta;
		
	}
	
	public List<Chamado> consultarPorDescricaoOuTitulo(Chamado chamado) throws SQLException{
		return repChamadosDAO.consultarPorDescricaoOuTitulo(chamado);
	}
	
	public List<Chamado> consultarPorPrazoSolucaoHoje(Chamado chamado) throws SQLException {
		return adicionarDiasParaPrazoNoChamado(repChamadosDAO.consultarPorPrazoSolucaoHojeERP(chamado));
	}
	
	public List<Chamado> consultarAbertosOuEmAndamento() throws SQLException{
		return repChamadosDAO.consultarAbertosOuEmAndamentoERP();
	}
	
	public List<Chamado> consultarAbertos() throws SQLException{
		return adicionarDiasParaPrazoNoChamado(repChamadosDAO.consultarAbertosERP());
	}
	
	public List<Chamado> consultarEmAndamento() throws SQLException{
		return adicionarDiasParaPrazoNoChamado(repChamadosDAO.consultarEmAndamentoERP());
	}
	
	/**CONSULTA CHAMADOS PENDENTES E FILTRA OS ATRAZADOS*/
	public List<Chamado> consultarAtrasados() throws SQLException{
		return filtrarAtrasados(repChamadosDAO.consultarAbertosOuEmAndamentoERP());
	}
	
	//----------------------------------------------------//
	
	public List<Chamado> consultarPorPrazoSolucaoHojeTI(Chamado chamado) throws SQLException {
		return adicionarDiasParaPrazoNoChamado(repChamadosDAO.consultarPorPrazoSolucaoHojeTI(chamado));
	}
	
	public List<Chamado> consultarAbertosOuEmAndamentoTI() throws SQLException{
		return repChamadosDAO.consultarAbertosOuEmAndamentoTI();
	}
	
	public List<Chamado> consultarAbertosTI() throws SQLException{
		return adicionarDiasParaPrazoNoChamado(repChamadosDAO.consultarAbertosTI());
	}
	
	public List<Chamado> consultarEmAndamentoTI() throws SQLException{
		return adicionarDiasParaPrazoNoChamado(repChamadosDAO.consultarEmAndamentoTI());
	}
	
	public List<Chamado> consultarAtrasadosTI() throws SQLException{
		return filtrarAtrasados(repChamadosDAO.consultarAbertosOuEmAndamentoTI());
	}
	
	//----------------------------------------------------//
	
	public void editar(Chamado chamado) throws SQLException{
		repChamadosDAO.editar(chamado);
	}
	
	/**Esse m�todo serve para contar chamados a quantidade de chamados:
	 * Pendentes,
	 * Em Andamento,
	 * Abertos,
	 * Para o Dia
	 * e retorna um objeto ContaPendentes.*/
	public ContaPendentes contarChamadosPendentes(List<Chamado> listaChamadosPendentes){
		ContaPendentes contaPendentes = new ContaPendentes();
		Calendar dataAtual = new GregorianCalendar();
		int dias = 0;
		
		for(Chamado chamadoDaConsulta : listaChamadosPendentes){
			if(chamadoDaConsulta.getStatus().equals("ABERTO")){
				contaPendentes.setQtdAbertos(contaPendentes.getQtdAbertos()+1);
			}else if(chamadoDaConsulta.getStatus().equals("EM ANDAMENTO")){
				contaPendentes.setQtdEmAndamento((contaPendentes.getQtdEmAndamento()+1));
			}
			
			if(chamadoDaConsulta.getPrazoSolucao().before(dataAtual) &&
					!sDFormat.format(chamadoDaConsulta.getPrazoSolucao().getTime()).equals(sDFormat.format(dataAtual.getTime()))){
				contaPendentes.setQtdAtrasados(contaPendentes.getQtdAtrasados()+1);
			}
			
			if(sDFormat.format(chamadoDaConsulta.getPrazoSolucao().getTime()).equals(sDFormat.format(dataAtual.getTime()))){
				contaPendentes.setQtdPrazoPraHoje(contaPendentes.getQtdPrazoPraHoje()+1);
			}
			
			
			contaPendentes.setTotalPendentes(((contaPendentes.getQtdEmAndamento()+contaPendentes.getQtdAbertos())));
			
			dias = calcularDiasParaPrazo(chamadoDaConsulta);
			if(dias > 0){
				chamadoDaConsulta.setDiferencaTempoDeEntrega(String.valueOf(dias));
			}else{
				chamadoDaConsulta.setDiferencaTempoDeEntrega(String.valueOf(dias));
			}
		}
		return contaPendentes;
	}
	
	/**Devido ao dado "Dias para prazo" n�o ser persistido, o m�todo adicionarDiasParaPrazoNoChamado() 
	 * foi criado para adicionar essa informa��o dinamicamento para cada chamado. A informa��o por sua vez �
	 * gerada atrav�s do m�todo calcularDiasParaPrazo().*/
	public List<Chamado> adicionarDiasParaPrazoNoChamado(List<Chamado> listaChamadosPendentes){
		List<Chamado> novaLista = new LinkedList<>();
		int dias = 0;
		for(Chamado chamadoDaLista : listaChamadosPendentes){
			
			dias = calcularDiasParaPrazo(chamadoDaLista);
			if(dias > 0){
				chamadoDaLista.setDiferencaTempoDeEntrega(String.valueOf(dias));
			}else{
				chamadoDaLista.setDiferencaTempoDeEntrega(String.valueOf(dias));
			}
			
			novaLista.add(chamadoDaLista);
		}
		
		return novaLista;
		
	}
	
	/**Calcula quantos dias faltam ou passaram do prazo de acordo com 
	 * o prazo para solu��o do chamado.*/
	public int calcularDiasParaPrazo(Chamado chamado){
		
		Date dData;
		dData = chamado.getPrazoSolucao().getTime();
		Calendar dataAtual = Calendar.getInstance();
		Calendar dataValidade = Calendar.getInstance();
		dataValidade.setTime(dData);
		dataAtual.setTime(new Date());
		int ano1 = dataAtual.get(Calendar.YEAR);
		int ano2 = dataValidade.get(Calendar.YEAR);
		int dias = dataValidade.get(Calendar.DAY_OF_YEAR) - dataAtual.get(Calendar.DAY_OF_YEAR);
		return (ano2 - ano1)*365 + (dias);
		
	}
	
	/**FILTRA NUMA LISTA DE CHAMADOS APENAS OS ATRASADOS. Verificando o resultado do m�todo
	 * CalculoAtraso(). Este retorna a quantidade de dias em um valor inteiro e
	 * caso seja menor que zero adiciona o chamado na lista de atrasados.*/
	public List<Chamado> filtrarAtrasados(List<Chamado> listaChamadosPendentes){
		List<Chamado> listaAtrasados = new LinkedList<>();
		int dias = 0;
		for(Chamado chamadoDaLista : listaChamadosPendentes){
			dias = calcularDiasParaPrazo(chamadoDaLista);
			if(dias < 0){
				chamadoDaLista.setDiferencaTempoDeEntrega(String.valueOf(dias));
				listaAtrasados.add(chamadoDaLista);
			}
		}
		return listaAtrasados;
	}

	
}
