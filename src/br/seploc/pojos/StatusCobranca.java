package br.seploc.pojos;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the tbl_statuscobranca database table.
 * 
 */
@Entity
@Table(name="tbl_statuscobranca")
public class StatusCobranca implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private StatusCobrancaPK id;

    @Temporal( TemporalType.DATE)
	private Date datDataCobr;

    @Temporal( TemporalType.DATE)
	private Date datDataPag;

	private Time horCobranca;

    public StatusCobranca() {
    }

	public StatusCobrancaPK getId() {
		return this.id;
	}

	public void setId(StatusCobrancaPK id) {
		this.id = id;
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

}