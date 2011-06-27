package br.seploc.mbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import br.seploc.dao.RequisicaoServicoDAO;
import br.seploc.dao.pagedqueries.FilteredSaidaPager;
import br.seploc.pojos.ReqServicosOpcionais;
import br.seploc.pojos.RequisicaoServico;
import br.seploc.util.Utils;

public class ReqServListaPagamentoMB implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private RequisicaoServico reqServico;
	private RequisicaoServicoDAO reqServicoDAO;
	private Date dataInicio;	
	private Date dataFim;	
	private int numSaidaMoto;
	private Integer numReqVisualizar;
	private Integer numReq;
	private boolean datasInvalidas = false;

	private int pagCurrentPage;
	private int pagPages;
	private FilteredSaidaPager pagamentoPager;
	
	public ReqServListaPagamentoMB (){
		this.load();
		this.iniciarDatas();
	}
	
	private void load(){
		reqServicoDAO = new RequisicaoServicoDAO();		
		reqServico = new RequisicaoServico();
	}

	public void iniciarDatas(){
		dataInicio = new Date(Utils.getDayAgo(6).getTimeInMillis());
		dataFim = new Date(Calendar.getInstance().getTimeInMillis());		
	}	
	
	// METODOS	
	public void mostrar(){
		try{
			reqServico = reqServicoDAO.recupera(numReqVisualizar);
		} catch (Exception e) {
			e.printStackTrace();
			addGlobalMessage(e.getMessage());
		}		
	}
	
	public void pesquisar(){

	}
	
	public void limpar(){				
		reqServico = new RequisicaoServico();		
		this.iniciarDatas();
	}
	
	public void pagar(){
		try {
			reqServico = reqServicoDAO.pagar(numReq);
			addGlobalMessage("Requisicao ' "+ String.format("%06d",numReq) +" ' Pago!");
		} catch (Exception e) {
			e.printStackTrace();
			addGlobalMessage(e.getMessage());
		}
	}
	
	public List<RequisicaoServico> getListaReqServPagamento(){
		java.sql.Date dataIni = new java.sql.Date(this.dataInicio.getTime());
		java.sql.Date dataFim = new java.sql.Date(this.dataFim.getTime());
		List<RequisicaoServico> retorno = this.getReqServicoDAO().getListaBalcao(dataIni, dataFim);
		return retorno;
	}
	
	public List<ReqServicosOpcionais> getGridOpcionais(){
		List<ReqServicosOpcionais> lista = new ArrayList<ReqServicosOpcionais>();
		if (reqServico != null) {
				
			if (reqServico.getNumReq() != null) {
				lista = reqServico.getOpcionais();
			}
		
		}
		return lista;
	}	
	
	// paginacao
	
	public int getReqServMaxPages() {
		return pagamentoPager.getMaxPages();
	}

	public void proximaPaginaSaida() {
		pagamentoPager.proximaPagina();
		pagCurrentPage = pagamentoPager.getCurrentPage();
	}

	public void paginaAnteriorSaida() {
		pagamentoPager.paginaAnterior();
		pagCurrentPage--;
	}

	public void primeiraPaginaSaida() {
		pagamentoPager.setCurrentPage(0);
		pagCurrentPage = 0;
		
	}

	public String getPaginacaoFormatada(){
		int paginacorrente = 0, maxpages = 0;
		if(!(pagamentoPager == null)){
			paginacorrente = pagamentoPager.getCurrentPage()+1;
		}
		if(!(pagamentoPager == null)){
			maxpages = pagamentoPager.getMaxPages()+1;
		}
		String retorno = ""+paginacorrente+"/"+maxpages;
		return retorno;
	}

	public List<Integer> getReqServPages(){
		List<Integer> retorno = new ArrayList<Integer>();
		int max = pagamentoPager.getMaxPages();
		for (int i = 1; i <= max;i++){
			retorno.add(i);
		}
		return retorno;
	}
	
	public void ultimaPaginaSaida() {
		pagamentoPager.setCurrentPage(pagamentoPager.getMaxPages());
		pagCurrentPage = pagamentoPager.getMaxPages();
	}
	
	// GETTERS E SETTERS		
	public RequisicaoServico getReqServico() {
		return reqServico;
	}

	public void setReqServico(RequisicaoServico reqServico) {
		this.reqServico = reqServico;
	}

	public RequisicaoServicoDAO getReqServicoDAO() {
		return reqServicoDAO;
	}

	public void setReqServicoDAO(RequisicaoServicoDAO reqServicoDAO) {
		this.reqServicoDAO = reqServicoDAO;
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

	public int getNumSaidaMoto() {
		return numSaidaMoto;
	}

	public void setNumSaidaMoto(int numSaidaMoto) {
		this.numSaidaMoto = numSaidaMoto;
	}

	public Integer getNumReqVisualizar() {
		return numReqVisualizar;
	}

	public void setNumReqVisualizar(Integer numReqVisualizar) {
		this.numReqVisualizar = numReqVisualizar;
	}

	public Integer getNumReq() {
		return numReq;
	}

	public void setNumReq(Integer numReq) {
		this.numReq = numReq;
	}

	public boolean isDatasInvalidas() {
		return datasInvalidas;
	}

	public void setDatasInvalidas(boolean datasInvalidas) {
		this.datasInvalidas = datasInvalidas;
	}

	public int getPagCurrentPage() {
		return pagCurrentPage;
	}

	public void setPagCurrentPage(int pagCurrentPage) {
		this.pagCurrentPage = pagCurrentPage;
	}

	public int getPagPages() {
		return pagPages;
	}

	public void setPagPages(int pagPages) {
		this.pagPages = pagPages;
	}

	public FilteredSaidaPager getPagamentoPager() {
		return pagamentoPager;
	}

	public void setPagamentoPager(FilteredSaidaPager pagamentoPager) {
		this.pagamentoPager = pagamentoPager;
	}

	/**
	 * Metodo para incluir mensagens globais no formulario
	 * 
	 * @param String
	 *            message
	 */
	public static void addGlobalMessage(String message) {
		FacesMessage facesMessage = new FacesMessage(message);
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	}
}
