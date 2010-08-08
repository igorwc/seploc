package br.seploc.mbeans;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import br.seploc.dao.GrupoDAO;
import br.seploc.pojos.Cliente;
import br.seploc.pojos.Grupo;

public class GrupoBean {

	private Grupo grupo;
	private GrupoDAO grupoDAO;
	
	public GrupoBean(){
		
		grupo = new Grupo();
		grupoDAO = new GrupoDAO();
		
	}
	
	public void cadastra() {
		
	}
	
	public void edita(){
		
	}
	
	public void apaga(){
		
	}
	
	public void limpa() {
		grupo =  new Grupo();
		System.out.println("Limpar Grupo");
	}	
	
	public static void addGlobalMessage(String message) {
		FacesMessage facesMessage = new FacesMessage(message);
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	}
	
	
}
