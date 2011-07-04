package br.seploc.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Query;

import br.seploc.pojos.Bairro;
import br.seploc.util.GenericDAO;

public class BairroDAO extends GenericDAO<Bairro, Integer> implements
		Serializable {
	private static final long serialVersionUID = 1L;
	@Override
	public void adiciona(Bairro t) throws Exception {
		em.getTransaction().begin();
		em.persist(t);
		em.getTransaction().commit();
		
	}

	@Override
	public Bairro recupera(Integer id) throws Exception {
		Bairro bairro = em.find(Bairro.class, id);
		return bairro;
	}

	@Override
	public Bairro altera(Bairro t) throws Exception {
		em.getTransaction().begin();
		em.merge(t);
		em.getTransaction().commit();
		return t;
	}

	@Override
	public Bairro remove(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Bairro> getLista() {
		em.getTransaction().begin();
		Query q = em.createNamedQuery("Bairro.RetornaBairros");
		em.getTransaction().commit();
		return (List<Bairro>) q.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Bairro> recuperaBairrosPorCidade(Integer cidade) {
		em.getTransaction().begin();
		Query q = em.createNamedQuery("Bairro.RetornaBairrosPorCidade")
				.setParameter("codigo", cidade);

		em.getTransaction().commit();
		return (List<Bairro>) q.getResultList();
	}
	
	@Override
	protected boolean verificaFilhos(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void ajustaPojo(Bairro pojo) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
