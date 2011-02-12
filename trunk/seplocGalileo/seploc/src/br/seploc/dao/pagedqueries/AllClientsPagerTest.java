package br.seploc.dao.pagedqueries;

import static org.junit.Assert.fail;

import java.util.List;

import javax.persistence.Query;

import org.junit.Test;

import br.seploc.pojos.Cliente;
import br.seploc.util.PersistenceServiceFactory;

public class AllClientsPagerTest {

	@Test
	public void testContagem() {
		AllClientsPager acp = new AllClientsPager();
		acp.init(0, null);
		System.out.println(acp.contagem());
	}

	@Test
	public void testGetCurrentResults() {
		AllClientsPager acp = new AllClientsPager();
		Query q = acp.getEntityManager().createNamedQuery(
				"Cliente.RetornaClientes");
		acp.init(16, q);
		System.out.println(acp.contagem());
		List<Cliente> lista = acp.getCurrentResults();
		for (Cliente c : lista) {
			System.out.println(c);
		}
	}

	@Test
	public void testInit() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPageSize() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetMaxPages() {
		fail("Not yet implemented");
	}

	@Test
	public void testProximaPagina() {
		AllClientsPager acp = new AllClientsPager();
		Query q = acp.getEntityManager().createNamedQuery(
				"Cliente.RetornaClientes");
		acp.init(16, q);
		System.out.println(acp.contagem());
		List<Cliente> lista = acp.getCurrentResults();
		for (Cliente c : lista) {
			System.out.println(c);
		}
		acp.proximaPagina();
		lista = acp.getCurrentResults();
		for (Cliente c : lista) {
			System.out.println(c);
		}
	}

	@Test
	public void testPaginaAnterior() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCurrentPage() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetCurrentPage() {
		fail("Not yet implemented");
	}

}
