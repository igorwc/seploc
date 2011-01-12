package br.seploc.dao;

import java.util.List;

import javax.persistence.Query;

import br.seploc.dao.exceptions.LoginExistenteException;
import br.seploc.dao.exceptions.ParentDeleteException;
import br.seploc.dao.exceptions.RecordNotFound;
import br.seploc.pojos.Usuario;
import br.seploc.util.DesEncrypter;
import br.seploc.util.GenericDAO;

public class UsuarioDAO extends GenericDAO<Usuario, Integer> {

	@Override
	public void adiciona(Usuario t) throws LoginExistenteException {
		em.getTransaction().begin();
		Usuario temp = getUsuarioPorLogin(t.getLogin());
		if (temp == null ) {
			em.persist(t);
			em.getTransaction().commit();
		} else {
			em.getTransaction().rollback();
			throw new LoginExistenteException("Login j� em uso");
		}		
	}

	@Override
	public Usuario altera(Usuario t) throws LoginExistenteException {
		em.getTransaction().begin();		
		if (!this.existeLogin(t.getLogin())) {
			em.merge(t);
			em.getTransaction().commit();
		} else {
			em.getTransaction().rollback();
			throw new LoginExistenteException("Login j� em uso");			
		}
		return t;
	}

	@Override
	public Usuario recupera(Integer id) {
		Usuario usuario = em.find(Usuario.class, id);
		return usuario;
	}

	@Override
	public Usuario remove(Integer id) throws Exception {
		em.getTransaction().begin();
		Usuario usuario = em.find(Usuario.class, id);
		if (usuario == null) {
			em.getTransaction().rollback();
			throw new RecordNotFound("Usuario Inexistente");
		} else {

			if (verificaFilhos(id)) {
				em.getTransaction().rollback();
				throw new ParentDeleteException(
						"Usuario tem registros depedentes...");
			} else {
				em.remove(usuario);
			}
		}
		em.getTransaction().commit();

		return usuario;
	}

	@Override
	protected boolean verificaFilhos(Integer id) throws ParentDeleteException {
		// Number contagemReqUsuario = 0; //TODO Implementar
		// Query q = em.createQuery(
		// "SELECT count(rsu.usuario) FROM br.seploc.pojos.ReqServUsuario rsu"
		// + " where rsu.usuario.login = :login").setParameter(
		// "grupo", id);
		// contagemReqUsuario = (Number) q.getSingleResult();
		// if (contagemReqUsuario.intValue() != 0)
		// return true;
		return false;
	}
	
	protected boolean existeLogin(String login) {
		boolean existe = false;
		
		if (this.getListaUsuariosPorLogin(login).size() > 0){
			existe = true;
		}
		
		return existe;
	}	

	@SuppressWarnings("unchecked")
	public List<Usuario> getLista() {
		boolean flag = em.getTransaction().isActive();
		if (!flag) {
			em.getTransaction().begin();
		}
		Query q = em.createNamedQuery("Usuario.RetornaUsuarios");
		if (!flag) {
			em.getTransaction().commit();
		}
		return (List<Usuario>) q.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Usuario> getListaUsuariosPorGrupo(Integer codGrupo) {
		boolean flag = em.getTransaction().isActive();
		if (!flag) {
			em.getTransaction().begin();
		}
		Query q = em.createNamedQuery("Usuario.RetornaUsuariosPorGrupo")
				.setParameter("grupo", codGrupo.intValue());
		if (!flag) {
			em.getTransaction().commit();
		}
		return (List<Usuario>) q.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Usuario> getListaUsuariosPorNome(String nome) {
		boolean flag = em.getTransaction().isActive();
		if (!flag) {
			em.getTransaction().begin();
		}
		Query q = em.createNamedQuery("Usuario.RetornaUsuariosPorNome")
				.setParameter("nome", "%" + nome + "%");
		if (!flag) {
			em.getTransaction().commit();
		}
		return (List<Usuario>) q.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Usuario> getListaUsuariosPorLogin(String login) {
		boolean flag = em.getTransaction().isActive();
		if (!flag) {
			em.getTransaction().begin();
		}
		Query q = em.createNamedQuery("Usuario.RetornaUsuariosPorLogin")
				.setParameter("login", "%" + login + "%");
		if (!flag) {
			em.getTransaction().commit();
		}
		return (List<Usuario>) q.getResultList();
	}
	
	public Usuario getUsuarioPorLogin(String login) {
		boolean flag = em.getTransaction().isActive();
		Usuario retorno = null;
		if (!flag) {
			em.getTransaction().begin();
		}
		Query q = em.createNamedQuery("Usuario.RetornaUsuarioPorLogin")
				.setParameter("login",  login  );
		if (!flag) {
			em.getTransaction().commit();
		}
		try {			
			retorno = (Usuario) q.getSingleResult();
		} catch (Exception e) {
			retorno = null;
		}
		return  retorno;		
	}

	public Usuario getUsuarioPorCpf(String cpf) {
		boolean flag = em.getTransaction().isActive();
		Usuario retorno = null;
		if (!flag) {
			em.getTransaction().begin();
		}
		Query q = em.createNamedQuery("Usuario.RetornaUsuarioPorCpf")
				.setParameter("cpf",  cpf  );
		if (!flag) {
			em.getTransaction().commit();
		}
		try {
			retorno = (Usuario) q.getSingleResult();
		} catch (Exception e) {
			retorno = null;
		}
		return  retorno;
	}
	
	public List<Usuario> getListaRequisicoesPorUsuario() {
		// TODO Implementar
		// em.getTransaction().begin();
		// Query q =
		// em.createNamedQuery("Usuario.RetornaRequisicoesPorUsuario");
		// em.getTransaction().commit();
		// return (List<Usuario>) q.getResultList();
		return null;
	}
	public Usuario recupera (String login, String senha){
		boolean flag = false;
		Usuario retorno = null;
		if (!em.getTransaction().isActive()) {
			flag = true;
			em.getTransaction().begin();
		} 
		DesEncrypter encrypter = new DesEncrypter();
		System.out.println("Senha Recebida: "+ senha);
		System.out.println("Senha Encriptada: "+ encrypter.encrypt(senha));
		
		Query q = em.createNamedQuery("Usuario.RecuperaLogin").setParameter(1, login).setParameter(2, encrypter.encrypt(senha));

		try {
			retorno = (Usuario)q.getSingleResult();
			if (flag) {
				em.getTransaction().commit();
			}
			return retorno;
			
		} catch (Exception e) {
			if (flag) {
				em.getTransaction().rollback();
			}
			return null;
		}
		 
//		if (q.getResultList() == null || q.getResultList().size() == 0)
//		    return retorno;
//		else{
//			retorno = (Usuario)q.getResultList().get(0);
//			System.out.println(retorno.getPassword());
//			em.refresh(retorno);
//			if (flag) {
//				retorno = recupera(retorno.getId());
//				em.getTransaction().commit();
//			}
//			return retorno;
//		}
	}
	@Override
	protected void ajustaPojo(Usuario pojo) {
		// TODO Auto-generated method stub
		
	}
}
