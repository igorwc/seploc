package br.seploc.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;

import br.seploc.mbeans.GrupoMenuMB;
import br.seploc.pojos.Grupo;
import br.seploc.pojos.GrupoMenu;
import br.seploc.pojos.Menu;

public class GrupoMenuCB implements Serializable {
	private static final long serialVersionUID = 1L;
	private GrupoMenuMB grupoMenuMB;

	// CONSTRUTOR
	public GrupoMenuCB(){
		setGrupoMenuMB(loadGrupoMB());
	}

	// GETTERS e SETTERS
	public void setGrupoMenuMB(GrupoMenuMB grupoMenuMB) {
		this.grupoMenuMB = grupoMenuMB;
	}

	public GrupoMenuMB getGrupoMenuMB() {
		return grupoMenuMB;
	}
	
	public GrupoMenuMB loadGrupoMB(){
		FacesContext context = FacesContext.getCurrentInstance();
		GrupoMenuMB grupoMenuMB = (GrupoMenuMB) context.getApplication()
            .evaluateExpressionGet(context, "#{grupoMenuMB}", GrupoMenuMB.class);
		return grupoMenuMB;
	}
	
	public List<GrupoMenu> getGridGrupoMenu(){
		List<GrupoMenu> lista = new ArrayList<GrupoMenu>();
		
		if (grupoMenuMB.getGrupoSelecionado() > 0 && grupoMenuMB.getMenuSelectionado() > 0){
			
			Grupo grupo = grupoMenuMB.getGrupoDAO().recupera(grupoMenuMB.getGrupoSelecionado());
			Menu menu = grupoMenuMB.getMenuDAO().recupera(grupoMenuMB.getMenuSelectionado());
			
			lista = grupoMenuMB.getGrupoMenuDAO().getFilterByGrupoMenu(grupo, menu);
			
		}
		return lista;
	}
}
