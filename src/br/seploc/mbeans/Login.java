package br.seploc.mbeans;

public class Login {
	private boolean loginOk;
	private String userName;
	private String password;

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
		if (userName != null && password != null
				&& !userName.equalsIgnoreCase(password)) {
			loginOk = true;
			return "principal";
		} else
			return "login";
	}
}
