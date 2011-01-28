package br.seploc.migracao.beans;

public class Usuario {
	private Integer intCodUsr;
	private String vcrLogin;
	private String vcrPassword;
	private String vcrCpf;
	private Integer intPermissao;
	private String vcrIpMaquina;
	private Integer intGrupo;
	private String vcrNome;
	public Usuario() {
	}

	 
	public Usuario(Integer intCodUsr, String vcrLogin, String vcrPassword,
			String vcrCpf, Integer intPermissao, String vcrIpMaquina,
			Integer intGrupo, String vcrNome) {
		this.intCodUsr = intCodUsr;
		this.vcrLogin = vcrLogin;
		this.vcrPassword = vcrPassword;
		this.vcrCpf = vcrCpf;
		this.intPermissao = intPermissao;
		this.vcrIpMaquina = vcrIpMaquina;
		this.intGrupo = intGrupo;
		this.vcrNome = vcrNome;
	}


	public Usuario(String vcrLogin, String vcrPassword, String vcrCpf,
			Integer intPermissao, String vcrIpMaquina, Integer intGrupo,
			String vcrNome) {
		this.vcrLogin = vcrLogin;
		this.vcrPassword = vcrPassword;
		this.vcrCpf = vcrCpf;
		this.intPermissao = intPermissao;
		this.vcrIpMaquina = vcrIpMaquina;
		this.intGrupo = intGrupo;
		this.vcrNome = vcrNome;
	}


	public String getVcrLogin() {
		return vcrLogin;
	}

	public void setVcrLogin(String vcrLogin) {
		this.vcrLogin = vcrLogin;
	}

	public String getVcrPassword() {
		return vcrPassword;
	}

	public void setVcrPassword(String vcrPassword) {
		this.vcrPassword = vcrPassword;
	}

	public String getVcrCpf() {
		return vcrCpf;
	}

	public void setVcrCpf(String vcrCpf) {
		this.vcrCpf = vcrCpf;
	}

	public Integer getIntPermissao() {
		return intPermissao;
	}

	public void setIntPermissao(Integer intPermissao) {
		this.intPermissao = intPermissao;
	}

	public String getVcrIpMaquina() {
		return vcrIpMaquina;
	}

	public void setVcrIpMaquina(String vcrIpMaquina) {
		this.vcrIpMaquina = vcrIpMaquina;
	}

	 

	public Integer getIntCodUsr() {
		return intCodUsr;
	}


	public void setIntCodUsr(Integer intCodUsr) {
		this.intCodUsr = intCodUsr;
	}


	public Integer getIntGrupo() {
		return intGrupo;
	}


	public void setIntGrupo(Integer intGrupo) {
		this.intGrupo = intGrupo;
	}


	public String getVcrNome() {
		return vcrNome;
	}

	public void setVcrNome(String vcrNome) {
		this.vcrNome = vcrNome;
	}

}
