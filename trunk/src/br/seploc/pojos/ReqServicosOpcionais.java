package br.seploc.pojos;

import java.io.Serializable;
import javax.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "tbl_reqservopcionais")
public class ReqServicosOpcionais implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ReqServicosOpcionaisPK id;

	@Column(name = "intQuant")
	private Integer quantidade;

	@Version
	@Column(name = "tspVersao")
	private Timestamp versao;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "intNumReq", referencedColumnName = "intNumReq", updatable = false, insertable = false)
	private RequisicaoServico reqServico;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "intCodOp", referencedColumnName = "intCod", updatable = false, insertable = false)
	private OpcionaisReqServ opcionaisReqServ;

	public ReqServicosOpcionais() {
		
	}


	public ReqServicosOpcionais(ReqServicosOpcionaisPK id) {
		this.id = id;
	}


	public ReqServicosOpcionais(ReqServicosOpcionaisPK id, Integer quantidade,
			RequisicaoServico reqServico, OpcionaisReqServ opcionaisReqServ) {
		this.id = id;
		this.quantidade = quantidade;
		this.reqServico = reqServico;
		this.opcionaisReqServ = opcionaisReqServ;
	}

	public ReqServicosOpcionaisPK getId() {
		return id;
	}

	public void setId(ReqServicosOpcionaisPK id) {
		this.id = id;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Timestamp getVersao() {
		return versao;
	}

	public void setVersao(Timestamp versao) {
		this.versao = versao;
	}

	public RequisicaoServico getReqServico() {
		return reqServico;
	}

	public void setReqServico(RequisicaoServico reqServico) {
		this.reqServico = reqServico;
	}

	public OpcionaisReqServ getOpcionaisReqServ() {
		return opcionaisReqServ;
	}

	public void setOpcionaisReqServ(OpcionaisReqServ opcionaisReqServ) {
		this.opcionaisReqServ = opcionaisReqServ;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		ReqServicosOpcionais other = (ReqServicosOpcionais) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "ReqServicosOpcionais [" + (id != null ? "id=" + id : "") + "]";
	}

}