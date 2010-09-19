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
		Pattern pattern = Pattern.compile("^\\s*\\s(\\s)$");
		Matcher m = pattern.matcher(value.toString());
		if (value instanceof String)
			nomeOpcional = value.toString().trim();
		else {
			FacesMessage message = new FacesMessage("Opcional Inválido");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
		if (nomeOpcional.length() < 2 || nomeOpcional.length() > 20) {
			FacesMessage message = new FacesMessage(
					"O nome do opcional deve ter entre 2 e 20 caracteres");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}

		if (m.matches()) {
			FacesMessage message = new FacesMessage("O nome do opcional só tem espaços");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
	}	
}
