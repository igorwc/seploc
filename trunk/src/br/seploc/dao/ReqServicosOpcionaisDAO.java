package br.seploc.dao;

import java.util.List;

import javax.persistence.Query;

import br.seploc.dao.exceptions.RecordNotFound;
import br.seploc.pojos.ReqServicosOpcionais;
import br.seploc.pojos.ReqServicosOpcionaisPK;
import br.seploc.pojos.RequisicaoServico;
import br.seploc.util.GenericDAO;

public class ReqServicosOpcionaisDAO extends
GenericDAO<ReqServicosOpcionais, ReqServicosOpcionaisPK>{

	@Override
	public void adiciona(ReqServicosOpcionais t) throws Exception {
		em.getTransaction().begin();
		em.merge(t);
		em.getTransaction().commit();		
	}

	@Override
	public ReqServicosOpcionais recupera(ReqServicosOpcionaisPK id)
			throws Exception {
		ReqServicosOpcionais reqServOpcionais = em.find(ReqServicosOpcionais.class, id);
		
		return reqServOpcionais;
	}

	@Override
	public ReqServicosOpcionais altera(ReqServicosOpcionais t) throws Exception {
		em.getTransaction().begin();
		em.merge(t);
		em.getTransaction().commit();
		return t;
	}

	@Override
	public ReqServicosOpcionais remove(ReqServicosOpcionaisPK id)
			throws RecordNotFound {
		em.getTransaction().begin();
		ReqServicosOpcionais reqServOpcionais = em.find(ReqServicosOpcionais.class, id);
		if(reqServOpcionais == null){
			em.getTransaction().rollback();
			throw new RecordNotFound("Requisição de Serviço Opcionais Inexistente");			
		} else {
			em.remove(reqServOpcionais);
		}
		em.getTransaction().commit();
		
		return reqServOpcionais;
	}

	@SuppressWarnings("unchecked")
	public List<ReqServicosOpcionais> getLista() {
		em.getTransaction().begin();
		Query q = em.createNamedQuery("ReqServicosOpcionais.RetornaReqServicosOpcionais");
		em.getTransaction().commit();
		
		return (List<ReqServicosOpcionais>) q.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<ReqServicosOpcionais> getListaPorReqServ(RequisicaoServico reqServ){
		em.getTransaction().begin();
		Query q = em.createNamedQuery("SELECT * FROM br.seploc.pojos.ReqServicosOpcionais rso"
			 + " where rso.id.intNumreq = :id").setParameter(
			 "id", reqServ.getNumReq());
		em.getTransaction().commit();
		
		return (List<ReqServicosOpcionais>) q.getResultList();
	}

	@Override
	protected boolean verificaFilhos(ReqServicosOpcionaisPK id)
			throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void ajustaPojo(ReqServicosOpcionais pojo) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
