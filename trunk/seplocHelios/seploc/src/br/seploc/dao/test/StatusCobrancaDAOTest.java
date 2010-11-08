package br.seploc.dao.test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import br.seploc.dao.CobradorDAO;
import br.seploc.dao.StatusCobrancaDAO;
import br.seploc.dao.exceptions.RecordNotFound;
import br.seploc.pojos.Cobrador;
import br.seploc.pojos.StatusCobranca;
import br.seploc.pojos.StatusCobrancaPK;

public class StatusCobrancaDAOTest {

	@Test
	public void testAdicionaStatusCobranca() throws Exception {
		StatusCobrancaDAO dao = new StatusCobrancaDAO();
		StatusCobrancaPK scPK = new StatusCobrancaPK(4, 2);
		StatusCobranca stc = new StatusCobranca();
		stc.setDataCobranca(Calendar.getInstance().getTime());
		stc.setId(scPK);
		dao.adiciona(stc);

		// Testa se salvou
		stc = null;
		stc = dao.recupera(scPK);
		Assert.assertNotNull(stc);
		System.out.println(stc);
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testRecuperaStatusCobrancaPK() throws Exception {
		StatusCobrancaDAO dao = new StatusCobrancaDAO();
		StatusCobrancaPK scPK = new StatusCobrancaPK(3, 1);
		Calendar data = new GregorianCalendar(2010, GregorianCalendar.JULY, 22);
		GregorianCalendar d = new GregorianCalendar(2010, 7, 22);
		// SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		// Date minhaData = (Date) format.parse("22/07/2010");
		// Locale.
		// data.set(2010, 7, 22);
		StatusCobranca sc = dao.recupera(scPK);

		Assert.assertNotNull(sc);
		System.out.println(sc.getDataCobranca().equals(data));

		System.out.println(sc.getDataCobranca().compareTo(data.getTime()));
		System.out.println(d.getTime());
		Assert.assertTrue(sc.getDataCobranca().compareTo(data.getTime()) == 0);
		data.set(2010, GregorianCalendar.JULY, 25);
		Assert.assertTrue(sc.getDataPagamento().compareTo(data.getTime()) == 0);
	}

	@Test
	public void testAlteraStatusCobranca() throws Exception {
		StatusCobrancaDAO dao = new StatusCobrancaDAO();
		StatusCobrancaPK scPK = new StatusCobrancaPK(4, 2);
		StatusCobranca stc = dao.recupera(scPK);
		CobradorDAO daoCob = new CobradorDAO();
		Cobrador cobrador = daoCob.recupera(2);
		Calendar data = new GregorianCalendar(2010, GregorianCalendar.OCTOBER,
				11);

		Assert.assertNotNull(stc);

		stc.setDataCobranca(data.getTime());
		// Chave primaria nao pode ser alterada.
		// Para poder o campo nao pode existir por fora
		// stc.setCobrador(cobrador);
		dao.altera(stc);

		stc = null;
		scPK = new StatusCobrancaPK(2, 2);
		stc = dao.recupera(scPK);
		Assert.assertNotNull(stc);
		Assert.assertTrue(stc.getDataCobranca().compareTo(data.getTime()) == 0);
	}

	@Test
	public void testRemoveStatusCobrancaPK() throws Exception {
		StatusCobrancaDAO dao = new StatusCobrancaDAO();
		dao.remove(new StatusCobrancaPK(4, 2));
		
		StatusCobrancaPK scPK = new StatusCobrancaPK(4, 2);
		StatusCobranca stc = dao.recupera(scPK);
		
		Assert.assertNull(stc);

	}

	@Test
	public void testRemoveStatusRecordNotFound() {
		StatusCobrancaDAO dao = new StatusCobrancaDAO();
		try {
			dao.remove(new StatusCobrancaPK(77, 77));
		} catch (Exception e) {
			Assert.assertTrue(e instanceof RecordNotFound);
			Assert.assertTrue(e.getMessage().equals(
					"Status de Cobrança Inexistente"));
		}
	}

	@Test
	public void testGetLista() {
		StatusCobrancaDAO dao = new StatusCobrancaDAO();
		List<StatusCobranca> lista = dao.getLista();
		Assert.assertTrue(lista.size() == 2);

		for (StatusCobranca s : lista) {
			System.out.println(s);
		}
	}

}
