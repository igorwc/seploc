package br.seploc.dao.pagedqueries.test;

import static org.junit.Assert.fail;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import br.seploc.dao.pagedqueries.FilteredNameClientesPager;
import br.seploc.pojos.Cliente;

public class FilteredNameClientsPagerTest {

	@Test
	public void testContagem() {
		FilteredNameClientesPager dd = new FilteredNameClientesPager();
		dd.setClientFilter("lia");
		Assert.assertEquals(221, dd.contagem());
	}

	@Test
	public void testGetClientFilter() {
		FilteredNameClientesPager clientePager = new FilteredNameClientesPager("alcoa");
		clientePager.init(10);
		List<Cliente> retorno = clientePager.getCurrentResults();
		System.out.println(retorno.size());
		for(Cliente c : retorno){
			System.out.println(c);
		}
	}

	@Test
	public void testSetClientFilter() {
		fail("Not yet implemented");
	}

}
