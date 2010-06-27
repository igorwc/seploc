package br.seploc.pojos;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * The persistent class for the tbl_parametroprog database table.
 * 
 */
@Entity
@Table(name="tbl_parametroprog")
public class ParametroProg implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private String vcrCodParametro;

	private String vcrDescricao;

	private String vcrValor;

    public ParametroProg() {
    }

	public String getVcrCodParametro() {
		return this.vcrCodParametro;
	}

	public void setVcrCodParametro(String vcrCodParametro) {
		this.vcrCodParametro = vcrCodParametro;
	}

	public String getVcrDescricao() {
		return this.vcrDescricao;
	}

	public void setVcrDescricao(String vcrDescricao) {
		this.vcrDescricao = vcrDescricao;
	}

	public String getVcrValor() {
		return this.vcrValor;
	}

	public void setVcrValor(String vcrValor) {
		this.vcrValor = vcrValor;
	}

}