package br.seploc.controllers;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import javax.faces.context.FacesContext;

import br.seploc.mbeans.AppServiceBean;
import br.seploc.mbeans.ReqServListaMB;
import br.seploc.pojos.Cliente;
import br.seploc.pojos.RequisicaoServico;

public class ReqServListaCB implements Serializable {

	private static final long serialVersionUID = 1L;
	private ReqServListaMB reqServListaMB;
	private String datasInvalidasMsg;
	private Locale locale;

	// CONSTRUTOR
	public ReqServListaCB() {
		locale = new Locale("pt", "br");
		this.setReqServListaMB(this.loadReqServList());
	}

	public ReqServListaMB getReqServListaMB() {
		return reqServListaMB;
	}

	public List<Cliente> getListaClientes() {
		return AppServiceBean.getListaClientes();
	}

	public void setReqServListaMB(ReqServListaMB reqServListaMB) {
		this.reqServListaMB = reqServListaMB;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public Locale getLocale() {
		return locale;
	}

	public void setDatasInvalidasMsg(String datasInvalidasMsg) {
		this.datasInvalidasMsg = datasInvalidasMsg;
	}

	public String getDatasInvalidasMsg() {
		return datasInvalidasMsg;
	}

	public ReqServListaMB loadReqServList() {
		FacesContext context = FacesContext.getCurrentInstance();
		ReqServListaMB reqServListaMB = (ReqServListaMB) context
				.getApplication().evaluateExpressionGet(context,
						"#{reqServListaMB}", ReqServListaMB.class);

		return reqServListaMB;
	}

	/**
	 * Metodo ira formatar o numero existente no banco para um numero de formato
	 * 000009
	 * 
	 * @param num
	 * @return
	 */
	public String getNumReqServFormatado(Integer num) {
		String retorno = num.toString();
		StringBuffer str = new StringBuffer(retorno);

		while (str.length() < 6) {
			str.append(0);
			retorno = str.reverse().toString();
		}

		return retorno;
	}

	public List<RequisicaoServico> getGridReqServ() {
		List<RequisicaoServico> lista = new ArrayList<RequisicaoServico>();
		Calendar dataInicio = new GregorianCalendar(Locale.getDefault());
		Calendar dataFim = new GregorianCalendar(Locale.getDefault());
		dataInicio.setTime(reqServListaMB.getDataInicio());
		dataFim.setTime(reqServListaMB.getDataFim());
		int numeroReqServ = reqServListaMB.getNumReqBusca();
		int clienteID = reqServListaMB.getClienteID();
		int projetoID = reqServListaMB.getProjetoID();

		lista = reqServListaMB.getReqServicoDAO().filtraReqServ(projetoID,
				numeroReqServ, clienteID, dataInicio, dataFim);
		Collections.reverse(lista);
		return lista;
	}

	public void validateDatas() {
		Date dataInicio = reqServListaMB.getDataInicio();
		Date dataFim = reqServListaMB.getDataFim();
		if (dataInicio.getTime() > dataFim.getTime()) {
			setDatasInvalidasMsg("A data final deve ser maior que a data inicial.");
			reqServListaMB.setDatasInvalidas(true);
		} else {
			reqServListaMB.setDatasInvalidas(false);
		}
	}
}