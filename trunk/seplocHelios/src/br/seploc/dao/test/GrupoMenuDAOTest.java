package br.seploc.dao.test;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import br.seploc.dao.GrupoDAO;
import br.seploc.dao.GrupoMenuDAO;
import br.seploc.dao.MenuDAO;
import br.seploc.dao.exceptions.RecordNotFound;
import br.seploc.pojos.Grupo;
import br.seploc.pojos.GrupoMenu;
import br.seploc.pojos.Menu;

public class GrupoMenuDAOTest {

	@Test
	public final void testAdicionaGrupoMenu() {

		MenuDAO daoMenu = new MenuDAO();
		Menu menu = new Menu("N", daoMenu.recupera(1), 0, 3, "##", "Teste");
		daoMenu.adiciona(menu);
		menu = daoMenu.recupera(19);
		GrupoDAO daoGrupo = new GrupoDAO();
		Grupo grupo = new Grupo("Grupooo");
		daoGrupo.adiciona(grupo);
		grupo = daoGrupo.recupera(4);
		GrupoMenuDAO dao = new GrupoMenuDAO();
		GrupoMenu grupoMenu = new GrupoMenu(menu, grupo, 'N');
		dao.adiciona(grupoMenu);
		Assert.assertNotNull(menu);
		Assert.assertNotNull(grupo);
		grupoMenu = dao.recupera(menu, grupo);
		Assert.assertNotNull(grupoMenu);
		System.out.println(grupoMenu);
	}

	@Test
	public final void testAlteraGrupoMenu() {
		MenuDAO daoMenu = new MenuDAO();
		Menu menu = daoMenu.recupera(19);
		GrupoDAO daoGrupo = new GrupoDAO();
		Grupo grupo = daoGrupo.recupera(4);
		GrupoMenuDAO dao = new GrupoMenuDAO();
		GrupoMenu grupoMenu = dao.recupera(menu, grupo);
		grupoMenu.setEscrita('S');
		dao.altera(grupoMenu);
		grupoMenu = null;
		grupoMenu = dao.recupera(menu, grupo);
		Assert.assertNotNull(grupoMenu);
		Assert.assertEquals('S', grupoMenu.getEscrita().charValue());
		System.out.println(grupoMenu);
	}

	@Test
	public final void testRecuperaMenuIDGrupoID() {
		GrupoMenuDAO dao = new GrupoMenuDAO();
		GrupoMenu grupoMenu = dao.recupera(3, 1);
		MenuDAO daoMenu = new MenuDAO();
		Menu menu = daoMenu.recupera(3);
		GrupoDAO daoGrupo = new GrupoDAO();
		Grupo grupo = daoGrupo.recupera(1);

		Assert.assertEquals(3, grupoMenu.getMenuId().intValue());
		Assert.assertEquals(1, grupoMenu.getGrupoId().intValue());
		Assert.assertEquals(menu, grupoMenu.getMenu());
		Assert.assertEquals(grupo, grupoMenu.getGrupo());
		System.out.println(grupoMenu);
	}

	@Test
	public final void testRecuperaMenuGrupo() {
		GrupoMenuDAO dao = new GrupoMenuDAO();
		MenuDAO daoMenu = new MenuDAO();
		Menu menu = daoMenu.recupera(3);
		GrupoDAO daoGrupo = new GrupoDAO();
		Grupo grupo = daoGrupo.recupera(1);
		GrupoMenu grupoMenu = dao.recupera(menu, grupo);
		Assert.assertEquals(3, grupoMenu.getMenuId().intValue());
		Assert.assertEquals(1, grupoMenu.getGrupoId().intValue());
		Assert.assertEquals(menu, grupoMenu.getMenu());
		Assert.assertEquals(grupo, grupoMenu.getGrupo());
		System.out.println(grupoMenu);
	}

	@Test
	public final void testRemove() throws Exception {
		MenuDAO daoMenu = new MenuDAO();
		Menu menu = daoMenu.recupera(19);
		GrupoDAO daoGrupo = new GrupoDAO();
		Grupo grupo = daoGrupo.recupera(4);
		GrupoMenuDAO dao = new GrupoMenuDAO();
		GrupoMenu grupoMenu = dao.recupera(menu, grupo);
		dao.remove(grupoMenu);
		grupoMenu = null;

		grupoMenu = dao.recupera(menu, grupo);

		Assert.assertNull(grupoMenu);
	}

	@Test
	public final void testRemoveRecordNotFound() {
		GrupoMenuDAO dao = new GrupoMenuDAO();
		try {
			dao.remove(25, 3);
		} catch (Exception e) {
			Assert.assertTrue(e instanceof RecordNotFound);
		}
	}

	@Test
	public final void testGetLista() {
		GrupoMenuDAO dao = new GrupoMenuDAO();
		List<GrupoMenu> lista = dao.getLista();

		for (GrupoMenu item : lista) {
			System.out.println(item);
		}
		Assert.assertTrue(lista.size() == 32);
	}

	@Test
	public final void testGetFilterByMenu() {
		GrupoMenuDAO dao = new GrupoMenuDAO();
		MenuDAO daoMenu = new MenuDAO();
		Menu menu = daoMenu.recupera(1);
		List<GrupoMenu> lista = dao.getFilterByMenu(menu);

		for (GrupoMenu item : lista) {
			System.out.println(item);
		}
		Assert.assertTrue(lista.size() == 2);
	}

	@Test
	public final void testGetFilterByGrupo() {
		GrupoMenuDAO dao = new GrupoMenuDAO();
		GrupoDAO daoMenu = new GrupoDAO();
		Grupo grupo = daoMenu.recupera(1);
		List<GrupoMenu> lista = dao.getFilterByGrupo(grupo);

		for (GrupoMenu item : lista) {
			System.out.println(item);
		}
		Assert.assertTrue(lista.size() == 16);
	}

}
