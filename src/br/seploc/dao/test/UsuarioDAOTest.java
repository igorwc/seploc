package br.seploc.dao.test;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Test;

import br.seploc.dao.UsuarioDAO;
import br.seploc.pojos.Usuario;

public class UsuarioDAOTest {

	@Test
	public final void testAdicionaUsuario() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testAlteraUsuario() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testRecuperaInteger() {
		UsuarioDAO dao = new UsuarioDAO();
		Usuario usuario = dao.recupera("igorwc");
		
		Assert.assertEquals("igorwc", usuario.getLogin());
		System.out.println(usuario);
	}

	@Test
	public final void testRemoveInteger() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testRemoveString() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetLista() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetListaUsariosPorGrupo() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetListaRequisicoesPorUsuario() {
		fail("Not yet implemented"); // TODO
	}

}
