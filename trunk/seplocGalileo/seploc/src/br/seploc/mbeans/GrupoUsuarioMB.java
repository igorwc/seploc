package br.seploc.mbeans;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import br.seploc.dao.GrupoDAO;
import br.seploc.dao.UsuarioDAO;
import br.seploc.pojos.Grupo;
import br.seploc.pojos.Usuario;

public class GrupoUsuarioMB   implements Serializable {
	
	private static final long serialVersionUID = 1L;	
	private List<Usuario> listaUsuarios;
	private List<Grupo> listaGrupos;
	private GrupoDAO grupoDAO;
	private UsuarioDAO usuarioDAO;
	private Grupo grupo;
	private Usuario usuario;
	private int grupoSelecionado;
	private int usuarioSelecionado;
	
	public GrupoUsuarioMB(){
		setGrupoDAO(new GrupoDAO());
		setUsuarioDAO(new UsuarioDAO());
		grupo = new Grupo();
		usuario = new Usuario();
		listaGrupos = grupoDAO.getLista();
		listaUsuarios = usuarioDAO.getListaUsuarioSemGrupo();
	}

	public List<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}

	public void setListaUsuarios(List<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}

	public List<Grupo> getListaGrupos() {
		return listaGrupos;
	}

	public void setListaGrupos(List<Grupo> listaGrupos) {
		this.listaGrupos = listaGrupos;
	}

	public GrupoDAO getGrupoDAO() {
		return grupoDAO;
	}

	public void setGrupoDAO(GrupoDAO grupoDAO) {
		this.grupoDAO = grupoDAO;
	}

	public UsuarioDAO getUsuarioDAO() {
		return usuarioDAO;
	}

	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public int getGrupoSelecionado() {
		return grupoSelecionado;
	}

	public void setGrupoSelecionado(int grupoSelecionado) {
		this.grupoSelecionado = grupoSelecionado;
	}

	public int getUsuarioSelecionado() {
		return usuarioSelecionado;
	}

	public void setUsuarioSelecionado(int usuarioSelecionado) {
		this.usuarioSelecionado = usuarioSelecionado;
	}
	
	public List<Grupo> getTodosGrupos(){
		List<Grupo> retorno = grupoDAO.getLista();
		
		return retorno;
	}
	
	public void remover(){
		try{
			usuario = usuarioDAO.recupera(this.usuarioSelecionado);
			usuario.setGrupo(null);
			usuarioDAO.altera(usuario);
			addGlobalMessage("Usuario '"+usuario.getLogin()+"' removido do grupo!");
		} catch (Exception e) {
			addGlobalMessage(e.getMessage());
		}
	}
	
	public void adicionar(){
		try{
			usuario = usuarioDAO.recupera(this.usuarioSelecionado);
			grupo = grupoDAO.recupera(this.grupoSelecionado);
			usuario.setGrupo(grupo);
			usuarioDAO.altera(usuario);
			addGlobalMessage("Usuario '"+usuario.getLogin()+"' associado ao grupo '"+grupo.getNomeGrupo()+"'!");
		} catch (Exception e) {
			addGlobalMessage(e.getMessage());
		}
	}	
	
	public static void addGlobalMessage(String message) {
		FacesMessage facesMessage = new FacesMessage(message);
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	}	

}
