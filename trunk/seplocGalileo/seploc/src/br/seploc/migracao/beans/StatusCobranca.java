package br.seploc.migracao.beans;

import java.sql.Date;
import java.sql.Time;

public class StatusCobranca {
	private Integer numReq;
	private Integer codCobrador;
	private Date datDataCobr;
	private Date datDataPag;
	private Time horCobranca;

	public StatusCobranca() {
	}

	public StatusCobranca(Integer numReq, Integer codCobrador,
			Date datDataCobr, Date datDataPag, Time horCobranca) {
		super();
		this.numReq = numReq;
		this.codCobrador = codCobrador;
		this.datDataCobr = datDataCobr;
		this.datDataPag = datDataPag;
		this.horCobranca = horCobranca;
	}

	public Integer getNumReq() {
		return numReq;
	}

	public void setNumReq(Integer numReq) {
		this.numReq = numReq;
	}

	public Integer getCodCobrador() {
		return codCobrador;
	}

	public void setCodCobrador(Integer codCobrador) {
		this.codCobrador = codCobrador;
	}

	public Date getDatDataCobr() {
		return datDataCobr;
	}

	public void setDatDataCobr(Date datDataCobr) {
		this.datDataCobr = datDataCobr;
	}

	public Date getDatDataPag() {
		return datDataPag;
	}

	public void setDatDataPag(Date datDataPag) {
		this.datDataPag = datDataPag;
	}

	public Time getHorCobranca() {
		return horCobranca;
	}

	public void setHorCobranca(Time horCobranca) {
		this.horCobranca = horCobranca;
	}

}
