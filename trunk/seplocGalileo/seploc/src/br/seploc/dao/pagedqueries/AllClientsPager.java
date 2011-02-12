package br.seploc.dao.pagedqueries;

import javax.persistence.Query;

import br.seploc.pojos.Cliente;

public class AllClientsPager extends AbstractResultPager<Cliente> {

	@Override
	public int contagem() {
		Query query= em.createNativeQuery("SELECT COUNT(*) FROM tbl_clientes");
		Number countResult=(Number) query.getSingleResult();
		return countResult.intValue();
	}

	public AllClientsPager() {
		super();
	}

	 

}
