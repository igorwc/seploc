package br.seploc.pojos;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the tbl_saidamotoqueiro database table.
 * 
 */
@Entity
@Table(name="tbl_saidamotoqueiro")
public class SaidaMotoqueiro implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private int intNumSaida;

    @Temporal( TemporalType.DATE)
	private Date datDataCobr;

    @Temporal( TemporalType.DATE)
	private Date datDataPag;

	private Time horCobranca;

	private Time horPagamen;

	private int intCodCobr;

	private int intNumReq;

	private String vcrCliente;

	private String vcrObs;

    public SaidaMotoqueiro() {
    }

	public int getIntNumSaida() {
		return this.intNumSaida;
	}

	public void setIntNumSaida(int intNumSaida) {
		this.intNumSaida = intNumSaida;
	}

	public Date getDatDataCobr() {
		return this.datDataCobr;
	}

	public void setDatDataCobr(Date datDataCobr) {
		this.datDataCobr = datDataCobr;
	}

	public Date getDatDataPag() {
		return this.datDataPag;
	}

	public void setDatDataPag(Date datDataPag) {
		this.datDataPag = datDataPag;
	}

	public Time getHorCobranca() {
		return this.horCobranca;
	}

	public void setHorCobranca(Time horCobranca) {
		this.horCobranca = horCobranca;
	}

	public Time getHorPagamen() {
		return this.horPagamen;
	}

	public void setHorPagamen(Time horPagamen) {
		this.horPagamen = horPagamen;
	}

	public int getIntCodCobr() {
		return this.intCodCobr;
	}

	public void setIntCodCobr(int intCodCobr) {
		this.intCodCobr = intCodCobr;
	}

	public int getIntNumReq() {
		return this.intNumReq;
	}

	public void setIntNumReq(int intNumReq) {
		this.intNumReq = intNumReq;
	}

	public String getVcrCliente() {
		return this.vcrCliente;
	}

	public void setVcrCliente(String vcrCliente) {
		this.vcrCliente = vcrCliente;
	}

	public String getVcrObs() {
		return this.vcrObs;
	}

	public void setVcrObs(String vcrObs) {
		this.vcrObs = vcrObs;
	}

}