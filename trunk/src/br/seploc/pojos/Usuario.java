package br.seploc.pojos;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.persistence.Version;

import br.seploc.util.DesEncrypter;

@Entity
@Table(name = "tbl_usuario")
@SqlResultSetMapping(name = "Usuario.implicit", entities = @EntityResult(entityClass = br.seploc.pojos.Usuario.class))
@NamedNativeQueries( {
		@NamedNativeQuery(name = "Usuario.RetornaUsuarios", query = " SELECT * "
				+ "FROM tbl_usuario u", resultSetMapping = "Usuario.implicit"),
		@NamedNativeQuery(name = "Usuario.RetornaUsuariosPorGrupo", query = " SELECT * "
				+ "FROM tbl_usuario u "
				+ "where intGrupo = :grupo", resultSetMapping = "Usuario.implicit"), 
		@NamedNativeQuery(name = "Usuario.RetornaRequisicoesPorUsuario", query = " SELECT * "
				+ "FROM tbl_usuario u "
				+ "where vcrLogin in " 
					             + "(SELECT vcrLogin FROM tbl_reqservusuario " 
					             + " WHERE vcrLogin = :login)", resultSetMapping = "Usuario.implicit"),
		@NamedNativeQuery(name = "Usuario.RetornaUsuariosPorNome", query = " SELECT * "
					    				+ "FROM tbl_usuario u "
					    				+ "where vcrNome like :nome", resultSetMapping = "Usuario.implicit"),
		@NamedNativeQuery(name = "Usuario.RetornaUsuariosPorLogin", query = " SELECT * "
				+ "FROM tbl_usuario u "
				+ "where vcrNome like :vcrLogin", resultSetMapping = "Usuario.implicit")
})
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "vcrLogin", length=30)
	private String login;

	@Column(name = "vcrPassword", length=30)
	private String password;
	
	@Column(name = "vcrCpf", length = 20)
	private String cpf;

	@Column(name = "intPermissao")
	private Integer permissao;

	@Column(name = "vcrIpMaquina", length=20)
	private String ipMaquina;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "intGrupo", referencedColumnName = "intGrupo")
	private Grupo grupo;
	
	@Version
	@Column(name = "tspVersao")
	private Timestamp versao;
	
	@Column(name = "vcrNome", length=100)
	private String nome;

	@OneToMany(fetch = FetchType.LAZY)
	private List<ReqServUsuario> reqServUsuario;
	
	public Usuario() {
		setReqServUsuario(new ArrayList<ReqServUsuario>());
	}

	public Usuario(String login, String nome, String password, String cpf,
			Integer permissao, String ipMaquina, Timestamp versao, Grupo grupo) {
		this();
		this.login = login;
		this.nome = nome;
		this.setPassword(password);
		this.cpf = cpf;
		this.permissao = permissao;
		this.ipMaquina = ipMaquina;
		this.versao = versao;
		this.grupo = grupo;
	}
	
	

	public Usuario(String login, String nome, String password, String cpf) {
		this.login = login;
		this.nome = nome;
		this.setPassword(password);
		this.cpf = cpf;
	}

	public Usuario(String login, String nome, String password, String cpf,
			Integer permissao, String ipMaquina) {
		this.login = login;
		this.nome = nome;
		this.setPassword(password);
		this.cpf = cpf;
		this.permissao = permissao;
		this.ipMaquina = ipMaquina;
	}

	public Usuario(String login, String nome, String password, String cpf,
			Integer permissao, String ipMaquina, Grupo grupo) {
		this.login = login;
		this.nome = nome;
		this.setPassword(password);
		this.cpf = cpf;
		this.permissao = permissao;
		this.ipMaquina = ipMaquina;
		this.grupo = grupo;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPassword() {
		try {
			// Create encrypter/decrypter class
			DesEncrypter encrypter = new DesEncrypter();
			String decrypted = encrypter.decrypt(this.password);
			this.password = decrypted;

		} catch (Exception e) {
		}
		return password;
	}

	public void setPassword(String password) {
		String senhaInterna = "";
		try {
			if (password == null || password.equals("")) {
				senhaInterna = "nova";
			} else {
				senhaInterna = password;
			}
			DesEncrypter encrypter = new DesEncrypter();
			String encrypted = encrypter.encrypt(senhaInterna);
			this.password = encrypted;
		} catch (Exception e) {
		}
	}

	public Integer getPermissao() {
		return permissao;
	}

	public void setPermissao(Integer permissao) {
		this.permissao = permissao;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getIpMaquina() {
		return ipMaquina;
	}

	public void setIpMaquina(String ipMaquina) {
		this.ipMaquina = ipMaquina;
	}

	public Timestamp getVersao() {
		return versao;
	}

	public void setVersao(Timestamp versao) {
		this.versao = versao;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((versao == null) ? 0 : versao.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (versao == null) {
			if (other.versao != null)
				return false;
		} else if (!versao.equals(other.versao))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Usuario [" 				
				+ (login != null ? "login=" + login + ", " : "")
				+ (nome != null ? "nome=" + nome : "") + "]"
				+ (ipMaquina != null ? "ipMaquina=" + ipMaquina + ", " : "")
				+ (grupo != null ? "grupo=" + grupo + ", " : "");
	}

	public void setReqServUsuario(List<ReqServUsuario> reqServUsuario) {
		this.reqServUsuario = reqServUsuario;
	}

	public List<ReqServUsuario> getReqServUsuario() {
		return reqServUsuario;
	}

	
}