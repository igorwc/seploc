package br.seploc.dao.test;

import static org.junit.Assert.fail;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import br.seploc.dao.GrupoDAO;
import br.seploc.dao.UsuarioDAO;
import br.seploc.dao.exceptions.LoginExistenteException;
import br.seploc.pojos.Grupo;
import br.seploc.pojos.Usuario;

public class UsuarioDAOTest {

	@Test
	public final void testAdicionaUsuario() throws LoginExistenteException {
		UsuarioDAO dao = new UsuarioDAO();
		Usuario usuario = new Usuario("igor", "sdfsdf da silva", "nova",
				"12312312323", 0, "192.186.123.12");
		dao.adiciona(usuario);
		usuario = null;
		usuario = dao.recupera(1);

		Assert.assertNotNull(usuario);
		Assert.assertEquals("igor", usuario.getLogin());
		Assert.assertEquals("sdfsdf da silva", usuario.getNome());
		Assert.assertEquals("12312312323", usuario.getCpf());
		Assert.assertEquals("192.186.123.12", usuario.getIpMaquina());

		System.out.println(usuario);

	}

	@Test
	public final void testAdicionaUsuarioLoginInsertException() {
		UsuarioDAO dao = new UsuarioDAO();
		Usuario usuario = new Usuario("xuxuxu", "xuxu da silva", "nova",
				"12312312323", 0, "192.186.123.12");
		try {
			dao.adiciona(usuario);
		} catch (LoginExistenteException e) {
			Assert.assertTrue(e instanceof LoginExistenteException);
			System.err.println(e.getMessage());
		}
	}

	@Test
	public final void testAlteraUsuario() {
		// UsuarioDAO dao = new UsuarioDAO();
		// Usuario usuario = dao.recupera("xuxuxu");
		// GrupoDAO grupoDAO= new GrupoDAO();
		// Assert.assertNull(usuario.getGrupo());
		// Grupo grupo = grupoDAO.recupera(4);
		// usuario.setGrupo(grupo);
		// dao.altera(usuario);
		//		
		// Assert.assertNotNull(usuario.getGrupo());
		// Assert.assertEquals(grupo, usuario.getGrupo());
		// System.out.println(usuario);
	}

	@Test
	public final void testRecuperaLogin() {
		// UsuarioDAO dao = new UsuarioDAO();
		// Usuario usuario = dao.recupera("igorwc");
		//		
		// Assert.assertEquals("igorwc", usuario.getLogin());
		// System.out.println(usuario);
	}

	@Test
	public final void testRecuperaPorNome() {
		UsuarioDAO dao = new UsuarioDAO();
		List<Usuario> lista = dao.getListaUsuariosPorNome("r");

		Assert.assertTrue(lista.size() == 2);

		for (Usuario item : lista) {
			System.out.println(item);
		}

	}

	@Test
	public final void testRemoveString() {
		// try{
		// UsuarioDAO dao = new UsuarioDAO();
		// Usuario usuario = new Usuario("xuxuxu2", "xuxu2 da silva", "nova",
		// "12312312323", 0, "192.186.123.12");
		// dao.adiciona(usuario);
		// usuario = null;
		// usuario = dao.recupera("xuxuxu2");
		//		
		// Assert.assertNotNull(usuario);
		// Assert.assertEquals("xuxuxu2", usuario.getLogin());
		// Assert.assertEquals("xuxu2 da silva", usuario.getNome());
		// Assert.assertEquals("12312312323", usuario.getCpf());
		// Assert.assertEquals("192.186.123.12", usuario.getIpMaquina() );
		//		
		// dao.remove(usuario.getLogin());
		// usuario = null;
		// usuario = dao.recupera("xuxuxu2");
		// Assert.assertNull(usuario);
		// }catch (Exception e) {
		// System.err.println(e.getMessage());
		// }
	}

	@Test
	public final void testGetLista() {
		UsuarioDAO dao = new UsuarioDAO();
		List<Usuario> lista = dao.getLista();

		Assert.assertTrue(lista.size() == 3);

		for (Usuario item : lista) {
			System.out.println(item);
		}
	}

	@Test
	public final void testGetListaUsariosPorGrupo() {
		UsuarioDAO dao = new UsuarioDAO();
		List<Usuario> lista = dao.getListaUsuariosPorGrupo(1);

		Assert.assertTrue(lista.size() == 1);

		for (Usuario item : lista) {
			System.out.println(item);
		}

		lista = dao.getListaUsuariosPorGrupo(2);

		Assert.assertTrue(lista.size() == 2);

		for (Usuario item : lista) {
			System.out.println(item);
		}
	}

	@Test
	public final void testGetListaRequisicoesPorUsuario() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetUsuarioCriadorPorReqServ() {
		UsuarioDAO dao = new UsuarioDAO();
		Usuario u = dao.getUsuarioCriadorPorReqServ(73635);
		Assert.assertTrue(u.getLogin().equals("igor"));
		System.out.println(u);
	}

	@Test
	public final void testGetUsuarioAlteradorPorReqServ() {
		UsuarioDAO dao = new UsuarioDAO();
		Usuario u = dao.getUsuarioAlteradorPorReqServ(73635);
		Assert.assertTrue(u.getLogin().equals("ghmarinho"));
		System.out.println(u);
	}
}
