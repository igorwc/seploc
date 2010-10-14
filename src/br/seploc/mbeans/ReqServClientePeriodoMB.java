package br.seploc.mbeans;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.seploc.dao.RequisicaoServicoDAO;
import br.seploc.pojos.Cliente;
import br.seploc.pojos.RequisicaoServico;

public class ReqServClientePeriodoMB implements Serializable{

	private static final long serialVersionUID = 1L;
	private Cliente cliente;
	private Date dataInicio;
	private Date dataFim;
	private Double desconto;
	private List<RequisicaoServico> listaRequisicoes;

	//METODOS NEGOCIO
	public List<RequisicaoServico> buscaRequisicoes() {
		RequisicaoServicoDAO dao = new RequisicaoServicoDAO();
		List<RequisicaoServico> retorno = null;
		if (dataInicio != null && dataFim != null && cliente != null
				&& cliente.getIdCliente() != null
				&& cliente.getIdCliente() != 0){
			retorno = dao.getListaPorPeriodo(dataInicio, dataFim,
					cliente.getIdCliente());
		}
		if (retorno == null || retorno.isEmpty()) {
			return new ArrayList<RequisicaoServico>();
		}
		return retorno;
	}
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

	public List<RequisicaoServico> getListaRequisicoes() {
		return listaRequisicoes;
	}

	public void setListaRequisicoes(List<RequisicaoServico> listaRequisicoes) {
		this.listaRequisicoes = listaRequisicoes;
	}
	
	
	
}
