package br.seploc.dao.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import br.seploc.dao.MenuDAO;
import br.seploc.dao.exceptions.ParentDeleteException;
import br.seploc.dao.exceptions.RecordNotFound;
import br.seploc.pojos.Menu;

public class MenuDAOTest {

	@Test
	public void testAdicionaMenu() {
		MenuDAO dao = new MenuDAO();
		Menu menu = new Menu();
		menu.setMenu("Menu teste");
		menu.setAcao("###");
		menu.setHabilitado("S");
		menu.setNivelX(0);
		menu.setNivelY(3);
		menu.setMenuPai(dao.recupera(1));
		dao.adiciona(menu);
		menu = null;
		menu = dao.recupera(17);

		assertNotNull(menu);
		assertEquals("###", menu.getAcao());
		assertEquals("Menu teste", menu.getMenu());
		assertEquals("S", menu.getHabilitado());
		assertEquals(new Integer(0), menu.getNivelX());
		assertEquals(new Integer(3),  menu.getNivelY());
		assertEquals(new Integer(1), menu.getMenuPai().getCodMenu());
	}
	@Test
	public void testAlteraMenu() {
//		MenuDAO dao = new MenuDAO();
//		Menu menu = dao.recupera('Z');
//		assertNotNull(menu);
//		menu.setRotulo("Criar2");
//		menu.setArquivo("teste.zip");
//		dao.altera(menu);
//		menu = null;
//		menu = dao.recupera('Z');
//		assertEquals("Criar2", menu.getRotulo());
//		assertEquals("teste.zip", menu.getArquivo());
	}
	@Test
	public void testRecuperaInteger() {
		MenuDAO dao = new MenuDAO();
		Menu menu = dao.recupera(1);
		assertNotNull(menu);
		assertEquals("Seploc", menu.getMenu());
	}
	@Test
	public void testRemove() throws Exception {
		MenuDAO dao = new MenuDAO();
		Menu menu = dao.recupera(18);
		assertNotNull(menu);
		dao.remove(menu.getCodMenu());

		menu = dao.recupera(18);
		assertEquals(null,menu);
	}
	
	@Test
	public final void testRemoveRecordNotFound()  {
		MenuDAO dao = new MenuDAO();
	
		try {
			dao.remove(25);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			Assert.assertTrue(e instanceof RecordNotFound);
		}
	}
	
	@Test
	public final void testRemoveParentDeleteException()  {
		MenuDAO dao = new MenuDAO();
		Menu menu = dao.recupera(19);
		assertNotNull(menu);
		
		try {
			dao.remove(menu.getCodMenu());
		} catch (Exception e) {
			System.err.println(e.getMessage());
			Assert.assertTrue(e instanceof ParentDeleteException);
		}
	}
	@Test
	public void testGetLista() {
//		fail("Not yet implemented");
		MenuDAO dao = new MenuDAO();
		List<Menu> lista = dao.getLista();
		
		for(Menu item : lista){
			System.out.println(item);
		}
		
	}
	
	@Test
	public void testGetMenusRaiz() {
//		fail("Not yet implemented");
		MenuDAO dao = new MenuDAO();
		List<Menu> lista = dao.getMenusRaiz();
		
		for(Menu item : lista){
			System.out.println(item);
		}
		
	}

}
