package br.seploc.controllers;

import java.io.Serializable;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import br.seploc.mbeans.UsuarioMB;
import br.seploc.pojos.Usuario;

public class UsuarioCB implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private UsuarioMB usuarioMB;

	// CONSTRUTOR
	/**
	 * Construtor
	 */
	public UsuarioCB(){
		this.setUsuarioMB(loadUsuarioMB());
	}

	// SETTERS AND GETTERS
	/**
	 * Recupera o UsuarioMB
	 */
	public UsuarioMB getUsuarioMB() {
		return usuarioMB;
	}

	/**
	 * Atribui UsuarioMB
	 * @param usuarioMB
	 */
	public void setUsuarioMB(UsuarioMB usuarioMB) {
		this.usuarioMB = usuarioMB;
	}
	
	/**
	 * lista dos usuarios
	 * @return List<Usuario>
	 */
	public List<Usuario> getListaUsuarios() {
		 List<Usuario> lista = usuarioMB.getLista();
		 for(Usuario u : lista){
			 System.out.println(u);
		 }
		return lista;
	}
	
	// METODOS
	/**
	 * 
	 */
	public UsuarioMB loadUsuarioMB(){
		FacesContext context = FacesContext.getCurrentInstance();
		UsuarioMB usuarioMB = (UsuarioMB) context.getApplication()
            .evaluateExpressionGet(context, "#{usuarioMB}", UsuarioMB.class);
		return usuarioMB;
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
		if (nome.length() < 4) {
			FacesMessage message = new FacesMessage("O nome do usuário deve ter 4 letras no mínimo");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
		if (nome.length() > 20) {
			FacesMessage message = new FacesMessage(
					"O nome do usuário deve ter entre 4 e 20 caracteres");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
		if (m.matches()) {
			FacesMessage message = new FacesMessage(
					"O nome do usuário só tem espaços");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
	}
	
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
	public void validateLogin(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		if (value == null)
			return;
		String nome;

		Pattern pattern = Pattern.compile("^\\s*\\s(\\s)$");
		Matcher m = pattern.matcher(value.toString());
		if (value instanceof String)
			nome = value.toString().trim();
		else {
			FacesMessage message = new FacesMessage("Login Inválido");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
		if (nome.length() < 5) {
			FacesMessage message = new FacesMessage("O login do usuário deve ter 5 letras no mínimo");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
		if (nome.length() > 20) {
			FacesMessage message = new FacesMessage(
					"O login do usuário deve ter entre 5 e 20 caracteres");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
		if (m.matches()) {
			FacesMessage message = new FacesMessage(
					"O login do usuário só tem espaços");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
	}
	
	
}
