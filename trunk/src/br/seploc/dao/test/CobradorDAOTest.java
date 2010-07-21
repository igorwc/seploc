package br.seploc.dao.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import br.seploc.dao.CobradorDAO;
import br.seploc.dao.exceptions.ParentDeleteException;
import br.seploc.dao.exceptions.RecordNotFound;
import br.seploc.pojos.Cobrador;

public class CobradorDAOTest {

	@Test
	public final void testAdicionaCobrador() {
		CobradorDAO dao = new CobradorDAO();
		Cobrador cobrador = new Cobrador();
		cobrador.setNome("teste23");
		cobrador.setFoneContato("3333-2222");

		dao.adiciona(cobrador);
		cobrador = null;
		cobrador = dao.recupera(4);
		assertNotNull(cobrador);
		assertEquals("teste23", cobrador.getNome());
		assertEquals("3333-2222", cobrador.getFoneContato());
	}

	@Test
	public final void testAlteraCobrador() {
		CobradorDAO dao = new CobradorDAO();
		Cobrador c = dao.recupera(4);
		assertNotNull(c);
		c.setNome("teste da silva");
		c.setFoneContato("3333-3333");
		dao.altera(c);
		c = null;
		c = dao.recupera(4);
		assertEquals("teste da silva", c.getNome());
		assertEquals("3333-3333", c.getFoneContato());
	}

	@Test
	public final void testRecuperaInteger() {
		CobradorDAO dao = new CobradorDAO();
		Cobrador c = dao.recupera(4);
		assertNotNull(c);
		assertEquals("teste da silva", c.getNome());
		assertEquals("3333-3333", c.getFoneContato());

	}

	@Test
	public final void testRemoveInteger() throws Exception {
		CobradorDAO dao = new CobradorDAO();
		Cobrador c = null;
		dao.remove(6);
		c = dao.recupera(6);
		Assert.assertNull(c);
	}

	@Test
	public final void testRemoveRecordNotFound() {
		CobradorDAO dao = new CobradorDAO();
		Cobrador c = null;
		try {
			dao.remove(77);
		} catch (Exception e) {
			Assert.assertTrue(e instanceof RecordNotFound);

			System.err.println(e.getMessage());
		}
		c = dao.recupera(5);
		Assert.assertNull(c);
	}

	@Test
	public final void testRemoveStatusConbranca() {
		CobradorDAO dao = new CobradorDAO();
		try {
			dao.remove(3);
		} catch (Exception e) {
			Assert.assertTrue(e instanceof ParentDeleteException);

			System.err.println(e.getMessage());
		}
	}

	@Test
	public final void testRemoveSaidaMotoqueiro() {
		CobradorDAO dao = new CobradorDAO();
		try {
			dao.remove(1);
		} catch (Exception e) {
			Assert.assertTrue(e instanceof ParentDeleteException);

			System.err.println(e.getMessage());
		}
	}

	@Test
	public final void testGetLista() {
		CobradorDAO dao = new CobradorDAO();
		List<Cobrador> lista = dao.getLista();
		Assert.assertTrue(lista.size() == 4);
		for (Cobrador c : lista) {
			System.out.println(c);
		}
	}

	@Test
	public final void testRecuperaPorNomeCobrador() {
		CobradorDAO dao = new CobradorDAO();
		List<Cobrador> lista = dao.recuperaPorNome("igor");
		Assert.assertTrue(lista.size() == 1);

		lista = dao.recuperaPorNome("teste");
		Assert.assertTrue(lista.size() == 3);
	}
}
