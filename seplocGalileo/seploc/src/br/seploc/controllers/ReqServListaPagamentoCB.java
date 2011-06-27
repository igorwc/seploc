package br.seploc.controllers;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.faces.context.FacesContext;

import br.seploc.mbeans.ReqServListaPagamentoMB;
import br.seploc.pojos.RequisicaoServico;

public class ReqServListaPagamentoCB  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private ReqServListaPagamentoMB reqServPagamentoMB;
	private String datasInvalidasMsg;
	private Locale locale;
	
	public ReqServListaPagamentoCB(){
		locale = new Locale("pt", "br");
		this.setReqServPagamentoMB(this.loadReqServList());
	}
	
	public ReqServListaPagamentoMB loadReqServList(){
		FacesContext context = FacesContext.getCurrentInstance();
		ReqServListaPagamentoMB reqServPagamentoMB = (ReqServListaPagamentoMB) context.getApplication()
		.evaluateExpressionGet(context, "#{reqServPagamentoMB}", ReqServListaPagamentoMB.class);
		
		return reqServPagamentoMB;
	}
	
	public void validateDatas() {
		Date dataInicio = reqServPagamentoMB.getDataInicio();
		Date dataFim = reqServPagamentoMB.getDataFim();
		if (dataInicio.getTime() > dataFim.getTime()) {
			setDatasInvalidasMsg("A data final deve ser maior que a data inicial.");
			reqServPagamentoMB.setDatasInvalidas(true);
		} else {
			reqServPagamentoMB.setDatasInvalidas(false);
		}
	}
	
	public List<RequisicaoServico> getGridPagamento(){
		List<RequisicaoServico> retorno = reqServPagamentoMB.getListaReqServPagamento();
		return retorno;
	}
	
	// GETTERS AND SETTERS
	
	public ReqServListaPagamentoMB getReqServPagamentoMB() {
		return reqServPagamentoMB;
	}

	public void setReqServPagamentoMB(ReqServListaPagamentoMB reqServPagamentoMB) {
		this.reqServPagamentoMB = reqServPagamentoMB;
	}

	public String getDatasInvalidasMsg() {
		return datasInvalidasMsg;
	}

	public void setDatasInvalidasMsg(String datasInvalidasMsg) {
		this.datasInvalidasMsg = datasInvalidasMsg;
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}		
	

}
