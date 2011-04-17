package br.seploc.reports.beans;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReqServImpBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String seq;
	private String numReq;
	private Date data;
	private String projeto;
	private String subtotal;
	private String subtotalDesc;
	private String desconto;
	
	
	public ReqServImpBean( ) {
		this.seq = "";
		this.numReq = "";
		this.data = null;
		this.projeto= "";
		this.subtotal= "";
		this.subtotalDesc= "";
		this.desconto= "";
	}
	
	public ReqServImpBean(String seq) {
		this.seq = seq;
		this.numReq = "";
		this.data = null;
		this.projeto= "";
		this.subtotal= "";
		this.subtotalDesc= "";
		this.desconto= "";
	}
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
	public String getDataFormatada(){
		if(data == null){
			return "";
		}
		SimpleDateFormat formatador = new SimpleDateFormat("dd-MM-yyyy");  
		return formatador.format(data);
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
