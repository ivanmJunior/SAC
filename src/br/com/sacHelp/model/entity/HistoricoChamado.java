package br.com.sacHelp.model.entity;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="historicochamado")
public class HistoricoChamado {
	
	@Id
	@GeneratedValue
	@Column(name="idhistorico")
	private int id;

	private String ocorrencia;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Calendar data;
	
	private String hora;
	
	@ManyToOne
	@JoinColumn(name="chamados_id")
	private Chamados chamado;
	
	
	public HistoricoChamado(){
		this.data = new GregorianCalendar();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOcorrencia() {
		return ocorrencia;
	}
	public void setOcorrencia(String ocorrencia) {
		this.ocorrencia = ocorrencia;
	}
	public Calendar getData() {
		return data;
	}
	public void setData(Calendar data) {
		this.data = data;
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	public Chamados getChamado() {
		return chamado;
	}
	public void setChamado(Chamados chamado) {
		this.chamado = chamado;
	}

}
