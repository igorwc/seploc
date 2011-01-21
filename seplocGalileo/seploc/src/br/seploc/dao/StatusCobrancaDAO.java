package br.seploc.dao;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.Query;

import br.seploc.dao.exceptions.RecordNotFound;
import br.seploc.pojos.StatusCobranca;
import br.seploc.util.GenericDAO;

public class StatusCobrancaDAO extends
		GenericDAO<StatusCobranca, Integer> implements Serializable{

	private static final long serialVersionUID = 1L;

	@Override
	public void adiciona(StatusCobranca t) throws Exception {
		em.getTransaction().begin();
		em.persist(t);
		em.getTransaction().commit();

	}

	public StatusCobranca recupera(Integer numReq) throws Exception {
		StatusCobranca status = em.find(StatusCobranca.class, numReq);
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
	public StatusCobranca remove(Integer numReq) throws Exception {
		em.getTransaction().begin();
		StatusCobranca status = em.find(StatusCobranca.class, numReq);
		if (status == null) {
			em.getTransaction().rollback();
			throw new RecordNotFound("Status de Cobran�a Inexistente");
		}
		em.remove(status);
		em.getTransaction().commit();

		return status;
	}

	@SuppressWarnings("unchecked")
	public List<StatusCobranca> getLista(Date dataInicio, Date dataFim) {
		em.getTransaction().begin();
		Query q = em.createNamedQuery("StatusCobranca.RetornaStatusCobrancas");
		q.setParameter("dataInicio", dataInicio);
		q.setParameter("dataFim", dataFim);
		em.getTransaction().commit();
		return (List<StatusCobranca>) q.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<StatusCobranca> getLista(Integer numReq) {
		em.getTransaction().begin();
		Query q = em.createNamedQuery("StatusCobranca.FiltraRequisicao");
		q.setParameter("id", numReq);		
		em.getTransaction().commit();
		return (List<StatusCobranca>) q.getResultList();
	}	
	
	@Override
	protected boolean verificaFilhos(Integer numReq) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void ajustaPojo(StatusCobranca pojo) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public List<StatusCobranca> getLista() {
		// TODO Auto-generated method stub
		return null;
	}

}
