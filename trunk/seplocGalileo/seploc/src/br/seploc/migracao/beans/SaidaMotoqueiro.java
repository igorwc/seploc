package br.seploc.migracao.beans;

import java.sql.Time;
import java.sql.Date;

public class SaidaMotoqueiro {
	private Integer intNumSaida;
	private Integer intCodCobr;
	private Integer intNumReq;
	private Date datDataCobr;
	private Date datDataPag;
	private Time horCobranca;
	private Time horPagamen;
	private String vcrCliente;
	private String vcrObs;

	public SaidaMotoqueiro() {
	}

	public SaidaMotoqueiro(Integer intNumSaida, Integer intCodCobr,
			Integer intNumReq, Date datDataCobr, Date datDataPag,
			Time horCobranca, Time horPagamen, String vcrCliente, String vcrObs) {
		this.intNumSaida = intNumSaida;
		this.intCodCobr = intCodCobr;
		this.intNumReq = intNumReq;
		this.datDataCobr = datDataCobr;
		this.datDataPag = datDataPag;
		this.horCobranca = horCobranca;
		this.horPagamen = horPagamen;
		this.vcrCliente = vcrCliente;
		this.vcrObs = vcrObs;
	}

	public Integer getIntNumSaida() {
		return intNumSaida;
	}

	public void setIntNumSaida(Integer intNumSaida) {
		this.intNumSaida = intNumSaida;
	}

	public Integer getIntCodCobr() {
		return intCodCobr;
	}

	public void setIntCodCobr(Integer intCodCobr) {
		this.intCodCobr = intCodCobr;
	}

	public Integer getIntNumReq() {
		return intNumReq;
	}

	public void setIntNumReq(Integer intNumReq) {
		this.intNumReq = intNumReq;
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

	public Time getHorPagamen() {
		return horPagamen;
	}

	public void setHorPagamen(Time horPagamen) {
		this.horPagamen = horPagamen;
	}

	public String getVcrCliente() {
		return vcrCliente;
	}

	public void setVcrCliente(String vcrCliente) {
		this.vcrCliente = vcrCliente;
	}

	public String getVcrObs() {
		return vcrObs;
	}

	public void setVcrObs(String vcrObs) {
		this.vcrObs = vcrObs;
	}

}
