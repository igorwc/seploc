package br.seploc.dao.pagedqueries;

import javax.persistence.Query;

import br.seploc.pojos.Projeto;

public class FilteredProjectsPager extends AbstractResultPager<Projeto> {

	private int clientId;

	@Override
	public int contagem() {
		Query query = em.createNativeQuery(
				"SELECT COUNT(*) FROM tbl_projetos where intClienteId = :id")
				.setParameter("id", clientId);
		Number countResult = (Number) query.getSingleResult();
		return countResult.intValue();
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

}
