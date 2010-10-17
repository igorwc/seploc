package br.seploc.controllers;

import java.util.Locale;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import br.seploc.mbeans.ReqServClientePeriodoMB;

public class ReqServClientePeriodoCB {

	ReqServClientePeriodoMB clientePeriodoMB;
	Locale locale;

	// METODOS AUXILIARES
	public static void addGlobalMessage(String message) {
		FacesMessage facesMessage = new FacesMessage(message);
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	}
	
	 
	public void atualizaDesconto(){
		
	}

	public ReqServClientePeriodoMB loadClientePeriodoMB() {
		FacesContext context = FacesContext.getCurrentInstance();
		ReqServClientePeriodoMB clienteMB = (ReqServClientePeriodoMB) context
				.getApplication().evaluateExpressionGet(context,
						"#{reqClientePeriodoMB}", ReqServClientePeriodoMB.class);
		return clienteMB;
	}

	public ReqServClientePeriodoCB() {
		System.out.println("construiu ReqServClientePeriodoCB");
		locale = new Locale("pt", "br");
		this.setClientePeriodoMB(loadClientePeriodoMB());
	}
	
	//GETTERS AND SETTERS
	public ReqServClientePeriodoMB getClientePeriodoMB() {
		return clientePeriodoMB;
	}

	public void setClientePeriodoMB(ReqServClientePeriodoMB clientePeriodoMB) {
		this.clientePeriodoMB = clientePeriodoMB;
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}
	

	
}
