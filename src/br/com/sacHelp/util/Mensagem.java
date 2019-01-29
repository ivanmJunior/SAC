package br.com.sacHelp.util;

public class Mensagem {

	private String  mensagemSucesso;
	private String  mensagemErro;

	public Mensagem(){
		this.mensagemSucesso = "";
		this.mensagemErro = "";
	}
	
	public String getMensagemSucesso() {
		return mensagemSucesso;
	}

	public void setMensagemSucesso(String mensagem) {
		this.mensagemSucesso = mensagem;
	} 
	
	public String getMensagemErro() {
		return mensagemErro;
	}

	public void setMensagemErro(String mensagem) {
		this.mensagemErro = mensagem;
	} 
}
