package br.seploc.controllers;

import java.io.Serializable;
import java.util.Locale;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import br.seploc.mbeans.RelRequisicaoProducaoMB;

public class RelRequisicaoProducaoCB implements Serializable {

	private static final long serialVersionUID = 1L;
	RelRequisicaoProducaoMB relRequisicaoProducaoMB;
	Locale locale;

	// CONSTRUTOR
	public RelRequisicaoProducaoCB() {
		locale = new Locale("pt", "br");
		this.setRelRequisicaoProducaoMB(loadRequisicaoProducao());
	}
	
	public RelRequisicaoProducaoMB loadRequisicaoProducao(){
		FacesContext context = FacesContext.getCurrentInstance();
		RelRequisicaoProducaoMB relRequisicaoProducaoMB = (RelRequisicaoProducaoMB) context
				.getApplication()
				.evaluateExpressionGet(context, "#{relRequisicaoProducaoMB}",
						RelRequisicaoProducaoMB.class);
		return relRequisicaoProducaoMB;
		
	}
	
	// METODOS AUXILIARES
	public static void addGlobalMessage(String message) {
		FacesMessage facesMessage = new FacesMessage(message);
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	}
	
	public RelRequisicaoProducaoMB getRelRequisicaoProducaoMB() {
		return relRequisicaoProducaoMB;
	}

	public void setRelRequisicaoProducaoMB(
			RelRequisicaoProducaoMB relRequisicaoProducaoMB) {
		this.relRequisicaoProducaoMB = relRequisicaoProducaoMB;
	}

	public Locale getLocale() {
		return locale;
	}
	
	public void setLocale(Locale locale) {
		this.locale = locale;
	}	

}
