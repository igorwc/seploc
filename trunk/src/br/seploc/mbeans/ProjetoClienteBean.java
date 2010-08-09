package br.seploc.mbeans;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import br.seploc.dao.ProjetoDAO;
import br.seploc.pojos.Cliente;
import br.seploc.pojos.Projeto;

public class ProjetoClienteBean {

	private Projeto projeto;
	private ProjetoDAO projetoDAO;
	
	public ProjetoClienteBean() {	
		projeto = new Projeto();
		projetoDAO = new ProjetoDAO();
	}
	
	public Projeto getProjeto() {
		System.out.println("Get Projeto");
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	public ProjetoDAO getProjetoDAO() {
		return projetoDAO;
	}

	public void setProjetoDAO(ProjetoDAO projetoDAO) {
		this.projetoDAO = projetoDAO;
	}

	public void cadastra() {
		
	}
	
	public void edita(){
		
	}
	
	public void apaga(){
		
	}
	
	public void limpa() {
		projeto =  new Projeto();
		System.out.println("Limpar Projeto");
	}	
	
	public List<Projeto> getLista(){
		Cliente c = projeto.getCliente();
		if (c == null){
			return null;
		} else {
			return projetoDAO.getListaProjetoPorCliente(c);
		}			
	}
	
	public static void addGlobalMessage(String message) {
		FacesMessage facesMessage = new FacesMessage(message);
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	}
	
}
