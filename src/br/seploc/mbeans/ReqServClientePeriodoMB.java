package br.seploc.mbeans;

import java.sql.Date;
import java.util.Calendar;

import br.seploc.pojos.Cliente;

public class ReqServClientePeriodoMB {

	private Cliente cliente;
	private Date dataInicio;
	private Date dataFim;
	private Double desconto;

	//CONSTRUTOR PADRÃO
	public ReqServClientePeriodoMB() {
		cliente = new Cliente();
		dataInicio  = new Date(Calendar.getInstance().getTimeInMillis());
		dataFim = new Date(Calendar.getInstance().getTimeInMillis());
		desconto = 0.0;
	}

	//GETTERS AND SETTERS
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public Double getDesconto() {
		return desconto;
	}

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}
	
	
	
}
