package br.seploc.dao;

import java.util.List;

import javax.persistence.Query;

import br.seploc.dao.exceptions.RecordNotFound;
import br.seploc.pojos.LinhaRequisicao;
import br.seploc.pojos.LinhaRequisicaoPK;
import br.seploc.pojos.RequisicaoServico;
import br.seploc.util.GenericDAO;

public class LinhaRequisicaoDAO extends
		GenericDAO<LinhaRequisicao, LinhaRequisicaoPK> {

	@Override
	public void adiciona(LinhaRequisicao t) throws Exception {
		em.getTransaction().begin();
		em.merge(t);
		em.getTransaction().commit();
	}

	@Override
	public LinhaRequisicao recupera(LinhaRequisicaoPK id) throws Exception {
		LinhaRequisicao linha = em.find(LinhaRequisicao.class, id);
		return linha;
	}

	@Override
	public LinhaRequisicao altera(LinhaRequisicao t) throws Exception {
		em.getTransaction().begin();
		em.merge(t);
		em.getTransaction().commit();
		return t;
	}

	@Override
	public LinhaRequisicao remove(LinhaRequisicaoPK id) throws RecordNotFound {
		em.getTransaction().begin();
		LinhaRequisicao linha = em.find(LinhaRequisicao.class, id);
		if(linha == null){
			em.getTransaction().rollback();
			throw new RecordNotFound("Linha de Requisição de Serviço Inexistente");			
		} else {
			em.remove(linha);
		}
		em.getTransaction().commit();
		
		return linha;
	}

	@SuppressWarnings("unchecked")
	public List<LinhaRequisicao> getLista() {
		em.getTransaction().begin();
		Query q = em.createNamedQuery("LinhaReqServ.RetornaLinhasReqServs");
		em.getTransaction().commit();
		return (List<LinhaRequisicao>) q.getResultList();
		
	}
	
	@SuppressWarnings("unchecked")
	public List<LinhaRequisicao> getListaPorReqServ(RequisicaoServico reqServ) {
		em.getTransaction().begin();
		Query q = em.createNamedQuery("SELECT * FROM br.seploc.pojos.LinhaRequisicao ls"
			 + " where ls.id.intNumreq = :id").setParameter(
			 "id", reqServ.getNumReq());
		em.getTransaction().commit();
		
		return (List<LinhaRequisicao>) q.getResultList();		
	}	

	@Override
	protected boolean verificaFilhos(LinhaRequisicaoPK id) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void ajustaPojo(LinhaRequisicao pojo) throws Exception {
		// TODO Auto-generated method stub

	}

}
