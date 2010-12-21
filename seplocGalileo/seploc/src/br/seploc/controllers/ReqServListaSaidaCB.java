package br.seploc.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import javax.faces.context.FacesContext;

import br.seploc.mbeans.ReqServListaSaidaMB;
import br.seploc.pojos.RequisicaoServico;

public class ReqServListaSaidaCB implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private ReqServListaSaidaMB reqServSaidasMB;
	private String datasInvalidasMsg;
	private Locale locale;
	
	public ReqServListaSaidaCB(){
		locale = new Locale("pt", "br");
		this.setReqServSaidasMB(this.loadReqServList());
	}

	public ReqServListaSaidaMB getReqServSaidasMB() {
		return reqServSaidasMB;
	}

	public void setReqServSaidasMB(ReqServListaSaidaMB reqServSaidas) {
		this.reqServSaidasMB = reqServSaidas;
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
	
	public ReqServListaSaidaMB loadReqServList(){
		FacesContext context = FacesContext.getCurrentInstance();
		ReqServListaSaidaMB reqServListaSaidaMB = (ReqServListaSaidaMB) context.getApplication()
		.evaluateExpressionGet(context, "#{reqServListaSaidaMB}", ReqServListaSaidaMB.class);
		
		return reqServListaSaidaMB;
	}
	
	/**
	 * Metodo ira formatar o numero existente no banco para um numero de formato 000009  
	 * @param num
	 * @return
	 */
	public String getNumReqServFormatado(Integer num){
		String retorno = num.toString();
		StringBuffer str = new StringBuffer(retorno);
		
		while (str.length() < 6) {
			str.append(0);			
			retorno = str.reverse().toString();			
		}		
		
		return retorno;
	}		

	public List<RequisicaoServico> getGridReqServ(){
		List<RequisicaoServico> lista = new ArrayList<RequisicaoServico>();
		Calendar dataInicio = new GregorianCalendar(Locale.getDefault());
		Calendar dataFim = new GregorianCalendar(Locale.getDefault());
		dataInicio.setTime(reqServSaidasMB.getDataInicio());	
		dataFim.setTime(reqServSaidasMB.getDataFim()); 
		int numeroReqServ = reqServSaidasMB.getNumReqBusca();
		int clienteID = reqServSaidasMB.getClienteID();
		int projetoID = reqServSaidasMB.getProjetoID();

		lista = reqServSaidasMB.getReqServicoDAO().filtraReqServ(projetoID , numeroReqServ, clienteID, dataInicio, dataFim);
		Collections.reverse(lista);		
		return lista;
	}	
	
	public void validateDatas() {
		Date dataInicio = reqServSaidasMB.getDataInicio();
		Date dataFim = reqServSaidasMB.getDataFim();
		if (dataInicio.getTime() > dataFim.getTime()) {
			setDatasInvalidasMsg("A data final deve ser maior que a data inicial.");
			reqServSaidasMB.setDatasInvalidas(true);
		} else {
			reqServSaidasMB.setDatasInvalidas(false);
		}
	}		
	
}