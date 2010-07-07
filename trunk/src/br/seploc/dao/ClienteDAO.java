package br.seploc.dao;

import java.util.List;

import javax.persistence.Query;

import br.seploc.dao.exceptions.ParentDeleteException;
import br.seploc.dao.exceptions.RecordNotFound;
import br.seploc.pojos.Cliente;
import br.seploc.util.GenericDAO;

public class ClienteDAO extends GenericDAO<Cliente, Integer> {

	@Override
	public void adiciona(Cliente t) throws Exception {
		em.getTransaction().begin();
//		ajustaPojo(t);
		em.merge(t);
		em.getTransaction().commit();

	}

	@Override
	public Cliente altera(Cliente t) {
		em.getTransaction().begin();
		em.merge(t);
		em.getTransaction().commit();
		return t;
	}

	@Override
	public Cliente recupera(Integer id) {
		Cliente cliente = em.find(Cliente.class, id);
		return cliente;
	}

	@Override
	public Cliente remove(Integer id) throws Exception {
		em.getTransaction().begin();
		Cliente cliente = em.find(Cliente.class, id);
		if (cliente == null) {
			em.getTransaction().rollback();
			throw new RecordNotFound("Cliente Inexistente");
		} else {
			em.remove(cliente);
		}
		em.getTransaction().commit();

		return cliente;
	}

	@Override
	protected boolean verificaFilhos(Integer id) throws ParentDeleteException {
		return false;
	}

	@SuppressWarnings("unchecked")
	public List<Cliente> getLista() {
		em.getTransaction().begin();
		Query q = em.createNamedQuery("Cliente.RetornaClientes");
		em.getTransaction().commit();
		return (List<Cliente>) q.getResultList();
	}

//	@SuppressWarnings("unchecked")
//	public List<Cliente> getClientesPorNome(String nome) {
//		em.getTransaction().begin();
//		Query q = em.createNamedQuery("Cliente.BuscaClientes").setParameter(
//				"nome", "%" + nome + "%");
//		em.getTransaction().commit();
//		return (List<Cliente>) q.getResultList();
//	}
	@Override
	protected void ajustaPojo(Cliente c) {
		if(c.getRazao() == null) c.setRazao(c.getFantasia());
	}
}
