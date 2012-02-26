package br.seploc.util;

import javax.persistence.EntityManager;

public abstract class GenericService {
	protected EntityManager em;

	public GenericService() {
		em = PersistenceServiceFactory.getInstance().getManager();
	}
	
	
}
