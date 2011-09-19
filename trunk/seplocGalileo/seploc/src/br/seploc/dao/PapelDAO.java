package br.seploc.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Query;

import br.seploc.dao.exceptions.ParentDeleteException;
import br.seploc.dao.exceptions.RecordNotFound;
import br.seploc.pojos.Papel;
import br.seploc.util.GenericDAO;

public class PapelDAO extends GenericDAO<Papel, Integer> implements Serializable {


	private static final long serialVersionUID = 1L;
	@Override
	public void adiciona(Papel t) {
		em.getTransaction().begin();
		em.persist(t);
		em.getTransaction().commit();

	}

	@Override
	public Papel altera(Papel t) {
		em.getTransaction().begin();
		em.merge(t);
		em.getTransaction().commit();
		return t;
	}

	@Override
	public Papel recupera(Integer id) {
		Papel papel = em.find(Papel.class, id);
		return papel;
	}
	
	public boolean existe(Papel papel) {
		boolean retorno = false;
		Number count = 0;		
		Query q = em.createNativeQuery(" SELECT count(1) "
				+ "FROM tbl_papel p "
				+ "WHERE p.vcrNome = :nomePapel")
		.setParameter("nomePapel", papel.getNome());
		
		count = (Number) q.getSingleResult();
		// se retornar diferente de zero setar para verdadeiro
		if (count.intValue() != 0) {
			retorno = true;
		}
		
		return retorno;
	}	
	
	@Override
	public Papel remove(Integer id) throws Exception {
		em.getTransaction().begin();
		Papel papel = em.find(Papel.class, id);
		if (papel == null) {
			em.getTransaction().rollback();
			throw new RecordNotFound("Papel nao cadastrado");
		} else {

			if (verificaFilhos(id)) {
				em.getTransaction().rollback();
				throw new ParentDeleteException(
						"Papel tem registros dependentes...");
			} else {
				em.remove(papel);
			}
		}
		em.getTransaction().commit();

		return papel;
	}

	@Override
	protected boolean verificaFilhos(Integer id) throws ParentDeleteException {
		Number contagemPapel = 0; // TODO Implementar
		 Query q =
		 em.createQuery("SELECT count(lr.papel) FROM br.seploc.pojos.LinhaRequisicao lr"
		 + " where lr.papel.codPapel = :codPapel").setParameter(
		 "codPapel", id);
		 contagemPapel = (Number) q.getSingleResult();
		 if (contagemPapel.intValue() != 0)
		 return true;
		return false;
	}

	@SuppressWarnings("unchecked")
	public List<Papel> getLista() {
		em.getTransaction().begin();
		Query q = em.createNamedQuery("Papel.RetornaPapeis");
		em.getTransaction().commit();
		return (List<Papel>) q.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Papel> getListaSohPapeis() {
		em.getTransaction().begin();
		Query q = em.createNamedQuery("Papel.RetornaSohPapeis");
		em.getTransaction().commit();
		return (List<Papel>) q.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Papel> getListaLona() {
		em.getTransaction().begin();
		Query q = em.createNamedQuery("Papel.RetornaLonaAdesivos");
		em.getTransaction().commit();
		return (List<Papel>) q.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Papel> getListaLonaPorNome(String nome) {
		em.getTransaction().begin();
		Query q = em.createNamedQuery("Papel.RetornaLonasPorNome")
				.setParameter("nome", "%" + nome + "%");
		;
		em.getTransaction().commit();
		return (List<Papel>) q.getResultList();
	}
	@SuppressWarnings("unchecked")
	public List<Papel> getListaPapelPorNome(String nome) {
		em.getTransaction().begin();
		Query q = em.createNamedQuery("Papel.RetornaPapeisPorNome")
				.setParameter("nome", "%" + nome + "%");
		;
		em.getTransaction().commit();
		return (List<Papel>) q.getResultList();
	}
	@SuppressWarnings("unchecked")
	public List<String> getPapeis() {
		em.getTransaction().begin();
		Query q = em.createNativeQuery("select vcrNome from tbl_papel where blEhPapel = 1");
		em.getTransaction().commit();
		return (List<String>) q.getResultList();
	}
	@Override
	protected void ajustaPojo(Papel pojo) {
		// TODO Auto-generated method stub
		
	}
}
