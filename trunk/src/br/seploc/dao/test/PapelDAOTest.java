package br.seploc.dao.test;

import static org.junit.Assert.*;

import org.junit.Test;

import br.seploc.dao.PapelDAO;
import br.seploc.pojos.Papel;

public class PapelDAOTest {

	@Test
	public final void testAdicionaPapel() {
		PapelDAO dao = new PapelDAO();
		Papel papel = new Papel();
		
		papel.setNome("teste");
		papel.setImpMono(1.5);
		papel.setImpColor(2.5);
		papel.setImpShade(3.5);
		dao.adiciona(papel);
		papel = null;
		papel = dao.recupera(1);
		
		assertNotNull(papel);
	}

	@Test
	public final void testAlteraPapel() {
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
