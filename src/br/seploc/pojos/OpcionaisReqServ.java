package br.seploc.pojos;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * The persistent class for the tbl_opcionaisreqserv database table.
 * 
 */
@Entity
@Table(name="tbl_opcionaisreqserv")
public class OpcionaisReqServ implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private String vcrCod;

	private double dblValorItem;

	private String vcrNomeItem;

    public OpcionaisReqServ() {
    }

	public String getVcrCod() {
		return this.vcrCod;
	}

	public void setVcrCod(String vcrCod) {
		this.vcrCod = vcrCod;
	}

	public double getDblValorItem() {
		return this.dblValorItem;
	}

	public void setDblValorItem(double dblValorItem) {
		this.dblValorItem = dblValorItem;
	}

	public String getVcrNomeItem() {
		return this.vcrNomeItem;
	}

	public void setVcrNomeItem(String vcrNomeItem) {
		this.vcrNomeItem = vcrNomeItem;
	}

}