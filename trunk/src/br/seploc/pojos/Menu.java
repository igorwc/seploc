package br.seploc.pojos;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Version;

@Entity
@Table(name = "tbl_menu")
@SqlResultSetMapping(name = "Menu.implicit", entities = @EntityResult(entityClass = br.seploc.pojos.Menu.class))
@NamedNativeQueries( {
		@NamedNativeQuery(name = "Menu.RetornaMenus", query = " SELECT * "
				+ "FROM tbl_menu m", resultSetMapping = "Menu.implicit"),
		@NamedNativeQuery(name = "Menu.RetornaMenusComPai", query = "SELECT * "
					+ "FROM tbl_menu where intMenuPai is not null" , resultSetMapping = "Menu.implicit"),
		@NamedNativeQuery(name = "Menu.RetornaMenusRaizes", query = "SELECT * "
				+ "FROM tbl_menu " + "WHERE intMenupai IS NULL", resultSetMapping = "Menu.implicit")
})
public class Menu implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "menu_id", strategy = GenerationType.TABLE)
	@TableGenerator(name = "menu_id", table = "ID_GEN", allocationSize = 1, pkColumnName = "NOME_ID", valueColumnName = "VAL_ID", pkColumnValue = "MENU_GEN")
	@Column(name = "intMenu")
	private Integer codMenu;

	@Column(name = "chrHabilitado")
	private String habilitado;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "intMenupai", referencedColumnName = "intMenu")
	private Menu menuPai;

	@Column(name = "intNivelX")
	private Integer nivelX;

	@Column(name = "intNivelY")
	private Integer nivelY;

	@Version
	@Column(name = "tspVersao")
	private Timestamp versao;

	@Column(name = "vcrAcao")
	private String acao;

	@Column(name = "vcrMenu")
	private String menu;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "menu")
	private List<GrupoMenu> grupoMenus;

	@OneToMany(mappedBy = "menuPai", fetch = FetchType.LAZY)
	private List<Menu> menusFilhos;

	public Menu() {
		this.grupoMenus = new ArrayList<GrupoMenu>();
		this.menusFilhos = new ArrayList<Menu>();
	}

	public Menu(Integer codMenu, String habilitado, Menu menuPai,
			Integer nivelX, Integer nivelY, String acao, String menu) {
		this();
		this.codMenu = codMenu;
		this.habilitado = habilitado;
		this.menuPai = menuPai;
		this.nivelX = nivelX;
		this.nivelY = nivelY;
		this.acao = acao;
		this.menu = menu;
	}

	public Menu(String habilitado, Menu menuPai, Integer nivelX,
			Integer nivelY, String acao, String menu) {
		this();
		this.habilitado = habilitado;
		this.menuPai = menuPai;
		this.nivelX = nivelX;
		this.nivelY = nivelY;
		this.acao = acao;
		this.menu = menu;
	}

	public Integer getCodMenu() {
		return codMenu;
	}

	public void setCodMenu(Integer codMenu) {
		this.codMenu = codMenu;
	}

	public String getHabilitado() {
		return habilitado;
	}

	public void setHabilitado(String habilitado) {
		this.habilitado = habilitado;
	}

	public Menu getMenuPai() {
		return menuPai;
	}

	public void setMenuPai(Menu menuPai) {
		this.menuPai = menuPai;
	}

	public Integer getNivelX() {
		return nivelX;
	}

	public void setNivelX(Integer nivelX) {
		this.nivelX = nivelX;
	}

	public Integer getNivelY() {
		return nivelY;
	}

	public void setNivelY(Integer nivelY) {
		this.nivelY = nivelY;
	}

	public Timestamp getVersao() {
		return versao;
	}

	public void setVersao(Timestamp versao) {
		this.versao = versao;
	}

	public String getAcao() {
		return acao;
	}

	public void setAcao(String acao) {
		this.acao = acao;
	}

	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}

	public List<GrupoMenu> getGrupoMenus() {
		return grupoMenus;
	}

	public void setGrupoMenus(List<GrupoMenu> grupoMenus) {
		this.grupoMenus = grupoMenus;
	}

	public List<Menu> getMenusFilhos() {
		return menusFilhos;
	}

	public void setMenusFilhos(List<Menu> menusFilhos) {
		this.menusFilhos = menusFilhos;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codMenu == null) ? 0 : codMenu.hashCode());
		result = prime * result + ((versao == null) ? 0 : versao.hashCode());
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
		Menu other = (Menu) obj;
		if (codMenu == null) {
			if (other.codMenu != null)
				return false;
		} else if (!codMenu.equals(other.codMenu))
			return false;
		if (versao == null) {
			if (other.versao != null)
				return false;
		} else if (!versao.equals(other.versao))
			return false;
		return true;
	}

	@Override
	public String toString() {

		return "[ " + (codMenu != null ? " " + codMenu + ", " : "")
				+ (menu != null ? " " + menu + " ]" : "");
	}

	public String toStringComFilhos() {
		String retorno;
		if (this.getMenusFilhos().size() == 0)
			return "[ " + (codMenu != null ? " " + codMenu + ", " : "")
					+ (menu != null ? " " + menu + " ]" : "");
		else {
			retorno = "[ " + (codMenu != null ? " " + codMenu + ", " : "")
					+ (menu != null ? " " + menu + " ]" : "");
			for (Menu item : this.getMenusFilhos()) {
				retorno += "\n\t" + item;
			}
			return retorno;
		}
	}
}
