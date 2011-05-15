package br.seploc.dao.test;

import static org.junit.Assert.fail;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import br.seploc.dao.OpcionaisReqServDAO;
import br.seploc.dao.PapelDAO;
import br.seploc.dao.RequisicaoServicoDAO;
import br.seploc.dao.UsuarioDAO;
import br.seploc.pojos.LinhaRequisicao;
import br.seploc.pojos.OpcionaisReqServ;
import br.seploc.pojos.Papel;
import br.seploc.pojos.ReqServUsuario;
import br.seploc.pojos.ReqServicosOpcionais;
import br.seploc.pojos.RequisicaoServico;
import br.seploc.pojos.Usuario;

public class RequisicaoServicoDAOTest {

	@Test
	public final void testAdicionaRequisicaoServico() {
		Assert.assertTrue((1 + 1) == 3);
	}

	@Test
	public final void testAlteraRequisicaoServico() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testListaSinceDate() {
		Calendar calendarData = Calendar.getInstance();
		int numeroDiasParaSubtrair = -60;
		calendarData.add(Calendar.DATE, numeroDiasParaSubtrair);
		java.sql.Date dias60 = new java.sql.Date(calendarData.getTimeInMillis());
		System.out.println(dias60);

		RequisicaoServicoDAO reqServicoDAO = new RequisicaoServicoDAO();
		List<RequisicaoServico> retorno = reqServicoDAO
				.getListaSinceDate(dias60);
		System.out.println(retorno.size());
	}

	@Test
	public final void testGetLista() {
		RequisicaoServicoDAO dao = new RequisicaoServicoDAO();
		List<RequisicaoServico> l = dao.getLista();
		System.out.println(l);
	}

	@Test
	public final void testRecuperaInteger() throws Exception {
		RequisicaoServicoDAO dao = new RequisicaoServicoDAO();
		RequisicaoServico rq = dao.recupera(1);
		System.out.println(rq.getOpcionais());
		// Assert.assertNotNull(rq);
		// Assert.assertTrue(rq.getProjeto().getCodProj().doubleValue() == 1);
	}

	@Test
	public final void testRemoveInteger() {
		// java.util.Date data = new java.util.Date();
		// # Calendar calendarData = Calendar.getInstance();
		// # calendarData.setTime(dataFinal);
		// java.sql.Date dias60 = new java.sql.Date(data.getTime()-
		// (6*60*24*3600*1000));

		// usa calendar para subtrair data
		Calendar calendarData = Calendar.getInstance();
		int numeroDiasParaSubtrair = -60;
		calendarData.add(Calendar.DATE, numeroDiasParaSubtrair);
		java.sql.Date dias60 = new java.sql.Date(calendarData.getTimeInMillis());
		System.out.println(dias60);
	}

	@Test
	public final void testListaPorPeriodo() {

		Date dataInicio = new Date(new GregorianCalendar(2007,
				GregorianCalendar.AUGUST, 01).getTimeInMillis());
		Date dataFim = new Date(new GregorianCalendar(2010,
				GregorianCalendar.AUGUST, 01).getTimeInMillis());
		System.out.println(dataInicio);
		RequisicaoServicoDAO dao = new RequisicaoServicoDAO();
		List<RequisicaoServico> l = dao.getListaPorPeriodo(dataInicio, dataFim,
				35);
		for (RequisicaoServico r : l) {
			System.out.println(r);
		}
	}

	@Test
	public final void testAddOpcional() throws Exception {
		RequisicaoServicoDAO dao = new RequisicaoServicoDAO();
		RequisicaoServico rq = dao.recupera(60);
		OpcionaisReqServDAO daoOP = new OpcionaisReqServDAO();
		OpcionaisReqServ op = daoOP.recupera(5);
		dao.addOpcional(rq, op, 2);
		dao.altera(rq);
		rq = null;
		rq = dao.recupera(60);
		// Assert.assertTrue(rq.getOpcionais().size() == 1);
		System.out.println(rq.getOpcionais().size());
		for (ReqServicosOpcionais rso : rq.getOpcionais()) {
			System.out.println(rso);
			System.out.println(rso.getOpcionaisReqServ().getNomeItem());
		}
	}

	@Test
	public final void testAddLinha() throws Exception {
		RequisicaoServicoDAO dao = new RequisicaoServicoDAO();
		RequisicaoServico rq = dao.recupera(113);
		LinhaRequisicao lr = new LinhaRequisicao();
		ReqServUsuario ru = new ReqServUsuario();
		PapelDAO pd = new PapelDAO();
		Papel p = pd.recupera(1);
		UsuarioDAO uDAO = new UsuarioDAO();
		Usuario u = uDAO.getUsuarioPorLogin("gustavo");
		ru.setUsuario(u);
		rq.setRequisicaoUsuario(ru);
		lr.setNomeArquivo("TESTE-00");
		lr.setDimensao(1.0);
		lr.setFormato(1.0);
		lr.setImpressao("Color");
		lr.setQuant(1);
		lr.setPapel(p);
		dao.addLinha(rq, lr);
		dao.altera(rq);
		rq = null;
		rq = dao.recupera(1);
		dao.registraUsuarioCriador(u, rq);
		// Assert.assertTrue(rq.getLinhaRequisicao().size() == 1);
		System.out.println("Apos rec: " + rq.getLinhaRequisicao().size());
		for (LinhaRequisicao lrs : rq.getLinhaRequisicao()) {
			System.out.println(lrs);
		}
	}

	@Test
	public final void testAddOpcionalRepetido() throws Exception {
		RequisicaoServicoDAO dao = new RequisicaoServicoDAO();
		RequisicaoServico rq = dao.recupera(1);
		OpcionaisReqServDAO daoOP = new OpcionaisReqServDAO();
		OpcionaisReqServ op = daoOP.recupera(104);
		dao.addOpcional(rq, op, 3);
		dao.altera(rq);
		rq = null;
		rq = dao.recupera(1);
		Assert.assertTrue(rq.getOpcionais().size() == 2);
		System.out.println(rq.getOpcionais().size());
		for (ReqServicosOpcionais rso : rq.getOpcionais()) {
			System.out.println(rso);
		}
	}

	@Test
	public final void testAddOpcionalRepetido2() throws Exception {
		RequisicaoServicoDAO dao = new RequisicaoServicoDAO();
		RequisicaoServico rq = dao.recupera(1);
		OpcionaisReqServDAO daoOP = new OpcionaisReqServDAO();
		OpcionaisReqServ op = daoOP.recupera(104);
		dao.addOpcional(rq, op, 2);
		dao.altera(rq);
		rq = null;
		rq = dao.recupera(1);
		Assert.assertTrue(rq.getOpcionais().size() == 2);
		System.out.println(rq.getOpcionais().size());
		for (ReqServicosOpcionais rso : rq.getOpcionais()) {
			System.out.println(rso);
		}
	}

	@Test
	public final void testAtualizaDescontoRequisicoes() {
		ArrayList<Integer> teste = new ArrayList<Integer>();
		for (int i = 1; i < 11; i++) {
			teste.add(i);
		}
		RequisicaoServicoDAO dao = new RequisicaoServicoDAO();
		dao.atualizaDescontoRequisicoes(teste, 10);
	}

	@Test
	public final void testAtualizaDescontoRequisicoes2() {

		RequisicaoServicoDAO dao = new RequisicaoServicoDAO();
		dao.atualizaDescontoRequisicoes(null, 10);
	}

	@Test
	public final void testAtualizaDescontoRequisicoes3() {
		ArrayList<Integer> teste = new ArrayList<Integer>();
		RequisicaoServicoDAO dao = new RequisicaoServicoDAO();
		dao.atualizaDescontoRequisicoes(teste, 10);
	}
	
	@Test
	public final void testAtualizaDescontoRequisicoes4() {
		ArrayList<Integer> teste = new ArrayList<Integer>();
		teste.add(4);
		RequisicaoServicoDAO dao = new RequisicaoServicoDAO();
		dao.atualizaDescontoRequisicoes(teste, 10);
	}
	@Test
	public final void testAtualizaDescontoRequisicoes5() {
		ArrayList<Integer> teste = new ArrayList<Integer>();
		teste.add(112452);
		teste.add(112451);
		teste.add(112450);
		RequisicaoServicoDAO dao = new RequisicaoServicoDAO();
		dao.atualizaDescontoRequisicoes(teste, 0);
	}
	@Test
	public final void testcalculaTotalRequisicao() {
		 
		RequisicaoServicoDAO dao = new RequisicaoServicoDAO();
		Double retorno = dao.calculaTotalRequisicao(null);
		Assert.assertEquals(0.0, retorno.doubleValue());
	}
	@Test
	public final void testcalculaTotalRequisicao2() {
		 
		RequisicaoServicoDAO dao = new RequisicaoServicoDAO();
		Double retorno = dao.calculaTotalRequisicao(0);
		Assert.assertEquals(0.0, retorno.doubleValue());
	}
	@Test
	public final void testcalculaTotalRequisicao3() {
		 
		RequisicaoServicoDAO dao = new RequisicaoServicoDAO();
//		Double retorno = dao.calculaTotalRequisicao(73635);
//		Double retorno = dao.calculaTotalRequisicao(76430);
//		Double retorno = dao.calculaTotalRequisicao(100000);
		Double retorno = dao.calculaTotalRequisicao(100798);
//		Assert.assertEquals(975.45, retorno.doubleValue()); // 73635
//		Assert.assertEquals(60.93, retorno.doubleValue()); // 76430 não tem opcionais
//		Assert.assertEquals(0.5, retorno.doubleValue()); // 100000 não tem linha só opcionais
		Assert.assertEquals(8.0, retorno.doubleValue()); // 100798 não tem linha só opcionais
	}
	
	@Test
	public final void testRelProducao() {
		RequisicaoServicoDAO dao = new RequisicaoServicoDAO();
		List<Object> l = dao.getListaProducao(0);
		for (Object o : l){
			System.out.println(o.toString());
		}
	}
	
	@Test
	public final void testgetListaProducao() {

		RequisicaoServicoDAO dao = new RequisicaoServicoDAO();
		List<Object> l = dao.getListaProducao(0);
		for (Object obj : l) {
			Object[] dd = (Object[]) obj;
			System.out.printf("Campo1: %d, ValorTotal: %f, Valor com Desconto: %f, Mês: %d%n",
					dd[0],dd[1],dd[2],dd[3]);

		}
	}
}
