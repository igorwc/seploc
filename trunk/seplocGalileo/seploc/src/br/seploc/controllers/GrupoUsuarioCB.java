package br.seploc.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;

import br.seploc.mbeans.GrupoMenuMB;
import br.seploc.mbeans.GrupoUsuarioMB;
import br.seploc.pojos.GrupoMenu;
import br.seploc.pojos.Usuario;

public class GrupoUsuarioCB implements Serializable {
	private static final long serialVersionUID = 1L;
	private GrupoUsuarioMB grupoUsuarioMB;
	
	public GrupoUsuarioCB() {
		setGrupoUsuarioMB(this.loadGrupoUsuarioMB());
	}
	
	public GrupoUsuarioMB loadGrupoUsuarioMB(){
		FacesContext context = FacesContext.getCurrentInstance();
		GrupoUsuarioMB grupoUsuarioMB = (GrupoUsuarioMB) context.getApplication()
            .evaluateExpressionGet(context, "#{grupoUsuarioMB}", GrupoUsuarioMB.class);
		return grupoUsuarioMB;
	}	

	public GrupoUsuarioMB getGrupoUsuarioMB() {
		return grupoUsuarioMB;
	}

	public void setGrupoUsuarioMB(GrupoUsuarioMB grupoUsuarioMB) {
		this.grupoUsuarioMB = grupoUsuarioMB;
	}
	
	public List<Usuario> getGridGrupoUsuario(){
		List<Usuario> lista = new ArrayList<Usuario>();
		
		if (grupoUsuarioMB.getGrupoSelecionado() > 0) {			
			lista = grupoUsuarioMB.getUsuarioDAO().getListaUsuariosPorGrupo(grupoUsuarioMB.getGrupoSelecionado());
		}
		
		return lista;
	}

}
