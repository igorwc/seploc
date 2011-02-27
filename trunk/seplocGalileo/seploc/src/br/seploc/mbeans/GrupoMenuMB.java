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
	private List<Menu> listaMenu;
	private List<Grupo> listaGrupos;
	private GrupoMenuDAO grupoMenuDAO;
	private GrupoMenu grupoMenu;
	private GrupoDAO grupoDAO;
	private MenuDAO menuDAO;
	private Grupo grupo;
	private Menu menu;
	private int menuSelectionado;
	private int grupoSelecionado;
	private int subMenuSelectionado;

		
	// CONSTRUTOR
	/**
	 * Construtor da classe
	 */
	public GrupoMenuMB(){	
		setGrupoMenuDAO(new GrupoMenuDAO());
		setMenuDAO(new MenuDAO());
		setGrupoDAO(new GrupoDAO());
		grupo = new Grupo();		
		listaGrupos = grupoDAO.getLista();
		menu = new Menu();
		listaMenu = menuDAO.getListaMenuSemPai();		
				
	}

	// GETTER E SETTERS

	public List<Menu> getListaMenu() {
		return listaMenu;
	}

	public void setListaMenu(List<Menu> listaMenu) {
		this.listaMenu = listaMenu;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public int getMenuSelectionado() {
		return menuSelectionado;
	}

	public void setMenuSelectionado(int menuSelectionado) {
		this.menuSelectionado = menuSelectionado;
	}

	public int getSubMenuSelectionado() {
		return subMenuSelectionado;
	}

	public void setSubMenuSelectionado(int subMenuSelectionado) {
		this.subMenuSelectionado = subMenuSelectionado;
	}

	public int getGrupoSelecionado() {
		return grupoSelecionado;
	}

	public void setGrupoSelecionado(int grupoSelecionado) {
		this.grupoSelecionado = grupoSelecionado;
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

	public void setGrupoMenu(GrupoMenu grupoMenu) {
		this.grupoMenu = grupoMenu;
	}

	public GrupoMenu getGrupoMenu() {
		return grupoMenu;
	}

	public GrupoDAO getGrupoDAO() {
		return grupoDAO;
	}

	public void setGrupoDAO(GrupoDAO grupoDAO) {
		this.grupoDAO = grupoDAO;
	}

	@SuppressWarnings("null")
	public List<Menu> getTodosMenu(){
		List<Menu> temp = menuDAO.getListaMenuComPai();
		
		List<Menu> retorno = null;
		for (Menu m : temp){		
			if(!temp.contains(m) || !temp.isEmpty()){
				retorno.add(m);
			}
		}
		
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
	public void alteraEscrita(){
		if (grupoMenu.getEscrita() == 'S'){
			grupoMenu.setEscrita('N');
		} else {
			grupoMenu.setEscrita('S');
		}			
		addGlobalMessage("Alteracao realizado!");
	}
	
	public void alteraVisibilidade(){
		if (grupoMenu.getVisivel() == 'S') {
			grupoMenu.setVisivel('N');
		} else {
			grupoMenu.setVisivel('S');
		}
		addGlobalMessage("Alteracao realizado!");
	}
	
	public static void addGlobalMessage(String message) {
		FacesMessage facesMessage = new FacesMessage(message);
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	}	
}
