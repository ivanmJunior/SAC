package br.com.sacHelp.model.entity;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

/**Classe Lembrete é a classe entidade que serve para instânciar lembretes.
 * Possui os atributos do lembrete com seus respectivos métodos de acesso Getters e Setters.
 * Possui as anotações (Annotations) que fazem todo o mapeamento para o Bonco de Dados.*/
@Entity
@Table(name="lembretes")
public class Lembrete {

	@Id
	@GeneratedValue
	@Column(name = "idlembretes")
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
	private boolean repetir;
	private char tipoRepetir;
	private boolean repetirAPartirData;
	private int dia;
	private int diaEmHoras;
	private int mes;
	private int ano;
	private boolean seg;
	private boolean ter;
	private boolean qua;
	private boolean qui;
	private boolean sex;
	private boolean sab;
	private boolean dom;
	
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
	public int getDiaEmHoras() {
		return diaEmHoras;
	}

	public void setDiaEmHoras(int diaEmHoras) {
		this.diaEmHoras = diaEmHoras;
	}

	public int getMes() {
		return mes;
	}
	public void setMes(int mes) {
		this.mes = mes;
	}
	public boolean isRepetir() {
		return repetir;
	}

	public void setRepetir(boolean repetir) {
		this.repetir = repetir;
	}

	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	

	public boolean isSeg() {
		return seg;
	}

	public void setSeg(boolean seg) {
		this.seg = seg;
	}

	public boolean isTer() {
		return ter;
	}

	public void setTer(boolean ter) {
		this.ter = ter;
	}

	public boolean isQua() {
		return qua;
	}

	public void setQua(boolean qua) {
		this.qua = qua;
	}

	public boolean isQui() {
		return qui;
	}

	public void setQui(boolean qui) {
		this.qui = qui;
	}

	public boolean isSex() {
		return sex;
	}

	public void setSex(boolean sex) {
		this.sex = sex;
	}

	public boolean isSab() {
		return sab;
	}

	public void setSab(boolean sab) {
		this.sab = sab;
	}

	public boolean isDom() {
		return dom;
	}

	public void setDom(boolean dom) {
		this.dom = dom;
	}

	public char getTipoRepetir() {
		return tipoRepetir;
	}

	public void setTipoRepetir(char tipoRepetir) {
		this.tipoRepetir = tipoRepetir;
	}

	public boolean isRepetirAPartirData() {
		return repetirAPartirData;
	}

	public void setRepetirAPartirData(boolean repetirAPartirData) {
		this.repetirAPartirData = repetirAPartirData;
	}

	@Override
	public String toString() {
		return "Lembrete [id=" + id + ", dataRegistro=" + dataRegistro + ", horaRegistro=" + horaRegistro + ", data="
				+ data + ", hora=" + hora + ", assunto=" + assunto + ", ativo=" + ativo + ", repetir=" + repetir
				+ ", tipoRepetir=" + tipoRepetir + ", repetirAPartirData=" + repetirAPartirData + ", dia=" + dia
				+ ", diaEmHoras=" + diaEmHoras + ", mes=" + mes + ", ano=" + ano + ", seg=" + seg + ", ter=" + ter
				+ ", qua=" + qua + ", qui=" + qui + ", sex=" + sex + ", sab=" + sab + ", dom=" + dom + "]";
	}

}
