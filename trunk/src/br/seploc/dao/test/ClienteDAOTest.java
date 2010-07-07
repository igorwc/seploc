package br.seploc.dao.test;

import static org.junit.Assert.fail;
import junit.framework.Assert;

import org.junit.Test;

import br.seploc.dao.ClienteDAO;
import br.seploc.pojos.Cliente;

public class ClienteDAOTest {

	@Test
	public final void testAdicionaCliente() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testAlteraCliente() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testRecuperaInteger() {
		ClienteDAO dao = new ClienteDAO();
		Cliente c = dao.recupera(1);
		
//		Assert.assertNotNull(c);
//		Assert.assertTrue(c.getRazao().toUpperCase().startsWith("IGOR"));
		System.out.println(c);
	}

	@Test
	public final void testRemoveInteger() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetLista() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetClientesPorNome() {
		fail("Not yet implemented"); // TODO
	}

}
