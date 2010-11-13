package br.seploc.migracao.beans;

import java.sql.Date;

public class RequisicaoServico {
	private Integer numReq;
	private Date data;
	private String cnpj;
	private Integer projeto;
	private Integer entrega;;
	private Double total;
	private Integer status;
	private Integer cobrador;
	private Integer visivelNF;
	private Integer visivelReq;
	private Double vlEntrega;

	public Integer getNumReq() {
		return numReq;
	}

	public void setNumReq(Integer numReq) {
		this.numReq = numReq;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public Integer getProjeto() {
		return projeto;
	}

	public void setProjeto(Integer projeto) {
		this.projeto = projeto;
	}

	public Integer getEntrega() {
		return entrega;
	}

	public void setEntrega(Integer entrega) {
		this.entrega = entrega;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getCobrador() {
		return cobrador;
	}

	public void setCobrador(Integer cobrador) {
		this.cobrador = cobrador;
	}

	public Integer getVisivelNF() {
		return visivelNF;
	}

	public void setVisivelNF(Integer visivelNF) {
		this.visivelNF = visivelNF;
	}

	public Integer getVisivelReq() {
		return visivelReq;
	}

	public void setVisivelReq(Integer visivelReq) {
		this.visivelReq = visivelReq;
	}

	public Double getVlEntrega() {
		return vlEntrega;
	}

	public void setVlEntrega(Double vlEntrega) {
		this.vlEntrega = vlEntrega;
	}

	@Override
	public String toString() {
		return "RequisicaoServico [cnpj=" + cnpj + ", cobrador=" + cobrador
				+ ", data=" + data + ", entrega=" + entrega + ", numReq="
				+ numReq + ", projeto=" + projeto + ", status=" + status
				+ ", total=" + total + ", visivelNF=" + visivelNF
				+ ", visivelReq=" + visivelReq + ", vlEntrega=" + vlEntrega
				+ "]";
	}
	
}
