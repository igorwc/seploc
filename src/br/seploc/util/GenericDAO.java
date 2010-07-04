package br.seploc.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

import br.seploc.dao.exceptions.LoginInsertException;


public abstract class GenericDAO<T> {
	@PersistenceContext(unitName = "seploc")
	protected EntityManagerFactory emf;
	protected EntityManager em;

	public GenericDAO() {
		em = PersistenceServiceFactory.getInstance().getManager(); 
	}

	public abstract void adiciona(T t) throws LoginInsertException;

	public abstract T recupera(Integer id);

	public abstract T altera(T t);

	public abstract T remove(Integer id) throws Exception;
}
