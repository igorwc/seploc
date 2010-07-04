package br.seploc.dao.test;

import static org.junit.Assert.fail;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import br.seploc.dao.PapelDAO;
import br.seploc.pojos.Papel;

public class PapelDAOTest {

	@Test
	public final void testAdicionaPapel() {
		PapelDAO dao = new PapelDAO();
		Papel papel = new Papel("A7", 1.2,2.3,4.5);
		dao.adiciona(papel);
		List<Papel> lista = dao.getListaPapelPorNome("A7");
		boolean teste = false;
		for(Papel p : lista){
			System.out.println(p);
			if(p.getNome().equals("A7")){
				teste = true;
				papel = p;
			}
		}
		
		Assert.assertTrue(teste);
		Assert.assertEquals(1.2,papel.getImpMono());
	}

	@Test
	public final void testAlteraPapel() {
		PapelDAO dao = new PapelDAO();
		Papel papel = dao.recupera(7);
		papel.setNome("B4");
		papel.setImpShade(10.33);
		dao.altera(papel);
		papel = null;
		
		papel = dao.recupera(7);
		Assert.assertNotNull(papel);
		Assert.assertEquals(10.33, papel.getImpShade().doubleValue());
		Assert.assertEquals("B4", papel.getNome());
	}

	@Test
	public final void testRecuperaInteger() {
		PapelDAO dao = new PapelDAO();
		Papel papel = dao.recupera(1);
		
		Assert.assertNotNull(papel);
		Assert.assertEquals("a3", papel.getNome());
		Assert.assertEquals(2.5, papel.getImpMono());
		Assert.assertEquals(1,papel.getCodPapel().intValue());
		System.out.println(papel);
	}

	@Test
	public final void testRemoveInteger() throws Exception {
		PapelDAO dao = new PapelDAO();
		Papel papel = dao.recupera(6);
		Assert.assertNotNull(papel);
		dao.remove(papel.getCodPapel());
		papel = dao.recupera(6);
		Assert.assertNull(papel);

	}

	@Test
	public final void testGetLista() {
		fail("Not yet implemented"); // TODO
	}

}
