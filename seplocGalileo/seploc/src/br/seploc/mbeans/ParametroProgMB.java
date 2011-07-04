package br.seploc.mbeans;

import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import br.seploc.dao.ParametroProgDAO;
import br.seploc.pojos.ParametroProg;

public class ParametroProgMB implements Serializable {

	private static final long serialVersionUID = 1L;
	private ParametroProg parametro;
	private ParametroProgDAO parametroDAO;

	public ParametroProgMB() {
		this.init();
	}
	
	private void init(){
		setParametro(new ParametroProg());
		setParametroDAO(new ParametroProgDAO());
	}
	
	// GETTERS AND SETTERS

	public void setParametro(ParametroProg parametro) {
		this.parametro = parametro;
	}

	public ParametroProg getParametro() {
		return parametro;
	}

	public void setParametroDAO(ParametroProgDAO parametroDAO) {
		this.parametroDAO = parametroDAO;
	}

	public ParametroProgDAO getParametroDAO() {
		return parametroDAO;
	}

	// METHODS
	
	public List<ParametroProg> getLista(){
		return parametroDAO.getLista();
	}
	
	public void alterar(){
		if (parametro.getCodParametro() == null || parametro.getCodParametro() == "") {
			addGlobalMessage("Favor selecione um item da lista!");
		} else {
			ParametroProg temp;
			try{
				temp = parametroDAO.recupera(parametro.getCodParametro());
				if (temp != null){
					temp.setCodParametro(parametro.getCodParametro());
					temp.setValor(parametro.getValor());
					try{
						parametroDAO.altera(temp);
						addGlobalMessage("Atualizacao feita com sucesso!");					
					} catch (Exception e) {
						addGlobalMessage(e.getMessage());
					}
				}								
			} catch (Exception e) {
				addGlobalMessage(e.getMessage());
			}
		}
	}
	
	public void limpar(){
		parametro = new ParametroProg();
	}
	
	public void apagar(){
		try{
			parametroDAO.remove(parametro.getCodParametro());
			addGlobalMessage("Excluido com sucesso!");
		} catch (Exception e) {
			addGlobalMessage(e.getMessage());
		}
		this.limpar();
	}
	
	public void editar(){
		try{
		parametro = parametroDAO.recupera(parametro.getCodParametro());
		} catch (Exception e) {
			e.printStackTrace();
			addGlobalMessage(e.getMessage());
		}		
	}
	
	public static void addGlobalMessage(String message) {
		FacesMessage facesMessage = new FacesMessage(message);
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	}	
}
