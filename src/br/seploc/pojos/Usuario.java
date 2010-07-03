package br.seploc.pojos;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Version;

import br.seploc.util.DesEncrypter;

/**
 * The persistent class for the tbl_usuario database table.
 * 
 */
/*
 * CREATE TABLE IF NOT EXISTS tbl_usuario ( vcrLogin varchar(30) NOT NULL
 * DEFAULT '', vcrPassword varchar(30) DEFAULT NULL, vcrCpf varchar(20) NOT NULL
 * DEFAULT '', intPermissao int(1) NOT NULL DEFAULT '0', vcrIpMaquina
 * varchar(20) DEFAULT NULL, intGrupo int(1) DEFAULT NULL, vcrNome varchar(100)
 * DEFAULT NULL, tspVersao timestamp NULL DEFAULT CURRENT_TIMESTAMP, PRIMARY KEY
 * (vcrLogin), KEY USUA_GRP_FK (intGrupo) ) ENGINE=InnoDB DEFAULT
 * CHARSET=latin1;
 */
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
					                   + " WHERE vcrLogin = :login)", resultSetMapping = "Usuario.implicit") 
})
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "usua_id", strategy = GenerationType.TABLE)
	@TableGenerator(name = "usua_id", table = "ID_GEN", allocationSize = 1, initialValue = 1, pkColumnName = "NOME_ID", valueColumnName = "VAL_ID", pkColumnValue = "USUA_GEN")
	@Column(name = "vcrLogin")
	private String login;

	@Column(name = "vcrNome")
	private String nome;

	@Column(name = "vcrPassword")
	private String password;

	@Column(name = "vcrCpf")
	private String cpf;

	@Column(name = "intPermissao")
	private Integer permissao;

	@Column(name = "vcrIpMaquina")
	private String ipMaquina;

	@Version
	@Column(name = "tspVersao")
	private Timestamp versao;

	@JoinColumn(name = "intGrupo", referencedColumnName = "intGrupo")
	@ManyToOne(fetch = FetchType.EAGER)
	private Grupo grupo;

	public Usuario() {
	}

	public Usuario(String login, String nome, String password, String cpf,
			Integer permissao, String ipMaquina, Timestamp versao, Grupo grupo) {
		this.login = login;
		this.nome = nome;
		this.password = password;
		this.cpf = cpf;
		this.permissao = permissao;
		this.ipMaquina = ipMaquina;
		this.versao = versao;
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

	
}