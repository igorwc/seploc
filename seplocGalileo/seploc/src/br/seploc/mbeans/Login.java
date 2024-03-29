package br.seploc.mbeans;

import java.util.Locale;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.seploc.dao.UsuarioDAO;
import br.seploc.dao.exceptions.LoginExistenteException;
import br.seploc.pojos.Usuario;
import br.seploc.util.MENUS;
import br.seploc.util.SessionObjectsManager;
import br.seploc.util.Utils;

public class Login {
	private boolean loginOk;
	private String userName;
	private String userNameFull;
	private String password;
	private boolean newPasswordOk;
	private String newPassword;
	private String retypeNewPassword;
	private Usuario user;
	private boolean falhaLogin;
	private String errorMsg;

	private UsuarioDAO usuarioDAO = new UsuarioDAO();

	public Login() {
		loginOk = false;
		userName = "";
		falhaLogin = false;
		errorMsg = "Ocorreu um erro!!!";

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

	public void setUserNameFull(String userNameFull) {
		this.userNameFull = userNameFull;
	}

	public String getUserNameFull() {
		return userNameFull;
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

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String logoff() {
		SessionObjectsManager.removeObjetoSessao("usuarioLogado");
		SessionObjectsManager.removeObjetoSessao("usuarioSessao");
		FacesContext context = FacesContext.getCurrentInstance();		
		context.getViewRoot().setViewId("/paginas/login.xhtml");
		for (MENUS m : MENUS.values()) {
			   SessionObjectsManager.removeObjetoSessao(m.getNome()+"_ESC" );
		}
		ExternalContext ectx = FacesContext.getCurrentInstance().getExternalContext();
		//HttpServletResponse response = (HttpServletResponse)ectx.getResponse();
		HttpSession session = (HttpSession)ectx.getSession(false);
		session.invalidate(); 
		this.loginOk = false;
		falhaLogin = false;
		return "login";
	}

	public String changePassword() {
		Usuario usuario = null;
		FacesContext context = FacesContext.getCurrentInstance();
		if (userName == null || password == null) {
			  errorMsg = Utils.getMessageResourceString("messages",
					"login.invalido", null, context.getViewRoot().getLocale());
			  return "loginErro";
		}
		usuario = usuarioDAO.recupera(userName, password);

		if (usuario == null) {
			errorMsg = Utils.getMessageResourceString("messages",
					"login.invalido", null, new Locale("pt"));
			return "loginErro";
		}
		if (newPassword == null || newPassword.trim().equals("")
				|| retypeNewPassword == null
				|| retypeNewPassword.trim().equals("")) {
			errorMsg = "Nova senha invalida!";
			return "loginErro";
			// FacesMessage message = new FacesMessage("Nova senha invalida!");
			// message.setSeverity(FacesMessage.SEVERITY_ERROR);
			// throw new ValidatorException(message);
		}
		if (!newPassword.trim().equals(retypeNewPassword)) {
			errorMsg = "Nova senha invalida!";
			System.out.println("Entrou na nova senha invalida");
			return "loginErro";
		}
		usuario.setPassword(newPassword);
		try {
			usuarioDAO.altera(usuario);
			return "principal";
		} catch (LoginExistenteException e) {
			errorMsg = "Erro ao alterar a senha!";
			
			e.printStackTrace();
			return "loginErro";
		}
	}

	public String resetErrorMessage(){
		errorMsg = "Ocorreu um erro!!!";
		return "login";
	}
	public String validateLogin() {
		// (new ListsLoader()).start();

		// newPasswordOk = true;
		Usuario usuario = null;
		System.out.println("Entrou aqui!!!");
		FacesContext context = FacesContext.getCurrentInstance();
		if (userName == null || password == null) {
			errorMsg = Utils.getMessageResourceString("messages",
					"login.invalido", null, context.getViewRoot().getLocale());

			return "loginErro";
		}
		usuario = usuarioDAO.recupera(userName, password);
		if (usuario == null) {
			errorMsg = Utils.getMessageResourceString("messages",
					"login.invalido", null, new Locale("pt"));
			falhaLogin = true;
			return "loginErro";
		}
		loginOk = true;
		SessionObjectsManager.adicionaObjetoSessao("usuarioLogado", loginOk);// recuperaObjetoSessao("usuarioSessao");
		this.user = usuario;
		SessionObjectsManager.adicionaObjetoSessao("usuarioSessao", this.user);
		falhaLogin = false;
		userName = this.user.getLogin();
		userNameFull = this.user.getNome();
		falhaLogin = false;
		processaMenus();
		processaMenusEscrita();
		return "principal";
		
	}

	private void processaMenus() {
		Map<String, Boolean> permissoesMenus = this.user.getGrupo()
				.retornaPermissoes();
		for (MENUS m : MENUS.values()) {
			SessionObjectsManager.adicionaObjetoSessao(m.getNome(),
					!permissoesMenus.get(m.getNome()));
			System.out.println(m.getNome() + ","
					+ permissoesMenus.get(m.getNome()));
		}
	}
	
	private void processaMenusEscrita() {
		Map<String, Boolean> permissoesMenusEscrita = this.user.getGrupo()
				.retornaPermissoesEscrita();
		for (MENUS m : MENUS.values()) {
			SessionObjectsManager.adicionaObjetoSessao(m.getNome()+"_ESC",
					!permissoesMenusEscrita.get(m.getNome()));
			System.out.println(m.getNome()+"_ESC" + ","
					+ permissoesMenusEscrita.get(m.getNome()));
		}
	}	

	
	public static void addGlobalMessage(String message) {
		FacesMessage facesMessage = new FacesMessage(message);
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	}

}
