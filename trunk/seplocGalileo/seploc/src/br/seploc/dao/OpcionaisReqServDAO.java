package br.seploc.dao;

import java.util.List;

import javax.persistence.Query;

import br.seploc.dao.exceptions.ParentDeleteException;
import br.seploc.dao.exceptions.RecordNotFound;
import br.seploc.pojos.OpcionaisReqServ;
import br.seploc.util.GenericDAO;

public class OpcionaisReqServDAO extends
		GenericDAO<br.seploc.pojos.OpcionaisReqServ,Integer> {

	@Override
	public void adiciona(OpcionaisReqServ t) throws Exception {
		em.getTransaction().begin();
		em.persist(t);
		em.getTransaction().commit();
		
	}

	@Override
	protected void ajustaPojo(OpcionaisReqServ pojo) throws Exception {
	
	}

	@Override
	public OpcionaisReqServ altera(OpcionaisReqServ t) throws Exception {
		em.getTransaction().begin();
		em.merge(t);
		em.getTransaction().commit();	
		return t;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OpcionaisReqServ> getLista() {
		em.getTransaction().begin();
		Query q = em.createNamedQuery("OpcionaisReqServ.RetornaOpcionais");
		em.getTransaction().commit();
		return (List<OpcionaisReqServ>) q.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<OpcionaisReqServ> getListaPorNome(String nome) {
		em.getTransaction().begin();
		Query q = em.createNamedQuery("OpcionaisReqServ.BuscaOpcionaisPorNome").setParameter(
				 "nome", "%" + nome + "%");
		em.getTransaction().commit();
		return (List<OpcionaisReqServ>) q.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<String> getOpcionais() {
		em.getTransaction().begin();
		Query q = em.createNativeQuery("select vcrNomeItem from tbl_opcionaisReqServ");
		em.getTransaction().commit();
		return (List<String>) q.getResultList();
	}
	
	
	@Override
	public OpcionaisReqServ recupera(Integer id) {
		OpcionaisReqServ opcionais = em.find(OpcionaisReqServ.class, id);
		return opcionais;
	}

	@Override
	public OpcionaisReqServ remove(Integer id) throws Exception {
		em.getTransaction().begin();
		OpcionaisReqServ op = em.find(OpcionaisReqServ.class, id);
		if (op == null) {
			em.getTransaction().rollback();
			throw new RecordNotFound("Opcional Inexistente");
		} else {
			if (verificaFilhos(id)) {
				em.getTransaction().rollback();
				throw new ParentDeleteException(
						"Opcional tem registros depedentes...");
			} else {
				em.remove(op);
			}
		}
		em.getTransaction().commit();

		return op;
	}

	@Override
	protected boolean verificaFilhos(Integer id) throws Exception {
		Number contagemRequisicoes = 0;

		Query q = em.createQuery(
				"SELECT count(rso.opcionaisReqServ) FROM br.seploc.pojos.ReqServicosOpcionais rso"
						+ " where rso.opcionaisReqServ.codOpReqServ = :opcionalID").setParameter(
				            "opcionalID", id);
		contagemRequisicoes = (Number) q.getSingleResult();
		if (contagemRequisicoes.intValue() != 0)
			return true;
		return false;
	}

}
