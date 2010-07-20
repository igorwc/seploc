package br.seploc.pojos;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "tbl_parametroprog")
@SqlResultSetMapping(name = "ParametroProg.implicit", entities = @EntityResult(entityClass = br.seploc.pojos.ParametroProg.class))
@NamedNativeQueries({
		@NamedNativeQuery(name = "ParametroProg.RetornaParametros", query = "SELECT * "
				+ "FROM  tbl_parametroprog "
				, resultSetMapping = "ParametroProg.implicit"),
		@NamedNativeQuery(name = "ParametroProg.BuscaParametro", query = "SELECT * "
				+ "FROM  tbl_parametroprog "
				+ "WHERE UPPER( vcrCodParametro ) = UPPER( :CODIGO )", resultSetMapping = "ParametroProg.implicit") })
public class ParametroProg implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "vcrCodParametro", nullable = false)
	private String codParametro;

	@Column(name = "vcrDescricao", nullable = false)
	private String descricao;

	@Column(name = "vcrValor", nullable = false)
	private String valor;

	@Version
	@Column(name = "tspVersao")
	private Timestamp versao;

	public ParametroProg() {
	}

	public ParametroProg(String codParametro, String descricao, String valor) {
		this.codParametro = codParametro;
		this.descricao = descricao;
		this.valor = valor;
	}

	public ParametroProg(String descricao, String valor) {
		this.descricao = descricao;
		this.valor = valor;
	}

	public String getCodParametro() {
		return codParametro;
	}

	public void setCodParametro(String codParametro) {
		this.codParametro = codParametro;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public Timestamp getVersao() {
		return versao;
	}

	public void setVersao(Timestamp versao) {
		this.versao = versao;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((codParametro == null) ? 0 : codParametro.hashCode());
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
		ParametroProg other = (ParametroProg) obj;
		if (codParametro == null) {
			if (other.codParametro != null)
				return false;
		} else if (!codParametro.equals(other.codParametro))
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
		return "ParametroProg ["
				+ (codParametro != null ? "codParametro=" + codParametro + ", "
						: "")
				+ (descricao != null ? "descricao=" + descricao + ", " : "")
				+ (valor != null ? "valor=" + valor : "") + "]";
	}

}