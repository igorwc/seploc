package br.seploc.dao;

import java.util.ArrayList;
import java.util.List;

import br.seploc.dao.exceptions.FieldNotNullException;
import br.seploc.pojos.Cliente;
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
		if (t.getOpcionais() == null) {
			em.persist(t);
		} else {
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

	@Override
	public List<RequisicaoServico> getLista() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RequisicaoServico recupera(Integer id) throws Exception {
		RequisicaoServico rq = em.find(RequisicaoServico.class, id);
		return rq;
	}

	@Override
	public RequisicaoServico remove(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected boolean verificaFilhos(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return false;
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
					System.out.println("Passou aqui");
					return;
				}
			}
		}
	}
}
