package br.com.sacHelp.model.entity;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="chamados")
public class Chamados {

	@Id
	@GeneratedValue
	private int id;
	private String descricao;
	private Long protocolo;
	private String tipoAtendimento;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Calendar dataAbertura;
	
	private String horaAbertura;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Calendar dataFechamento;
	
	@OneToMany(mappedBy="chamado", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@OrderBy("id desc")
	private Set<HistoricoChamado> historicosChamado;
	
	private String horaFechamento;
	private String titulo;
	private String Solucao;
	private String loja; // Loja futuramente será objeto Cliente.
	private String tecResponsavel; // tecResponsavel futuramente será objeto Tecnico.
	private String setorResponsavel; // setorResponsavel futuramente será objeto setor.
	private String status;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Calendar prazoSolucao;
	private String diferencaTempoDeEntrega; // vereificar como serar a resposta.
	private String contato; // identificar quem criou o chamado.
	private String observacao;
	
	public Chamados(){
		this.dataAbertura = new GregorianCalendar();
		//this.dataFechamento = new GregorianCalendar();
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Calendar getDataAbertura() {
		return dataAbertura;
	}
	public void setDataAbertura(Calendar dataAbertura) {
		this.dataAbertura = dataAbertura;
	}
	public String getHoraAbertura() {
		return horaAbertura;
	}
	public void setHoraAbertura(String horaAbertura) {
		this.horaAbertura = horaAbertura;
	}
	public Calendar getDataFechamento() {
		return dataFechamento;
	}
	public void setDataFechamento(Calendar dataFechamento) {
		this.dataFechamento = dataFechamento;
	}
	public String getHoraFechamento() {
		return horaFechamento;
	}
	public void setHoraFechamento(String horaFechamento) {
		this.horaFechamento = horaFechamento;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public String getTipoAtendimento() {
		return tipoAtendimento;
	}


	public void setTipoAtendimento(String tipoAtendimento) {
		this.tipoAtendimento = tipoAtendimento;
	}


	public String getSolucao() {
		return Solucao;
	}
	public Set<HistoricoChamado> getHistoricosChamado() {
		return historicosChamado;
	}


	public void setHistoricosChamado(Set<HistoricoChamado> historicosChamado) {
		this.historicosChamado = historicosChamado;
	}


	public Long getProtocolo() {
		return protocolo;
	}
	public void setProtocolo(Long protocolo) {
		this.protocolo = protocolo;
	}
	public void setSolucao(String solucao) {
		Solucao = solucao;
	}
	public String getLoja() {
		return loja;
	}
	public void setLoja(String loja) {
		this.loja = loja;
	}
	public String getTecResponsavel() {
		return tecResponsavel;
	}
	public void setTecResponsavel(String tecResponsavel) {
		this.tecResponsavel = tecResponsavel;
	}
	public String getSetorResponsavel() {
		return setorResponsavel;
	}
	public void setSetorResponsavel(String setorResponsavel) {
		this.setorResponsavel = setorResponsavel;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Calendar getPrazoSolucao() {
		return prazoSolucao;
	}
	public void setPrazoSolucao(Calendar prazoSolucao) {
		this.prazoSolucao = prazoSolucao;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public String getDiferencaTempoDeEntrega() {
		return diferencaTempoDeEntrega;
	}
	public void setDiferencaTempoDeEntrega(String diferencaTempoDeEntrega) {
		this.diferencaTempoDeEntrega = diferencaTempoDeEntrega;
	}
	public String getContato() {
		return contato;
	}
	public void setContato(String contato) {
		this.contato = contato;
	}
}
