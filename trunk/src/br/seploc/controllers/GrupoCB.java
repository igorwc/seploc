package br.seploc.controllers;

import java.io.Serializable;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import br.seploc.mbeans.GrupoMB;
import br.seploc.pojos.Grupo;
import br.seploc.util.Utils;

public class GrupoCB implements Serializable {
	private static final long serialVersionUID = 1L;
	private GrupoMB grupoMB;
	
	// CONSTRUTOR
	/**
	 * Construtor
	 */
	public GrupoCB() {
		this.setGrupoMB(loadGrupoMB());
	}
	
	public GrupoMB getGrupoMB() {
		return grupoMB;
	}

	public void setGrupoMB(GrupoMB grupoMB) {
		this.grupoMB = grupoMB;
	}

	public List<Grupo> getListaGrupos(){
		List<Grupo> lista = grupoMB.getLista();
		
		return lista;
	}
	
	// METODOS
	/**
	 * 
	 */
	public GrupoMB loadGrupoMB(){
		FacesContext context = FacesContext.getCurrentInstance();
		GrupoMB grupoMB = (GrupoMB) context.getApplication()
            .evaluateExpressionGet(context, "#{grupoMB}", GrupoMB.class);
		return grupoMB;
	}		
	
	// VALIDADORES
	/**
	 * @param FacesContext
	 *            context
	 * @param UIComponent
	 *            component
	 * @param Object
	 *            value
	 * @throws ValidatorException
	 * 
	 */
	public void validateNome(FacesContext context, UIComponent component,
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
					"nome.invalido", null, context.getViewRoot()
							.getLocale());
			FacesMessage message = new FacesMessage(errorMsg);			
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
		if (nome.length() < 5) {
			errorMsg = Utils.getMessageResourceString("messages",
					"nome.invalido.menor", null, context.getViewRoot()
							.getLocale());
			FacesMessage message = new FacesMessage(errorMsg);
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
		if (nome.length() >= 60) {
			errorMsg = Utils.getMessageResourceString("messages",
					"nome.invalido.maior", null, context.getViewRoot()
							.getLocale());
			FacesMessage message = new FacesMessage(errorMsg);
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
		if (m.matches()) {
			errorMsg = Utils.getMessageResourceString("messages",
					"nome.invalido.espacos", null, context.getViewRoot()
							.getLocale());
			FacesMessage message = new FacesMessage(errorMsg);
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
	}
}
