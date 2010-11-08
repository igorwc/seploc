package br.seploc.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Query;

import br.seploc.pojos.Estado;
import br.seploc.util.GenericDAO;

public class EstadoDAO extends GenericDAO<Estado, Integer> implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void adiciona(Estado t) throws Exception {
		em.getTransaction().begin();
		em.persist(t);
		em.getTransaction().commit();
	}

	@Override
	public Estado recupera(Integer id) throws Exception {
		Estado estado = em.find(Estado.class, id);
		return estado;
	}

	@Override
	public Estado altera(Estado t) throws Exception {
		em.getTransaction().begin();
		em.merge(t);
		em.getTransaction().commit();
		return t;
	}

	@Override
	public Estado remove(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Estado> getLista() {
		em.getTransaction().begin();
		Query q = em.createNamedQuery("Estado.RetornaEstados");
		em.getTransaction().commit();
		return (List<Estado>) q.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Estado> recuperaPorCodigo(Integer codigo) {
		em.getTransaction().begin();
		Query q = em.createNamedQuery("Estado.RetornaEstadoPorCodigo")
				.setParameter("codigo",  codigo );
		
		em.getTransaction().commit();
		return (List<Estado>) q.getResultList();
	}
	
	@Override
	protected boolean verificaFilhos(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void ajustaPojo(Estado pojo) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
