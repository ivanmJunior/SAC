package br.com.sacHelp.model.entity;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="lembretes")
public class Lembrete {

	@Id
	@GeneratedValue
	private int id;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Calendar dataRegistro;
	
	private String horaRegistro;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Calendar data;
	private String hora;
	private String assunto;
	private boolean ativo;
	private int dia;
	private int mes;
	private int ano;
	private String seg;
	private String ter;
	private String qua;
	private String qui;
	private String sex;
	private String sab;
	
	@Transient
	private String dom;
	
	public Lembrete(){
		this.dataRegistro = new GregorianCalendar();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Calendar getDataRegistro() {
		return dataRegistro;
	}
	public void setDataRegistro(Calendar dataRegistro) {
		this.dataRegistro = dataRegistro;
	}
	public String getHoraRegistro() {
		return horaRegistro;
	}
	public void setHoraRegistro(String horaRegistro) {
		this.horaRegistro = horaRegistro;
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
	public String getAssunto() {
		return assunto;
	}
	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}
	public boolean isAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	public int getDia() {
		return dia;
	}
	public void setDia(int dia) {
		this.dia = dia;
	}
	public int getMes() {
		return mes;
	}
	public void setMes(int mes) {
		this.mes = mes;
	}
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	public String getSeg() {
		return seg;
	}
	public void setSeg(String seg) {
		this.seg = seg;
	}
	public String getTer() {
		return ter;
	}
	public void setTer(String ter) {
		this.ter = ter;
	}
	public String getQua() {
		return qua;
	}
	public void setQua(String qua) {
		this.qua = qua;
	}
	public String getQui() {
		return qui;
	}
	public void setQui(String qui) {
		this.qui = qui;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getSab() {
		return sab;
	}
	public void setSab(String sab) {
		this.sab = sab;
	}
	public String getDom() {
		return dom;
	}
	public void setDom(String dom) {
		this.dom = dom;
	}
	
}
