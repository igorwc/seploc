package br.seploc.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.TemporalType;

import br.seploc.dao.exceptions.ParentDeleteException;
import br.seploc.dao.exceptions.RecordNotFound;
import br.seploc.pojos.Cobrador;
import br.seploc.pojos.RequisicaoServico;
import br.seploc.pojos.SaidaMotoqueiro;
import br.seploc.reports.beans.CobradorBeanGrid;
import br.seploc.util.GenericDAO;

public class CobradorDAO extends GenericDAO<Cobrador, Integer> {

	@Override
	public void adiciona(Cobrador t) {
		em.getTransaction().begin();
		em.merge(t);
		em.getTransaction().commit();
	}

	@Override
	public Cobrador altera(Cobrador t) {
		em.getTransaction().begin();
		em.merge(t);
		em.getTransaction().commit();
		return t;
	}

	@Override
	public Cobrador recupera(Integer id) {
		Cobrador cobrador = em.find(Cobrador.class, id);
		return cobrador;
	}

	@SuppressWarnings("unchecked")
	public List<Cobrador> recuperaPorNome(String nome) {
		em.getTransaction().begin();
		Query q = em.createNamedQuery("Cobrador.FiltraCobradores")
				.setParameter("nome", "%" + nome + "%");
		
		em.getTransaction().commit();
		return (List<Cobrador>) q.getResultList();
	}

	@Override
	public Cobrador remove(Integer id) throws Exception {
		em.getTransaction().begin();
		Cobrador cobrador = em.find(Cobrador.class, id);
		if (cobrador == null) {
			em.getTransaction().rollback();
			throw new RecordNotFound("Cobrador Inexistente");
		} else {
			if (verificaFilhos(id)) {
				em.getTransaction().rollback();
				throw new ParentDeleteException(
						"O registro do cobrador tem registros dependentes...");
			} else {
				em.remove(cobrador);
			}
		}
		em.getTransaction().commit();

		return cobrador;
	}

	@SuppressWarnings("unchecked")
	public List<Cobrador> getLista() {
		em.getTransaction().begin();
		Query q = em.createNamedQuery("Cobrador.RetornaCobradores");
		em.getTransaction().commit();
		return (List<Cobrador>) q.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Cobrador> getListaAtivos() {
		em.getTransaction().begin();
		Query q = em.createNamedQuery("Cobrador.RetornaCobradoresAtivos");
		em.getTransaction().commit();
		return (List<Cobrador>) q.getResultList();
	}	
	
	public Integer getQuantidadeRequisicoesCobrador(Integer id, Date dataInicio, Date dataFim) {
		em.getTransaction().begin();
		Number contagemSaidaMotoqueiros = 0;

		Query q = em.createQuery(
				"SELECT count(sm) FROM br.seploc.pojos.SaidaMotoqueiro sm"
						+ " where sm.cobrador.codCobrador = :cobradorID and "
						+ "sm.dataCobranca between :dataInicio and " 
						+ "  :dataFim  "
						+ "AND sm.reqServico is not null" 
						+ " and sm.reqServico.numReq in (SELECT s.numReq from br.seploc.pojos.RequisicaoServico s )" )
						.setParameter(
				            "cobradorID", id)
				        .setParameter(
				            "dataInicio", dataInicio,TemporalType.DATE)
				        .setParameter(
				            "dataFim", dataFim,TemporalType.DATE);
		contagemSaidaMotoqueiros = (Number) q.getSingleResult();
		em.getTransaction().commit();
		return contagemSaidaMotoqueiros.intValue();
	}	
	@Override
	protected boolean verificaFilhos(Integer id) throws Exception {
		Number contagemStatusCobranca = 0;
		Number contagemSaidaMotoqueiros = 0;

		Query q = em.createQuery(
				"SELECT count(sm.cobrador) FROM br.seploc.pojos.SaidaMotoqueiro sm"
						+ " where sm.cobrador.codCobrador = :cobradorID").setParameter(
				            "cobradorID", id);
		contagemSaidaMotoqueiros = (Number) q.getSingleResult();
		
//		q = em.createQuery(
//				"SELECT count(sc.cobrador) FROM br.seploc.pojos.StatusCobranca sc"
//						+ " where sc.cobrador.codCobrador = :cobradorID").setParameter(
//				            "cobradorID", id);
//		contagemStatusCobranca = (Number) q.getSingleResult();
//		System.out.println("Entregas: "+contagemRequisicoes.intValue());
//		if (contagemSaidaMotoqueiros.intValue() != 0 || contagemStatusCobranca.intValue() != 0)
		if (contagemSaidaMotoqueiros.intValue() != 0)			
			return true;
		return false;
	}
	
	public List<CobradorBeanGrid> getListaCobradoresGrid(Date dataInicio,
			Date dataFim) {
		List<Cobrador> listaCobradores = this.getListaAtivos();
		List<CobradorBeanGrid> listOrdenada = new ArrayList<CobradorBeanGrid>();
		for (int i = listaCobradores.size() - 1; i >= 0; i--) {
			Cobrador c = listaCobradores.get(i);
			Integer resultado = this.getQuantidadeRequisicoesCobrador(c
					.getCodCobrador().intValue(), dataInicio, dataFim);
			listOrdenada.add(new CobradorBeanGrid(c.getNome(), resultado,c.getCodCobrador()));

		}
		Collections.sort(listOrdenada);
		int i = 1;
		for (CobradorBeanGrid cc : listOrdenada) {
			cc.setSeq(i++);
		}
		return listOrdenada;
	}
	@SuppressWarnings("unchecked")
	public List<RequisicaoServico> getListaReqServPorCobrador(Integer cobradorID, Date dataInicio,
			Date dataFim) {
		List<RequisicaoServico> retorno = new ArrayList<RequisicaoServico>();
		em.getTransaction().begin();

		Query q = em.createQuery(
				"SELECT sm FROM br.seploc.pojos.SaidaMotoqueiro sm"
						+ " where sm.cobrador.codCobrador = :cobradorID and "
						+ "sm.dataCobranca between :dataInicio and  :dataFim " 
						+ "AND sm.reqServico is not null" 
						+ " and sm.reqServico.numReq in (SELECT s.numReq from br.seploc.pojos.RequisicaoServico s )" )
						.setParameter(
				            "cobradorID", cobradorID)
				        .setParameter(
				            "dataInicio", dataInicio,TemporalType.DATE)
				        .setParameter(
				            "dataFim", dataFim,TemporalType.DATE);
		List<SaidaMotoqueiro> listaSaidas=  (List<SaidaMotoqueiro>)q.getResultList();
		for(SaidaMotoqueiro sm : listaSaidas){
			retorno.add(sm.getReqServico());
		}
		em.getTransaction().commit();
		return retorno; 
	}

	@Override
	protected void ajustaPojo(Cobrador pojo) {
		// TODO Auto-generated method stub
		
	}

}
