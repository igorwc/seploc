package br.seploc.dao.test;

import br.seploc.dao.MenuDAO;
import br.seploc.pojos.Menu;
import junit.framework.TestCase;

public class MenuDAOTest extends TestCase {

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

	public void testRecuperaInteger() {
		MenuDAO dao = new MenuDAO();
		Menu menu = dao.recupera('A');
		assertNotNull(menu);
		assertEquals("requisicao.jpg", menu.getImagem());
	}

	public void testRemove() throws Exception {
		MenuDAO dao = new MenuDAO();
		Menu menu = dao.recupera('Z');
		assertNotNull(menu);
		dao.remove(menu.getCodMenu());

		menu = dao.recupera('Z');
		assertNull(menu);
	}

	public void testGetLista() {
		fail("Not yet implemented");
	}

}
