package br.seploc.dao;

import java.io.Serializable;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Query;

import br.seploc.dao.exceptions.RecordNotFound;
import br.seploc.pojos.SaidaMotoqueiro;
import br.seploc.util.GenericDAO;
import br.seploc.util.Utils;

public class SaidaMotoqueiroDAO extends
		GenericDAO<SaidaMotoqueiro, Integer> implements Serializable{

	private static final long serialVersionUID = 1L;

	@Override
	public void adiciona(SaidaMotoqueiro t) throws Exception {
		em.getTransaction().begin();
		em.persist(t);
		em.getTransaction().commit();

	}

	public SaidaMotoqueiro recuperaPorReq(Integer numReq) throws Exception {
		SaidaMotoqueiro saida = null;
		
		Query q = em.createNamedQuery("SaidaMotoqueiro.FiltraRequisicao")
		.setParameter("numReq", numReq);
		saida = (SaidaMotoqueiro) q.getSingleResult();		
		
		return saida;
	}
	
	public SaidaMotoqueiro recupera(Integer numSaida) throws Exception {
		SaidaMotoqueiro saida = em.find(SaidaMotoqueiro.class, numSaida);
		return saida;
	}	

	@Override
	public SaidaMotoqueiro altera(SaidaMotoqueiro t) throws Exception {
		em.getTransaction().begin();
		em.merge(t);
		em.getTransaction().commit();
		return t;
	}
	
	public SaidaMotoqueiro remove(Integer numSaida) throws Exception {
		em.getTransaction().begin();
		SaidaMotoqueiro saida = em.find(SaidaMotoqueiro.class, numSaida);
		if (saida == null) {
			em.getTransaction().rollback();
			throw new RecordNotFound("Saida de Cobranca Inexistente");
		}
		em.remove(saida);
		em.getTransaction().commit();

		return saida;
	}	

	@SuppressWarnings("unchecked")
	public List<SaidaMotoqueiro> getLista(Date dataInicio, Date dataFim) {
		em.getTransaction().begin();
		Query q = em.createNamedQuery("SaidaMotoqueiro.RetornaSaidaMotoqueiro");
		q.setParameter("dataInicio", dataInicio);
		q.setParameter("dataFim", dataFim);
		em.getTransaction().commit();
		return (List<SaidaMotoqueiro>) q.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<SaidaMotoqueiro> getLista(Integer numReq) {
		em.getTransaction().begin();
		Query q = em.createNamedQuery("SaidaMotoqueiro.FiltraRequisicao");
		q.setParameter("numReq", numReq);		
		em.getTransaction().commit();
		return (List<SaidaMotoqueiro>) q.getResultList();
	}	
	
	@Override
	protected boolean verificaFilhos(Integer numReq) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void ajustaPojo(SaidaMotoqueiro pojo) throws Exception {
		// TODO Auto-generated method stub

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SaidaMotoqueiro> getLista() {
		java.util.Date data = new java.util.Date();
		java.sql.Date hoje = new java.sql.Date(data.getTime());
		java.sql.Date dataFim = Utils.getCalcularDataFrente(Calendar.getInstance(), 30); 
		
		em.getTransaction().begin();
		Query q = em.createNamedQuery("SaidaMotoqueiro.RetornaSaidaMotoqueiro");		
		q.setParameter("dataInicio", hoje);
		q.setParameter("dataFim", dataFim);
		em.getTransaction().commit();
		return (List<SaidaMotoqueiro>) q.getResultList();		
	}

}
