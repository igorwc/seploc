package br.seploc.pojos;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

@Entity
@Table(name = "id_gen")
@SqlResultSetMapping(name = "ParametroProg.implicit", entities = @EntityResult(entityClass = br.seploc.pojos.ParametroProg.class))
@NamedNativeQueries({
		@NamedNativeQuery(name = "ParametroProg.RetornaParametros", query = "SELECT * "
				+ "FROM  id_gen "
				, resultSetMapping = "ParametroProg.implicit"),
		@NamedNativeQuery(name = "ParametroProg.BuscaParametro", query = "SELECT * "
				+ "FROM  id_gen "
				+ "WHERE UPPER( nome_id ) = UPPER( :codigo )", resultSetMapping = "ParametroProg.implicit") })
public class ParametroProg implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "nome_id", nullable = false)
	private String codParametro;

	@Column(name = "val_id", nullable = false)
	private Integer valor;

	public ParametroProg() {
	}

	public ParametroProg(String codParametro, Integer valor) {
		this.codParametro = codParametro;
		this.valor = valor;
	}

	public ParametroProg(String codParametro) {
		this.codParametro = codParametro;
		this.valor = 0;
	}

	public String getCodParametro() {
		return codParametro;
	}

	public void setCodParametro(String codParametro) {
		this.codParametro = codParametro;
	}

	public Integer getValor() {
		return valor;
	}

	public void setValor(Integer valor) {
		this.valor = valor;
	}
	
	public String getCodParametroFormatado(){
		int valorGEN = codParametro.indexOf("_GEN");
		String retorno = codParametro.substring(0,valorGEN);
		return retorno;
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
		return true;
	}

	@Override
	public String toString() {
		return "ParametroProg ["
				+ (codParametro != null ? "codParametro=" + codParametro + ", "
						: "")
				+ (valor != null ? "valor=" + valor : "") + "]";
	}

}