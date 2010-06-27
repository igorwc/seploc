package br.seploc.pojos;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * The persistent class for the tbl_reqservopcionais database table.
 * 
 */
@Entity
@Table(name="tbl_reqservopcionais")
public class ReqServOpcionais implements Serializable{
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ReqServOpcionaisPK id;

	private int intQuant;

	private String vcrCod;

    public ReqServOpcionais() {
    }

	public ReqServOpcionaisPK getId() {
		return this.id;
	}

	public void setId(ReqServOpcionaisPK id) {
		this.id = id;
	}
	
	public int getIntQuant() {
		return this.intQuant;
	}

	public void setIntQuant(int intQuant) {
		this.intQuant = intQuant;
	}

	public String getVcrCod() {
		return this.vcrCod;
	}

	public void setVcrCod(String vcrCod) {
		this.vcrCod = vcrCod;
	}

}