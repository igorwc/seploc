package br.seploc.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.seploc.dao.exceptions.PrimaryKeyException;
import br.seploc.dao.exceptions.RecordNotFound;
import br.seploc.pojos.ParametroProg;
import br.seploc.util.GenericDAO;

public class ParametroProgDAO extends GenericDAO<ParametroProg, String> {


	@Override
	public void adiciona(ParametroProg t) throws Exception {
		em.getTransaction().begin();
		ajustaPojo(t);
		em.persist(t);
		em.getTransaction().commit();

	}

	@Override
	public ParametroProg recupera(String id) throws Exception {
		ParametroProg parametro = em.find(ParametroProg.class, id);
		return parametro;

	}

	@Override
	public ParametroProg altera(ParametroProg t) throws Exception {

		em.getTransaction().begin();
		ajustaPojo(t);
		em.merge(t);
		em.getTransaction().commit();
		return t;
	}

	@Override
	public ParametroProg remove(String id) throws Exception {
		em.getTransaction().begin();
		ParametroProg parametro = em.find(ParametroProg.class, id);
		if (parametro == null) {
			em.getTransaction().rollback();
			throw new RecordNotFound("Parametro Inexistente");
		}
		em.remove(parametro);
		em.getTransaction().commit();

		return parametro;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ParametroProg> getLista() {
		em.getTransaction().begin();
		Query q = em.createNamedQuery("ParametroProg.RetornaParametros");
		em.getTransaction().commit();
		return (List<ParametroProg>) q.getResultList();
	}

	@Override
	protected boolean verificaFilhos(String id) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void ajustaPojo(ParametroProg pojo) throws Exception {
		// em.getTransaction().begin();
		Query q = em.createNamedQuery("ParametroProg.BuscaParametro")
				.setParameter("codigo", pojo.getCodParametro());
		// em.getTransaction().commit();
		try {
			// ParametroProg resultado = (ParametroProg)
			ParametroProg p = (ParametroProg) q.getSingleResult();
			if (p != null)
				return;
		} catch (NoResultException e) {
			return;
		}
		throw new PrimaryKeyException("Codigo de Programa ja existente");

	}
}
