package br.seploc.reports.beans;

import br.seploc.util.MESES;

public class ReqServProducaoBeanGrid implements Comparable<ReqServProducaoBeanGrid>{
	private int mes;
	private int totalReq;
	private double valorTotal;
	private double valorDesconto;
	
	
	public ReqServProducaoBeanGrid(){
		// TODO Auto-generated constructor stub
	}
	
	public ReqServProducaoBeanGrid(int totalReq, double valorTotal, double valorDesconto, int mes){
		this.totalReq = totalReq;
		this.valorTotal = valorTotal;
		this.valorDesconto = valorDesconto;
		this.mes = mes;
	}
	
	public ReqServProducaoBeanGrid(int totalReq, double valorTotal, double valorDesconto){
		this.totalReq = totalReq;
		this.valorTotal = valorTotal;
		this.valorDesconto = valorDesconto;
	}

	public ReqServProducaoBeanGrid(int mes){
		this.totalReq = 0;
		this.valorTotal = 0.0;
		this.valorDesconto = 0.0;
		this.mes = mes;
	}
	
	
	public int getTotalReq() {
		return totalReq;
	}

	public void setTotalReq(int totalReq) {
		this.totalReq = totalReq;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public double getValorDesconto() {
		return valorDesconto;
	}

	public void setValorDesconto(double valorDesconto) {
		this.valorDesconto = valorDesconto;
	}

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}
	
	public String getNomeMes(){
		MESES nm = MESES.getMes(this.mes);
		return nm.toString();		
	}

	@Override
	public int compareTo(ReqServProducaoBeanGrid o) {		
		return this.mes - o.getMes();
	}
	
}
