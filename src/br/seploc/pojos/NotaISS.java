package br.seploc.pojos;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * The persistent class for the tbl_notaiss database table.
 * 
 */
@Entity
@Table(name="tbl_notaiss")
public class NotaISS implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private int intNumNota;

	private int intValorIss;

    public NotaISS() {
    }

	public int getIntNumNota() {
		return this.intNumNota;
	}

	public void setIntNumNota(int intNumNota) {
		this.intNumNota = intNumNota;
	}

	public int getIntValorIss() {
		return this.intValorIss;
	}

	public void setIntValorIss(int intValorIss) {
		this.intValorIss = intValorIss;
	}

}