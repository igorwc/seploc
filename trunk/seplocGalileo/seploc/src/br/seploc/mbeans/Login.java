package br.seploc.mbeans;

import java.util.Locale;

import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.seploc.dao.UsuarioDAO;
import br.seploc.pojos.Usuario;
import br.seploc.util.SessionObjectsManager;
import br.seploc.util.Utils;

public class Login {
	private boolean loginOk;
	private String userName;
	private String password;
	private boolean newPasswordOk;
	private String newPassword;
	private String retypeNewPassword;
	private Usuario user;
	private boolean falhaLogin;

	private UsuarioDAO usuarioDAO = new UsuarioDAO();

	public Login() {
		loginOk = false;
		userName = "";
		
	}

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

	public boolean isNewPasswordOk() {
		return newPasswordOk;
	}

	public void setNewPasswordOk(boolean newPasswordOk) {
		this.newPasswordOk = newPasswordOk;
	}

	public void setLoginOk(boolean loginOk) {
		this.loginOk = loginOk;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getRetypeNewPassword() {
		return retypeNewPassword;
	}

	public void setRetypeNewPassword(String retypeNewPassword) {
		this.retypeNewPassword = retypeNewPassword;
	}

	public boolean isFalhaLogin() {
		return falhaLogin;
	}

	public void setFalhaLogin(boolean falhaLogin) {
		this.falhaLogin = falhaLogin;
	}

	public String logoff() {
		SessionObjectsManager.removeObjetoSessao("usuarioLogado");
		SessionObjectsManager.removeObjetoSessao("usuarioSessao");
		FacesContext context = FacesContext.getCurrentInstance();
		NavigationHandler nh = context.getApplication().getNavigationHandler();
		context.getViewRoot().setViewId("/paginas/login.xhtml");
		this.loginOk = false;
		return "login";
	}

	public String validateLogin() {
		newPasswordOk = true;
		Usuario usuario = null;
		System.out.println("Entrou aqui!!!");
		FacesContext context = FacesContext.getCurrentInstance();
		if (userName == null || password == null) {
			String errorMsg = Utils.getMessageResourceString("messages",
					"login.invalido", null, context.getViewRoot().getLocale());
			addGlobalMessage(errorMsg);
			FacesMessage message = new FacesMessage(errorMsg);
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
		usuario = usuarioDAO.recupera(userName, password);
		if (usuario == null) {
			String errorMsg = Utils.getMessageResourceString("messages",
					"login.invalido", null, new Locale("pt"));
			addGlobalMessage(errorMsg);
			falhaLogin = true;
			return "login";
		}
		// DesEncrypter encrypter = new DesEncrypter();
		// String decrypted = encrypter.decrypt(usuario.getPassword());
		// if (usuario.getLogin().equals(userName) &&
		// decrypted.equals(password)) {
		loginOk = true;
		// APAGAR
		// FacesContext.getCurrentInstance().getExternalContext()
		// .getSessionMap().put("usuarioLogado", loginOk);
		SessionObjectsManager.adicionaObjetoSessao("usuarioLogado", loginOk);// recuperaObjetoSessao("usuarioSessao");
		this.user = usuario;
		// APAGAR
		// HttpServletRequest req = (HttpServletRequest) FacesContext
		// .getCurrentInstance().getExternalContext().getRequest();
		// HttpSession s = req.getSession(true);
		// s.setMaxInactiveInterval(3600);
		// req.getSession().setAttribute("usuarioSessao", this.user);
		SessionObjectsManager.adicionaObjetoSessao("usuarioSessao", this.user);
		falhaLogin = false;
		userName = this.user.getLogin();
		return "principal";
		// }
		// else {
		// addGlobalMessage("Usuário e/ou Senha estão errados");
		// falhaLogin = true;
		// return "login";
		// }
	}

	// }
	// public String validateLogin() {
	// FacesContext context = FacesContext.getCurrentInstance();
	// if (userName == null || password == null) {
	//			
	// String errorMsg = Utils.getMessageResourceString("messages",
	// "login.invalido",
	// null, context.getViewRoot().getLocale());
	// addGlobalMessage(errorMsg);
	// FacesMessage message = new FacesMessage(errorMsg);
	// message.setSeverity(FacesMessage.SEVERITY_ERROR);
	// throw new ValidatorException(message);
	// }
	// Usuario user = usuarioDAO.getUsuarioPorLogin(userName);
	// if (user == null) {
	// String errorMsg = Utils.getMessageResourceString("messages",
	// "login.invalido",
	// null,new Locale("pt"));
	// addGlobalMessage(errorMsg);
	// return null;
	// // FacesMessage message = new FacesMessage(errorMsg);
	// // message.setSeverity(FacesMessage.SEVERITY_ERROR);
	// // throw new FacesException(errorMsg);
	// }
	// if (user.getLogin().equals(userName)
	// && user.getPassword().equals(password)) {
	// loginOk = true;
	// FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuarioLogado",
	// loginOk);
	// return "principal";
	// } else
	// return "login";
	// }

	public static void addGlobalMessage(String message) {
		FacesMessage facesMessage = new FacesMessage(message);
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	}
}
