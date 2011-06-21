package br.seploc.mbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import br.seploc.dao.UsuarioDAO;
import br.seploc.pojos.RequisicaoServico;
import br.seploc.reports.beans.PlotadorBeanGrid;
import br.seploc.util.SessionObjectsManager;
import br.seploc.util.Utils;

public class RelReqServPlotadorMB  implements Serializable {
 
	private static final long serialVersionUID = 1L;
	private Date dataInicio;
	private Date dataFim;
	private Integer ploID;
	private String urlPlotadorImpressao;
	private String urlPlotadorGridImpressao;
	private PlotadorBeanGrid plotadorImpressao;
	private List<PlotadorBeanGrid> listaPlotadores;
	
	public RelReqServPlotadorMB (){
		this.load();
	}
	
	private void load() {
		dataInicio = Utils.getDataInicioMesCorrente();
		dataFim = Utils.getDataFinalMesCorrente();
		urlPlotadorImpressao = "";
		urlPlotadorGridImpressao = "";
		setPlotadorImpressao(new PlotadorBeanGrid());
	}
	
	// METODOS NEGOCIO
	public List<PlotadorBeanGrid>  buscaRequisicoes() {
		UsuarioDAO dao = new UsuarioDAO();
		List<PlotadorBeanGrid>  retorno = null;
		if (dataInicio != null && dataFim != null  ) {
			retorno = dao.getListaPlotadoresGrid(dataInicio, dataFim);
		}
		if (retorno == null || retorno.isEmpty()) {
			listaPlotadores = new ArrayList<PlotadorBeanGrid>();
			return listaPlotadores;
		}
		listaPlotadores = retorno;
		return retorno;
	}
	
	public void geraURLImpressaoPlotador() {
		FacesContext fcontext = FacesContext.getCurrentInstance();
		ServletContext scontext = (ServletContext) fcontext
				.getExternalContext().getContext();
		UsuarioDAO dao = new UsuarioDAO();
		List<RequisicaoServico> listaReqServ = dao.getListaReqServPorPlotador(ploID, dataInicio, dataFim);
		ArrayList<Integer> listaids = new ArrayList<Integer>();
		if(!listaReqServ.isEmpty()){
			for(RequisicaoServico r : listaReqServ){
				listaids.add(r.getNumReq());
			}
		}
		SessionObjectsManager.adicionaObjetoSessao("ReqServIDs", listaids);
		SessionObjectsManager.adicionaObjetoSessao("ploID", ploID);
		urlPlotadorImpressao = scontext.getContextPath()
				+ "/RelPlotadorImpressaoReqServ.report";
	}	
	
	// GETTERS AND SETTERS
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

	public Integer getPloID() {
		return ploID;
	}

	public void setPloID(Integer ploID) {
		this.ploID = ploID;
	}

	public String getUrlPlotadorImpressao() {
		return urlPlotadorImpressao;
	}

	public void setUrlPlotadorImpressao(String urlPlotadorImpressao) {
		this.urlPlotadorImpressao = urlPlotadorImpressao;
	}

	public String getUrlPlotadorGridImpressao() {
		return urlPlotadorGridImpressao;
	}

	public void setUrlPlotadorGridImpressao(String urlPlotadorGridImpressao) {
		this.urlPlotadorGridImpressao = urlPlotadorGridImpressao;
	}

	public void setPlotadorImpressao(PlotadorBeanGrid plotadorImpressao) {
		this.plotadorImpressao = plotadorImpressao;
	}

	public PlotadorBeanGrid getPlotadorImpressao() {
		return plotadorImpressao;
	}

	public List<PlotadorBeanGrid> getListaPlotadores() {
		return listaPlotadores;
	}

	public void setListaPlotadores(List<PlotadorBeanGrid> listaPlotadores) {
		this.listaPlotadores = listaPlotadores;
	}	
}