package br.seploc.pojos;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the tbl_linhareq database table.
 * 
 */
//@Entity
//@Table(name="tbl_linhareq")
public class LinhaReq implements Serializable {
	private static final long serialVersionUID = 1L;
/*
	@EmbeddedId
	private LinhaReqPK id;

	@Column(name="dblDimensao")
	private Double dimensao;

	@Column(name="dblFormato")
	private Double formato;

	@Column(name="dblValorSubUnit")
	private Double valorSubUnit;

	@Column(name="dblValorUnit")
	private Double valorUnit;

	@Column(name="intQuant")
	private Integer quant;

	@Version
	@Column(name="tspVersao")
	private Timestamp versao;

	@Column(name="vcrImpressao")
	private String impressao;

	@Column(name="vcrNomeArq")
	private String nomeArquivo;

	//bi-directional many-to-one association to RequisicaoServico
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="intNumreq")
	private RequisicaoServico tblReqserv;

	//bi-directional many-to-one association to Papel
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="intCodPap")
	private Papel tblPapel;

    public LinhaReq() {
    }

	public LinhaReqPK getId() {
		return this.id;
	}

	public void setId(LinhaReqPK id) {
		this.id = id;
	}
	
	public Double getDimensao() {
		return this.dimensao;
	}

	public void setDimensao(Double dimensao) {
		this.dimensao = dimensao;
	}

	public Double getFormato() {
		return this.formato;
	}

	public void setFormato(Double formato) {
		this.formato = formato;
	}

	public Double getValorSubUnit() {
		return this.valorSubUnit;
	}

	public void setValorSubUnit(Double valorSubUnit) {
		this.valorSubUnit = valorSubUnit;
	}

	public Double getValorUnit() {
		return this.valorUnit;
	}

	public void setValorUnit(Double valorUnit) {
		this.valorUnit = valorUnit;
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

	public String getImpressao() {
		return this.impressao;
	}

	public void setImpressao(String impressao) {
		this.impressao = impressao;
	}

	public String getNomeArquivo() {
		return this.nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

	public RequisicaoServico getTblReqserv() {
		return this.tblReqserv;
	}

	public void setTblReqserv(RequisicaoServico tblReqserv) {
		this.tblReqserv = tblReqserv;
	}
	
	public Papel getTblPapel() {
		return this.tblPapel;
	}

	public void setTblPapel(Papel tblPapel) {
		this.tblPapel = tblPapel;
	}
*/	
}