package br.seploc.dao.test;

import static org.junit.Assert.fail;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import junit.framework.Assert;

import org.junit.Test;

import br.seploc.dao.StatusCobrancaDAO;
import br.seploc.pojos.StatusCobranca;
import br.seploc.pojos.StatusCobrancaPK;

public class StatusCobrancaDAOTest {

	@Test
	public void testAdicionaStatusCobranca() {
		fail("Not yet implemented");
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testRecuperaStatusCobrancaPK() throws Exception {
		StatusCobrancaDAO dao = new StatusCobrancaDAO();
		StatusCobrancaPK scPK = new StatusCobrancaPK(3,1);
		Calendar data = new GregorianCalendar(2010, GregorianCalendar.JULY, 22);
		GregorianCalendar d = new  GregorianCalendar(2010, 7, 22);
//		 SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");  
//		 Date minhaData = (Date) format.parse("22/07/2010");
//		Locale.
//		data.set(2010, 7, 22);
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
	public void testAlteraStatusCobranca() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveStatusCobrancaPK() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveStatusRecordNotFound() {
		fail("Not yet implemented");
	}
	@Test
	public void testGetLista() {
		fail("Not yet implemented");
	}

}
