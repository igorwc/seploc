package br.seploc.controllers;

import java.io.Serializable;
import java.util.List;
import javax.faces.context.FacesContext;

import br.seploc.mbeans.ParametroProgMB;
import br.seploc.pojos.ParametroProg;

public class ParametroProgCB implements Serializable {
	private static final long serialVersionUID = 1L;
	private ParametroProgMB parametroMB;
	
	public ParametroProgCB(){
		this.setParametroMB(loadParametroMB());
	}
	
	public ParametroProgMB loadParametroMB(){
		FacesContext context = FacesContext.getCurrentInstance();
		ParametroProgMB parametroMB = (ParametroProgMB) context.getApplication()
            .evaluateExpressionGet(context, "#{parametroMB}", ParametroProgMB.class);
		return parametroMB;
	}
	
	public List<ParametroProg> getLista(){
		return parametroMB.getLista();
	}

	// GETTERS AND SETTERS
	public void setParametroMB(ParametroProgMB parametroMB) {
		this.parametroMB = parametroMB;
	}

	public ParametroProgMB getParametroMB() {
		return parametroMB;
	}		
}
