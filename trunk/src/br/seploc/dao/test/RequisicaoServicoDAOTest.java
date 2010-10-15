package br.seploc.dao.test;

import static org.junit.Assert.fail;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import br.seploc.dao.OpcionaisReqServDAO;
import br.seploc.dao.PapelDAO;
import br.seploc.dao.RequisicaoServicoDAO;
import br.seploc.pojos.LinhaRequisicao;
import br.seploc.pojos.OpcionaisReqServ;
import br.seploc.pojos.Papel;
import br.seploc.pojos.ReqServicosOpcionais;
import br.seploc.pojos.RequisicaoServico;

public class RequisicaoServicoDAOTest {

	@Test
	public final void testAdicionaRequisicaoServico() {
		Assert.assertTrue((1+1)==3);
	}

	@Test
	public final void testAlteraRequisicaoServico() {
		fail("Not yet implemented"); // TODO
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
		//Assert.assertNotNull(rq);
		//Assert.assertTrue(rq.getProjeto().getCodProj().doubleValue() == 1);
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
		
		Date dataInicio = new Date(new GregorianCalendar(2007,GregorianCalendar.AUGUST,01).getTimeInMillis());
		Date dataFim = new Date(new GregorianCalendar(2010,GregorianCalendar.AUGUST,01).getTimeInMillis());
		System.out.println(dataInicio);
		RequisicaoServicoDAO dao = new RequisicaoServicoDAO();
		List<RequisicaoServico> l = dao.getListaPorPeriodo(dataInicio, dataFim,35);
		for(RequisicaoServico r: l){
			System.out.println(r);
		}
	}

	@Test
	public final void testAddOpcional() throws Exception {
		RequisicaoServicoDAO dao = new RequisicaoServicoDAO();
		RequisicaoServico rq = dao.recupera(1);
		OpcionaisReqServDAO daoOP = new OpcionaisReqServDAO();
		OpcionaisReqServ op = daoOP.recupera(4);
		dao.addOpcional(rq,op,2);
		dao.altera(rq);
		rq = null;
		rq = dao.recupera(1);
		Assert.assertTrue(rq.getOpcionais().size() == 2);
		System.out.println(rq.getOpcionais().size());
		for(ReqServicosOpcionais rso :rq.getOpcionais()){
			System.out.println(rso);
		}
	}
	
	@Test
	public final void testAddLinha() throws Exception {
		RequisicaoServicoDAO dao = new RequisicaoServicoDAO();
		RequisicaoServico rq = dao.recupera(1);
		LinhaRequisicao lr = new LinhaRequisicao();
		PapelDAO pd = new PapelDAO();
		Papel p = pd.recupera(1);
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
		Assert.assertTrue(rq.getLinhaRequisicao().size() == 1);
		System.out.println("Apos rec: "+rq.getLinhaRequisicao().size());
		for(LinhaRequisicao lrs :rq.getLinhaRequisicao()){
			System.out.println(lrs);
		}
	}	
	
	@Test
	public final void testAddOpcionalRepetido() throws Exception {
		RequisicaoServicoDAO dao = new RequisicaoServicoDAO();
		RequisicaoServico rq = dao.recupera(1);
		OpcionaisReqServDAO daoOP = new OpcionaisReqServDAO();
		OpcionaisReqServ op = daoOP.recupera(104);
		dao.addOpcional(rq,op,3);
		dao.altera(rq);
		rq = null;
		rq = dao.recupera(1);
		Assert.assertTrue(rq.getOpcionais().size() == 2);
		System.out.println(rq.getOpcionais().size());
		for(ReqServicosOpcionais rso :rq.getOpcionais()){
			System.out.println(rso);
		}
	}
	
	@Test
	public final void testAddOpcionalRepetido2() throws Exception {
		RequisicaoServicoDAO dao = new RequisicaoServicoDAO();
		RequisicaoServico rq = dao.recupera(1);
		OpcionaisReqServDAO daoOP = new OpcionaisReqServDAO();
		OpcionaisReqServ op = daoOP.recupera(104);
		dao.addOpcional(rq,op,2);
		dao.altera(rq);
		rq = null;
		rq = dao.recupera(1);
		Assert.assertTrue(rq.getOpcionais().size() == 2);
		System.out.println(rq.getOpcionais().size());
		for(ReqServicosOpcionais rso :rq.getOpcionais()){
			System.out.println(rso);
		}
	}
}
