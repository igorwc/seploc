package br.seploc.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Query;

import br.seploc.pojos.Cidade;
import br.seploc.pojos.Estado;
import br.seploc.util.GenericDAO;

public class CidadeDAO extends GenericDAO<Cidade, Integer> implements
		Serializable {
	private static final long serialVersionUID = 1L;
	@Override
	public void adiciona(Cidade t) throws Exception {
		em.getTransaction().begin();
		em.persist(t);
		em.getTransaction().commit();
	}

	@Override
	public Cidade recupera(Integer id) throws Exception {
		Cidade cidade = em.find(Cidade.class, id);
		return cidade;
	}

	@Override
	public Cidade altera(Cidade t) throws Exception {
		em.getTransaction().begin();
		em.merge(t);
		em.getTransaction().commit();
		return t;
	}

	@Override
	public Cidade remove(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Cidade> getLista() {
		em.getTransaction().begin();
		Query q = em.createNamedQuery("Cidade.RetornaCidades");
		em.getTransaction().commit();
		return (List<Cidade>) q.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Estado> recuperaCidadesPorEstado(Integer estado) {
		em.getTransaction().begin();
		Query q = em.createNamedQuery("Cidade.RetornaCidadesPorEstado")
				.setParameter("codigo", estado);

		em.getTransaction().commit();
		return (List<Estado>) q.getResultList();
	}
	
	@Override
	protected boolean verificaFilhos(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void ajustaPojo(Cidade pojo) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
