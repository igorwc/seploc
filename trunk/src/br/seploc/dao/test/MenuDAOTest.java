package br.seploc.dao.test;

import static org.junit.Assert.*;

import org.junit.Test;

import br.seploc.dao.MenuDAO;
import br.seploc.pojos.Menu;

public class MenuDAOTest {

	@Test
	public void testAdicionaMenu() {
		MenuDAO dao = new MenuDAO();
		Menu menu = new Menu();
		menu.setArquivo("teste.txt");
		menu.setCodMenu('Z');
		menu.setComentario("Comentario Qualquer");// Criar
		menu.setRotulo("Criar");
		menu.setTextoAlt("texto alternativo");
		menu.setImagem("arquivo.jpg");
		dao.adiciona(menu);
		menu = null;
		menu = dao.recupera('Z');

		assertEquals("Comentario Qualquer", menu.getComentario());
		assertEquals("Criar", menu.getRotulo());
		assertEquals("texto alternativo", menu.getTextoAlt());
		assertEquals("teste.txt", menu.getArquivo());
		assertEquals("arquivo.jpg", menu.getImagem());
		assertEquals('Z', menu.getCodMenu().charValue());
	}
	@Test
	public void testAlteraMenu() {
		MenuDAO dao = new MenuDAO();
		Menu menu = dao.recupera('Z');
		assertNotNull(menu);
		menu.setRotulo("Criar2");
		menu.setArquivo("teste.zip");
		dao.altera(menu);
		menu = null;
		menu = dao.recupera('Z');
		assertEquals("Criar2", menu.getRotulo());
		assertEquals("teste.zip", menu.getArquivo());
	}
	@Test
	public void testRecuperaInteger() {
		MenuDAO dao = new MenuDAO();
		Menu menu = dao.recupera('A');
		assertNotNull(menu);
		assertEquals("requisicao.jpg", menu.getImagem());
	}
	@Test
	public void testRemove() throws Exception {
		MenuDAO dao = new MenuDAO();
		Menu menu = dao.recupera('Z');
		assertNotNull(menu);
		dao.remove(menu.getCodMenu());

		menu = dao.recupera('Z');
		assertNull(menu);
	}
	@Test
	public void testGetLista() {
		fail("Not yet implemented");
	}

}
