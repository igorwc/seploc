package br.seploc.controllers;

import java.io.Serializable;
import java.util.Locale;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import br.seploc.mbeans.RelReqServCobradorMB;

public class RelReqServCobradorCB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	RelReqServCobradorMB relCobradorMB;
	Locale locale;

	// METODOS AUXILIARES
	public static void addGlobalMessage(String message) {
		FacesMessage facesMessage = new FacesMessage(message);
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	}
	
	public RelReqServCobradorMB loadRelCobradorMB() {
		FacesContext context = FacesContext.getCurrentInstance();
		RelReqServCobradorMB relCobradorMB = (RelReqServCobradorMB) context
				.getApplication()
				.evaluateExpressionGet(context, "#{relCobradorMB}",
						RelReqServCobradorMB.class);
		return relCobradorMB;
	}

	
	public RelReqServCobradorCB() {
		locale = new Locale("pt", "br");
		this.setRelCobradorMB(loadRelCobradorMB());
	}
	
	//GETTERS AND SETTERS
	public RelReqServCobradorMB getRelCobradorMB() {
		return relCobradorMB;
	}
	public void setRelCobradorMB(RelReqServCobradorMB relCobradorMB) {
		this.relCobradorMB = relCobradorMB;
	}
	public Locale getLocale() {
		return locale;
	}
	public void setLocale(Locale locale) {
		this.locale = locale;
	}
	
	
}
