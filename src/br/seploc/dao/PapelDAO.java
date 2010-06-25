package br.seploc.dao;

import java.util.List;

import javax.persistence.Query;

import br.seploc.pojos.Papel;
import br.seploc.util.GenericDAO;

public class PapelDAO extends GenericDAO<Papel> {

	@Override
	public void adiciona(Papel t) {
		em.getTransaction().begin();
		em.merge(t);
		em.getTransaction().commit();

	}

	@Override
	public Papel altera(Papel t) {
		em.getTransaction().begin();
		em.merge(t);
		em.getTransaction().commit();
		return t;
	}

	@Override
	public Papel recupera(Integer id) {
		Papel papel = em.find(Papel.class, id);
		return papel;
	}

	@Override
	public Papel remove(Integer id) throws Exception {
		em.getTransaction().begin();
		Papel papel = em.find(Papel.class, id);
		if (papel != null)
			em.remove(papel);
		em.getTransaction().commit();

		return papel;
	}

	@SuppressWarnings("unchecked")
	public List<Papel> getLista() {
		em.getTransaction().begin();
		Query q = em.createNamedQuery("Papel.RetornaPapeis");
		em.getTransaction().commit();
		return (List<Papel>) q.getResultList();
	}
}
