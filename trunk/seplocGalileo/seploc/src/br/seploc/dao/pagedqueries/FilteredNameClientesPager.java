package br.seploc.dao.pagedqueries;

import java.io.Serializable;

import javax.persistence.Query;

import br.seploc.pojos.Cliente;

public class FilteredNameClientesPager extends AbstractResultPager<Cliente> {

	private String clientFilter;

	@Override
	public int contagem() {
		Query query = em.createNativeQuery(
				"SELECT COUNT(*) FROM tbl_clientes where  UPPER(vcrFantasia) like  :nome and intBalcao = 0 ")
				.setParameter("nome", "%"+clientFilter.toUpperCase()+"%"); 
		Number countResult = (Number) query.getSingleResult();
		return countResult.intValue();
	}

	public String getClientFilter() {
		return clientFilter;
	}

	public void setClientFilter(String clientFilter) {
		this.clientFilter = clientFilter;
	}

	public FilteredNameClientesPager() {
		this.clientFilter = "";
		Query query = em.createNamedQuery("Cliente.BuscaClientesPorFantasia")
		.setParameter("nome", "%"+clientFilter.toUpperCase()+"%");
		this.setQuery(query);
	}

	public FilteredNameClientesPager(String clientFilter) {
		this.clientFilter = clientFilter;
		Query query =  em.createNamedQuery("Cliente.BuscaClientesPorFantasia")
		.setParameter("nome", "%"+clientFilter.toUpperCase()+"%");
		this.setQuery(query);
	}

	
}
