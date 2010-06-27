package br.seploc.pojos;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * The primary key class for the tbl_reqservusuario database table.
 * 
 */
@Embeddable
public class ReqServUsuarioPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private String vcrLogin;

	private int intNumReq;

    public ReqServUsuarioPK() {
    }
	public String getVcrLogin() {
		return this.vcrLogin;
	}
	public void setVcrLogin(String vcrLogin) {
		this.vcrLogin = vcrLogin;
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
		if (!(other instanceof ReqServUsuarioPK)) {
			return false;
		}
		ReqServUsuarioPK castOther = (ReqServUsuarioPK)other;
		return 
			this.vcrLogin.equals(castOther.vcrLogin)
			&& (this.intNumReq == castOther.intNumReq);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.vcrLogin.hashCode();
		hash = hash * prime + this.intNumReq;
		
		return hash;
    }
}