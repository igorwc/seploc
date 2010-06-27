package br.seploc.pojos;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * The persistent class for the tbl_grupoacesso database table.
 * 
 */
@Entity
@Table(name="tbl_grupoacesso")
public class Grupoacesso implements Serializable{
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private GrupoacessoPK id;

    public Grupoacesso() {
    }

	public GrupoacessoPK getId() {
		return this.id;
	}

	public void setId(GrupoacessoPK id) {
		this.id = id;
	}
	
}