package br.seploc.mbeans;

import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import br.seploc.dao.GrupoDAO;
import br.seploc.pojos.Grupo;

public class GrupoMB implements Serializable {

	private static final long serialVersionUID = 1L;
	static int quantidade = 0;
	private Grupo grupo;
	private GrupoDAO grupoDAO;
	
	//CONSTRUTOR
	/**
	 * Construtor do Objeto Grupo	
	 */
	public GrupoMB(){
		init();
	}
	
	/**
	 * Metodo de inicialização
	 */
	public void init(){
		quantidade++;	
		grupo = new Grupo();
		grupoDAO = new GrupoDAO();
	}

	// SETTERS AND GETTERS
	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public GrupoDAO getGrupoDAO() {
		return grupoDAO;
	}

	public void setGrupoDAO(GrupoDAO grupoDAO) {
		this.grupoDAO = grupoDAO;
	}
	
	/**
	 * Recupera lista de usuarios
	 * @return List<Usuario> 
	 */
	public List<Grupo> getLista(){
		return grupoDAO.getLista();
	}
	
	// METODO
	public void cadastrar() {
		if (grupo.getCodGrupo() == null || grupo.getCodGrupo() == 0) {
			try {
				grupoDAO.adiciona(grupo);
				addGlobalMessage("Inclusão feita com sucesso!");
			} catch (Exception e) {
				addGlobalMessage(e.getMessage());
			}
		} else {
			Grupo temp;
			temp = grupoDAO.recupera(grupo.getCodGrupo());
			if (temp != null) {
				temp.setCodGrupo(grupo.getCodGrupo());
				temp.setNomeGrupo(grupo.getNomeGrupo().trim());
								
				try {
					grupoDAO.altera(temp);
					addGlobalMessage("Atualização feita com sucesso!");					
				} catch (Exception e) {
					addGlobalMessage(e.getMessage());
				}
			}
		}
		this.limpar();		
	}
	
	public void editar(){
		try {
			grupo = grupoDAO.recupera(grupo.getCodGrupo());
		} catch (Exception e) {
			e.printStackTrace();
			addGlobalMessage(e.getMessage());
		}				
	}
	
	public void apagar(){
		try {
			grupoDAO.remove(grupo.getCodGrupo());
			addGlobalMessage("Excluído com sucesso!");
		} catch (Exception e) {
			addGlobalMessage(e.getMessage());
		}
		this.limpar();		
	}
	
	public void limpar() {
		grupo =  new Grupo();
	}	
	
	public static void addGlobalMessage(String message) {
		FacesMessage facesMessage = new FacesMessage(message);
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	}
	
}
