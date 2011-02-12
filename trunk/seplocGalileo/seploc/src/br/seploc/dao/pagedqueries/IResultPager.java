package br.seploc.dao.pagedqueries;

import java.util.List;

import javax.persistence.Query;

public interface IResultPager<T> {
	 public void init(int pageSize,   Query reportQueryName);
	    public List<T> getCurrentResults();
	    public void proximaPagina();
	    public void paginaAnterior();
	    public int getCurrentPage();
	    public int getMaxPages();
}
