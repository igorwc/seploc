package br.seploc.pojos;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the tbl_reqservopcionais database table.
 * 
 */
//@Embeddable
public class ReqServicosOpcionaisPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

/*	private int intNumReq;

	private int intCodOp;

    public ReqServicosOpcionaisPK() {
    }
	public int getIntNumReq() {
		return this.intNumReq;
	}
	public void setIntNumReq(int intNumReq) {
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
		if (!(other instanceof ReqServicosOpcionaisPK)) {
			return false;
		}
		ReqServicosOpcionaisPK castOther = (ReqServicosOpcionaisPK)other;
		return 
			(this.intNumReq == castOther.intNumReq)
			&& (this.intCodOp == castOther.intCodOp);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.intNumReq;
		hash = hash * prime + this.intCodOp;
		
		return hash;
    }*/
}