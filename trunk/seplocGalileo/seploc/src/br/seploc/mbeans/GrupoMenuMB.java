package br.seploc.mbeans;

import java.io.Serializable;
import java.util.ArrayList;
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
	private List<Menu> menuAuxiliar;
	private List<Menu> menuEscrita;
	private List<Menu> menuLeitura;
	private List<Grupo> listaGrupos;
	private List<String> comboEscrita;
	private String escrita;
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
		escrita = "Não";
		setGrupomenuDAO(new GrupoMenuDAO());
		setMenuDAO(new MenuDAO());
		setGrupoDAO(new GrupoDAO());
		grupo = new Grupo();		
		listaGrupos = grupoDAO.getLista();
		menuAuxiliar = null;
		comboEscrita = new ArrayList<String>();
		comboEscrita.add("Não");
		comboEscrita.add("Sim");		
	}

	// GETTER E SETTERS
	public List<Menu> getMenuEscrita() {
		menuEscrita = null;
		List<GrupoMenu> lgm = this.grupoMenuDAO.getFilterByGrupo(this.grupo);
		for (GrupoMenu gm : lgm){
			menuEscrita.add(gm.getMenu());
		}
		return menuEscrita;
	}

	public void setGrupoMenuEscrita(List<Menu> menuEscrita) {
		this.menuEscrita = menuEscrita;
	}

	public List<Menu> getMenuLeitura() {
		menuLeitura = null;
		List<GrupoMenu> lgm = this.grupoMenuDAO.getFilterByGrupo(this.grupo);
		for (GrupoMenu gm : lgm){
			menuLeitura.add(gm.getMenu());
		}
		return menuLeitura;
	}

	public void setMenuLeitura(List<Menu> menuLeitura) {		
		this.menuLeitura = menuLeitura;
	}

	public List<Menu> getMenuAuxiliar() {
		return menuAuxiliar;
	}

	public void setMenuAuxiliar(List<Menu> menuAuxiliar) {
		this.menuAuxiliar = menuAuxiliar;
	}

	public void setMenuEscrita(List<Menu> menuEscrita) {
		this.menuEscrita = menuEscrita;
	}

	public List<String> getComboEscrita() {
		return comboEscrita;
	}

	public void setComboEscrita(List<String> comboEscrita) {
		this.comboEscrita = comboEscrita;
	}

	public String getEscrita() {
		return escrita;
	}

	public void setEscrita(String escrita) {
		this.escrita = escrita;
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
