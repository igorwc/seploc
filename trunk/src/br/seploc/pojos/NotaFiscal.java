package br.seploc.pojos;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the tbl_notafiscal database table.
 * 
 */
@Entity
@Table(name="tbl_notafiscal")
public class NotaFiscal implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private int intNumNota;

    @Temporal( TemporalType.DATE)
	private Date datData;

	private double dblSubValor;

	private double dblValor;

	private int intCodEnt;

	private String vcrCnpj;

	private String vcrDescricao;

	private String vcrObservacao;

    public NotaFiscal() {
    }

	public int getIntNumNota() {
		return this.intNumNota;
	}

	public void setIntNumNota(int intNumNota) {
		this.intNumNota = intNumNota;
	}

	public Date getDatData() {
		return this.datData;
	}

	public void setDatData(Date datData) {
		this.datData = datData;
	}

	public double getDblSubValor() {
		return this.dblSubValor;
	}

	public void setDblSubValor(double dblSubValor) {
		this.dblSubValor = dblSubValor;
	}

	public double getDblValor() {
		return this.dblValor;
	}

	public void setDblValor(double dblValor) {
		this.dblValor = dblValor;
	}

	public int getIntCodEnt() {
		return this.intCodEnt;
	}

	public void setIntCodEnt(int intCodEnt) {
		this.intCodEnt = intCodEnt;
	}

	public String getVcrCnpj() {
		return this.vcrCnpj;
	}

	public void setVcrCnpj(String vcrCnpj) {
		this.vcrCnpj = vcrCnpj;
	}

	public String getVcrDescricao() {
		return this.vcrDescricao;
	}

	public void setVcrDescricao(String vcrDescricao) {
		this.vcrDescricao = vcrDescricao;
	}

	public String getVcrObservacao() {
		return this.vcrObservacao;
	}

	public void setVcrObservacao(String vcrObservacao) {
		this.vcrObservacao = vcrObservacao;
	}

}