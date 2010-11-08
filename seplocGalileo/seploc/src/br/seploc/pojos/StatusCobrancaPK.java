package br.seploc.pojos;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class StatusCobrancaPK implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "intCodCobr", nullable = false)
	private Integer intCodCobr;

	@Column(name = "intNumreq", nullable = false)
	private Integer intNumreq;

	public StatusCobrancaPK() {
	}

	public StatusCobrancaPK(Integer intCodCobr, Integer intNumreq) {
		this.intCodCobr = intCodCobr;
		this.intNumreq = intNumreq;
	}

	public Integer getIntCodCobr() {
		return intCodCobr;
	}

	public void setIntCodCobr(Integer intCodCobr) {
		this.intCodCobr = intCodCobr;
	}

	public Integer getIntNumreq() {
		return intNumreq;
	}

	public void setIntNumreq(Integer intNumreq) {
		this.intNumreq = intNumreq;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + intCodCobr;
		result = prime * result + intNumreq;
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
		StatusCobrancaPK other = (StatusCobrancaPK) obj;
		if (intCodCobr != other.intCodCobr)
			return false;
		if (intNumreq != other.intNumreq)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "StatusCobrancaPK [intCodCobr=" + intCodCobr + ", intNumreq="
				+ intNumreq + "]";
	}


}