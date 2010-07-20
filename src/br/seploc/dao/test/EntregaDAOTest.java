package br.seploc.dao.test;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import br.seploc.dao.EntregaDAO;
import br.seploc.dao.exceptions.FieldNotNullException;
import br.seploc.dao.exceptions.ParentDeleteException;
import br.seploc.dao.exceptions.RecordNotFound;
import br.seploc.pojos.Entrega;

public class EntregaDAOTest {

	@Test
	public void testAdicionaEntrega() throws Exception {
		EntregaDAO dao = new EntregaDAO();
		Entrega entrega = new Entrega();
		entrega.setLocal("Casa Azul");
		entrega.setPreco(37.50);
		dao.adiciona(entrega);
		entrega = null;
		entrega = dao.recupera(6);
		Assert.assertNotNull(entrega);
		Assert.assertTrue(entrega.getLocal().equals("Casa Azul"));
		Assert.assertTrue(entrega.getPreco().doubleValue() == 37.50);
		System.out.println(entrega);
	}

	@Test
	public void testAlteraEntrega() throws Exception {
		EntregaDAO dao = new EntregaDAO();
		Entrega entrega = dao.recupera(0);
		entrega.setLocal("Torreao");

		dao.altera(entrega);
		entrega = dao.recupera(0);

		Assert.assertNotNull(entrega);
		Assert.assertTrue(entrega.getLocal().equals("Torreao"));
		Assert.assertTrue(entrega.getPreco().doubleValue() == 20.00);

		entrega = dao.recupera(1);
		entrega.setPreco(19.50);

		dao.altera(entrega);
		entrega = dao.recupera(1);
		Assert.assertNotNull(entrega);
		Assert.assertTrue(entrega.getLocal().equals("Madalena"));
		Assert.assertTrue(entrega.getPreco().doubleValue() == 19.50);
	}

	@Test
	public void testRecuperaInteger() {
		EntregaDAO dao = new EntregaDAO();
		Entrega entrega = dao.recupera(0);

		Assert.assertNotNull(entrega);
		Assert.assertTrue(entrega.getLocal().equals("Torre"));
		Assert.assertTrue(entrega.getPreco().doubleValue() == 20.00);

		System.out.println(entrega);
	}

	@Test
	public void testRemoveInteger() throws Exception {
		EntregaDAO dao = new EntregaDAO();
		dao.remove(3);

		Entrega e = dao.recupera(2);
		Assert.assertNull(e);
	}

	@Test
	public void testRemoveRecordNotFound() {
		EntregaDAO dao = new EntregaDAO();

		try {
			dao.remove(101);
		} catch (Exception e) {
			Assert.assertTrue(e instanceof RecordNotFound);

			System.err.println(e.getMessage());
		}
	}

	@Test
	public void testVerificaFilhosCliente() {
		EntregaDAO dao = new EntregaDAO();
		try {
			dao.remove(0);
		} catch (Exception e) {
			Assert.assertTrue(e instanceof ParentDeleteException);

			System.err.println(e.getMessage());
		}
	}

	@Test
	public void testVerificaFilhosRequisicao() {
		EntregaDAO dao = new EntregaDAO();
		try {
			dao.remove(1);
		} catch (Exception e) {
			Assert.assertTrue(e instanceof ParentDeleteException);

			System.err.println(e.getMessage());
		}
	}

	@Test
	public void testVerificaFilhosRequisicaoCliente() {
		EntregaDAO dao = new EntregaDAO();
		try {
			dao.remove(1);
		} catch (Exception e) {
			Assert.assertTrue(e instanceof ParentDeleteException);

			System.err.println(e.getMessage());
		}
	}

	@Test
	public void testAjustaPojoEntrega() {
		EntregaDAO dao = new EntregaDAO();
		Entrega entrega = new Entrega();
		entrega.setPreco(18.50);
		try {
			dao.adiciona(entrega);
		} catch (Exception e) {
			Assert.assertTrue(e instanceof FieldNotNullException);

			System.err.println(e.getMessage());
		}
	}

	@Test
	public void testGetLista() {
		EntregaDAO dao = new EntregaDAO();
		List<Entrega> lista = dao.getLista();
		
		Assert.assertNotNull(lista);
		Assert.assertTrue(lista.size() == 5);
		
		for(Entrega e : lista){
			System.out.println(e);			
		}
	}

	@Test
	public void testGetEntregasPorLocal() {
		EntregaDAO dao = new EntregaDAO();
		List<Entrega> lista = dao.getEntregasPorLocal("asa");
		
		Assert.assertNotNull(lista);
		Assert.assertTrue(lista.size() == 3);
		
		for(Entrega e : lista){
			System.out.println(e);			
		}
	}

}
