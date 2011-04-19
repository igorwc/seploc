package br.seploc.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.TemporalType;

import br.seploc.dao.exceptions.ParentDeleteException;
import br.seploc.dao.exceptions.RecordNotFound;
import br.seploc.pojos.Cobrador;
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
						+ "  :dataFim  " )
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
		
		q = em.createQuery(
				"SELECT count(sc.cobrador) FROM br.seploc.pojos.StatusCobranca sc"
						+ " where sc.cobrador.codCobrador = :cobradorID").setParameter(
				            "cobradorID", id);
		contagemStatusCobranca = (Number) q.getSingleResult();
//		System.out.println("Entregas: "+contagemRequisicoes.intValue());
		if (contagemSaidaMotoqueiros.intValue() != 0 || contagemStatusCobranca.intValue() != 0)
			return true;
		return false;
	}

	@Override
	protected void ajustaPojo(Cobrador pojo) {
		// TODO Auto-generated method stub
		
	}

}
