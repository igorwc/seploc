package br.seploc.dao.pagedqueries.test;

import static org.junit.Assert.*;

import org.junit.Test;

import br.seploc.dao.pagedqueries.AllClientsPager;
import br.seploc.dao.pagedqueries.FilteredProjectsPager;

public class FilteredProjectsPagerTest {

	@Test
	public void testContagem() {
		FilteredProjectsPager fpp = new FilteredProjectsPager();
		fpp.setClientId(39);
		fpp.init(0, null);
		System.out.println(fpp.contagem());
	}

	@Test
	public void testGetCurrentResults() {
		fail("Not yet implemented");
	}

	@Test
	public void testInit() {
		fail("Not yet implemented");
	}

	@Test
	public void testAbstractResultPager() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPageSize() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetEntityManager() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetMaxPages() {
		fail("Not yet implemented");
	}

	@Test
	public void testProximaPagina() {
		fail("Not yet implemented");
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
