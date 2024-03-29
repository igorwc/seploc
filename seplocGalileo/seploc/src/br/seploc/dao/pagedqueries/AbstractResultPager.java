package br.seploc.dao.pagedqueries;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.seploc.util.PersistenceServiceFactory;

public abstract class AbstractResultPager<T> implements IResultPager<T>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected EntityManager em;

	private Query query;
	private int currentPage;
	private int maxResults;
	private int pageSize;

	public abstract int contagem();
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> getCurrentResults() {
		return ( List<T>) query.setFirstResult(
				currentPage * pageSize).setMaxResults(pageSize).getResultList();
	}

	@Override
	public void init(int pageSize, Query query) {
		
		this.pageSize = pageSize;
        this.query = query;
        maxResults = contagem();
        currentPage = 0;
	}
	
	public void init(int pageSize) {
		
		this.pageSize = pageSize;
        maxResults = contagem();
        currentPage = 0;
	}

	public AbstractResultPager() {
		em = PersistenceServiceFactory.getInstance().getManager();
	}

	public int getPageSize() {
		return pageSize;
	}

	public EntityManager getEntityManager() {
		return em;
	}

	@Override
	public int getMaxPages() {
		return maxResults / pageSize;
	}

	

	@Override
	public void proximaPagina() {
		currentPage++;
		if(currentPage > getMaxPages()){
			currentPage = getMaxPages();
		}
	}

	public void paginaAnterior() {
		currentPage--;
		if (currentPage < 0) {
			currentPage = 0;
		}
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public Query getQuery() {
		return query;
	}

	public void setQuery(Query query) {
		this.query = query;
	}

}
