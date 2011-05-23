package br.seploc.controllers;

import java.io.Serializable;
import java.util.Locale;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import br.seploc.mbeans.RelRequisicaoProducaoMB;

public class RelRequisicaoProducaoCB implements Serializable {

	private static final long serialVersionUID = 1L;
	RelRequisicaoProducaoMB relProducaoMB;
	Locale locale;

	// CONSTRUTOR
	public RelRequisicaoProducaoCB() {
		locale = new Locale("pt", "br");
		this.setRelProducaoMB(loadRequisicaoProducao());
	}
	
	public RelRequisicaoProducaoMB loadRequisicaoProducao(){
		FacesContext context = FacesContext.getCurrentInstance();
		RelRequisicaoProducaoMB relProducaoMB = (RelRequisicaoProducaoMB) context
				.getApplication()
				.evaluateExpressionGet(context, "#{relProducaoMB}",
						RelRequisicaoProducaoMB.class);
		return relProducaoMB;
		
	}
	
	// METODOS AUXILIARES
	public static void addGlobalMessage(String message) {
		FacesMessage facesMessage = new FacesMessage(message);
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	}
	
	public RelRequisicaoProducaoMB getRelProducaoMB() {
		return relProducaoMB;
	}

	public void setRelProducaoMB(
			RelRequisicaoProducaoMB relProducaoMB) {
		this.relProducaoMB = relProducaoMB;
	}

	public Locale getLocale() {
		return locale;
	}
	
	public void setLocale(Locale locale) {
		this.locale = locale;
	}	

}
