package br.seploc.dao.test;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import br.seploc.dao.OpcionaisReqServDAO;
import br.seploc.dao.exceptions.ParentDeleteException;
import br.seploc.dao.exceptions.RecordNotFound;
import br.seploc.pojos.OpcionaisReqServ;

public class OpcionaisReqServDAOTest {

	@Test
	public final void testAdicionaOpcionaisReqServ() throws Exception {
		OpcionaisReqServDAO dao = new OpcionaisReqServDAO();
		OpcionaisReqServ op = new OpcionaisReqServ();
		op.setNomeItem("Item teste 22");
		op.setValorItem(22.5);
		dao.adiciona(op);
		List<OpcionaisReqServ> lista = dao.getListaPorNome("22");
		op = null;
		op = lista.get(0);
		Assert.assertTrue(op.getNomeItem().equals("Item teste 22"));
		Assert.assertTrue(op.getValorItem().doubleValue() == 22.5);
		
		System.out.println(op);
		
	}

	@Test
	public final void testAlteraOpcionaisReqServ() throws Exception {
		OpcionaisReqServDAO dao = new OpcionaisReqServDAO();
		OpcionaisReqServ op = dao.recupera(1);
		op.setNomeItem("Item teste 23");
		op.setValorItem(25.65);
		dao.altera(op);
		
		op = null;
		op = dao.recupera(1);
		Assert.assertTrue(op.getNomeItem().equals("Item teste 23"));
		Assert.assertTrue(op.getValorItem().doubleValue() == 25.65);
		System.out.println(op);
	}

	@Test
	public final void testGetLista() {
		OpcionaisReqServDAO dao = new OpcionaisReqServDAO();
		List<OpcionaisReqServ> lista = dao.getLista();

		Assert.assertTrue(lista.size() == 2);

		for (OpcionaisReqServ op : lista) {
			System.out.println(op);
		}
	}

	@Test
	public final void testGetListaPorNome() {
		OpcionaisReqServDAO dao = new OpcionaisReqServDAO();
		List<OpcionaisReqServ> lista = dao.getListaPorNome("o");
		Assert.assertTrue(lista.size() == 2);
		for (OpcionaisReqServ op : lista) {
			System.out.println(op);
		}
		lista = dao.getListaPorNome("ou");
		Assert.assertTrue(lista.size() == 1);
		for (OpcionaisReqServ op : lista) {
			System.out.println(op);
		}
	}

	@Test
	public final void testRecuperaInteger() throws Exception {
		OpcionaisReqServDAO dao = new OpcionaisReqServDAO();
		OpcionaisReqServ op = dao.recupera(100);

		Assert.assertNotNull(op);
		System.out.println(op);
	}

	@Test
	public final void testRemoveInteger() throws Exception {
		OpcionaisReqServDAO dao = new OpcionaisReqServDAO();
		dao.remove(100);
		
		OpcionaisReqServ op = dao.recupera(100);
		
		Assert.assertNull(op);
	}
	
	@Test
	public final void testRemoveRecordNotFound() {
		OpcionaisReqServDAO dao = new OpcionaisReqServDAO();
		try {
			dao.remove(110);
		} catch (Exception e) {
			 Assert.assertTrue(e instanceof RecordNotFound);
			 Assert.assertTrue(e.getMessage().equals("Opcional Inexistente"));
			 System.err.println(e.getMessage());
		}
	}
	
	@Test
	public final void testRemoveParentDeleteException() {
		OpcionaisReqServDAO dao = new OpcionaisReqServDAO();
		try {
			dao.remove(101);
		} catch (Exception e) {
			 Assert.assertTrue(e instanceof ParentDeleteException);
			 Assert.assertTrue(e.getMessage().equals("Opcional tem registros depedentes..."));
			 System.err.println(e.getMessage());
		}
	}

}
