package br.seploc.mbeans;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import br.seploc.dao.GrupoDAO;
import br.seploc.dao.GrupoMenuDAO;
import br.seploc.dao.MenuDAO;
import br.seploc.pojos.Grupo;
import br.seploc.pojos.GrupoMenu;
import br.seploc.pojos.Menu;

public class GrupoMenuMB  implements Serializable {
	
	private static final long serialVersionUID = 1L;	
	private List<Menu> menuEscrita;
	private List<Menu> menuLeitura;
	private List<Grupo> listaGrupos;
	private GrupoMenuDAO grupoMenuDAO;
	private GrupoDAO grupoDAO;
	private MenuDAO menuDAO;
	private Grupo grupo;
	private Menu menu;	
		
	// CONSTRUTOR
	/**
	 * Construtor da classe
	 */
	public GrupoMenuMB(){		
		menuEscrita = null;
		menuLeitura = null;		
		setGrupomenuDAO(new GrupoMenuDAO());
		setMenuDAO(new MenuDAO());
		setGrupoDAO(new GrupoDAO());
		grupo = new Grupo();
		menu = new Menu();	
		listaGrupos = grupoDAO.getLista();
	}

	// GETTER E SETTERS
	public List<Menu> getMenuEscrita() {
		return menuEscrita;
	}

	public void setGrupoMenuEscrita(List<Menu> menuEscrita) {
		this.menuEscrita = menuEscrita;
	}

	public List<Menu> getMenuLeitura() {
		return menuLeitura;
	}

	public void setMenuLeitura(List<Menu> menuLeitura) {
		this.menuLeitura = menuLeitura;
	}

	public GrupoMenuDAO getGrupomenuDAO() {
		return grupoMenuDAO;
	}

	public void setGrupomenuDAO(GrupoMenuDAO grupomenuDAO) {
		this.grupoMenuDAO = grupomenuDAO;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}
	
	public void setMenuDAO(MenuDAO menuDAO) {
		this.menuDAO = menuDAO;
	}

	public MenuDAO getMenuDAO() {
		return menuDAO;
	}

	public GrupoMenuDAO getGrupoMenuDAO() {
		return grupoMenuDAO;
	}

	public void setGrupoMenuDAO(GrupoMenuDAO grupoMenuDAO) {
		this.grupoMenuDAO = grupoMenuDAO;
	}

	public GrupoDAO getGrupoDAO() {
		return grupoDAO;
	}

	public void setGrupoDAO(GrupoDAO grupoDAO) {
		this.grupoDAO = grupoDAO;
	}

	public List<Menu> getTodosMenu(){
		List<Menu> retorno = menuDAO.getListaMenuComPai();
		
		return retorno;
	}
	
	public List<Grupo> getTodosGrupos(){
		List<Grupo> retorno = grupoDAO.getLista();
		
		return retorno;
	}
	
	public List<Grupo> getListaGrupos() {
		return listaGrupos;
	}

	public void setListaGrupos(List<Grupo> listaGrupos) {
		this.listaGrupos = listaGrupos;
	}

	//METODOS
	public void cadastrar(){		
		try {
			//TO DO
		} catch (Exception e) {
			addGlobalMessage(e.getMessage());
		}
	}
	
	public void editar(){
		
	}
	
	public void apagar(){
		
	}
	
	public void limpar(){
		
	}
	
	public static void addGlobalMessage(String message) {
		FacesMessage facesMessage = new FacesMessage(message);
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	}	
}
