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
import br.seploc.pojos.SaidaMotoqueiro;

public class ReqServListaSaidaCB implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private ReqServListaSaidaMB reqServSaidaMB;
	private String datasInvalidasMsg;
	private Locale locale;
	
	public ReqServListaSaidaCB(){
		locale = new Locale("pt", "br");
		this.setReqServSaidaMB(this.loadReqServList());
	}

	public ReqServListaSaidaMB getReqServSaidaMB() {
		return reqServSaidaMB;
	}

	public void setReqServSaidaMB(ReqServListaSaidaMB reqServSaidas) {
		this.reqServSaidaMB = reqServSaidas;
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
		ReqServListaSaidaMB reqServSaidaMB = (ReqServListaSaidaMB) context.getApplication()
		.evaluateExpressionGet(context, "#{reqServSaidaMB}", ReqServListaSaidaMB.class);
		
		return reqServSaidaMB;
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
	
	public List<SaidaMotoqueiro> getGridSaida(){
		List<SaidaMotoqueiro> lista = new ArrayList<SaidaMotoqueiro>();
		Calendar dI = new GregorianCalendar(Locale.getDefault());
		Calendar dF = new GregorianCalendar(Locale.getDefault());		
		dI.setTime(reqServSaidaMB.getDataInicio());	
		dF.setTime(reqServSaidaMB.getDataFim()); 
		Date dataInicio = dI.getTime();
		Date dataFim = dF.getTime();
		Integer numeroReqServ;
		if (reqServSaidaMB.getSaidaMotoqueiro() == null || reqServSaidaMB.getSaidaMotoqueiro().getReqServico() == null ) {
			numeroReqServ = null;
		} else {
			numeroReqServ = reqServSaidaMB.getSaidaMotoqueiro().getReqServico().getNumReq();
		}
		 

		if (numeroReqServ == null || numeroReqServ == 0){
			lista = reqServSaidaMB.getSaidaMotoqueiroDAO().getLista(dataInicio,dataFim);
		} else {
			lista = reqServSaidaMB.getSaidaMotoqueiroDAO().getLista(numeroReqServ);
		}
		//Collections.reverse(lista);		
		return lista;
	}	
	
	public void validateDatas() {
		Date dataInicio = reqServSaidaMB.getDataInicio();
		Date dataFim = reqServSaidaMB.getDataFim();
		if (dataInicio.getTime() > dataFim.getTime()) {
			setDatasInvalidasMsg("A data final deve ser maior que a data inicial.");
			reqServSaidaMB.setDatasInvalidas(true);
		} else {
			reqServSaidaMB.setDatasInvalidas(false);
		}
	}		
	
}