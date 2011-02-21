package br.seploc.dao.pagedqueries;

import java.util.Calendar;
import javax.persistence.Query;

import br.seploc.dao.pagedqueries.FilteredSaidaPager.queries;
import br.seploc.pojos.SaidaMotoqueiro;

public class FilteredSaidaPager extends AbstractResultPager<SaidaMotoqueiro> {

	enum queries {
		FILTRO_TEMPO, FILTRO_CLIENTE, FILTRO_REQSERV
	}

	private int numReqServ;
	private Calendar dataIni;
	private Calendar dataFim;
	private String nomeCliente;
	private queries tipoQuery;
	
	public FilteredSaidaPager() {
		super();
	}	
	
	@Override
	public int contagem() {
		Query query = null;
		Number countResult = 0;
		switch (tipoQuery) {
		case FILTRO_TEMPO:
			query = em.createNativeQuery(
					" SELECT count(*) FROM tbl_saidamotoqueiro " 
					+ "where datDataCobr between :ini and :fim ")
					.setParameter("ini", dataIni)
					.setParameter("fim", dataFim);
			countResult = (Number) query.getSingleResult();
			break;
		case FILTRO_REQSERV:
			query = em.createNativeQuery(
					" SELECT count(*)  FROM tbl_saidamotoqueiro "
							+ "where intNumReq = :numReq").setParameter(
					"numReq", numReqServ);
			countResult = (Number) query.getSingleResult();
			break;
		case FILTRO_CLIENTE:
			query = em
					.createNativeQuery(
							" SELECT count(*) "
									+ "FROM tbl_saidamotoqueiro "
									+ "where datDataCobr between :dataInicio and :dataFinal "
									+ "and vcrCliente like :cliente ")
					.setParameter("dataInicio", dataIni)
					.setParameter("dataFinal", dataFim)
					.setParameter("cliente", '%'+nomeCliente+'%');
			countResult = (Number) query.getSingleResult();
			break;
		default:
			break;
		}
		return countResult.intValue();
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

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}	

	public void setParameters(int numReqServ, String cliente,
			Calendar dataIni, Calendar dataFim) {

		if ((numReqServ == 0) && (cliente.length() == 0)) {
			Query q = em.createNamedQuery(
					"SaidaMotoqueiro.RetornaSaidaMotoqueiro")
					.setParameter("dataInicio", dataIni)
					.setParameter("dataFinal", dataFim);			
			this.numReqServ = numReqServ;
			this.nomeCliente = cliente;
			this.dataIni = dataIni;
			this.dataFim = dataFim;
			setQuery(q);
			tipoQuery = queries.FILTRO_TEMPO;
		} else if (numReqServ > 0) {
			Query q = em.createNamedQuery("SaidaMotoqueiro.FiltraRequisicao")
					.setParameter("numReq", numReqServ);
			this.numReqServ = numReqServ;
			this.nomeCliente = cliente;
			this.dataIni = dataIni;
			this.dataFim = dataFim;
			setQuery(q);
			tipoQuery = queries.FILTRO_REQSERV;
		} else if ((numReqServ == 0) && (cliente.length() > 0)) {
			Query q = em.createNamedQuery("SaidaMotoqueiro.FiltraCliente")
					.setParameter("nome", cliente);
			this.numReqServ = numReqServ;
			this.nomeCliente = cliente;
			this.dataIni = dataIni;
			this.dataFim = dataFim;
			setQuery(q);
			tipoQuery = queries.FILTRO_CLIENTE;
		} 
	}
}
