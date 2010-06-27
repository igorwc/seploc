package br.seploc.pojos;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * The primary key class for the tbl_reqservopcionais database table.
 * 
 */
@Embeddable
public class ReqServOpcionaisPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private String intNumReq;

	private int intCodOp;

    public ReqServOpcionaisPK() {
    }
	public String getIntNumReq() {
		return this.intNumReq;
	}
	public void setIntNumReq(String intNumReq) {
		this.intNumReq = intNumReq;
	}
	public int getIntCodOp() {
		return this.intCodOp;
	}
	public void setIntCodOp(int intCodOp) {
		this.intCodOp = intCodOp;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ReqServOpcionaisPK)) {
			return false;
		}
		ReqServOpcionaisPK castOther = (ReqServOpcionaisPK)other;
		return 
			this.intNumReq.equals(castOther.intNumReq)
			&& (this.intCodOp == castOther.intCodOp);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.intNumReq.hashCode();
		hash = hash * prime + this.intCodOp;
		
		return hash;
    }
}