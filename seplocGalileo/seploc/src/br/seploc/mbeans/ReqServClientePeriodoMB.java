package br.seploc.mbeans;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletContext;

import br.seploc.dao.RequisicaoServicoDAO;
import br.seploc.dao.pagedqueries.FilteredNameClientesPager;
import br.seploc.pojos.Cliente;
import br.seploc.pojos.RequisicaoServico;
import br.seploc.util.SessionObjectsManager;
import br.seploc.util.Utils;

public class ReqServClientePeriodoMB implements Serializable {

	private static final long serialVersionUID = 1L;
	private Cliente cliente;
	private Date dataInicio;
	private Date dataFim;
	private Integer desconto = 0;
	private List<RequisicaoServico> listaRequisicoes;
	private String dataInicioStr = "", dataFimStr = "";
	private Double valorTotalRequisicoes;
	private Double valorTotalDesconto;
	private String filtroCliente;
	private String filtroClienteAnterior;
	private FilteredNameClientesPager clientePager;
	private boolean resetaFiltroCliente;
	private RequisicaoServico reqImpressao;
	private RequisicaoServico reqDesconto;
	private Integer descontoIndividual = 0;
	private String urlReqImpressao;
	private String urlReqListImpressao;

	// METODOS NEGOCIO
	public List<RequisicaoServico> buscaRequisicoes() {
		RequisicaoServicoDAO dao = new RequisicaoServicoDAO();
		List<RequisicaoServico> retorno = null;
		resetaFiltro();
		System.out.println("Valor datainicio busca: " + dataInicio);
		System.out.println("Valor dataInicioStr busca: " + dataInicioStr);
		if (dataInicio != null && dataFim != null && cliente != null
				&& cliente.getIdCliente() != null
				&& cliente.getIdCliente() != 0) {
			retorno = dao.getListaPorPeriodo(new java.sql.Date(dataInicio
					.getTime()), new java.sql.Date(dataFim.getTime()), cliente
					.getIdCliente());
		}
		if (retorno == null || retorno.isEmpty()) {
			listaRequisicoes = new ArrayList<RequisicaoServico>();
			atualizaValorTotalRequisicoes();
			atualizaValorTotalDescontoRequisicoes();
			resetaFiltro();
			return listaRequisicoes;
		}
		listaRequisicoes = retorno;
		atualizaValorTotalRequisicoes();
		atualizaValorTotalDescontoRequisicoes();

		return retorno;
	}

	public void atualizaDescontoRequisicoes() {
		RequisicaoServicoDAO dao = new RequisicaoServicoDAO();
		ArrayList<Integer> listaIDs = new ArrayList<Integer>();
		for (RequisicaoServico r : listaRequisicoes) {
			listaIDs.add(r.getNumReq());
		}
		dao.atualizaDescontoRequisicoes(listaIDs, desconto);
		buscaRequisicoes();
	}

	public void atualizaDescontoRequisaoIndividual() {
		RequisicaoServicoDAO dao = new RequisicaoServicoDAO();
		ArrayList<Integer> listaIDs = new ArrayList<Integer>();
		listaIDs.add(reqDesconto.getNumReq());
		dao.atualizaDescontoRequisicoes(listaIDs, descontoIndividual);
		buscaRequisicoes();
	}

	// CONSTRUTOR PADRAO
	public ReqServClientePeriodoMB() {
		this.load();
	}

	private void load() {
		cliente = new Cliente();
		dataInicio = Utils.getDataInicioMesCorrente();
		dataFim = Utils.getDataFinalMesCorrente();
		desconto = 0;
		filtroCliente = "";
		filtroClienteAnterior = "";
		clientePager = new FilteredNameClientesPager();
		clientePager.init(10);
		resetaFiltroCliente = false;
		reqImpressao = new RequisicaoServico();
		urlReqImpressao = "";
		urlReqListImpressao = "";
		listaRequisicoes = new ArrayList<RequisicaoServico>();
	}

	public void limpar() {
		load();
	}

	public Double atualizaValorTotalDescontoRequisicoes() {
		Double retorno = 0.0;		
		if (listaRequisicoes != null) {
			for (RequisicaoServico r : listaRequisicoes) {
				retorno += r.getValorTotalComDesconto();
			}
		}
		if (retorno == 0.0) {
			valorTotalDesconto = 0.0;
		} else {
			valorTotalDesconto = retorno;
		}

		return retorno;
	}

	public Double atualizaValorTotalRequisicoes() {
		Double retorno = 0.0;
		NumberFormat formatter = new DecimalFormat("#.##");
		if (listaRequisicoes != null) {
			for (RequisicaoServico r : listaRequisicoes) {
				retorno += r.getValorTotal();
			}
		}
		valorTotalRequisicoes = retorno;
		if (retorno == 0.0) {
			valorTotalDesconto = 0.0;
		} else {
			valorTotalDesconto = retorno - ((retorno * desconto) / 100);
			valorTotalDesconto = Double.parseDouble(formatter.format(
					valorTotalDesconto).replace(',', '.'));
		}

		return retorno;
	}

	public void resetaFiltro() {
		filtroCliente = "";
		resetaFiltroCliente = true;
	}

	public void putReportParameters() {

	}

	// GETTERS AND SETTERS

	public Cliente getCliente() {
		return cliente;
	}

	public String getFiltroCliente() {
		return filtroCliente;
	}

	public void setFiltroCliente(String filtroCliente) {
		this.filtroCliente = filtroCliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
		System.out.println("Setou data " + this.dataInicio);
	}

	public void setDataInicio(String dataInicio) {
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date = null;
		System.out.println("nova data" + dataInicio);
		try {
			date = (Date) formatter.parse(dataInicio);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.dataInicio = date;
		System.out.println("Setou datadddd");
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
		System.out.println("Setou data " + this.dataFim);
	}

	public void setDataFim(String dataFim) {
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date = null;
		System.out.println("nova data" + dataFim);
		try {
			date = (Date) formatter.parse(dataFim);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.dataFim = date;

	}

	public Integer getDesconto() {
		return desconto;
	}

	public void setDesconto(Integer desconto) {
		this.desconto = desconto;
	}

	public List<RequisicaoServico> getListaRequisicoes() {
		if (listaRequisicoes == null || listaRequisicoes.isEmpty()) {
			return new ArrayList<RequisicaoServico>();
		}
		return listaRequisicoes;
	}

	public void setListaRequisicoes(List<RequisicaoServico> listaRequisicoes) {
		this.listaRequisicoes = listaRequisicoes;
	}

	public String getDataInicioStr() {
		return dataInicioStr;
	}

	public void setDataInicioStr(String dataInicioStr) {
		setDataInicio(dataInicioStr);
		this.dataInicioStr = dataInicioStr;
	}

	public String getDataFimStr() {
		return dataFimStr;
	}

	public void setDataFimStr(String dataFimStr) {
		System.out.println(dataFimStr);
		this.dataFimStr = dataFimStr;
	}

	public Double getValorTotalRequisicoes() {
		return valorTotalRequisicoes;
	}

	public void setValorTotalRequisicoes(Double valorTotalRequisicoes) {
		this.valorTotalRequisicoes = valorTotalRequisicoes;
	}

	public Double getValorTotalDesconto() {
		return valorTotalDesconto;
	}

	public void setValorTotalDesconto(Double valorTotalDesconto) {
		this.valorTotalDesconto = valorTotalDesconto;
	}

	public List<Cliente> getListaClientes() {
		List<Cliente> retorno = new ArrayList<Cliente>();
		if (resetaFiltroCliente) {
			clientePager = new FilteredNameClientesPager(filtroCliente);
			clientePager.init(10);
			filtroCliente = "";
			retorno = clientePager.getCurrentResults();
			resetaFiltroCliente = false;
			return retorno;
		}
		if (filtroClienteAnterior.equals(filtroCliente)) {
			if (null != clientePager) {
				retorno = clientePager.getCurrentResults();
				return retorno;
			} else {
				clientePager = new FilteredNameClientesPager(filtroCliente);
				clientePager.init(10);
				retorno = clientePager.getCurrentResults();
				return retorno;
			}
		} else if (!filtroClienteAnterior.equals(filtroCliente)
				&& filtroCliente.length() < 3) {
			filtroClienteAnterior = filtroCliente;
			retorno = clientePager.getCurrentResults();
			return retorno;
		} else {
			filtroClienteAnterior = filtroCliente;
			clientePager = new FilteredNameClientesPager(filtroCliente);
			clientePager.init(10);
			retorno = clientePager.getCurrentResults();
			return retorno;
		}
	}

	public void atualizaFiltro() {
		System.out.println(filtroCliente);
	}

	public void atualizaFiltro2(ActionEvent e) {
		Map entrada = e.getComponent().getAttributes();
		filtroCliente = (String) entrada.get("value");
		System.out.println(filtroCliente);
	}

	public int getClienteCurrentPage() {
		return clientePager.getCurrentPage();
	}

	public void setClienteCurrentPage(int clienteCurrentPage) {
		clientePager.setCurrentPage(clienteCurrentPage);
	}

	public int getClientePages() {
		return clientePager.getMaxPages();
	}

	public void ultimaPaginaCliente() {
		clientePager.setCurrentPage(clientePager.getMaxPages());

	}

	public void paginaAnteriorCliente() {
		clientePager.paginaAnterior();
	}

	public void primeiraPaginaCliente() {
		clientePager.setCurrentPage(0);
	}

	public void proximaPaginaCliente() {
		clientePager.proximaPagina();
	}

	public String getUrlReqImpressao() {
		return urlReqImpressao;
	}

	public void setUrlReqImpressao(String urlReqImpressao) {
		this.urlReqImpressao = urlReqImpressao;
	}

	public void geraURLImpressao() {
		FacesContext fcontext = FacesContext.getCurrentInstance();
		ServletContext scontext = (ServletContext) fcontext
				.getExternalContext().getContext();
		int reqID = 0;
		if (reqImpressao == null || reqImpressao.getNumReq() == null) {
			reqID = 0;
		} else {
			reqID = reqImpressao.getNumReq();
		}

		urlReqImpressao = scontext.getContextPath()
				+ "/RelReqServImpressao.report?reqID=" + reqID;
	}

	public void geraURLListaReqServImpressao() {
		FacesContext fcontext = FacesContext.getCurrentInstance();
		ServletContext scontext = (ServletContext) fcontext
				.getExternalContext().getContext();
		SessionObjectsManager.adicionaObjetoSessao("clientID", this
				.getCliente().getIdCliente());
		ArrayList<Integer> listaids = new ArrayList<Integer>();
		
		RequisicaoServicoDAO dao = new RequisicaoServicoDAO();
		List<RequisicaoServico> retorno = null;
		if (dataInicio != null && dataFim != null && cliente != null
				&& cliente.getIdCliente() != null
				&& cliente.getIdCliente() != 0) {
			retorno = dao.getListaPorPeriodo(new java.sql.Date(dataInicio
					.getTime()), new java.sql.Date(dataFim.getTime()), cliente
					.getIdCliente());
		}
		if (retorno != null && !retorno.isEmpty()) {
			for (RequisicaoServico r : retorno) {
				listaids.add(r.getNumReq());
			}
		} 
		SessionObjectsManager.adicionaObjetoSessao("ReqServIDs", listaids);
		urlReqListImpressao = scontext.getContextPath()
				+ "/relClienteReqList.report";
	}

	public String getUrlReqListImpressao() {
		return urlReqListImpressao;
	}

	public void setUrlReqListImpressao(String urlReqListImpressao) {
		this.urlReqListImpressao = urlReqListImpressao;
	}

	public RequisicaoServico getReqImpressao() {
		return reqImpressao;
	}

	public void setReqImpressao(RequisicaoServico reqImpressao) {
		this.reqImpressao = reqImpressao;
	}

	public String getPaginacaoFormatada() {
		int paginacorrente = 0, maxpages = 0;
		if (!(clientePager == null)) {
			paginacorrente = clientePager.getCurrentPage() + 1;
		}
		if (!(clientePager == null)) {
			maxpages = clientePager.getMaxPages() + 1;
		}
		String retorno = "" + paginacorrente + "/" + maxpages;
		return retorno;
	}

	public RequisicaoServico getReqDesconto() {
		return reqDesconto;
	}

	public void setReqDesconto(RequisicaoServico reqDesconto) {
		this.reqDesconto = reqDesconto;
	}

	public Integer getDescontoIndividual() {
		return descontoIndividual;
	}

	public void setDescontoIndividual(Integer descontoIndividual) {
		this.descontoIndividual = descontoIndividual;
	}

}
