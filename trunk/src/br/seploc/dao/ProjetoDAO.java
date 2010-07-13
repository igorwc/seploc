package br.seploc.dao;

import java.util.List;

import javax.persistence.Query;

import br.seploc.dao.exceptions.FieldNotNullException;
import br.seploc.dao.exceptions.ParentDeleteException;
import br.seploc.dao.exceptions.RecordNotFound;
import br.seploc.pojos.Cliente;
import br.seploc.pojos.Projeto;
import br.seploc.util.GenericDAO;

public class ProjetoDAO extends GenericDAO<br.seploc.pojos.Projeto, Integer> {

	@Override
	public void adiciona(Projeto t) throws Exception {
		em.getTransaction().begin();
		ajustaPojo(t);
		em.persist(t);
		em.getTransaction().commit();
	}

	@Override
	protected void ajustaPojo(Projeto p) throws Exception {
		if (p.getCliente() == null)
			throw new FieldNotNullException(
					"Cliente é Obrigatório para Projeto");
	}

	@Override
	public Projeto altera(Projeto t) throws Exception {
		em.getTransaction().begin();
		ajustaPojo(t);
		em.persist(t);
		em.getTransaction().commit();
		return t;
	}

	@Override
	public Projeto recupera(Integer id) throws Exception {
		Projeto projeto = em.find(Projeto.class, id);
		return projeto;
	}

	@Override
	public Projeto remove(Integer id) throws Exception {
		em.getTransaction().begin();
		Projeto p = em.find(Projeto.class, id);
		if (p == null) {
			em.getTransaction().rollback();
			throw new RecordNotFound("Projeto Inexistente");
		} else {
			if (verificaFilhos(id)) {
				em.getTransaction().rollback();
				throw new ParentDeleteException(
						"Projeto tem registros depedentes...");
			} else {
				em.remove(p);
			}
		}
		em.getTransaction().commit();

		return p;
	}

	public Projeto remove(Projeto p) throws Exception {
		return remove(p.getCodProj());
	}

	@Override
	protected boolean verificaFilhos(Integer id) throws Exception {
		Number contagemRequisicoes = 0;

		Query q = em.createQuery(
				"SELECT count(rs.projeto) FROM br.seploc.pojos.RequisicaoServico rs"
						+ " where rs.projeto.codProj = :projetoID").setParameter(
				            "projetoID", id);
		contagemRequisicoes = (Number) q.getSingleResult();
		if (contagemRequisicoes.intValue() != 0)
			return true;
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Projeto> getLista() {
		em.getTransaction().begin();
		Query q = em.createNamedQuery("Projeto.RetornaProjetos");
		em.getTransaction().commit();
		return (List<Projeto>) q.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Projeto> getListaProjetosPorNome(String nome) {
		em.getTransaction().begin();
		 Query q = em.createNamedQuery("Projeto.BuscaProjetosPorNome").setParameter(
		 "nome", "%" + nome + "%");
		 em.getTransaction().commit();
		 return (List<Projeto>) q.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Projeto> getListaProjetoPorCliente(Cliente c) {
		em.getTransaction().begin();
		 Query q = em.createNamedQuery("Projeto.ListaProjetosPorCliente").setParameter(
		 "cliente", c.getIdCliente().intValue());
		 em.getTransaction().commit();
		 return (List<Projeto>) q.getResultList();
	}

}
