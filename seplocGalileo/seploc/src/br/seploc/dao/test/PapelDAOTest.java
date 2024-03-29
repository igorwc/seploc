package br.seploc.dao.test;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import br.seploc.dao.PapelDAO;
import br.seploc.dao.exceptions.ParentDeleteException;
import br.seploc.pojos.Papel;

public class PapelDAOTest {

	@Test
	public final void testAdicionaPapel() {
		PapelDAO dao = new PapelDAO();
		Papel papel = new Papel("A3", 2.2,2.3,4.5);
		dao.adiciona(papel);
		List<Papel> lista = dao.getListaPapelPorNome("A3");
		boolean teste = false;
		for(Papel p : lista){
			System.out.println(p);
			if(p.getNome().equals("A3")){
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
	public final void testExistePapel(){
		PapelDAO dao = new PapelDAO();
		Papel papel = new Papel();
		papel.setNome("NN");
		papel.setImpColor(10.0);
		boolean r = dao.existe(papel);
		
		Assert.assertEquals(true,r);
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
	public final void testRemoveInteger() {
		PapelDAO dao = new PapelDAO();
		Papel papel = dao.recupera(34);
		Assert.assertNotNull(papel);
		
		try {
			dao.remove(papel.getCodPapel());
		} catch (Exception e) {
			Assert.assertTrue(e instanceof ParentDeleteException);

			System.err.println(e.getMessage());
		}
		papel = dao.recupera(6);
		Assert.assertNull(papel);

	}
	
	@Test
	public final void testRemoveException() throws Exception {
		PapelDAO dao = new PapelDAO();
		Papel papel = dao.recupera(34);
		Assert.assertNotNull(papel);
		dao.remove(papel.getCodPapel());
		papel = dao.recupera(6);
		Assert.assertNull(papel);

	}

	@Test
	public final void testGetLista() {
		PapelDAO papelDAO = new PapelDAO();
		List<Papel> retorno = papelDAO.getLista();
		for (Papel l : retorno){
			System.out.println(l.getNome());
		}
	}
	@Test
	public final void testGetListaSohPapeis() {
		PapelDAO papelDAO = new PapelDAO();
		List<Papel> retorno = papelDAO.getListaSohPapeis();
		for (Papel l : retorno){
			System.out.println(l.getNome());
		}
	}
	
	@Test
	public final void testGetListaLona() {
		PapelDAO papelDAO = new PapelDAO();
		List<Papel> retorno = papelDAO.getListaLona();
		for (Papel l : retorno){
			System.out.println(l.getNome());
		}
	}

	@Test
	public final void testGetEhPapel() {
		PapelDAO papelDAO = new PapelDAO();
		Papel p1 = papelDAO.recupera(6);
	    System.out.println(p1.getNome() + " Eh papel? " + p1.getEhPapel());
		Papel p2 = papelDAO.recupera(98);
	    System.out.println(p2.getNome() + " Eh papel? " + p2.getEhPapel());
		
	}
	
	
	@Test
	public final void testGetListaLonaPorNome() {
		PapelDAO papelDAO = new PapelDAO();
		List<Papel> retorno = papelDAO.getLista();
		for (Papel l : retorno){
			System.out.println(l.getNome());
		}
	}
	public final void testGetListaPapelPorNome() {
		PapelDAO papelDAO = new PapelDAO();
		List<Papel> retorno = papelDAO.getLista();
		for (Papel l : retorno){
			System.out.println(l.getNome());
		}
	}
}
