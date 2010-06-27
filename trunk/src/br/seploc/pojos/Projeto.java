package br.seploc.pojos;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * The persistent class for the tbl_projetos database table.
 * 
 */
@Entity
@Table(name="tbl_projetos")
public class Projeto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private int intCodProj;

	private String vcrCnpj;

	private String vcrProjeto;

    public Projeto() {
    }

	public int getIntCodProj() {
		return this.intCodProj;
	}

	public void setIntCodProj(int intCodProj) {
		this.intCodProj = intCodProj;
	}

	public String getVcrCnpj() {
		return this.vcrCnpj;
	}

	public void setVcrCnpj(String vcrCnpj) {
		this.vcrCnpj = vcrCnpj;
	}

	public String getVcrProjeto() {
		return this.vcrProjeto;
	}

	public void setVcrProjeto(String vcrProjeto) {
		this.vcrProjeto = vcrProjeto;
	}

}