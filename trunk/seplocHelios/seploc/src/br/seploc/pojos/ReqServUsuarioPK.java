package br.seploc.pojos;

import java.io.Serializable;
import javax.persistence.*;

@Embeddable
public class ReqServUsuarioPK implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "vcrLogin", nullable = false, length = 30)
	private String vcrLogin;

	@Column(name = "intNumReq", nullable = false)
	private int intNumReq;

	public ReqServUsuarioPK() {
	}

	public ReqServUsuarioPK(String vcrLogin, int intNumReq) {
		this.vcrLogin = vcrLogin;
		this.intNumReq = intNumReq;
	}

	public String getVcrLogin() {
		return vcrLogin;
	}

	public void setVcrLogin(String vcrLogin) {
		this.vcrLogin = vcrLogin;
	}

	public int getIntNumReq() {
		return intNumReq;
	}

	public void setIntNumReq(int intNumReq) {
		this.intNumReq = intNumReq;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + intNumReq;
		result = prime * result
				+ ((vcrLogin == null) ? 0 : vcrLogin.hashCode());
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
		ReqServUsuarioPK other = (ReqServUsuarioPK) obj;
		if (intNumReq != other.intNumReq)
			return false;
		if (vcrLogin == null) {
			if (other.vcrLogin != null)
				return false;
		} else if (!vcrLogin.equals(other.vcrLogin))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ReqServUsuarioPK [intNumReq=" + intNumReq + ", "
				+ (vcrLogin != null ? "vcrLogin=" + vcrLogin : "") + "]";
	}
	
	
}