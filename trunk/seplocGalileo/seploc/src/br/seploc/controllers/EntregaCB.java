package br.seploc.controllers;

import java.io.Serializable;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import br.seploc.mbeans.EntregaMB;
import br.seploc.pojos.Entrega;
import br.seploc.util.Utils;

/**
 * @author Gustavo
 * 
 */
public class EntregaCB implements Serializable {

	private static final long serialVersionUID = 1L;
	private EntregaMB entregaMB;

	// CONSTRUTOR
	/**
	 * Construtor
	 */
	public EntregaCB() {		
		this.setEntregaMB(loadEntregaMB());
	}
		
	// SETTERS AND GETTERS

	/**
	 * @param entregaMB
	 *            atribuir entregaMB
	 */
	public void setEntregaMB(EntregaMB entregaMB) {
		this.entregaMB = entregaMB;
	}

	/**
	 * @return the entregaMB
	 */
	public EntregaMB getEntregaMB() {
		return entregaMB;
	}
	
	/**
	 * lista das entregas
	 * @return List<Entrega>
	 */
	public List<Entrega> getListaEntregas() {
		 List<Entrega> lista = entregaMB.getLista();
		 for(Entrega e : lista){
			 System.out.println(e);
		 }
		return lista;
	}
	
	// METODOS
	/**
	 * 
	 */
	public EntregaMB loadEntregaMB(){
		FacesContext context = FacesContext.getCurrentInstance();
		EntregaMB entregaMB = (EntregaMB) context.getApplication()
            .evaluateExpressionGet(context, "#{entregaMB}", EntregaMB.class);
		return entregaMB;
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
	public void validateLocal(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		if (value == null)
			return;
		String local;
		String errorMsg = "";
		
		Pattern pattern = Pattern.compile("^\\s*\\s(\\s)$");
		Matcher m = pattern.matcher(value.toString());
		if (value instanceof String)
			local = value.toString().trim();
		else {
			errorMsg = Utils.getMessageResourceString("messages",
					"local.invalido", null, context.getViewRoot()
							.getLocale());
			FacesMessage message = new FacesMessage(errorMsg);	
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
		if (m.matches()) {
			errorMsg = Utils.getMessageResourceString("messages",
					"local.invalido.espacos", null, context.getViewRoot()
							.getLocale());
			FacesMessage message = new FacesMessage(errorMsg);
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
		if (local.length() < 2) {
			errorMsg = Utils.getMessageResourceString("messages",
					"local.invalido.menor", null, context.getViewRoot()
							.getLocale());
			FacesMessage message = new FacesMessage(errorMsg);	
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
		if (local.length() > 20) {
			errorMsg = Utils.getMessageResourceString("messages",
					"local.invalido.maior", null, context.getViewRoot()
							.getLocale());
			FacesMessage message = new FacesMessage(errorMsg);	
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}

	}
	
}
