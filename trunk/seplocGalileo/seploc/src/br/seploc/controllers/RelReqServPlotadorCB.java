package br.seploc.controllers;

import java.io.Serializable;
import java.util.Locale;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import br.seploc.mbeans.RelReqServPlotadorMB;

public class RelReqServPlotadorCB implements Serializable {

	private static final long serialVersionUID = 1L;
	RelReqServPlotadorMB relPlotadorMB;
	Locale locale;
	
	// METODOS AUXILIARES
	public static void addGlobalMessage(String message) {
		FacesMessage facesMessage = new FacesMessage(message);
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	}
	
	public RelReqServPlotadorMB load(){
		FacesContext context = FacesContext.getCurrentInstance();
		RelReqServPlotadorMB relPlotadorMB = (RelReqServPlotadorMB) context
		.getApplication()
		.evaluateExpressionGet(context, "#{relPlotadorMB}",
				RelReqServPlotadorMB.class);
		return relPlotadorMB;
	}
	
	public RelReqServPlotadorCB() {
		locale = new Locale("pt", "br");
		this.setRelPlotadorMB(this.load());	
    }
	
	// GETTERS AND SETTERS
	public RelReqServPlotadorMB getRelPlotadorMB() {
		return relPlotadorMB;
	}

	public void setRelPlotadorMB(RelReqServPlotadorMB relPlotadorMB) {
		this.relPlotadorMB = relPlotadorMB;
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}	
}