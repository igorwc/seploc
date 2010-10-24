package br.seploc.controllers;

import java.util.Locale;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletContext;

import br.seploc.mbeans.ReqServClientePeriodoMB;
import br.seploc.util.SessionObjectsManager;

public class ReqServClientePeriodoCB {

	ReqServClientePeriodoMB clientePeriodoMB;
	Locale locale;
	String url = "";

	// METODOS AUXILIARES
	public static void addGlobalMessage(String message) {
		FacesMessage facesMessage = new FacesMessage(message);
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	}

	public void atualizaDesconto() {

	}

	public void setaObjetosSessao(ActionEvent event) {
		SessionObjectsManager.adicionaObjetoSessao("clientID", clientePeriodoMB.getCliente().getIdCliente());
		SessionObjectsManager.adicionaObjetoSessao("clientDesconto", clientePeriodoMB.getDesconto() );
		SessionObjectsManager.adicionaObjetoSessao("clientDataInicio", clientePeriodoMB.getDataInicio());
		SessionObjectsManager.adicionaObjetoSessao("clientDataFim", clientePeriodoMB.getDataFim() );
		 FacesContext fcontext = FacesContext.getCurrentInstance();
		   ServletContext scontext = (ServletContext) fcontext.getExternalContext
		().getContext();
         
		url = scontext.getContextPath()+"/RelPeriodoCliente.report";
		System.out.println(url);
	}

	public ReqServClientePeriodoMB loadClientePeriodoMB() {
		FacesContext context = FacesContext.getCurrentInstance();
		ReqServClientePeriodoMB clienteMB = (ReqServClientePeriodoMB) context
				.getApplication()
				.evaluateExpressionGet(context, "#{reqClientePeriodoMB}",
						ReqServClientePeriodoMB.class);
		return clienteMB;
	}

	public ReqServClientePeriodoCB() {
		System.out.println("construiu ReqServClientePeriodoCB");
		locale = new Locale("pt", "br");
		this.setClientePeriodoMB(loadClientePeriodoMB());
	}

	// GETTERS AND SETTERS
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
