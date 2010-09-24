package br.seploc.controllers;

import java.io.Serializable;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import br.seploc.mbeans.ProjetoMB;
import br.seploc.pojos.Projeto;
import br.seploc.util.Utils;

public class ProjetoCB implements Serializable {
	
	private static final long serialVersionUID = 1L;	
	
	private ProjetoMB projetoMB;	
	
	//CONSTRUTOR
	public ProjetoCB(){
		this.setProjetoMB(loadProjetoMB());
	}
	
	public ProjetoMB loadProjetoMB() {
		FacesContext context = FacesContext.getCurrentInstance();
		ProjetoMB projetoMB = (ProjetoMB) context
				.getApplication()
				.evaluateExpressionGet(context, "#{projetoMB}", ProjetoMB.class);
		return projetoMB;
	}	
	
	// SETTTER AND GETTERS
	public ProjetoMB getProjetoMB() {
		return projetoMB;
	}


	public void setProjetoMB(ProjetoMB projetoMB) {
		this.projetoMB = projetoMB;
	}

	// METODOS
	public List<Projeto> getListaProjetos(){
		List<Projeto> lista = projetoMB.getLista();

		return lista;
	}
	
	// VALIDATES
	public void validateNomeProj(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		if (value == null)
			return;
		String nome;
		String errorMsg = "";

		Pattern pattern = Pattern.compile("^\\s*\\s(\\s)$");
		Matcher m = pattern.matcher(value.toString());
		if (value instanceof String)
			nome = value.toString().trim();
		else {
			errorMsg = Utils.getMessageResourceString("messages",
					"projeto.invalido", null, context.getViewRoot()
							.getLocale());
			FacesMessage message = new FacesMessage(errorMsg);	
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
		if (m.matches()) {
			errorMsg = Utils.getMessageResourceString("messages",
					"projeto.invalido.espacos", null, context.getViewRoot()
							.getLocale());
			FacesMessage message = new FacesMessage(errorMsg);	
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}		
		if (nome.length() < 5) {
			errorMsg = Utils.getMessageResourceString("messages",
					"projeto.invalido.menor", null, context.getViewRoot()
							.getLocale());
			FacesMessage message = new FacesMessage(errorMsg);	
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
		if (nome.length() >= 100) {
			errorMsg = Utils.getMessageResourceString("messages",
					"projeto.invalido.maior", null, context.getViewRoot()
							.getLocale());
			FacesMessage message = new FacesMessage(errorMsg);	
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}

	}	
}
