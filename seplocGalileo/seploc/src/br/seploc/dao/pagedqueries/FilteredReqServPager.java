package br.seploc.dao.pagedqueries;

import java.util.Calendar;
import java.util.List;

import javax.persistence.Query;

import br.seploc.pojos.Projeto;
import br.seploc.pojos.RequisicaoServico;

public class FilteredReqServPager extends AbstractResultPager<RequisicaoServico> {

	enum queries {
		REQ_FILTRO_TEMPO, FILTRO_PROJETO, FILTRO_REQSERV, FILTRO_CLIENTE
	}

	private int projeto;
	private int numReqServ;
	private int cliente;
	private Calendar dataIni;
	private Calendar dataFim;
	private queries tipoQuery;

	public void setParameters(int projeto, int numReqServ, int cliente,
			Calendar dataIni, Calendar dataFim) {

		if ((projeto == 0) && (numReqServ == 0) && (cliente == 0)) {
			Query q = em.createNamedQuery(
					"RequisicaoServico.RetornaRequisicoesLimitadoTempo")
					.setParameter("data", dataIni);
			this.projeto = projeto;
			this.numReqServ = numReqServ;
			this.cliente = cliente;
			this.dataIni = dataIni;
			this.dataFim = dataFim;
			setQuery(q);
			tipoQuery = queries.REQ_FILTRO_TEMPO;
		} else if ((projeto > 0) && (numReqServ == 0) && (cliente == 0)) {
			Query q = em.createNamedQuery("RequisicaoServico.FiltraProjeto")
					.setParameter("projeto", projeto);
			this.projeto = projeto;
			this.numReqServ = numReqServ;
			this.cliente = cliente;
			this.dataIni = dataIni;
			this.dataFim = dataFim;
			setQuery(q);
		} else if (numReqServ > 0) {
			Query q = em.createNamedQuery("RequisicaoServico.FiltraReqServ")
					.setParameter("numReq", numReqServ);
			this.projeto = projeto;
			this.numReqServ = numReqServ;
			this.cliente = cliente;
			this.dataIni = dataIni;
			this.dataFim = dataFim;
			setQuery(q);
			tipoQuery = queries.FILTRO_REQSERV;
		} else if ((projeto == 0) && (numReqServ == 0) && (cliente > 0)) {
			Query q = em.createNamedQuery("RequisicaoServico.FiltraCliente")
					.setParameter("clienteId", cliente).setParameter(
							"dataInicio", dataIni).setParameter("dataFinal",
							dataFim);
			this.projeto = projeto;
			this.numReqServ = numReqServ;
			this.cliente = cliente;
			this.dataIni = dataIni;
			this.dataFim = dataFim;
			setQuery(q);
			tipoQuery = queries.FILTRO_CLIENTE;
		} else if ((projeto > 0) && (numReqServ == 0) && (cliente > 0)) {
			Query q = em.createNamedQuery("RequisicaoServico.FiltraProjeto")
					.setParameter("projeto", projeto);
			setQuery(q);
			this.projeto = projeto;
			this.numReqServ = numReqServ;
			this.cliente = cliente;
			this.dataIni = dataIni;
			this.dataFim = dataFim;
			tipoQuery = queries.FILTRO_PROJETO;
		}
	}

	public int getProjeto() {
		return projeto;
	}

	public void setProjeto(int projeto) {
		this.projeto = projeto;
	}

	public int getNumReqServ() {
		return numReqServ;
	}

	public void setNumReqServ(int numReqServ) {
		this.numReqServ = numReqServ;
	}

	public int getCliente() {
		return cliente;
	}

	public void setCliente(int cliente) {
		this.cliente = cliente;
	}

	public Calendar getDataIni() {
		return dataIni;
	}

	public void setDataIni(Calendar dataIni) {
		this.dataIni = dataIni;
	}

	public Calendar getDataFim() {
		return dataFim;
	}

	public void setDataFim(Calendar dataFim) {
		this.dataFim = dataFim;
	}

	@Override
	public int contagem() {
		Query query = null;
		Number countResult = 0;
		switch (tipoQuery) {
		case REQ_FILTRO_TEMPO:
			query = em.createNativeQuery(
					" SELECT count(*) FROM tbl_reqserv " + "where datData >= :data")
					.setParameter("data", dataIni);
			countResult = (Number) query.getSingleResult();
			break;

		case FILTRO_PROJETO:
			query = em.createNativeQuery(
					" SELECT count(*)  FROM tbl_reqserv "
							+ "where intCodProj = :projeto").setParameter(
					"projeto", projeto);
			countResult = (Number) query.getSingleResult();
			break;
		case FILTRO_REQSERV:
			query = em.createNativeQuery(
					" SELECT count(*)  FROM tbl_reqserv "
							+ "where intNumreq = :numReq").setParameter(
					"numReq", numReqServ);
			countResult = (Number) query.getSingleResult();
			break;
		case FILTRO_CLIENTE:
			query = em
					.createNativeQuery(
							" SELECT count(*) "
									+ "FROM tbl_reqserv "
									+ "where datData between :dataInicio and :dataFinal "
									+ "and intCodProj in " 
									+ "(select intCodProj FROM tbl_projetos where intClienteId = :clienteId) ")
					.setParameter("dataInicio", dataIni)
					.setParameter("dataFinal", dataFim)
					.setParameter("clienteId",	cliente);
			countResult = (Number) query.getSingleResult();
			break;
		default:
			break;
		}
		return countResult.intValue();
	}

	public FilteredReqServPager() {
		super();
	}

}
