package br.seploc.mbeans;

import java.util.List;

import javax.persistence.Query;

import br.seploc.dao.UsuarioDAO;
import br.seploc.pojos.Usuario;

public class UsuarioBean {

    /**
     * pojo usuario
     */
	private Usuario usuario;
	
	/**
	 * DAO do pojo usuario
	 */
	private UsuarioDAO usuarioDAO;	
	
	/**
	 * Construtor do Bean do usuario
	 */
	public UsuarioBean() {
		usuario = new Usuario();
		usuarioDAO = new UsuarioDAO();
		System.out.println("criou bean usuario");
	}
	
	/**
	 * Metodo recupera o usuario 
	 * @return usuario 
	 */
	public Usuario getUsuario() {
		System.out.println("get usuario");
		return usuario;
	}
	/**
	 * Metodo altera o usuario
	 * @param usuario
	 */
	public void setUsuario(Usuario usuario) {
		System.out.println("set usuario");
		this.usuario = usuario;
	}
	/**
	 * Metodo recupera o DAO do usuario
	 * @return usuarioDAO
	 */
	public UsuarioDAO getUsuarioDAO() {
		return usuarioDAO;
	}
	/**
	 * Metodo altera o DAO do usuario
	 * @param usuarioDAO
	 */
	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}
	/**
	 * Metodo cadastra o usuario
	 */
	public void cadastra() {
		System.out.println("cadastra usuario");
		
		Usuario temp;
		temp = usuarioDAO.recupera(usuario.getLogin());
		
		if (temp == null)
			try{
			usuarioDAO.adiciona(usuario);
			} catch (Exception e) {
				e.printStackTrace();
			}
		else {			
			if (temp != null) {
				temp.setNome(usuario.getNome());
				temp.setCpf(usuario.getCpf());
				temp.setGrupo(usuario.getGrupo());
				temp.setPermissao(usuario.getPermissao());
				usuarioDAO.altera(temp);
			}
		}
		this.limpar();
	}
	/**
	 * Metodo apaga o registro de usuario
	 */
	public void apaga(){
		System.out.println("apaga usuario");
		try {
			usuarioDAO.remove(usuario.getLogin());
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.limpar();
	}
	/**
	 * Metodo edita o registro do usuario
	 */
	public void editar(){
		System.out.println("edita usuario");
		try {
			usuario =  usuarioDAO.recupera(usuario.getLogin());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Metodo recupera a lista de usuarios do banco
	 * @return array de usuario
	 */
	public List<Usuario> getLista() {
		return usuarioDAO.getLista();
	}

	/**
	 * Metodo reinicia o objeto usuario do Bean
	 */
	public void limpar(){
		System.out.println("limpa usuario");
		usuario = new Usuario();
	}
	
}
