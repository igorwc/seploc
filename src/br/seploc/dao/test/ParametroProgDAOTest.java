package br.seploc.dao.test;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Test;

import br.seploc.dao.ParametroProgDAO;
import br.seploc.dao.exceptions.PrimaryKeyException;
import br.seploc.pojos.ParametroProg;

public class ParametroProgDAOTest {

	@Test
	public void testAdicionaParametroProg() throws Exception {
		ParametroProgDAO dao = new ParametroProgDAO();
		ParametroProg p = new ParametroProg();
		p.setCodParametro("teste1");
		p.setDescricao("teste1");
		p.setValor("tesla1");
		
		dao.adiciona(p);
		
		p = null;
		p = dao.recupera("teste1");
		Assert.assertNotNull(p);
		Assert.assertTrue(p.getCodParametro().equals("teste1"));
		Assert.assertTrue(p.getDescricao().equals("teste1"));
		Assert.assertTrue(p.getValor().equals("tesla1"));
		
	}

	@Test
	public void testRecuperaString() throws Exception {
		ParametroProgDAO dao = new ParametroProgDAO();
		ParametroProg p = dao.recupera("teste1");
		Assert.assertNotNull(p);
		Assert.assertTrue(p.getCodParametro().equals("teste1"));
		Assert.assertTrue(p.getDescricao().equals("teste1"));
		Assert.assertTrue(p.getValor().equals("tesla1"));
		
	}

	@Test
	public void testAlteraParametroProg() throws Exception {
		ParametroProgDAO dao = new ParametroProgDAO();
		ParametroProg p = dao.recupera("teste1");
		p.setDescricao("teste de novo");
		
		dao.altera(p);
		
		p = null;
		p = dao.recupera("teste1");
		Assert.assertNotNull(p);
		Assert.assertTrue(p.getCodParametro().equals("teste1"));
		Assert.assertTrue(p.getDescricao().equals("teste de novo"));
		Assert.assertTrue(p.getValor().equals("tesla1"));
	}

	@Test
	public void testRemoveString() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetLista() {
		fail("Not yet implemented");
	}

	@Test
	public void testAjustaPojoParametroProg() {
		ParametroProgDAO dao = new ParametroProgDAO();
		ParametroProg p = new ParametroProg();
		p.setCodParametro("teste1");
		p.setDescricao("teste1");
		p.setValor("tesla1");
		
		try {
			dao.adiciona(p);
		} catch (Exception e) {
			Assert.assertTrue(e instanceof PrimaryKeyException);
			System.err.println(e.getMessage());
		}
		
		
	}

}
