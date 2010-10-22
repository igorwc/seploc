package br.seploc.pojos;

import java.io.Serializable;
import javax.persistence.*;

@Embeddable
public class ReqServicosOpcionaisPK implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Column(name = "intNumReq", nullable = false)
	private Integer intNumReq;

	@Column(name = "intCodOp", nullable = false)
	private Integer intCodOp;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "intNumReq", referencedColumnName = "intNumReq", updatable = false, insertable = false)
	private RequisicaoServico reqServico;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "intCodOp", referencedColumnName = "intCod", updatable = false, insertable = false)
	private OpcionaisReqServ opcionaisReqServ;	
	
	public ReqServicosOpcionaisPK() {
	}

	public ReqServicosOpcionaisPK(Integer intNumReq, Integer intCodOp) {
		this.intNumReq = intNumReq;
		this.intCodOp = intCodOp;
	}

	public Integer getIntNumReq() {
		return intNumReq;
	}

	public void setIntNumReq(Integer intNumReq) {
		this.intNumReq = intNumReq;
	}

	public Integer getIntCodOp() {
		return intCodOp;
	}

	public void setIntCodOp(Integer intCodOp) {
		this.intCodOp = intCodOp;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setOpcionaisReqServ(OpcionaisReqServ opcionaisReqServ) {
		this.opcionaisReqServ = opcionaisReqServ;
	}

	public OpcionaisReqServ getOpcionaisReqServ() {
		return opcionaisReqServ;
	}

	public void setReqServico(RequisicaoServico reqServico) {
		this.reqServico = reqServico;
	}

	public RequisicaoServico getReqServico() {
		return reqServico;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((intCodOp == null) ? 0 : intCodOp.hashCode());
		result = prime * result
				+ ((intNumReq == null) ? 0 : intNumReq.hashCode());
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
		ReqServicosOpcionaisPK other = (ReqServicosOpcionaisPK) obj;
		if (intCodOp == null) {
			if (other.intCodOp != null)
				return false;
		} else if (!intCodOp.equals(other.intCodOp))
			return false;
		if (intNumReq == null) {
			if (other.intNumReq != null)
				return false;
		} else if (!intNumReq.equals(other.intNumReq))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ReqServicosOpcionaisPK ["
				+ (intCodOp != null ? "intCodOp=" + intCodOp + ", " : "")
				+ (intNumReq != null ? "intNumReq=" + intNumReq : "") + "]";
	}

	
}