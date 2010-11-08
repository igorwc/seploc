package br.seploc.controllers;

import java.io.Serializable;

import javax.faces.context.FacesContext;

import br.seploc.mbeans.GrupoMB;
import br.seploc.mbeans.GrupoMenuMB;

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
}
