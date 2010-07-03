package br.seploc.dao;

import java.util.List;

import javax.persistence.Query;

import br.seploc.dao.exceptions.ParentDeleteException;
import br.seploc.dao.exceptions.RecordNotFound;
import br.seploc.pojos.Usuario;
import br.seploc.util.GenericDAO;

public class UsuarioDAO extends GenericDAO<Usuario> {

	@Override
	public void adiciona(Usuario t) {
		em.getTransaction().begin();
		em.merge(t);
		em.getTransaction().commit();
	}

	@Override
	public Usuario altera(Usuario t) {
		em.getTransaction().begin();
		em.merge(t);
		em.getTransaction().commit();
		return t;
	}

	public Usuario recupera(String id) {
		Usuario usuario = em.find(Usuario.class, id);
		return usuario;
	}
	public Usuario remove(String id) throws Exception {
		em.getTransaction().begin();
		Usuario usuario = em.find(Usuario.class, id);
		if(usuario == null){
			em.getTransaction().rollback();
			throw new RecordNotFound("Usuario Inexistente");
		}else {

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
	private boolean verificaFilhos(String id) throws ParentDeleteException {
//		Number contagemReqUsuario = 0; //TODO Implementar 
//		Query q = em.createQuery(
//				"SELECT count(rsu.usuario) FROM br.seploc.pojos.ReqServUsuario rsu"
//						+ " where rsu.usuario.login = :login").setParameter(
//				"grupo", id);
//		contagemReqUsuario = (Number) q.getSingleResult();
//		if (contagemReqUsuario.intValue() != 0)
//			return true;
		return false;
	}
	
	@SuppressWarnings("unchecked")
	public List<Usuario> getLista() {
		em.getTransaction().begin();
		Query q = em.createNamedQuery("Usuario.RetornaUsuarios");
		em.getTransaction().commit();
		return (List<Usuario>) q.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Usuario> getListaUsariosPorGrupo() {
		em.getTransaction().begin();
		Query q = em.createNamedQuery("Usuario.RetornaUsuariosPorGrupo");
		em.getTransaction().commit();
		return (List<Usuario>) q.getResultList();
	}
	public List<Usuario> getListaRequisicoesPorUsuario() {
		//TODO Implementar
//		em.getTransaction().begin();
//		Query q = em.createNamedQuery("Usuario.RetornaRequisicoesPorUsuario");
//		em.getTransaction().commit();
//		return (List<Usuario>) q.getResultList();
		return null;
	}

	@Override
	public Usuario recupera(Integer id) {
		Usuario usuario = em.find(Usuario.class, id);
		return usuario;
	}

	@Override
	public Usuario remove(Integer id) {

		return null;
	}
}
