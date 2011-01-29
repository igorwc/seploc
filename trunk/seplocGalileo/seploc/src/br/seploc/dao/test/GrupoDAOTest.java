package br.seploc.dao.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import br.seploc.dao.GrupoDAO;
import br.seploc.dao.exceptions.ParentDeleteException;
import br.seploc.dao.exceptions.RecordNotFound;
import br.seploc.pojos.Grupo;

public class GrupoDAOTest {

	@Test
	public final void testAdicionaGrupo() {
		GrupoDAO dao = new GrupoDAO();
		Grupo grupo = new Grupo();
		grupo.setNomeGrupo("grupo teste");
		dao.adiciona(grupo);

		grupo = null;
		grupo = dao.recupera(3);
		assertNotNull(grupo);
		assertEquals("grupo teste", grupo.getNomeGrupo());

	}

	@Test
	public final void testAlteraGrupo() {
		GrupoDAO dao = new GrupoDAO();
		Grupo grupo = dao.recupera(3);
		assertNotNull(grupo);
		assertEquals("grupo teste", grupo.getNomeGrupo());
		grupo.setNomeGrupo("teste grupo");

		dao.altera(grupo);

		grupo = null;

		grupo = dao.recupera(3);
		assertNotNull(grupo);
		assertEquals("teste grupo", grupo.getNomeGrupo());
	}

	@Test
	public final void testRecuperaInteger() {
		GrupoDAO dao = new GrupoDAO();
		Grupo grupo = dao.recupera(1);
		assertNotNull(grupo);
		org.junit.Assert.assertEquals("Sistema", grupo.getNomeGrupo());

	}

	@Test
	public final void testRemoveInteger() throws Exception {
		GrupoDAO dao = new GrupoDAO();
		Grupo grupo = dao.recupera(3);
		assertNotNull(grupo);
		dao.remove(grupo.getCodGrupo());

		grupo = null;
		grupo = dao.recupera(3);

		Assert.assertNull(grupo);
	}

	@Test
	public final void testRemoveRecordNotFound() {
		GrupoDAO dao = new GrupoDAO();

		try {
			dao.remove(25);
		} catch (Exception e) {
			Assert.assertTrue(e instanceof RecordNotFound);
		}
	}

	@Test
	public final void testRemoveParentDeleteException() {
		GrupoDAO dao = new GrupoDAO();
		Grupo grupo = dao.recupera(4);
		assertNotNull(grupo);

		try {
			dao.remove(grupo.getCodGrupo());
		} catch (Exception e) {
			Assert.assertTrue(e instanceof ParentDeleteException);
		}
	}

	@Test
	public final void testGetLista() {
		GrupoDAO dao = new GrupoDAO();
		List<Grupo> lista = dao.getLista();

		for (Grupo item : lista) {
			System.out.println(item);
		}
		Assert.assertTrue(lista.size() == 2);
	}
	@Test
	public final void testPermissoes() {
		GrupoDAO dao = new GrupoDAO();
		Grupo  g = dao.recupera(3);
		System.out.println(g.retornaPermissoes());
		g = dao.recupera(2);
		System.out.println(g.retornaPermissoes());
	}
}
