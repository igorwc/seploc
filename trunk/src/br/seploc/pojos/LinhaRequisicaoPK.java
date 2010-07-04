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

	@GeneratedValue(generator = "entrega_id", strategy = GenerationType.TABLE)
	@Column(name = "intNumLin", nullable = false)
	private Integer numLinha;
	
	@Column(name = "intNumreq", nullable = false)
	private Integer numRequisicao;
	
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

	
}