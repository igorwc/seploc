package br.seploc.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;


public abstract class GenericDAO<T,D> {
	@PersistenceContext(unitName = "seploc")
	protected EntityManagerFactory emf;
	protected EntityManager em;

	public GenericDAO() {
		em = PersistenceServiceFactory.getInstance().getManager(); 
	}

	public abstract void adiciona(T t) throws Exception;

	public abstract T recupera(D id);

	public abstract T altera(T t);

	public abstract T remove(D id) throws Exception;
	
	protected abstract boolean verificaFilhos(D id) throws Exception;
	
	protected abstract void ajustaPojo(T pojo);
}
