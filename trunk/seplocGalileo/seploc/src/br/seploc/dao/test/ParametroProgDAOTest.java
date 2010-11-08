package br.seploc.dao.test;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import br.seploc.dao.ParametroProgDAO;
import br.seploc.dao.exceptions.PrimaryKeyException;
import br.seploc.dao.exceptions.RecordNotFound;
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
		p.setDescricao("teste de novo22223");
		
		dao.altera(p);
		
		p = null;
		p = dao.recupera("teste1");
		Assert.assertNotNull(p);
		Assert.assertTrue(p.getCodParametro().equals("teste1"));
		Assert.assertTrue(p.getDescricao().equals("teste de novo22223"));
		Assert.assertTrue(p.getValor().equals("tesla1"));
	}

	@Test
	public void testRemoveString() {
		ParametroProgDAO dao = new ParametroProgDAO();
		try {
			dao.remove("1");
			ParametroProg p = dao.recupera("1");
			Assert.assertNull(p);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testRemoveRecordNotFound() {
		ParametroProgDAO dao = new ParametroProgDAO();
		try {
			dao.remove("teseeeee");
		} catch (Exception e) {
			// RecordNotFound
			Assert.assertTrue(e instanceof RecordNotFound);
			System.err.println(e.getMessage());
		}
	}
	@Test
	public void testGetLista() {
		ParametroProgDAO dao = new ParametroProgDAO();
		List<ParametroProg> lista = dao.getLista();
		Assert.assertTrue(lista.size() == 4);
		for (ParametroProg p : lista){
			System.out.println(p);
		}
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
