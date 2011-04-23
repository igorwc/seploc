package br.seploc.mbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import br.seploc.dao.CobradorDAO;
import br.seploc.dao.RequisicaoServicoDAO;
import br.seploc.dao.pagedqueries.FilteredNameClientesPager;
import br.seploc.pojos.Cliente;
import br.seploc.pojos.RequisicaoServico;
import br.seploc.reports.beans.CobradorBeanGrid;
import br.seploc.util.SessionObjectsManager;
import br.seploc.util.Utils;

public class RelReqServCobradorMB implements Serializable {
 
	private static final long serialVersionUID = 1L;
	private Date dataInicio;
	private Date dataFim;
	private List<CobradorBeanGrid> listaCobradores;
	private Integer cobID;
	private String urlCobradorImpressao;
	private String urlCobradorGridImpressao;
	private CobradorBeanGrid cobradorImpressao;
	
	// CONSTRUTOR PADRAO
	public RelReqServCobradorMB() {
		this.load();
	}

	private void load() {
		dataInicio = Utils.getDataInicioMesCorrente();
		dataFim = Utils.getDataFinalMesCorrente();
		urlCobradorImpressao = "";
		urlCobradorGridImpressao = "";
		cobradorImpressao = new CobradorBeanGrid();
		
	}
	// METODOS NEGOCIO
	public List<CobradorBeanGrid> buscaRequisicoes() {
		CobradorDAO dao = new CobradorDAO();
		List<CobradorBeanGrid> retorno = null;
		if (dataInicio != null && dataFim != null  ) {
			retorno = dao.getListaCobradoresGrid(dataInicio, dataFim) ;
		}
		if (retorno == null || retorno.isEmpty()) {
			listaCobradores = new ArrayList<CobradorBeanGrid>();
			return listaCobradores;
		}
		listaCobradores = retorno;
		return retorno;
	}
	public void geraURLImpressaoCobrador() {
		FacesContext fcontext = FacesContext.getCurrentInstance();
		ServletContext scontext = (ServletContext) fcontext
				.getExternalContext().getContext();
		CobradorDAO dao = new CobradorDAO();
		List<RequisicaoServico> listaReqServ = dao.getListaReqServPorCobrador(cobID, dataInicio, dataFim);
		ArrayList<Integer> listaids = new ArrayList<Integer>();
		if(!listaReqServ.isEmpty()){
			for(RequisicaoServico r : listaReqServ){
				listaids.add(r.getNumReq());
			}
		}
		SessionObjectsManager.adicionaObjetoSessao("ReqServIDs", listaids);
		SessionObjectsManager.adicionaObjetoSessao("cobID", cobID);
		urlCobradorImpressao = scontext.getContextPath()
				+ "/RelCobradorImpressaoReqServ.report";//?cobID=" + cobID;
	}
	
	public void geraURLListaReqServImpressao() {
		FacesContext fcontext = FacesContext.getCurrentInstance();
		ServletContext scontext = (ServletContext) fcontext
				.getExternalContext().getContext();
		SessionObjectsManager.adicionaObjetoSessao("dataInicio",  dataInicio);
		SessionObjectsManager.adicionaObjetoSessao("dataFim",  dataFim);
		 
		urlCobradorGridImpressao = scontext.getContextPath()
				+ "/RelListaCobradorImpressaoReqServ.report";
	}
	public List<CobradorBeanGrid> getListaCobradores() {
		return listaCobradores;
	}

	public void setListaCobradores(List<CobradorBeanGrid> listaCobradores) {
		this.listaCobradores = listaCobradores;
	}

	public void limpar() {
		load();
	}
	
	public Date getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}
	public Date getDataFim() {
		return dataFim;
	}
	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public Integer getCobID() {
		return cobID;
	}

	public void setCobID(Integer cobID) {
		this.cobID = cobID;
	}

	public String getUrlCobradorImpressao() {
		return urlCobradorImpressao;
	}

	public void setUrlCobradorImpressao(String urlCobradorImpressao) {
		this.urlCobradorImpressao = urlCobradorImpressao;
	}

	public String getUrlCobradorGridImpressao() {
		return urlCobradorGridImpressao;
	}

	public void setUrlCobradorGridImpressao(String urlCobradorGridImpressao) {
		this.urlCobradorGridImpressao = urlCobradorGridImpressao;
	}
	
	
}
