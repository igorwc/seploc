package br.seploc.pojos;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.seploc.dao.GrupoDAO;
import br.seploc.dao.MenuDAO;

@Entity
@Table(name = "tbl_grupomenu")
@SqlResultSetMapping(name = "GrupoMenu.implicit", entities = @EntityResult(entityClass = br.seploc.pojos.GrupoMenu.class))
@NamedNativeQueries( {
		@NamedNativeQuery(name = "GrupoMenu.RetornaGruposMenus", query = " SELECT * "
				+ "FROM tbl_grupomenu gm", resultSetMapping = "GrupoMenu.implicit"),
		@NamedNativeQuery(name = "GrupoMenu.RetornaPorGrupoMenusPai", query = "SELECT gm.* "
				+ "FROM tbl_grupomenu gm, tbl_menu m where gm.intMenu=m.intMenu and "
				+ "gm.intGrupo = :GRUPO and m.intMenupai = :MENU " , resultSetMapping = "GrupoMenu.implicit"),
		@NamedNativeQuery(name = "GrupoMenu.RetornaPorMenus", query = "SELECT * "
				+ "FROM tbl_grupomenu where intMenu = :MENU" , resultSetMapping = "GrupoMenu.implicit"),
		@NamedNativeQuery(name = "GrupoMenu.RetornaPorGrupo", query = "SELECT * "
				+ "FROM tbl_grupomenu where intGrupo = :GRUPO" , resultSetMapping = "GrupoMenu.implicit")
})
public class GrupoMenu {

	@EmbeddedId
	private GrupoMenuPK id = new GrupoMenuPK();

	@Column(name = "chrEscrita")
	private Character escrita;
	
	@Column(name = "chrVisivel")
	private Character visivel;

	@JoinColumn(name = "intMenu", referencedColumnName = "intMenu", updatable = false, insertable= false)
	@ManyToOne(fetch = FetchType.EAGER)
	private Menu menu;

	@JoinColumn(name = "intGrupo", referencedColumnName = "intGrupo", updatable = false, insertable= false)
	@ManyToOne(fetch = FetchType.EAGER)
	private Grupo grupo;

	public GrupoMenu() {

	}

	public GrupoMenu(GrupoMenuPK id, Character escrita, Menu menu, Grupo grupo) {
		this.id = id;
		this.escrita = escrita;
		this.menu = menu;
		this.grupo = grupo;
	}

	public GrupoMenu(Menu menu, Grupo grupo, Character escrita) {
		this.id = new GrupoMenuPK(menu.getCodMenu(), grupo.getCodGrupo());
		this.escrita = escrita;
		this.menu = menu;
		this.grupo = grupo;
	}

	public GrupoMenu(Integer codMenu, Integer codGrupo, Character escrita) {
		this.id = new GrupoMenuPK(codMenu, codGrupo);
		this.escrita = escrita;
		this.menu = new MenuDAO().recupera(codMenu);
		this.grupo = new GrupoDAO().recupera(codGrupo);
	}

	public GrupoMenuPK getId() {
		return id;
	}

	public void setId(GrupoMenuPK id) {
		this.id = id;
	}

	@Transient
	public Integer getMenuId() {
		return getId().getCodMenu();
	}

	@Transient
	public Integer getGrupoId() {
		return getId().getGrupo();
	}

	public Character getEscrita() {
		return escrita;
	}

	public void setEscrita(Character escrita) {
		this.escrita = escrita;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public Menu getMenu() {
		return menu;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public Character getVisivel() {
		return visivel;
	}

	public void setVisivel(Character visivel) {
		this.visivel = visivel;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		GrupoMenu other = (GrupoMenu) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "GrupoMenu [" + (menu != null ? "menu=" + menu : "") + "]"
				+ (grupo != null ? "grupo=" + grupo + ", " : "")
				+ (escrita != null ? "escrita=" + escrita + ", " : "");
	}

}
