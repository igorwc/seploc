package br.seploc.mbeans;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import br.seploc.dao.UsuarioDAO;
import br.seploc.dao.exceptions.LoginInsertException;
import br.seploc.dao.exceptions.RecordNotFound;
import br.seploc.pojos.Usuario;

public class UsuarioBean {

	/**
	 * pojo usuario
	 */
	private Usuario usuario;

	/**
	 * DAO do pojo usuario
	 */
	private UsuarioDAO usuarioDAO;
	
	String antigoLogin;

	/**
	 * Construtor do Bean do usuario
	 */
	public UsuarioBean() {
		usuario = new Usuario();
		usuario.setPermissao(-1);
		usuarioDAO = new UsuarioDAO();
		
		System.out.println("criou bean usuario");
	}

	/**
	 * Metodo recupera o usuario
	 * 
	 * @return usuario
	 */
	public Usuario getUsuario() {
		System.out.println("get usuario");
//		this.antigoLogin = usuario.getLogin();
		return usuario;
	}

	/**
	 * Metodo altera o usuario
	 * 
	 * @param usuario
	 */
	public void setUsuario(Usuario usuario) {
		System.out.println("set usuario");
//		this.antigoLogin = usuario.getLogin();
	}

	/**
	 * Metodo recupera o DAO do usuario
	 * 
	 * @return usuarioDAO
	 */
	public UsuarioDAO getUsuarioDAO() {
		return usuarioDAO;
	}

	/**
	 * Metodo altera o DAO do usuario
	 * 
	 * @param usuarioDAO
	 */
	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}

	

	/**
	 * @return the novoLogin
	 */
	public String getAntigoLogin() {
		return antigoLogin;
	}

	/**
	 * @param novoLogin the novoLogin to set
	 */
	public void setAntigoLogin(String antigoLogin) {
		this.antigoLogin = antigoLogin;
	}

	/**
	 * Metodo cadastra o usuario
	 */
	public void cadastra() {
		System.out.println("cadastra usuario");

		Usuario temp;

		try {
			if (usuario.getPermissao() == -1) {
				usuario.setPermissao(0);
				usuarioDAO.adiciona(usuario);
				addGlobalMessage("Inclusão feita com sucesso!");
			} else {
				if(!antigoLogin.equals(usuario.getLogin())){
					addGlobalMessage("O login do usuário não pode ser modificado!");
					this.limpar();
					return;
				}
				temp = usuarioDAO.recupera(antigoLogin);
				if (temp == null) {
					throw new RecordNotFound("Usuario Inexistente");
				}
				temp.setNome(usuario.getNome());
				temp.setCpf(usuario.getCpf());
				temp.setGrupo(usuario.getGrupo());
				temp.setPermissao(usuario.getPermissao());
				usuarioDAO.altera(temp);
				addGlobalMessage("Atualização feita com sucesso!");
			}
		} catch (RecordNotFound e) {
			addGlobalMessage(e.getMessage());
		} catch (LoginInsertException e) {
			addGlobalMessage(e.getMessage());
		} catch (Exception e) {
			addGlobalMessage("A operação não pôde ser realizada.");
		}
		this.limpar();
	}

	/**
	 * Metodo apaga o registro de usuario
	 */
	public void apaga() {
		System.out.println("apaga usuario");
		try {
			usuarioDAO.remove(usuario.getLogin());
			addGlobalMessage("Usuário excluído com sucesso!");
		} catch (Exception e) {
			addGlobalMessage(e.getMessage());
			e.printStackTrace();
		}
		this.limpar();
	}

	/**
	 * Metodo edita o registro do usuario
	 */
	public void editar() {
		try {
			usuario = usuarioDAO.recupera(usuario.getLogin());
			antigoLogin = usuario.getLogin();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Metodo recupera a lista de usuarios do banco
	 * 
	 * @return array de usuario
	 */
	public List<Usuario> getLista() {
		return usuarioDAO.getLista();
	}

	/**
	 * Metodo reinicia o objeto usuario do Bean
	 */
	public void limpar() {
		System.out.println("limpa usuario");
		usuario = new Usuario();
	}

	/**
	 * Método para incluir mensagens globais no formulário de cadastro
	 * 
	 * @param String
	 *            message
	 */
	public static void addGlobalMessage(String message) {
		FacesMessage facesMessage = new FacesMessage(message);
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	}

	// Validadores
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
		if (nome.length() < 5) {
			FacesMessage message = new FacesMessage("O nome do usuário deve ter 5 letras no mínimo");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
		if (nome.length() > 20) {
			FacesMessage message = new FacesMessage(
					"O nome do usuário deve ter entre 5 e 20 caracteres");
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
