package br.seploc.pojos;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the tbl_reqservopcionais database table.
 * 
 */
//@Entity
//@Table(name="tbl_reqservopcionais")
public class ReqServicosOpcionais implements Serializable{
	private static final long serialVersionUID = 1L;
/*
	@EmbeddedId
	private ReqServicosOpcionaisPK id;

	@Column(name="intQuant")
	private Integer quant;

	@Version
	@Column(name="tspVersao")
	private Timestamp versao;

	//bi-directional many-to-one association to RequisicaoServico
//	@ManyToOne(fetch=FetchType.LAZY)
//	@JoinColumn(name="intNumReq")
//	private RequisicaoServico tblReqserv;

	//bi-directional many-to-one association to OpcionaisReqServ
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="vcrCod")
	private OpcionaisReqServ tblOpcionaisreqserv;

    public ReqServicosOpcionais() {
    }

	public ReqServicosOpcionaisPK getId() {
		return this.id;
	}

	public void setId(ReqServicosOpcionaisPK id) {
		this.id = id;
	}
	
	public Integer getQuant() {
		return this.quant;
	}

	public void setQuant(Integer quant) {
		this.quant = quant;
	}

	public Timestamp getVersao() {
		return this.versao;
	}

	public void setVersao(Timestamp versao) {
		this.versao = versao;
	}

//	public RequisicaoServico getTblReqserv() {
//		return this.tblReqserv;
//	}
//
//	public void setTblReqserv(RequisicaoServico tblReqserv) {
//		this.tblReqserv = tblReqserv;
//	}
	
	public OpcionaisReqServ getTblOpcionaisreqserv() {
		return this.tblOpcionaisreqserv;
	}

	public void setTblOpcionaisreqserv(OpcionaisReqServ tblOpcionaisreqserv) {
		this.tblOpcionaisreqserv = tblOpcionaisreqserv;
	}
*/	
}