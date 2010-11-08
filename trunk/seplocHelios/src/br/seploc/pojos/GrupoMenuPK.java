package br.seploc.pojos;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class GrupoMenuPK implements Serializable {

	private static final long serialVersionUID = -8283527901956056749L;

	@Column(name = "intMenu", nullable = false)
	private Integer codMenu;

	@Column(name = "intGrupo", nullable = false)
	private Integer codGrupo;

	public Integer getCodMenu() {
		return codMenu;
	}

	public void setCodMenu(Integer codMenu) {
		this.codMenu = codMenu;
	}

	public int getGrupo() {
		return codGrupo;
	}

	public void setGrupo(int grupoID) {
		this.codGrupo = grupoID;
	}

	public GrupoMenuPK() {

	}

	public GrupoMenuPK(Integer codMenu, Integer codGrupo) {
		this.codMenu = codMenu;
		this.codGrupo = codGrupo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((codGrupo == null) ? 0 : codGrupo.hashCode());
		result = prime * result + ((codMenu == null) ? 0 : codMenu.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GrupoMenuPK other = (GrupoMenuPK) obj;
		if (codGrupo == null) {
			if (other.codGrupo != null)
				return false;
		} else if (!codGrupo.equals(other.codGrupo))
			return false;
		if (codMenu == null) {
			if (other.codMenu != null)
				return false;
		} else if (!codMenu.equals(other.codMenu))
			return false;
		return true;
	}

	
	
}
