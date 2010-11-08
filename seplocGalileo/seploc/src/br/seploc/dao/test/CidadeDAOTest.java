package br.seploc.dao.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import br.seploc.dao.CidadeDAO;
import br.seploc.pojos.Cidade;

public class CidadeDAOTest {

	@Test
	public void testAdicionaCidade() {
		fail("Not yet implemented");
	}

	@Test
	public void testRecuperaInteger() {
		fail("Not yet implemented");
	}

	@Test
	public void testAlteraCidade() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveInteger() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetLista() {
		CidadeDAO cidadeDAO = new CidadeDAO();
		List<Cidade> retorno = cidadeDAO.getLista();
		for(Cidade c : retorno){
			System.out.println(c);
		}
	}

	@Test
	public void testGetCidadePorNome() {
		CidadeDAO cidadeDAO = new CidadeDAO();
		List<Cidade> retorno = cidadeDAO.recuperaCidadesPorNome("jo");
		for(Cidade c : retorno){
			System.out.println(c.getNome());
		}
	}
	@Test
	public void testRecuperaCidadesPorEstado() {
		fail("Not yet implemented");
	}

}
