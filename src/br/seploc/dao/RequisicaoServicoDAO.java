package br.seploc.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import br.seploc.dao.exceptions.FieldNotNullException;
import br.seploc.dao.exceptions.ParentDeleteException;
import br.seploc.dao.exceptions.RecordNotFound;
import br.seploc.pojos.Cliente;
import br.seploc.pojos.LinhaRequisicao;
import br.seploc.pojos.LinhaRequisicaoPK;
import br.seploc.pojos.OpcionaisReqServ;
import br.seploc.pojos.ReqServicosOpcionais;
import br.seploc.pojos.ReqServicosOpcionaisPK;
import br.seploc.pojos.RequisicaoServico;
import br.seploc.util.GenericDAO;

public class RequisicaoServicoDAO extends
		GenericDAO<RequisicaoServico, Integer> {

	@Override
	public void adiciona(RequisicaoServico t) throws Exception {
		em.getTransaction().begin();
		ajustaPojo(t);
		if (t.getOpcionais() == null && t.getLinhaRequisicao() == null) {
			em.persist(t);
		} else {
			//persistir os opcionais
			if (t.getOpcionais() != null){				
				List<ReqServicosOpcionais> opcionais = t.getOpcionais();
				for (ReqServicosOpcionais rso : opcionais) {
					rso.setReqServico(null);
				}
				t.setOpcionais(null);				
				em.persist(t);
				for (ReqServicosOpcionais rso : opcionais) {
					rso.setReqServico(t);
					rso.setId(new ReqServicosOpcionaisPK(rso.getReqServico()
							.getNumReq(), rso.getOpcionaisReqServ()
							.getCodOpReqServ()));
					em.persist(rso);
				}				
				t.setOpcionais(opcionais);				
			}
			//persistir as linhas
			if (t.getLinhaRequisicao() != null){
				List<LinhaRequisicao> linhas = t.getLinhaRequisicao();
				for (LinhaRequisicao lr : linhas){
					lr.setReqServico(null);
				}
				t.setLinhaRequisicao(null);
				em.persist(t);
				for (LinhaRequisicao lr : linhas){
					lr.setReqServico(t);
					lr.setId(new LinhaRequisicaoPK(lr.getReqServico()
							.getNumReq(),lr.getNumLinha()));
					em.persist(t);
				}
				t.setLinhaRequisicao(linhas);
			}

			em.merge(t);

		}
		em.getTransaction().commit();

	}

	@Override
	protected void ajustaPojo(RequisicaoServico pojo) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public RequisicaoServico altera(RequisicaoServico t) throws Exception {
		em.getTransaction().begin();
		ajustaPojo(t);
		em.merge(t);
		em.getTransaction().commit();
		return t;
	}

	@SuppressWarnings("unchecked")	
	public List<RequisicaoServico> getLista() {
		em.getTransaction().begin();
		Query q = em.createNamedQuery("RequisicaoServico.RetornaRequisicoes");
		em.getTransaction().commit();
		return (List<RequisicaoServico>) q.getResultList();
	}
	
	@SuppressWarnings("unchecked")	
	public List<RequisicaoServico> getListaPorPorjeto(Cliente cliente) {		
		em.getTransaction().begin();
		Query q = em.createNamedQuery("SELECT * FROM br.seploc.pojos.RequisicaoServico rs"
			 + " where rs.projeto.cliente.idCliente = :id").setParameter(
			 "id", cliente.getIdCliente());
		em.getTransaction().commit();
		return (List<RequisicaoServico>) q.getResultList();
	}	

	@Override
	public RequisicaoServico recupera(Integer id) throws Exception {
		RequisicaoServico rq = em.find(RequisicaoServico.class, id);
		return rq;
	}

	@Override
	public RequisicaoServico remove(Integer id) throws Exception {
		em.getTransaction().begin();
		RequisicaoServico reqServ = em.find(RequisicaoServico.class , id);
		if (reqServ == null){
			em.getTransaction().rollback();
			throw new RecordNotFound("Requisição de Serviço não encontrado!");
		} else {
			if (verificaFilhos(id)) {
				em.getTransaction().rollback();
				throw new ParentDeleteException(
						"Requisição tem registros dependentes...");
			} else {
				em.remove(reqServ);
			}
		}
		em.getTransaction().commit();
		
		return null;
	}

	@Override
	protected boolean verificaFilhos(Integer numReqServ) throws Exception {
		Number contagemLinha = 0;
		Number contagemOpcional = 0;
		boolean retorno = false;
		 Query q1 =
			 em.createQuery("SELECT count(lr.id) FROM br.seploc.pojos.LinhaRequisicao lr"
			 + " where lr.id.NumRequisicao = :numReq").setParameter(
			 "numReq", numReqServ);
		 Query q2 =
			 em.createQuery("SELECT count(or.id) FROM br.seploc.pojos.ReqServicosOpcionais or"
			 + " where or.id.NumRequisicao = :numReq").setParameter(
			 "numReq", numReqServ);		 
		 
		 contagemLinha = (Number) q1.getSingleResult();
		 contagemOpcional = (Number) q2.getSingleResult();
		 
		 if (contagemLinha.intValue() != 0 || contagemOpcional.intValue() != 0)
		 retorno = true;
		
		return retorno;
	}

	public void addOpcional(RequisicaoServico rq,  OpcionaisReqServ op, Integer qtd)
			throws FieldNotNullException {
		ReqServicosOpcionais temp = null;
		if (qtd == null || qtd.intValue() == 0) {
			throw new FieldNotNullException(
					"Quantidade de opcionais não pode ser nulo");
		}
		if (rq.getOpcionais() == null) {
			rq.setOpcionais(new ArrayList<ReqServicosOpcionais>());
		} else {
			for (ReqServicosOpcionais rso : rq.getOpcionais()) {
				if (rso.getOpcionaisReqServ().getCodOpReqServ().intValue() == op
						.getCodOpReqServ().intValue()) {
					temp = rso;
					temp.setQuantidade(qtd);
					em.getTransaction().begin();
					em.merge(temp);
					em.getTransaction().commit();					
					return;
				}
			}
		}
	}
	
	public void addLinha(RequisicaoServico rq,  LinhaRequisicao lr)
	throws FieldNotNullException {
		LinhaRequisicao temp = null;
		if (rq.getLinhaRequisicao() == null) {
			rq.setLinhaRequisicao(new ArrayList<LinhaRequisicao>());
		} else {
			for (LinhaRequisicao l : rq.getLinhaRequisicao()) {
				if (l.getId() == lr.getId()) {
					temp = l;
					temp.setNomeArquivo(lr.getNomeArquivo());
					temp.setDimensao(lr.getDimensao());
					temp.setFormato(lr.getFormato());
					temp.setPapel(lr.getPapel());
					temp.setImpressao(lr.getImpressao());
					temp.setValorUnit(lr.getValorUnit());
					temp.setValorSubUnit(lr.getValorSubUnit());

					em.getTransaction().begin();
					em.merge(temp);
					em.getTransaction().commit();					
					return;
				}
			}
		}
	}	
	

	
}
