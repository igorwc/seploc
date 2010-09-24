/**
 * 
 */
package br.seploc.controllers;

import java.io.Serializable;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import br.seploc.mbeans.CobradorMB;
import br.seploc.pojos.Cobrador;
import br.seploc.util.Utils;

/**
 * @author Gustavo
 * 
 */
public class CobradorCB implements Serializable {

	private static final long serialVersionUID = 1L;
	private CobradorMB cobradorMB;

	// CONSTRUTOR
	/**
	 * Construtor
	 */
	public CobradorCB() {		
		this.setCobradorMB(loadCobradorMB());
	}
		
	// SETTERS AND GETTERS

	/**
	 * @param cobradorMB
	 *            the cobradorMB to set
	 */
	public void setCobradorMB(CobradorMB cobradorMB) {
		this.cobradorMB = cobradorMB;
	}

	/**
	 * @return the cobradorMB
	 */
	public CobradorMB getCobradorMB() {
		return cobradorMB;
	}
	
	/**
	 * lista dos cobradores
	 * @return List<Cobrador>
	 */
	public List<Cobrador> getListaCobradores() {
		 List<Cobrador> lista = cobradorMB.getLista();
		 for(Cobrador c : lista){
			 System.out.println(c);
		 }
		return lista;
	}
	
	// METODOS
	/**
	 * 
	 */
	public CobradorMB loadCobradorMB(){
		FacesContext context = FacesContext.getCurrentInstance();
		CobradorMB cobradorMB = (CobradorMB) context.getApplication()
            .evaluateExpressionGet(context, "#{cobradorMB}", CobradorMB.class);
		return cobradorMB;
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
		if (m.matches()) {
			errorMsg = Utils.getMessageResourceString("messages",
					"nome.invalido.espacos", null, context.getViewRoot()
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

	}
	
	public void validateFone(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		if (value == null)
			return;
		String fone;		
		String errorMsg = "";
		
		Pattern pattern = Pattern.compile("^\\s*\\s(\\s)$");
		Matcher m = pattern.matcher(value.toString());
		if (value instanceof String)
			fone = value.toString();
		else
			fone = "";
		if (m.matches()) {
			errorMsg = Utils.getMessageResourceString("messages",
					"fone.invalido.espacos", null, context.getViewRoot()
							.getLocale());
			FacesMessage message = new FacesMessage(errorMsg);
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
		if (fone.length() > 1 && fone.length() < 14) {
			errorMsg = Utils.getMessageResourceString("messages",
					"fone.invalido", null, context.getViewRoot()
							.getLocale());
			FacesMessage message = new FacesMessage(errorMsg);
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
	}	
		
}
