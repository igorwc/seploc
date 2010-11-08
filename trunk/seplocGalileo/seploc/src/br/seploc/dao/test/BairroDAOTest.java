package br.seploc.dao.test;

import java.util.List;

import org.junit.Test;

import br.seploc.dao.BairroDAO;
import br.seploc.pojos.Bairro;

public class BairroDAOTest {

	@Test
	public void testRecuperaBairrosPorCidade() {
		BairroDAO dao = new BairroDAO();
		List<Bairro> lista = dao.recuperaBairrosPorCidade(133);
		for(Bairro b : lista){
			System.out.println(b);
		}
	}

}
