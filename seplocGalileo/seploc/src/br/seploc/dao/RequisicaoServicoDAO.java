package br.seploc.dao;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import javax.persistence.Query;

import br.seploc.dao.exceptions.FieldNotNullException;
import br.seploc.dao.exceptions.RecordNotFound;
import br.seploc.pojos.Cliente;
import br.seploc.pojos.LinhaRequisicao;
import br.seploc.pojos.LinhaRequisicaoPK;
import br.seploc.pojos.OpcionaisReqServ;
import br.seploc.pojos.ReqServUsuario;
import br.seploc.pojos.ReqServicosOpcionais;
import br.seploc.pojos.ReqServicosOpcionaisPK;
import br.seploc.pojos.RequisicaoServico;
import br.seploc.pojos.Usuario;
import br.seploc.reports.beans.ReqServProducaoBeanGrid;
import br.seploc.util.GenericDAO;

public class RequisicaoServicoDAO extends
		GenericDAO<RequisicaoServico, Integer> implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Override
	public void adiciona(RequisicaoServico t) throws Exception {
		em.getTransaction().begin();
		ajustaPojo(t);
		if (t.getOpcionais() == null && t.getLinhaRequisicao() == null) {
			em.persist(t);
		} else {
			// persistir os opcionais
			if (t.getOpcionais() != null) {
				List<ReqServicosOpcionais> opcionais = t.getOpcionais();
				for (ReqServicosOpcionais rso : opcionais) {
					rso.setReqServico(null);
				}				
				//t.setOpcionais(null);
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
			// persistir as linhas
			if (t.getLinhaRequisicao() != null) {
				List<LinhaRequisicao> linhas = t.getLinhaRequisicao();
				for (LinhaRequisicao lr : linhas) {
					lr.setReqServico(null);
				}
				t.setLinhaRequisicao(null);
				em.persist(t);
				for (LinhaRequisicao lr : linhas) {
					lr.setReqServico(t);
					lr.setId(new LinhaRequisicaoPK(lr.getReqServico()
							.getNumReq(), lr.getNumLinha()));
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
	public List<RequisicaoServico> getListaBalcao(Date dataInicio, Date dataFim) {
		Query q = em.createNamedQuery("RequisicaoServico.RetornaRequisicoesNaoOrcamento");
		q.setParameter("dataInicio", dataInicio);
		q.setParameter("dataFim", dataFim);		
		q.setParameter("balcao", 1); // 0 = NAO e 1= SIM
		
		return (List<RequisicaoServico>) q.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<RequisicaoServico> getListaPorProjeto(Cliente cliente) {
		em.getTransaction().begin();
		Query q = em.createNamedQuery(
				"SELECT * FROM br.seploc.pojos.RequisicaoServico rs"
						+ " where rs.projeto.cliente.idCliente = :id")
				.setParameter("id", cliente.getIdCliente());
		em.getTransaction().commit();
		return (List<RequisicaoServico>) q.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<RequisicaoServico> getListaPorPeriodo(Date dataInicio, Date dataFim, Integer clienteId) {
//		em.getTransaction().begin();
		Query q = em.createNamedQuery("RequisicaoServico.RetornaRequisicoesPorPeriodo");
		q.setParameter("dataInicio", dataInicio);
		q.setParameter("dataFim", dataFim);
		q.setParameter("clienteId", clienteId.intValue());
//		em.getTransaction().commit();
		return (List<RequisicaoServico>) q.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Object>  getListaProducao(Integer balcao) {
		Query q = em.createNamedQuery("RequisicaoServico.Producao");
		q.setParameter("balcao", balcao.intValue());
		return (List<Object>) q.getResultList();
	}	
	
	public double getGratificacao(String login,Date dataInicio, Date dataFim) {
		Query q = em.createNamedQuery("RequisicaoServico.GratificacaoUsuario");
		q.setParameter("login", login);
		q.setParameter("dataIni", dataInicio);
		q.setParameter("dataFim", dataFim);
		Double retorno = (Double) q.getSingleResult();
		if (retorno == null) {
			retorno = 0.0;
		}
		
		return retorno;
	}	
	
	public List<ReqServProducaoBeanGrid> getListaProducaoGrid(int balcao) {
		List<Object> l = this.getListaProducao(balcao);
		List<ReqServProducaoBeanGrid> listOrdenada = new ArrayList<ReqServProducaoBeanGrid>();
		for (Object obj : l) {
			Object[] o = (Object[]) obj;
			System.out.printf("TotalReq: %d, ValorTotal: %f, ValorDesconto: %f, Mês: %d%n",
					o[0],o[1],o[2],o[3]);			
			int totalReq = Integer.parseInt(o[0].toString());
			double valorTotal = (Double) o[1];
			double valorDesc = (Double) o[2];			
			int mes = Integer.parseInt(o[3].toString());
			listOrdenada.add(new ReqServProducaoBeanGrid(totalReq, valorTotal, valorDesc, mes));
		}
		Collections.sort(listOrdenada);
		return listOrdenada;
	}

	@SuppressWarnings("unchecked")
	public List<Object> getListaProducaoPeriodo(Date dataInicio, Date dataFim, Integer balcao) {		 
		Query q = em.createNamedQuery("RequisicaoServico.ProducaoPeriodo");
		q.setParameter("dataIni", dataInicio);
		q.setParameter("dataFim", dataFim);
		q.setParameter("balcao", balcao.intValue());		
		return (List<Object>) q.getResultList();
	}	
	
	public List<ReqServProducaoBeanGrid> getListaProducaoPeriodoGrid(Date dataInicio, Date dataFim, int balcao) {
		List<Object> l = this.getListaProducaoPeriodo(dataInicio, dataFim, balcao);
		List<ReqServProducaoBeanGrid> listOrdenada = new ArrayList<ReqServProducaoBeanGrid>();
		for (Object obj : l) {
			Object[] o = (Object[]) obj;
			System.out.printf("TotalReq: %d, ValorTotal: %f, ValorDesconto: %f, Mês: %d%n",
					o[0],o[1],o[2],o[3]);
			int totalReq = (Integer) o[0];
			double valorTotal = (Double) o[1];
			double valorDesc = (Double) o[2];			
			listOrdenada.add(new ReqServProducaoBeanGrid(totalReq, valorTotal, valorDesc));
		}
		return listOrdenada;
	}

	@SuppressWarnings("unchecked")
	public List<RequisicaoServico> getListaSinceDate(Date data) {
		Query q = em.createNamedQuery("RequisicaoServico.RetornaRequisicoesLimitadoTempo");
		q.setParameter("data", data);
		List<RequisicaoServico> retorno = (List<RequisicaoServico>) q.getResultList();
		Collections.reverse(retorno);
		return retorno;
	}
	
	
	@Override
	public RequisicaoServico recupera(Integer id) throws Exception {
//		em.getTransaction().begin();
		RequisicaoServico rq = em.find(RequisicaoServico.class, id);
//		em.getTransaction().commit();
		return rq;
	}

	@Override
	public RequisicaoServico remove(Integer id) throws Exception {
		em.getTransaction().begin();
		RequisicaoServico reqServ = em.find(RequisicaoServico.class, id);
		if (reqServ == null) {
			em.getTransaction().rollback();
			throw new RecordNotFound("Requisição de Servicao nao encontrado!");
		} else {
			em.remove(reqServ);
		}
		em.getTransaction().commit();

		return null;
	}
	
	public RequisicaoServico pagar(Integer id) throws Exception {
		em.getTransaction().begin();
		RequisicaoServico reqServ = em.find(RequisicaoServico.class, id);
		if (reqServ == null) {
			em.getTransaction().rollback();			
			throw new RecordNotFound("Requisição de Servicao nao encontrado!");
		} else {			
			reqServ.setPago("S");
			em.merge(reqServ);
		}
		em.getTransaction().commit();

		return reqServ;		
	}
	
	public LinhaRequisicao removeLinha(LinhaRequisicao l) throws Exception {
		em.getTransaction().begin();
		LinhaRequisicao linhaReqServ = em.find(LinhaRequisicao.class, l.getId());
		if (linhaReqServ == null) {
			em.getTransaction().rollback();
			throw new RecordNotFound("Linha de Requisição não encontrado!");
		} else {
			em.remove(linhaReqServ);
		}
		em.getTransaction().commit();
		
		return null;
	}

	public ReqServicosOpcionais removeOpcionais(ReqServicosOpcionaisPK id) throws Exception {
		em.getTransaction().begin();
		ReqServicosOpcionais opcionalReqServ = em.find(ReqServicosOpcionais.class, id);
		if (opcionalReqServ == null) {
			em.getTransaction().rollback();
			throw new RecordNotFound("Opcional da Requisição não encontrado!");
		} else {
			em.remove(opcionalReqServ);
		}
		em.getTransaction().commit();
		
		return null;
	}
	
	@Override
	protected boolean verificaFilhos(Integer numReqServ) throws Exception {
		Number contagemLinha = 0;
		Number contagemOpcional = 0;
		boolean retorno = false;
		Query q1 = em.createQuery(
				"SELECT count(1) FROM br.seploc.pojos.LinhaRequisicao lr"
						+ " where lr.id.numRequisicao = :numReq").setParameter(
				"numReq", numReqServ);
		Query q2 = em.createQuery(
				"SELECT count(or.id) FROM br.seploc.pojos.ReqServicosOpcionais or"
						+ " where or.id.numRequisicao = :numReq").setParameter(
				"numReq", numReqServ);

		contagemLinha = (Number) q1.getSingleResult();
		contagemOpcional = (Number) q2.getSingleResult();
	
		if (contagemLinha.intValue() != 0 || contagemOpcional.intValue() != 0)
			retorno = true;

		return retorno;
	}

	public void addOpcional(RequisicaoServico rq, OpcionaisReqServ op,
			Integer qtd) throws FieldNotNullException {
		ReqServicosOpcionais temp = null;
		OpcionaisReqServDAO opcionaisDAO = new OpcionaisReqServDAO();
		OpcionaisReqServ oprs;
		if (qtd == null || qtd.intValue() == 0) {
			throw new FieldNotNullException(
					"Quantidade de opcionais não pode ser nulo");
		} else {
			if (rq.getOpcionais().size() != 0) {
				for (ReqServicosOpcionais rso : rq.getOpcionais()) {
					if (rso.getId().getIntCodOp().intValue() == op
							.getCodOpReqServ().intValue()) {
						temp = rso;
						oprs = opcionaisDAO.recupera(rso.getId().getIntCodOp());
						temp.setOpcionaisReqServ(oprs);
						temp.setQuantidade(qtd);
						em.getTransaction().begin();
						em.merge(temp);
						em.getTransaction().commit();
						return;
					} 
				}
				ReqServicosOpcionaisPK id = new ReqServicosOpcionaisPK(
						rq.getNumReq(), op.getCodOpReqServ());
				ReqServicosOpcionais rso = new ReqServicosOpcionais(id);				
				oprs = opcionaisDAO.recupera(id.getIntCodOp());
				rso.setOpcionaisReqServ(oprs);
				rso.setQuantidade(qtd);
				rq.getOpcionais().add(rso);
				em.getTransaction().begin();
				em.merge(rq);
				em.getTransaction().commit();	
			} else {
				ReqServicosOpcionaisPK id = new ReqServicosOpcionaisPK(
						rq.getNumReq(), op.getCodOpReqServ());
				ReqServicosOpcionais rso = new ReqServicosOpcionais(id);
				oprs = opcionaisDAO.recupera(id.getIntCodOp());
				rso.setOpcionaisReqServ(oprs);
				rso.setQuantidade(qtd);
				rq.getOpcionais().add(rso);
				em.getTransaction().begin();
				em.merge(rq);
				em.getTransaction().commit();				
			}
		}
	}

	public void addLinha(RequisicaoServico rq, LinhaRequisicao l)
			throws FieldNotNullException {
		LinhaRequisicao temp;
		if (rq.getLinhaRequisicao() == null) {
			rq.setLinhaRequisicao(new ArrayList<LinhaRequisicao>());
		} else {
			if (rq.getLinhaRequisicao().size() != 0) {
				if (l.getId() != null){ 
					for (LinhaRequisicao lr : rq.getLinhaRequisicao()) {
						if (lr.getId().getNumLinha().intValue() == l.getId().getNumLinha().intValue() &&
							lr.getId().getNumRequisicao().intValue() == l.getId().getNumRequisicao().intValue()) {
							temp = lr;						
							em.getTransaction().begin();
							em.merge(temp);
							em.getTransaction().commit();
							return;
						} 
					}
				}
				LinhaRequisicaoPK id = new LinhaRequisicaoPK(
						(rq.getLinhaRequisicao().size()+1),rq.getNumReq());
				l.setId(id);
				rq.getLinhaRequisicao().add(l);				
				em.getTransaction().begin();
				em.merge(rq);
				em.getTransaction().commit();	
			} else {
				LinhaRequisicaoPK id = new LinhaRequisicaoPK(
						(rq.getLinhaRequisicao().size()+1),rq.getNumReq());
				l.setId(id);
				rq.getLinhaRequisicao().add(l);				
				em.getTransaction().begin();
				em.merge(rq);
				em.getTransaction().commit();	
			}
		}
	}
	
	public void refresh(RequisicaoServico reqServico){		
		em.refresh(reqServico);		
	}
	
	public void registraUsuarioCriador(Usuario u, RequisicaoServico r){
		ReqServUsuario usuario = new ReqServUsuario(u, r);
		em.getTransaction().begin();
		em.merge(usuario);
		em.getTransaction().commit();		

	}

	public void registraUsuarioAlterador(Usuario u, RequisicaoServico r){		
		em.getTransaction().begin();
		ReqServUsuario usuario = em.find(ReqServUsuario.class, r.getNumReq());
		usuario.setUsuario(u);
		// setar data de criacao da requisicao
		java.util.Date data = new java.util.Date();
		java.sql.Date hoje = new java.sql.Date(data.getTime());
		usuario.setDataAlteracao(hoje);
		em.merge(usuario);
		em.getTransaction().commit();		

	}	
	
	@SuppressWarnings("unchecked")
	public List<RequisicaoServico> filtraReqServ(int projeto, int numReqServ, int cliente, Calendar dataIni, Calendar dataFim){
		List<RequisicaoServico> resultado = null;
		
		if ((projeto == 0) && (numReqServ == 0) && (cliente == 0)){
			Query q = em.createNamedQuery("RequisicaoServico.RetornaRequisicoesLimitadoTempo")
            .setParameter("data", dataIni);
			resultado = (List<RequisicaoServico>) q.getResultList();
		} else if ((projeto > 0) && (numReqServ == 0) && (cliente == 0)){
			Query q = em.createNamedQuery("RequisicaoServico.FiltraProjeto")
            .setParameter("projeto", projeto);
			resultado = (List<RequisicaoServico>) q.getResultList();
		} else if (numReqServ > 0){
			Query q = em.createNamedQuery("RequisicaoServico.FiltraReqServ")
            .setParameter("numReq", numReqServ);
			resultado = (List<RequisicaoServico>) q.getResultList();
		} else if ((projeto == 0) && (numReqServ == 0) && (cliente > 0)){
			Query q = em.createNamedQuery("RequisicaoServico.FiltraCliente")
			.setParameter("clienteId", cliente)
			.setParameter("dataInicio", dataIni)
			.setParameter("dataFinal", dataFim);
			resultado = (List<RequisicaoServico>) q.getResultList();
		} else if ((projeto > 0) && (numReqServ == 0) && (cliente > 0)){
			Query q = em.createNamedQuery("RequisicaoServico.FiltraProjeto")
            .setParameter("projeto", projeto);
			resultado = (List<RequisicaoServico>) q.getResultList();
		}
		return resultado;
	}

	public void atualizaDescontoRequisicoes(ArrayList<Integer> listaIds,double desconto){
		String sql = "";
		int linhasAlteradas = 0;
		if (listaIds == null || listaIds.size() == 0){
			return;
		}
		String strIds = "";
		if (listaIds.size() == 1){
			strIds = "("+listaIds.get(0)+")";
		}else{
			strIds = "("+listaIds.get(0);
			for(int i = 1; i < listaIds.size();i++ ){
				strIds += ","+listaIds.get(i)+"";
			}
			strIds += ")";
		}
		sql = "update tbl_reqserv set dblDesconto = "+desconto+
		", dblValorDesc = ((IFNULL(dblValorTotal,0)-IFNULL(dblValorEnt,0)) - (IFNULL(dblValorTotal,0)-IFNULL(dblValorEnt,0)) * IFNULL(dblDesconto,0) / 100) + IFNULL(dblValorEnt,0)" +
		" where intNumreq in "+strIds;
		em.getTransaction().begin();
		Query q1 = em.createNativeQuery(sql);
		linhasAlteradas = q1.executeUpdate();
		if (linhasAlteradas != listaIds.size()){
			em.getTransaction().rollback();
		}
		em.getTransaction().commit();	
//		
	}
	
	@SuppressWarnings("unchecked")
	public Double calculaTotalRequisicao(Integer numReq){
		if(numReq == null){
			return 0.0;
		}
		if(numReq == 0){
			return 0.0;
		}
		Double totalLinhas = 0.0;
		Double totalOpcionais = 0.0;
		String sql1 = "select "
			+ " sum(li.dblValorUnit) "
			+ "from tbl_linhareq li,tbl_reqserv rr "
			+ "where  rr.intNumReq = ? "
			+ "and rr.intNumReq = li.intNumReq ";
		String sql2 =  " SELECT sum(op.dblValorItem*rso.intQuant)  "
			+ "FROM tbl_reqserv r,tbl_opcionaisreqserv op, tbl_reqservopcionais rso "
			+ "WHERE "
			+ "rso.intNumReq = r.intNumReq and "
			+ "rso.intCodOp = op.intCod and "
			+ "r.intNumReq = ?   " ;
		em.getTransaction().begin();
		Query q = em.createNativeQuery(sql1).setParameter(1, numReq.intValue());
		List<Double> lista = q.getResultList();
		if (!lista.isEmpty()) {
			if(lista.get(0) != null)
				totalLinhas = lista.get(0);
			else
				totalLinhas = 0.0;
		} else {
			totalLinhas = 0.0;
		}
		q = em.createNativeQuery(sql2).setParameter(1, numReq.intValue());
		lista = q.getResultList();
		if (!lista.isEmpty()) {
			if(lista.get(0) != null)
				totalOpcionais = lista.get(0);
			else
				totalOpcionais = 0.0;
		} else {
			totalOpcionais = 0.0;
		}
		em.getTransaction().commit();
		return totalLinhas + totalOpcionais;
	}
}
