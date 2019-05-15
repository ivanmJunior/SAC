package br.com.sacHelp.util;

public class ContaPendentes {
	
	private int qtdAbertos;
	private int qtdEmAndamento;
	private int qtdAtrasados;
	private int totalPendentes;
	
	public int getQtdAbertos() {
		return qtdAbertos;
	}
	public void setQtdAbertos(int qtdAbertos) {
		this.qtdAbertos = qtdAbertos;
	}
	public int getQtdEmAndamento() {
		return qtdEmAndamento;
	}
	public void setQtdEmAndamento(int qtdEmAndamento) {
		this.qtdEmAndamento = qtdEmAndamento;
	}
	public int getTotalPendentes() {
		return totalPendentes;
	}
	public void setTotalPendentes(int totalPendentes) {
		this.totalPendentes = totalPendentes;
	}
	public int getQtdAtrasados() {
		return qtdAtrasados;
	}
	public void setQtdAtrasados(int qtdAtrasados) {
		this.qtdAtrasados = qtdAtrasados;
	}
	
}
