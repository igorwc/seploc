package br.seploc.dao.test;

import static org.junit.Assert.fail;

import org.junit.Test;

import br.seploc.dao.CobradorDAO;
import br.seploc.pojos.Cobrador;

public class CobradorDAOTest {

	@Test
	public final void testAdicionaCobrador() {
		CobradorDAO dao = new CobradorDAO();
		Cobrador cobrador = new Cobrador();
		cobrador.setNome("teste");
		cobrador.setFone("1111-11111");
		
		dao.adiciona(cobrador);
		cobrador = null;
		cobrador = dao.recupera(1);
	}

	@Test
	public final void testAlteraCobrador() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testRecuperaInteger() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testRemoveInteger() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetLista() {
		fail("Not yet implemented"); // TODO
	}

}
