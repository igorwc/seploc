package br.seploc.pojos;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * The persistent class for the tbl_usuario database table.
 * 
 */
@Entity
@Table(name="tbl_usuario")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private String vcrLogin;

	private int intGrupoAcesso;

	private int intPermissao;

	private String vcrCpf;

	private String vcrIpMaquina;

	private String vcrNome;

	private String vcrPassword;

    public Usuario() {
    }

	public String getVcrLogin() {
		return this.vcrLogin;
	}

	public void setVcrLogin(String vcrLogin) {
		this.vcrLogin = vcrLogin;
	}

	public int getIntGrupoAcesso() {
		return this.intGrupoAcesso;
	}

	public void setIntGrupoAcesso(int intGrupoAcesso) {
		this.intGrupoAcesso = intGrupoAcesso;
	}

	public int getIntPermissao() {
		return this.intPermissao;
	}

	public void setIntPermissao(int intPermissao) {
		this.intPermissao = intPermissao;
	}

	public String getVcrCpf() {
		return this.vcrCpf;
	}

	public void setVcrCpf(String vcrCpf) {
		this.vcrCpf = vcrCpf;
	}

	public String getVcrIpMaquina() {
		return this.vcrIpMaquina;
	}

	public void setVcrIpMaquina(String vcrIpMaquina) {
		this.vcrIpMaquina = vcrIpMaquina;
	}

	public String getVcrNome() {
		return this.vcrNome;
	}

	public void setVcrNome(String vcrNome) {
		this.vcrNome = vcrNome;
	}

	public String getVcrPassword() {
		return this.vcrPassword;
	}

	public void setVcrPassword(String vcrPassword) {
		this.vcrPassword = vcrPassword;
	}

}