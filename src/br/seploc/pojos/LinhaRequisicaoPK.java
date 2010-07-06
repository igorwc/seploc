package br.seploc.pojos;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the tbl_linhareq database table.
 * 
 */
@Embeddable
public class LinhaRequisicaoPK implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "intNumreq", nullable = false)
	private Integer numRequisicao;
	
	@Column(name = "intNumLin", nullable = false)
	private Integer numLinha;

	
	public LinhaRequisicaoPK(Integer numLinha, Integer numRequisicao) {
		this.numLinha = numLinha;
		this.numRequisicao = numRequisicao;
	}

	public LinhaRequisicaoPK() {
	}

	public Integer getNumLinha() {
		return numLinha;
	}

	public void setNumLinha(Integer numLinha) {
		this.numLinha = numLinha;
	}

	public Integer getNumRequisicao() {
		return numRequisicao;
	}

	public void setNumRequisicao(Integer numRequisicao) {
		this.numRequisicao = numRequisicao;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((numLinha == null) ? 0 : numLinha.hashCode());
		result = prime * result
				+ ((numRequisicao == null) ? 0 : numRequisicao.hashCode());
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
		LinhaRequisicaoPK other = (LinhaRequisicaoPK) obj;
		if (numLinha == null) {
			if (other.numLinha != null)
				return false;
		} else if (!numLinha.equals(other.numLinha))
			return false;
		if (numRequisicao == null) {
			if (other.numRequisicao != null)
				return false;
		} else if (!numRequisicao.equals(other.numRequisicao))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "LinhaRequisicaoPK ["
				+ (numLinha != null ? "numLinha=" + numLinha + ", " : "")
				+ (numRequisicao != null ? "numRequisicao=" + numRequisicao
						: "") + "]";
	}

	
}