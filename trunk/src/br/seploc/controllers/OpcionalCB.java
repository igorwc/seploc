package br.seploc.controllers;

import java.io.Serializable;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import br.seploc.mbeans.OpcionalMB;
import br.seploc.pojos.OpcionaisReqServ;
import br.seploc.util.Utils;

public class OpcionalCB implements Serializable {

	private static final long serialVersionUID = 1L;
	private OpcionalMB opcionalMB;
	
	// CONSTRUTOR
	/**
	 * Construtor
	 */
	public OpcionalCB(){
		this.setOpcionalMB(loadOpcionalMB());
	}

	// SETTERS AND GETTERS
	/**
	 * Atribuir OpcionalMB
	 */
	public void setOpcionalMB(OpcionalMB opcionalMB) {
		this.opcionalMB = opcionalMB;
	}

	/**
	 * Recuperar OpcionalMB
	 * @return OpcionalMB
	 */
	public OpcionalMB getOpcionalMB() {
		return opcionalMB;
	}
	
	/**
	 * lista dos opcionais
	 * @return List<OpcionaisReqServ>
	 */
	public List<OpcionaisReqServ> getListaOpcionais() {
		 List<OpcionaisReqServ> lista = opcionalMB.getLista();
		 for(OpcionaisReqServ o : lista){
			 System.out.println(o);
		 }
		return lista;
	}
	
	// METODOS
	/**
	 * 
	 */
	public OpcionalMB loadOpcionalMB(){
		FacesContext context = FacesContext.getCurrentInstance();
		OpcionalMB opcionalMB = (OpcionalMB) context.getApplication()
            .evaluateExpressionGet(context, "#{opcionalMB}", OpcionalMB.class);
		return opcionalMB;
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
	public void validateOpcional(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		if (value == null)
			return;
		String nomeOpcional;
		String errorMsg = "";
		
		Pattern pattern = Pattern.compile("^\\s*\\s(\\s)$");
		Matcher m = pattern.matcher(value.toString());
		if (value instanceof String)
			nomeOpcional = value.toString().trim();
		else {
			errorMsg = Utils.getMessageResourceString("messages",
					"opcional.invalido", null, context.getViewRoot()
							.getLocale());
			FacesMessage message = new FacesMessage(errorMsg);	
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
		if (nomeOpcional.length() < 2) {
			errorMsg = Utils.getMessageResourceString("messages",
					"nome.invalido.menor", null, context.getViewRoot()
							.getLocale());
			FacesMessage message = new FacesMessage(errorMsg);	
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
		if (nomeOpcional.length() > 20) {
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
