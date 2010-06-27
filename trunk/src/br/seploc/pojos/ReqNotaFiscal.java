package br.seploc.pojos;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * The persistent class for the tbl_reqnotafiscal database table.
 * 
 */
@Entity
@Table(name="tbl_reqnotafiscal")
public class ReqNotaFiscal implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ReqNotaFiscalPK id;

    public ReqNotaFiscal() {
    }

	public ReqNotaFiscalPK getId() {
		return this.id;
	}

	public void setId(ReqNotaFiscalPK id) {
		this.id = id;
	}
	
}