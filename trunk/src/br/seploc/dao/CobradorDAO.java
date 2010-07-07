package br.seploc.dao;

import java.util.List;

import javax.persistence.Query;

import br.seploc.pojos.Cobrador;
import br.seploc.util.GenericDAO;

public class CobradorDAO extends GenericDAO<Cobrador, Integer> {

	@Override
	public void adiciona(Cobrador t) {
		em.getTransaction().begin();
		em.merge(t);
		em.getTransaction().commit();
	}

	@Override
	public Cobrador altera(Cobrador t) {
		em.getTransaction().begin();
		em.merge(t);
		em.getTransaction().commit();
		return t;
	}

	@Override
	public Cobrador recupera(Integer id) {
		Cobrador cobrador = em.find(Cobrador.class, id);
		return cobrador;
	}

	@SuppressWarnings("unchecked")
	public List<Cobrador> recuperaPorNome(String nome) {
		em.getTransaction().begin();
		Query q = em.createNamedQuery("Cobrador.FiltraCobradores")
				.setParameter("nome", "%" + nome + "%");
		
		em.getTransaction().commit();
		return (List<Cobrador>) q.getResultList();
	}

	@Override
	public Cobrador remove(Integer id) throws Exception {
		em.getTransaction().begin();
		Cobrador cobrador = em.find(Cobrador.class, id);
		em.remove(cobrador);
		em.getTransaction().commit();

		return cobrador;
	}

	@SuppressWarnings("unchecked")
	public List<Cobrador> getLista() {
		em.getTransaction().begin();
		Query q = em.createNamedQuery("Cobrador.RetornaCobradores");
		em.getTransaction().commit();
		return (List<Cobrador>) q.getResultList();
	}

	@Override
	protected boolean verificaFilhos(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void ajustaPojo(Cobrador pojo) {
		// TODO Auto-generated method stub
		
	}

}
