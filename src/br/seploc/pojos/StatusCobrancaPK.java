package br.seploc.pojos;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the tbl_statuscobranca database table.
 * 
 */
@Embeddable
public class StatusCobrancaPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private int intCodCobr;

	private int intNumreq;

    public StatusCobrancaPK() {
    }
	public int getIntCodCobr() {
		return this.intCodCobr;
	}
	public void setIntCodCobr(int intCodCobr) {
		this.intCodCobr = intCodCobr;
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
		if (!(other instanceof StatusCobrancaPK)) {
			return false;
		}
		StatusCobrancaPK castOther = (StatusCobrancaPK)other;
		return 
			(this.intCodCobr == castOther.intCodCobr)
			&& (this.intNumreq == castOther.intNumreq);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.intCodCobr;
		hash = hash * prime + this.intNumreq;
		
		return hash;
    }
}