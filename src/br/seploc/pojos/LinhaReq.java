package br.seploc.pojos;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * The persistent class for the tbl_linhareq database table.
 * 
 */
@Entity
@Table(name="tbl_linhareq")
public class LinhaReq implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private LinhaReqPK id;

	private double dblDimensao;

	private double dblFormato;

	private double dblValorSubUnit;

	private double dblValorUnit;

	private int intCodPap;

	private int intQuant;

	private String vcrImpressao;

	private String vcrNomeArq;

    public LinhaReq() {
    }

	public LinhaReqPK getId() {
		return this.id;
	}

	public void setId(LinhaReqPK id) {
		this.id = id;
	}
	
	public double getDblDimensao() {
		return this.dblDimensao;
	}

	public void setDblDimensao(double dblDimensao) {
		this.dblDimensao = dblDimensao;
	}

	public double getDblFormato() {
		return this.dblFormato;
	}

	public void setDblFormato(double dblFormato) {
		this.dblFormato = dblFormato;
	}

	public double getDblValorSubUnit() {
		return this.dblValorSubUnit;
	}

	public void setDblValorSubUnit(double dblValorSubUnit) {
		this.dblValorSubUnit = dblValorSubUnit;
	}

	public double getDblValorUnit() {
		return this.dblValorUnit;
	}

	public void setDblValorUnit(double dblValorUnit) {
		this.dblValorUnit = dblValorUnit;
	}

	public int getIntCodPap() {
		return this.intCodPap;
	}

	public void setIntCodPap(int intCodPap) {
		this.intCodPap = intCodPap;
	}

	public int getIntQuant() {
		return this.intQuant;
	}

	public void setIntQuant(int intQuant) {
		this.intQuant = intQuant;
	}

	public String getVcrImpressao() {
		return this.vcrImpressao;
	}

	public void setVcrImpressao(String vcrImpressao) {
		this.vcrImpressao = vcrImpressao;
	}

	public String getVcrNomeArq() {
		return this.vcrNomeArq;
	}

	public void setVcrNomeArq(String vcrNomeArq) {
		this.vcrNomeArq = vcrNomeArq;
	}

}