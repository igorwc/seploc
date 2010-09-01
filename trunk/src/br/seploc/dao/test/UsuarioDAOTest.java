package br.seploc.dao.test;

import static org.junit.Assert.fail;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import br.seploc.dao.GrupoDAO;
import br.seploc.dao.UsuarioDAO;
import br.seploc.dao.exceptions.LoginInsertException;
import br.seploc.pojos.Grupo;
import br.seploc.pojos.Usuario;

public class UsuarioDAOTest {

	@Test
	public final void testAdicionaUsuario() throws LoginInsertException {
		UsuarioDAO dao = new UsuarioDAO();
		Usuario usuario = new Usuario("xuxu", "xuxu da silva", "nova", "12312312323", 0, "192.186.123.12");
		dao.adiciona(usuario);
		usuario = null;
		usuario = dao.recupera(1);
		
		Assert.assertNotNull(usuario);
		Assert.assertEquals("xuxu", usuario.getLogin());
		Assert.assertEquals("xuxu da silva", usuario.getNome());
		Assert.assertEquals("12312312323", usuario.getCpf());
		Assert.assertEquals("192.186.123.12", usuario.getIpMaquina() );
		
		System.out.println(usuario);
		
	}
	@Test
	public final void testAdicionaUsuarioLoginInsertException()  {
		UsuarioDAO dao = new UsuarioDAO();
		Usuario usuario = new Usuario("xuxuxu", "xuxu da silva", "nova", "12312312323", 0, "192.186.123.12");
		try {
			dao.adiciona(usuario);
		} catch (LoginInsertException e) {
			Assert.assertTrue(e instanceof LoginInsertException );
			System.err.println(e.getMessage());
		}
	}
	@Test
	public final void testAlteraUsuario() {
//		UsuarioDAO dao = new UsuarioDAO();
//		Usuario usuario = dao.recupera("xuxuxu");
//		GrupoDAO grupoDAO= new GrupoDAO();
//		Assert.assertNull(usuario.getGrupo());
//		Grupo grupo = grupoDAO.recupera(4);
//		usuario.setGrupo(grupo);
//		dao.altera(usuario);
//		
//		Assert.assertNotNull(usuario.getGrupo());
//		Assert.assertEquals(grupo, usuario.getGrupo());
//		System.out.println(usuario);
	}

	@Test
	public final void testRecuperaLogin() {
//		UsuarioDAO dao = new UsuarioDAO();
//		Usuario usuario = dao.recupera("igorwc");
//		
//		Assert.assertEquals("igorwc", usuario.getLogin());
//		System.out.println(usuario);
	}
	
	@Test
	public final void testRecuperaPorNome() {
		UsuarioDAO dao = new UsuarioDAO();
		List<Usuario> lista = dao.getListaUsariosPorNome("r");
		
		Assert.assertTrue(lista.size() == 2);
		
		for(Usuario item : lista){
			System.out.println(item);
		}
		
	}

	@Test
	public final void testRemoveString()  {
//		try{
//		UsuarioDAO dao = new UsuarioDAO();
//		Usuario usuario = new Usuario("xuxuxu2", "xuxu2 da silva", "nova", "12312312323", 0, "192.186.123.12");
//		dao.adiciona(usuario);
//		usuario = null;
//		usuario = dao.recupera("xuxuxu2");
//		
//		Assert.assertNotNull(usuario);
//		Assert.assertEquals("xuxuxu2", usuario.getLogin());
//		Assert.assertEquals("xuxu2 da silva", usuario.getNome());
//		Assert.assertEquals("12312312323", usuario.getCpf());
//		Assert.assertEquals("192.186.123.12", usuario.getIpMaquina() );
//		
//		dao.remove(usuario.getLogin());
//		usuario = null;
//		usuario = dao.recupera("xuxuxu2");
//		Assert.assertNull(usuario);
//		}catch (Exception e) {
//			System.err.println(e.getMessage());
//		}
	}

	@Test
	public final void testGetLista() {
		UsuarioDAO dao = new UsuarioDAO();
		List<Usuario> lista = dao.getLista();
		
		Assert.assertTrue(lista.size() == 3);
		
		for(Usuario item : lista){
			System.out.println(item);
		}
	}

	@Test
	public final void testGetListaUsariosPorGrupo() {
		UsuarioDAO dao = new UsuarioDAO();
		List<Usuario> lista = dao.getListaUsariosPorGrupo(1);
		
		Assert.assertTrue(lista.size() == 1);
		
		for(Usuario item : lista){
			System.out.println(item);
		}
		
        lista = dao.getListaUsariosPorGrupo(2);
		
		Assert.assertTrue(lista.size() == 2);
		
		for(Usuario item : lista){
			System.out.println(item);
		}
	}

	@Test
	public final void testGetListaRequisicoesPorUsuario() {
		fail("Not yet implemented"); // TODO
	}

}
