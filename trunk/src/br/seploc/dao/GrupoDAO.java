package br.seploc.dao;

import java.util.List;

import javax.persistence.Query;

import br.seploc.dao.exceptions.ParentDeleteException;
import br.seploc.dao.exceptions.RecordNotFound;
import br.seploc.pojos.Grupo;
import br.seploc.util.GenericDAO;

public class GrupoDAO extends GenericDAO<Grupo>{

	@Override
	public void adiciona(Grupo t) {
		em.getTransaction().begin();
		em.merge(t);
		em.getTransaction().commit();
		
	}

	@Override
	public Grupo altera(Grupo t) {
		em.getTransaction().begin();
		em.merge(t);
		em.getTransaction().commit();
		return t;
	}

	@Override
	public Grupo recupera(Integer id) {
		Grupo grupo = em.find(Grupo.class, id);
		return grupo;
	}

	@Override
	public Grupo remove(Integer id) throws Exception {
		em.getTransaction().begin();
		Grupo grupo = em.find(Grupo.class, id);
		if(grupo == null){
			em.getTransaction().rollback();
			throw new RecordNotFound("Grupo Inexistente");
		}else {

			if (verificaFilhos(id)) {
				em.getTransaction().rollback();
				throw new ParentDeleteException(
						"Grupo tem registros depedentes...");
			} else {
				em.remove(grupo);
			}
		}
		em.getTransaction().commit();

		return grupo;
	}
	private boolean verificaFilhos(Integer id) throws ParentDeleteException {
		Number contagemGrupoMenu = 0;
//		Number contagemUsuario = 0;
		Query q = em.createQuery(
				"SELECT count(gm.grupo) FROM br.seploc.pojos.GrupoMenu gm"
						+ " where gm.grupo.codGrupo = :grupo").setParameter(
				"grupo", id);
		contagemGrupoMenu = (Number) q.getSingleResult();
		
		/*em.createQuery(
				"SELECT count(u.grupo) FROM br.seploc.pojos.Usuario u"
						+ " where u.grupo.codGrupo = :grupo").setParameter(
				"grupo", id);
		contagemUsuario = (Number) q.getSingleResult();*/
		if (contagemGrupoMenu.intValue() != 0)
			return true;
//		if (contagemUsuario.intValue() != 0)
//			return true;
		return false;
	}
	
	@SuppressWarnings("unchecked")
	public List<Grupo> getLista() {
		em.getTransaction().begin();
		Query q = em.createNamedQuery("Grupo.RetornaGrupos");
		em.getTransaction().commit();
		return (List<Grupo>) q.getResultList();
	}
}
