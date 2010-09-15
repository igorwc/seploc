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

import br.seploc.mbeans.tests.PapelMB;
import br.seploc.pojos.Papel;

/**
 * @author Igor
 * 
 */
public class PapelCB implements Serializable {

	private static final long serialVersionUID = 1L;
	private PapelMB papelMB;

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

	// CONSTRUTOR
	/**
	 * @return the papelMB
	 */
	public PapelMB getPapelMB() {
		return papelMB;
	}
	public List<Papel> getListaPapeis() {
		 List<Papel> lista = papelMB.getLista();
		 for(Papel p : lista){
			 System.out.println(p);
		 }
		return lista;
	}
	public PapelMB loadPapelMB(){
		FacesContext context = FacesContext.getCurrentInstance();
		PapelMB papelMB = (PapelMB) context.getApplication()
            .evaluateExpressionGet(context, "#{papelMB}", PapelMB.class);
		return papelMB;
	}
	// SETTERS AND GETTERS

	/**
	 * @param papelMB
	 *            the papelMB to set
	 */
	public void setPapelMB(PapelMB papelMB) {
		this.papelMB = papelMB;
	}

	/**
	 * 
	 */
	public PapelCB() {
		System.out.println("construiu papelCB");
		this.setPapelMB(loadPapelMB());
		System.out.println(loadPapelMB());
	}

}
