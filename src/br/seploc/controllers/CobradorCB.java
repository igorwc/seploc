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
		Pattern pattern = Pattern.compile("^\\s*\\s(\\s)$");
		Matcher m = pattern.matcher(value.toString());
		if (value instanceof String)
			nome = value.toString().trim();
		else {
			FacesMessage message = new FacesMessage("Nome Inválido");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
		if (nome.length() < 2 || nome.length() > 20) {
			FacesMessage message = new FacesMessage(
					"O nome deve ter entre 2 e 20 caracteres");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}

		if (m.matches()) {
			FacesMessage message = new FacesMessage("O nome só tem espaços");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
	}
	
}
