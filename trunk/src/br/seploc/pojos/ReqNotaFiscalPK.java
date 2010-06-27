package br.seploc.pojos;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * The primary key class for the tbl_reqnotafiscal database table.
 * 
 */
@Embeddable
public class ReqNotaFiscalPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private int intNumNota;

	private int intNumReq;

    public ReqNotaFiscalPK() {
    }
	public int getIntNumNota() {
		return this.intNumNota;
	}
	public void setIntNumNota(int intNumNota) {
		this.intNumNota = intNumNota;
	}
	public int getIntNumReq() {
		return this.intNumReq;
	}
	public void setIntNumReq(int intNumReq) {
		this.intNumReq = intNumReq;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ReqNotaFiscalPK)) {
			return false;
		}
		ReqNotaFiscalPK castOther = (ReqNotaFiscalPK)other;
		return 
			(this.intNumNota == castOther.intNumNota)
			&& (this.intNumReq == castOther.intNumReq);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.intNumNota;
		hash = hash * prime + this.intNumReq;
		
		return hash;
    }
}