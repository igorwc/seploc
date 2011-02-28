package br.seploc.mbeans;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import br.seploc.dao.UsuarioDAO;
import br.seploc.dao.exceptions.LoginExistenteException;
import br.seploc.dao.exceptions.RecordNotFound;
import br.seploc.pojos.Usuario;

public class UsuarioMB implements Serializable {

	private static final long serialVersionUID = 1L;
	static int quantidade = 0;
	private Usuario usuario;
	private UsuarioDAO usuarioDAO;
	
	//CONSTRUTOR
	/**
	 * Construtor da classe
	 */
	public UsuarioMB(){
		init();
	}
	
	/**
	 * Metodo de inicializacao
	 */
	public void init(){
		quantidade++;
		usuario = new Usuario();
		usuarioDAO = new UsuarioDAO();
	}

	// SETTERS AND GETTERS
	
	/**
	 * recuperar o usuario
	 * @return Usuario
	 */
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * Atribuir usuario
	 * @param usuario
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	/**
	 * Recuperar usuarioDAO
	 * @return UsuarioDAO
	 */
	public UsuarioDAO getUsuarioDAO() {
		return usuarioDAO;
	}

	/**
	 * Atribuir usuarioDAO
	 * @param usuarioDAO
	 */
	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}

	/**
	 * Recupera lista de usuarios
	 * @return List<Usuario> 
	 */
	public List<Usuario> getLista() {
		return usuarioDAO.getLista();
	}
	
	// METODOS
	/**
	 * Cadastrar ou alterar o usuario
	 */
	public void cadastrar() {
		try {
			if (usuario.getId() == null || usuario.getId() == 0) {
				if (this.existeCpf(usuario.getCpf())){
					addGlobalMessage("CPF usado por outro usu�rio!");
				} else {
					usuario.setPermissao(0);
					usuarioDAO.adiciona(usuario);
					addGlobalMessage("Inclus�o feita com sucesso!");
				}
			} else {
				Usuario temp;
				temp = usuarioDAO.recupera(usuario.getId());
				if (temp == null) {
					throw new RecordNotFound("Usuario Inexistente");
				}
				if(!temp.getLogin().equals(usuario.getLogin())){
					addGlobalMessage("O login do usu�rio n�o pode ser modificado!");
					this.limpar();
					return;
				}
				temp.setNome(usuario.getNome());
				temp.setCpf(usuario.getCpf());
				temp.setGrupo(usuario.getGrupo());
				temp.setPermissao(usuario.getPermissao());
				usuarioDAO.altera(temp);
				addGlobalMessage("Atualiza��o feita com sucesso!");
			}
		} catch (RecordNotFound e) {
			addGlobalMessage(e.getMessage());
		} catch (LoginExistenteException e) {
			addGlobalMessage(e.getMessage());
		} catch (Exception e) {
			addGlobalMessage("A opera��o n�o p�de ser realizada.");
			e.printStackTrace();
		}
		this.limpar();
	}

	/**
	 * Limpar o usuario
	 */
	public void limpar() {
		usuario = new Usuario();		
	}
	
	/**
	 * Editar usuario
	 */
	public void editar(){
		try {
			usuario = usuarioDAO.recupera(usuario.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	/**
	 * Excluir o usuario
	 */
	public void apagar() {
		try {
			usuarioDAO.remove(usuario.getId());
			addGlobalMessage("Excluido com sucesso!");
		} catch (Exception e) {
			addGlobalMessage(e.getMessage());
		}
		usuario = new Usuario();
	}

	/**
	 * Verificar se existe usuario com o mesmo CPF
	 * @param cpf
	 * @return boolean
	 */
	private boolean existeCpf(String cpf){
		boolean retorno = false;

		if (this.usuarioDAO.getUsuarioPorCpf(cpf) != null){
			retorno = true;
		}
		
		return retorno;
	}
	
	/**
	 * M�todo para incluir mensagens globais no formul�rio de cadastro
	 * 
	 * @param String
	 *            message
	 */
	public static void addGlobalMessage(String message) {
		FacesMessage facesMessage = new FacesMessage(message);
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	}

}