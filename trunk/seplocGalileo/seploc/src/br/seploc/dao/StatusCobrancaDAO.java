package br.seploc.dao;

import java.util.List;

import javax.persistence.Query;

import br.seploc.dao.exceptions.RecordNotFound;
import br.seploc.pojos.StatusCobranca;
import br.seploc.pojos.StatusCobrancaPK;
import br.seploc.util.GenericDAO;

public class StatusCobrancaDAO extends
		GenericDAO<StatusCobranca, StatusCobrancaPK> {

	@Override
	public void adiciona(StatusCobranca t) throws Exception {
		em.getTransaction().begin();
		em.persist(t);
		em.getTransaction().commit();

	}

	@Override
	public StatusCobranca recupera(StatusCobrancaPK id) throws Exception {
		StatusCobranca status = em.find(StatusCobranca.class, id);
		return status;
	}

	@Override
	public StatusCobranca altera(StatusCobranca t) throws Exception {
		em.getTransaction().begin();
		em.merge(t);
		em.getTransaction().commit();
		return t;
	}

	@Override
	public StatusCobranca remove(StatusCobrancaPK id) throws Exception {
		em.getTransaction().begin();
		StatusCobranca status = em.find(StatusCobranca.class, id);
		if (status == null) {
			em.getTransaction().rollback();
			throw new RecordNotFound("Status de Cobrança Inexistente");
		}
		em.remove(status);
		em.getTransaction().commit();

		return status;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StatusCobranca> getLista() {
		em.getTransaction().begin();
		Query q = em.createNamedQuery("StatusCobranca.RetornaStatusCobrancas");
		em.getTransaction().commit();
		return (List<StatusCobranca>) q.getResultList();
	}

	@Override
	protected boolean verificaFilhos(StatusCobrancaPK id) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void ajustaPojo(StatusCobranca pojo) throws Exception {
		// TODO Auto-generated method stub

	}

}
