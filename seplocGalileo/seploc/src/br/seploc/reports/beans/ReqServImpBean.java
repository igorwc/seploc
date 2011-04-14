package br.seploc.reports.beans;

import java.util.Date;

public class ReqServImpBean {
	String seq;
	String numReq;
	Date data;
	String projeto;
	String subtotal;
	String subtotalDesc;
	String desconto;
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getNumReq() {
		return numReq;
	}
	public void setNumReq(String numReq) {
		this.numReq = numReq;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public String getProjeto() {
		return projeto;
	}
	public void setProjeto(String projeto) {
		this.projeto = projeto;
	}
	public String getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(String subtotal) {
		this.subtotal = subtotal;
	}
	public String getSubtotalDesc() {
		return subtotalDesc;
	}
	public void setSubtotalDesc(String subtotalDesc) {
		this.subtotalDesc = subtotalDesc;
	}
	public String getDesconto() {
		return desconto;
	}
	public void setDesconto(String desconto) {
		this.desconto = desconto;
	}
	 
}
