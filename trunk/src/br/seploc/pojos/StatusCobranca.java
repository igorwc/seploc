package br.seploc.pojos;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.sql.Time;
import java.util.Date;


/**
 * The persistent class for the tbl_statuscobranca database table.
 * 
 */
//@Entity
//@Table(name="tbl_statuscobranca")
public class StatusCobranca implements Serializable {
	private static final long serialVersionUID = 1L;

//	@EmbeddedId
//	private StatusCobrancaPK id;
//
//    @Temporal( TemporalType.DATE)
//	@Column(name="datDataCobr")
//	private Date dataCobranca;
//
//    @Temporal( TemporalType.DATE)
//	@Column(name="datDataPag")
//	private Date dataPagamento;
//
//	@Column(name="horCobranca")
//	private Time horaCobranca;
//
//	@Version
//	@Column(name="tspVersao")
//	private Timestamp versao;
//
//	//bi-directional many-to-one association to Cobrador
//	@ManyToOne(fetch=FetchType.LAZY)
//	@JoinColumn(name="intCodCobr")
//	private Cobrador tblCobrador;
//
//	//bi-directional many-to-one association to RequisicaoServico
////	@ManyToOne(fetch=FetchType.LAZY)
////	@JoinColumn(name="intNumreq")
////	private RequisicaoServico tblReqserv;
//
//    public StatusCobranca() {
//    }
//
//	public StatusCobrancaPK getId() {
//		return this.id;
//	}
//
//	public void setId(StatusCobrancaPK id) {
//		this.id = id;
//	}
//	
//	public Date getDataCobranca() {
//		return this.dataCobranca;
//	}
//
//	public void setDataCobranca(Date dataCobranca) {
//		this.dataCobranca = dataCobranca;
//	}
//
//	public Date getDataPagamento() {
//		return this.dataPagamento;
//	}
//
//	public void setDataPagamento(Date dataPagamento) {
//		this.dataPagamento = dataPagamento;
//	}
//
//	public Time getHoraCobranca() {
//		return this.horaCobranca;
//	}
//
//	public void setHoraCobranca(Time horaCobranca) {
//		this.horaCobranca = horaCobranca;
//	}
//
//	public Timestamp getVersao() {
//		return this.versao;
//	}
//
//	public void setVersao(Timestamp versao) {
//		this.versao = versao;
//	}
//
//	public Cobrador getTblCobrador() {
//		return this.tblCobrador;
//	}
//
//	public void setTblCobrador(Cobrador tblCobrador) {
//		this.tblCobrador = tblCobrador;
//	}
//	
////	public RequisicaoServico getTblReqserv() {
////		return this.tblReqserv;
////	}
////
////	public void setTblReqserv(RequisicaoServico tblReqserv) {
////		this.tblReqserv = tblReqserv;
////	}
	
}