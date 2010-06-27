package br.seploc.pojos;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the tbl_reqservusuario database table.
 * 
 */
@Entity
@Table(name="tbl_reqservusuario")
public class ReqServUsuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ReqServUsuarioPK id;

    @Temporal( TemporalType.DATE)
	private Date datData;

    @Temporal( TemporalType.DATE)
	private Date datdataAlter;

	private String vcrLoginAlter;

    public ReqServUsuario() {
    }

	public ReqServUsuarioPK getId() {
		return this.id;
	}

	public void setId(ReqServUsuarioPK id) {
		this.id = id;
	}
	
	public Date getDatData() {
		return this.datData;
	}

	public void setDatData(Date datData) {
		this.datData = datData;
	}

	public Date getDatdataAlter() {
		return this.datdataAlter;
	}

	public void setDatdataAlter(Date datdataAlter) {
		this.datdataAlter = datdataAlter;
	}

	public String getVcrLoginAlter() {
		return this.vcrLoginAlter;
	}

	public void setVcrLoginAlter(String vcrLoginAlter) {
		this.vcrLoginAlter = vcrLoginAlter;
	}

}