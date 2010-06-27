package br.seploc.pojos;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * The primary key class for the tbl_linhareq database table.
 * 
 */
@Embeddable
public class LinhaReqPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private int intNumLin;

	private int intNumreq;

    public LinhaReqPK() {
    }
	public int getIntNumLin() {
		return this.intNumLin;
	}
	public void setIntNumLin(int intNumLin) {
		this.intNumLin = intNumLin;
	}
	public int getIntNumreq() {
		return this.intNumreq;
	}
	public void setIntNumreq(int intNumreq) {
		this.intNumreq = intNumreq;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof LinhaReqPK)) {
			return false;
		}
		LinhaReqPK castOther = (LinhaReqPK)other;
		return 
			(this.intNumLin == castOther.intNumLin)
			&& (this.intNumreq == castOther.intNumreq);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.intNumLin;
		hash = hash * prime + this.intNumreq;
		
		return hash;
    }
}