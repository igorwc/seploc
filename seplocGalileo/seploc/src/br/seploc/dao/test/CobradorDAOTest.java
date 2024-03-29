package br.seploc.dao.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import br.seploc.dao.CobradorDAO;
import br.seploc.dao.exceptions.ParentDeleteException;
import br.seploc.dao.exceptions.RecordNotFound;
import br.seploc.pojos.Cobrador;
import br.seploc.pojos.RequisicaoServico;
import br.seploc.reports.beans.CobradorBeanGrid;
import br.seploc.reports.utils.CobradorBeanGridComparator;

public class CobradorDAOTest {

	@Test
	public final void testAdicionaCobrador() {
		CobradorDAO dao = new CobradorDAO();
		Cobrador cobrador = new Cobrador();
		cobrador.setNome("teste23");
		cobrador.setFoneContato("3333-2222");
		cobrador.setAtivo("S");

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
	
	@Test
	public final void testQuantidadeRequisicoesCobrador() {
		CobradorDAO dao = new CobradorDAO();
		Integer resultado = dao.getQuantidadeRequisicoesCobrador(1,
				new Date(new GregorianCalendar(2006, Calendar.JUNE, 1)
						.getTimeInMillis()), new Date(new GregorianCalendar(
						2006, Calendar.JUNE, 30).getTimeInMillis()));
		System.out.println("Resultado: " + resultado);
		System.out.println(new Date(new GregorianCalendar(2006, 06, 01)
				.getTimeInMillis()));
	}
	@Test
	public final void testSaidaConsulta() {
		CobradorDAO dao = new CobradorDAO();
		List<Cobrador> listaCobradores = dao.getListaAtivos();
		List<CobradorBeanGrid> listOrdenada = new ArrayList<CobradorBeanGrid>();
		for(int i = listaCobradores.size()-1; i >= 0;i--){
			Cobrador c = listaCobradores.get(i);
			Integer resultado = dao.getQuantidadeRequisicoesCobrador(c.getCodCobrador().intValue(),  new Date(
						new GregorianCalendar(2006, Calendar.JANUARY, 1).getTimeInMillis()),
						new Date(
								new GregorianCalendar(2006, Calendar.DECEMBER, 31).getTimeInMillis()));
			System.out.println("Cobrador: "+c.getNome()+"\nQuantidade: "+resultado+"\n\n");
			listOrdenada.add(new  CobradorBeanGrid   (c.getNome(),resultado));
			
		}
		Collections.sort(listOrdenada);
		int i = 1;
		for(CobradorBeanGrid cc : listOrdenada){
			cc.setSeq(i++);
			System.out.println(cc);
		}
		 
	}
	
	@Test
	public final void testQuantidadeListaRequisicoesCobrador() {
		CobradorDAO dao = new CobradorDAO();
		Integer resultado = dao
				.getQuantidadeRequisicoesCobrador(2, new Date(
						new GregorianCalendar(2009, Calendar.JUNE, 1).getTimeInMillis()),
						new Date(
								new GregorianCalendar(2009, Calendar.JUNE, 30).getTimeInMillis()));
		System.out.println("Resultado: "+ resultado);
		System.out.println(new Date(
						new GregorianCalendar(2009, 06, 01).getTimeInMillis()));
		List<RequisicaoServico> listaReqServ = dao.getListaReqServPorCobrador(2, new Date(
				new GregorianCalendar(2009, Calendar.JUNE, 1).getTimeInMillis()),
				new Date(
						new GregorianCalendar(2009, Calendar.JUNE, 30).getTimeInMillis()));
		System.out.println("Quantidade: "+ listaReqServ.size());
		for(RequisicaoServico r : listaReqServ){
			System.out.println(r);
		}
	}
}
