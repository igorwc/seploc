package br.seploc.controllers;

import java.io.Serializable;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import br.seploc.mbeans.ProjetoMB;
import br.seploc.pojos.Projeto;

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

		Pattern pattern = Pattern.compile("^\\s*\\s(\\s)$");
		Matcher m = pattern.matcher(value.toString());
		if (value instanceof String)
			nome = value.toString().trim();
		else {
			FacesMessage message = new FacesMessage("Nome do Projeto Inválido");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
		if (nome.length() < 5) {
			FacesMessage message = new FacesMessage(
					"O Nome do Projeto deve ter 5 letras no mínimo");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
		if (nome.length() >= 80) {
			FacesMessage message = new FacesMessage(
					"O Nome do Projeto deve ter entre 5 e 80 caracteres");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
		if (m.matches()) {
			FacesMessage message = new FacesMessage(
					"O Nome do Projeto só tem espaços");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
	}	
}
