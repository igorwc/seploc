package br.seploc.pojos;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * The primary key class for the tbl_grupoacesso database table.
 * 
 */
@Embeddable
public class GrupoacessoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private int intGrupoAcesso;

	private String vcrGrupoMenu;

    public GrupoacessoPK() {
    }
	public int getIntGrupoAcesso() {
		return this.intGrupoAcesso;
	}
	public void setIntGrupoAcesso(int intGrupoAcesso) {
		this.intGrupoAcesso = intGrupoAcesso;
	}
	public String getVcrGrupoMenu() {
		return this.vcrGrupoMenu;
	}
	public void setVcrGrupoMenu(String vcrGrupoMenu) {
		this.vcrGrupoMenu = vcrGrupoMenu;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof GrupoacessoPK)) {
			return false;
		}
		GrupoacessoPK castOther = (GrupoacessoPK)other;
		return 
			(this.intGrupoAcesso == castOther.intGrupoAcesso)
			&& this.vcrGrupoMenu.equals(castOther.vcrGrupoMenu);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.intGrupoAcesso;
		hash = hash * prime + this.vcrGrupoMenu.hashCode();
		
		return hash;
    }
}