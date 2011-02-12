package br.seploc.dao.pagedqueries;

import java.util.Calendar;
import java.util.List;

import javax.persistence.Query;

import br.seploc.pojos.Projeto;
import br.seploc.pojos.RequisicaoServico;

public class FilteredReqServPager extends AbstractResultPager<Projeto>{

	enum queries { REQ_FILTRO_TEMPO, FILTRO_PROJETO, FILTRO_REQSERV, FILTRO_CLIENTE }
	private int projeto;
	private int numReqServ;
	private int cliente;
	private Calendar dataIni;
	private Calendar dataFim;

	public Query setParameters(int projeto, int numReqServ, int cliente, Calendar dataIni, Calendar dataFim){
		Query resultado = null;
		
		if ((projeto == 0) && (numReqServ == 0) && (cliente == 0)){
			Query q = em.createNamedQuery("RequisicaoServico.RetornaRequisicoesLimitadoTempo")
            .setParameter("data", dataIni);
			this.dataIni = dataIni;
			setQuery(q);
		} else if ((projeto > 0) && (numReqServ == 0) && (cliente == 0)){
			Query q = em.createNamedQuery("RequisicaoServico.FiltraProjeto")
            .setParameter("projeto", projeto);
			this.projeto = projeto;
			setQuery(q);
		} else if (numReqServ > 0){
			Query q = em.createNamedQuery("RequisicaoServico.FiltraReqServ")
            .setParameter("numReq", numReqServ);
			this.numReqServ = numReqServ;
			setQuery(q);
		} else if ((projeto == 0) && (numReqServ == 0) && (cliente > 0)){
			Query q = em.createNamedQuery("RequisicaoServico.FiltraCliente")
			.setParameter("clienteId", cliente)
			.setParameter("dataInicio", dataIni)
			.setParameter("dataFinal", dataFim);
			this.cliente = cliente;
			this.dataIni = dataIni;
			this.dataFim = dataFim;
			setQuery(q);
		} else if ((projeto > 0) && (numReqServ == 0) && (cliente > 0)){
			Query q = em.createNamedQuery("RequisicaoServico.FiltraProjeto")
            .setParameter("projeto", projeto);
			setQuery(q);
		}
		return resultado;
	}

	@Override
	public int contagem() {
		// TODO Auto-generated method stub
		return 0;
	}
}
