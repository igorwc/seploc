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

import br.seploc.mbeans.LonaMB;
import br.seploc.pojos.Papel;
import br.seploc.util.Utils;

/**
 * @author Igor
 * 
 */
public class LonaCB implements Serializable {

	private static final long serialVersionUID = 1L;
	private LonaMB lonaMB;

	// CONSTRUTOR
	/**
	 * Construtor 
	 */
	public LonaCB() {
		this.setLonaMB(loadLonaMB());
	}
	
	// SETTERS AND GETTERS
	/**
	 * @return the papelMB
	 */
	public LonaMB getLonaMB() {
		return lonaMB;
	}
	public List<Papel> getListaPapeis() {
		 List<Papel> lista = lonaMB.getLista();
		 for(Papel p : lista){
			 System.out.println(p);
		 }
		return lista;
	}
	public LonaMB loadLonaMB(){
		FacesContext context = FacesContext.getCurrentInstance();
		LonaMB lonaMB = (LonaMB) context.getApplication()
            .evaluateExpressionGet(context, "#{lonaMB}", LonaMB.class);
		return lonaMB;
	}

	/**
	 * @param papelMB
	 *            the papelMB to set
	 */
	public void setLonaMB(LonaMB lonaMB) {
		this.lonaMB = lonaMB;
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
		if (nome.length() > 20) {
			errorMsg = Utils.getMessageResourceString("messages",
					"nome.invalido.maior20", null, context.getViewRoot()
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
