package br.seploc.mbeans;

import java.util.Locale;

import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import br.seploc.dao.UsuarioDAO;
import br.seploc.pojos.Usuario;
import br.seploc.util.Utils;

public class Login {
	private boolean loginOk;
	private String userName;
	private String password;

	private UsuarioDAO usuarioDAO = new UsuarioDAO();

	public boolean isLoginOk() {
		return loginOk;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String validateLogin() {
		FacesContext context = FacesContext.getCurrentInstance();
		if (userName == null || password == null) {
			
			String errorMsg = Utils.getMessageResourceString("messages", "login.invalido",
					null, context.getViewRoot().getLocale());
			addGlobalMessage(errorMsg);
			FacesMessage message = new FacesMessage(errorMsg);
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
		Usuario user = usuarioDAO.getUsarioPorLogin(userName);
		if (user == null) {
			String errorMsg = Utils.getMessageResourceString("messages", "login.invalido",
					null,new Locale("pt"));
			addGlobalMessage(errorMsg);
			return null;
//			FacesMessage message = new FacesMessage(errorMsg);
//			message.setSeverity(FacesMessage.SEVERITY_ERROR);
//			throw new FacesException(errorMsg);
		}
		if (user.getLogin().equals(userName)
				&& user.getPassword().equals(password)) {
			loginOk = true;
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuarioLogado", loginOk);
			return "principal";
		} else
			return "login";
	}

	public static void addGlobalMessage(String message) {
		FacesMessage facesMessage = new FacesMessage(message);
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	}
}
