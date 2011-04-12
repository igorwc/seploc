package br.seploc.mbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.Query;
import javax.servlet.ServletContext;

import br.seploc.dao.ProjetoDAO;
import br.seploc.dao.RequisicaoServicoDAO;
import br.seploc.dao.pagedqueries.AllClientsPager;
import br.seploc.dao.pagedqueries.FilteredNameClientesPager;
import br.seploc.dao.pagedqueries.FilteredReqServPager;
import br.seploc.pojos.Cliente;
import br.seploc.pojos.LinhaRequisicao;
import br.seploc.pojos.Projeto;
import br.seploc.pojos.ReqServicosOpcionais;
import br.seploc.pojos.RequisicaoServico;
import br.seploc.util.SessionObjectsManager;

public class ReqServListaMB implements Serializable {

	private static final long serialVersionUID = 1L;
	private RequisicaoServico reqServico;
	private RequisicaoServico reqImpressao;
	private RequisicaoServicoDAO reqServicoDAO;
	private int numReqBusca;
	private int numReqVisualizar;
	private int projetoID;
	private int clienteID;
	private Date dataInicio;
	private Date dataFim;
	private String filtroProjeto;
	private String filtroCliente;
	private String filtroClienteAnterior;
	private String urlReqImpressao;
	private Cliente cliente;
	private Projeto projeto;
	private boolean datasInvalidas = false;
	private boolean resetaFiltroCliente;

	private int clienteCurrentPage;
	private int clientePages;
	private AllClientsPager clientesPager = new AllClientsPager();
	private FilteredNameClientesPager clientePager;

	private int reqCurrentPage;
	private int reqPages;
	private FilteredReqServPager reqServPager;

	public String getPaginacaoFormatada(){
		int paginacorrente = 0, maxpages = 0;
		if(!(reqServPager == null)){
			paginacorrente = reqServPager.getCurrentPage()+1;
		}
		if(!(reqServPager == null)){
			maxpages = reqServPager.getMaxPages()+1;
		}
		String retorno = ""+paginacorrente+"/"+maxpages;
		return retorno;
	}
	
	public String getPaginacaoFormatadaCliente() {
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
	
	public FilteredReqServPager getReqServPager() {
		return reqServPager;
	}

	public int getReqServCurrentPage() {
		return reqServPager.getCurrentPage();
	}

	public int getReqServMaxPages() {
		return reqServPager.getMaxPages();
	}

	public void proximaPaginaReqServ() {
		reqServPager.proximaPagina();
		reqCurrentPage = reqServPager.getCurrentPage();
	}

	public void paginaAnteriorReqServ() {
		reqServPager.paginaAnterior();
		reqCurrentPage--;
	}

	public void primeiraPaginaReqServ() {
		reqServPager.setCurrentPage(0);
		reqCurrentPage = 0;
		
	}
	
	public String getUrlReqImpressao() {
		return urlReqImpressao;
	}

	public List<Integer> getReqServPages(){
		List<Integer> retorno = new ArrayList<Integer>();
		int max = reqServPager.getMaxPages();
		for (int i = 1; i <= max;i++){
			retorno.add(i);
		}
		return retorno;
	}
	public void ultimaPaginaReqServ() {
		reqServPager.setCurrentPage(reqServPager.getMaxPages());
		reqCurrentPage = reqServPager.getMaxPages();
	}

	public void setReqPages(int reqPages) {
		this.reqPages = reqPages;
	}

	public int getReqPages() {
		return reqPages;
	}

	public List<RequisicaoServico> listaReqservInicial(int projeto,
			int numReqServ, int cliente, Calendar dataIni, Calendar dataFim) {
		List<RequisicaoServico> retorno = null;
		reqServPager = new FilteredReqServPager();
		reqServPager.setParameters(projeto, numReqServ, cliente, dataIni,
				dataFim);
		reqServPager.init(12);
		retorno = reqServPager.getCurrentResults();
		return retorno;
	}

	public List<RequisicaoServico> filtroReqserv(int projeto, int numReqServ,
			int cliente, Calendar dataIni, Calendar dataFim) {
		List<RequisicaoServico> retorno = null;
		
		if (numReqBusca <= 0){
			if (reqServPager != null && projetoID == reqServPager.getProjeto()
					&& clienteID == reqServPager.getCliente()
					&& dataIni.getTimeInMillis() == reqServPager.getDataIni().getTimeInMillis()
					&& (dataFim == null || dataFim.getTimeInMillis()== reqServPager.getDataFim().getTimeInMillis())) {
				retorno = reqServPager.getCurrentResults();
			} else {
				reqServPager = new FilteredReqServPager();
				reqServPager.setParameters(projeto, numReqServ, cliente, dataIni,
						dataFim);
				reqServPager.init(12);
				retorno = reqServPager.getCurrentResults();
			}
		} else {
				reqServPager = new FilteredReqServPager();
				reqServPager.setParameters(0, numReqBusca, 0, null,
						null);
				reqServPager.init(12);
				retorno = reqServPager.getCurrentResults();				
		}

		return retorno;
	}

	public int getClienteCurrentPage() {
		return clienteCurrentPage;
	}

	public void setClienteCurrentPage(int clienteCurrentPage) {
		this.clienteCurrentPage = clienteCurrentPage;
	}

	public int getClientePages() {
		return clientePages;
	}
	public void ultimaPaginaCliente() {
		clientePager.setCurrentPage(clientePager.getMaxPages());
		clienteCurrentPage = clientePager.getMaxPages();
	}

	public void paginaAnteriorCliente() {
		clientePager.paginaAnterior();
		clienteCurrentPage--;
	}

	public void primeiraPaginaCliente() {
		clientePager.setCurrentPage(0);
		clienteCurrentPage = 0;
		
	}
	public AllClientsPager getClientesPager() {
		return clientesPager;
	}

	public void proximaPaginaCliente() {
		clientePager.proximaPagina();
		clienteCurrentPage++;
	}

//	public List<Cliente> getListaClientes() {
//		// return new ClienteDAO().getListaClientesCadastrados();
//		List<Cliente> lista = clientesPager.getCurrentResults();
//		return clientesPager.getCurrentResults();
//		// AppServiceBean.getListaClientesCadastrados();
//	}
	
	public List<Cliente> getListaClientes() {
		List<Cliente> retorno = new ArrayList<Cliente>();
		if (resetaFiltroCliente) {
			clientePager = new FilteredNameClientesPager(filtroCliente);
			clientePager.init(10);
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
	
	public void resetaFiltro() {
		filtroCliente = "";
		resetaFiltroCliente = true;
	}	

	// CONSTRUTOR
	/**
	 * Construtor da classe
	 */
	public ReqServListaMB() {
		this.load();
		this.iniciarDatas();

	}

	private void load() {
		reqServicoDAO = new RequisicaoServicoDAO();
		reqServico = new RequisicaoServico();
		reqImpressao = new RequisicaoServico();
		cliente = new Cliente();
		projeto = new Projeto();
		numReqBusca = 0;
		projetoID = 0;
		clienteID = 0;
		Query q = clientesPager.getEntityManager().createNamedQuery(
				"Cliente.RetornaClientes");
		clientesPager.init(10, q);
		urlReqImpressao = "";
		filtroCliente = "";
		filtroClienteAnterior = "";
		clientePager = new FilteredNameClientesPager();
		clientePager.init(10);
		resetaFiltroCliente = false;
	}

	// GETTERS E SETTERS
	public void setReqServico(RequisicaoServico reqServico) {
		this.reqServico = reqServico;
	}

	public RequisicaoServico getReqServico() {
		return reqServico;
	}

	public RequisicaoServicoDAO getReqServicoDAO() {
		return reqServicoDAO;
	}

	public void setReqServicoDAO(RequisicaoServicoDAO reqServicoDAO) {
		this.reqServicoDAO = reqServicoDAO;
	}

	public int getNumReqBusca() {
		return numReqBusca;
	}

	public void setNumReqBusca(int numReqBusca) {
		this.numReqBusca = numReqBusca;
	}

	public int getNumReqVisualizar() {
		return numReqVisualizar;
	}

	public void setNumReqVisualizar(int numReqVisualizar) {
		this.numReqVisualizar = numReqVisualizar;
		try {
			reqServico = reqServicoDAO.recupera(numReqVisualizar);
		} catch (Exception e) {
			e.printStackTrace();
			addGlobalMessage(e.getMessage());
		}
	}

	public int getProjetoID() {
		if (projeto.getCodProj() != null) {
			projetoID = projeto.getCodProj();
		}
		return projetoID;
	}

	public void setProjetoID(int projetoID) {
		this.projetoID = projetoID;
	}

	public int getClienteID() {
		if (cliente.getIdCliente() != null) {
			clienteID = cliente.getIdCliente();
		}
		return clienteID;
	}

	public void setClienteID(int clienteID) {
		this.clienteID = clienteID;
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

	public void setFiltroProjeto(String filtroProjeto) {
		this.filtroProjeto = filtroProjeto;
	}

	public String getFiltroProjeto() {
		return filtroProjeto;
	}

	public void setFiltroCliente(String filtroCliente) {
		this.filtroCliente = filtroCliente;
	}

	public String getFiltroCliente() {
		return filtroCliente;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
		projeto = new Projeto();
	}

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	public void setDatasInvalidas(boolean datasInvalidas) {
		this.datasInvalidas = datasInvalidas;
	}

	public boolean isDatasInvalidas() {
		return datasInvalidas;
	}	

	public RequisicaoServico getReqImpressao() {
		return reqImpressao;
	}	
	
	public void setReqImpressao(RequisicaoServico reqImpressao) {
		this.reqImpressao = reqImpressao;
	}
	

	
	public List<RequisicaoServico> getListaReqServ() {
		// setar data de 60 dias atras
		Calendar calendarData = Calendar.getInstance();
		int numeroDiasParaSubtrair = -60;
		calendarData.add(Calendar.DATE, numeroDiasParaSubtrair);
		java.sql.Date dias60 = new java.sql.Date(calendarData.getTimeInMillis());

		List<RequisicaoServico> retorno = reqServicoDAO
				.getListaSinceDate(dias60);

		return retorno;
	}

	public List<ReqServicosOpcionais> getGridOpcionais() {
		List<ReqServicosOpcionais> lista = new ArrayList<ReqServicosOpcionais>();
		if (reqServico.getOpcionais() != null) {
			lista = reqServico.getOpcionais();
		}

		return lista;
	}
	

	public void geraURLImpressao( ){
		FacesContext fcontext = FacesContext.getCurrentInstance();
		   ServletContext scontext = (ServletContext) fcontext.getExternalContext
		().getContext();
		int reqID = 0;
		   if (reqImpressao == null || reqImpressao.getNumReq() == null){
			   reqID = 0;
		   }else{
			   reqID = reqImpressao.getNumReq();
		   }
      
		urlReqImpressao = scontext.getContextPath()+"/RelReqServImpressao.report?reqID="+reqID;
	}

	
	public String editar() {
		SessionObjectsManager.adicionaObjetoSessao("numReqServ", reqServico
				.getNumReq());
		return "reqServ";
	}

	public void apagar() {
		try {
			reqServico = reqServicoDAO.recupera(reqServico.getNumReq());
			Integer numReq = reqServico.getNumReq();
			reqServicoDAO.remove(reqServico.getNumReq());
			addGlobalMessage("Requisição " + String.format("%06d", numReq)
					+ " Excluido!");
		} catch (Exception e) {
			e.printStackTrace();
			addGlobalMessage(e.getMessage());
		}

	}

	public void imprimir() {
		try {
			reqServico = reqServicoDAO.recupera(reqServico.getNumReq());
		} catch (Exception e) {
			e.printStackTrace();
			addGlobalMessage(e.getMessage());
		}
	}

	public void mostrar() {
		try {
			reqServico = reqServicoDAO.recupera(reqServico.getNumReq());
		} catch (Exception e) {
			e.printStackTrace();
			addGlobalMessage(e.getMessage());
		}
	}

	public void pesquisar() {
		if(String.valueOf(numReqBusca) == null) {
			numReqBusca = 0;
		}
	}

	public void mostrarMapa() {
		try {
			reqServico = reqServicoDAO.recupera(reqServico.getNumReq());
		} catch (Exception e) {
			e.printStackTrace();
			addGlobalMessage(e.getMessage());
		}
	}

	public void limpar() {
		System.out.println("Limpar campos!");
		this.load();
	}

	public String duplicar() {
		try {
			reqServico = reqServicoDAO.recupera(reqServico.getNumReq());
			RequisicaoServico temp = new RequisicaoServico();
			// setar data de criacao da requisicao
			java.util.Date data = new java.util.Date();
			java.sql.Date hoje = new java.sql.Date(data.getTime());
			temp.setData(hoje);
			// projeto
			temp.setProjeto(reqServico.getProjeto());
			// entrega
			if (reqServico.getEntrega() != null) {
				temp.setEntrega(reqServico.getEntrega());
				temp.setValorEnt(reqServico.getEntrega().getPreco());
			} else {
				temp.setValorEnt(0.0);
			}
			// inserir valores iniciais
			temp.setStatus(0);
			temp.setVisivelNf(0);
			temp.setVisivelReq(0);
			// criar reqServ
			reqServicoDAO.adiciona(temp);
			// recuperar a requisicao
			temp = reqServicoDAO.recupera(temp.getNumReq());
			// opcionais
			if (reqServico.getOpcionais() != null) {
				for (ReqServicosOpcionais ro : reqServico.getOpcionais()) {
					reqServicoDAO.addOpcional(temp, ro.getOpcionaisReqServ(),
							ro.getQuantidade());
				}
			}
			// linhas requisicao
			if (reqServico.getLinhaRequisicao() != null) {
				for (LinhaRequisicao l : reqServico.getLinhaRequisicao()) {
					LinhaRequisicao linha = new LinhaRequisicao();
					linha.setNomeArquivo(l.getNomeArquivo());
					linha.setDimensao(l.getDimensao());
					linha.setFormato(l.getFormato());
					linha.setPapel(l.getPapel());
					linha.setImpressao(l.getImpressao());
					linha.setQuant(l.getQuant());
					linha.setValorUnit(l.getValorUnit());
					linha.setValorSubUnit(l.getValorSubUnit());
					reqServicoDAO.addLinha(temp, linha);
				}
			}
			// totais			
			temp.setValorTotal(reqServico.getValorTotal());
			temp.setValorDesconto(reqServico.getValorDesconto());
			reqServicoDAO.altera(temp);
			FacesContext.getCurrentInstance().getExternalContext()
					.getSessionMap().put("numReqServ", temp.getNumReq());
		} catch (Exception e) {
			e.printStackTrace();
			addGlobalMessage(e.getMessage());
		}
		return "reqServ";
	}

	private Calendar getDayAgo(int dias) {
		Calendar dia = Calendar.getInstance();
		// dias atras
		dias = dias * -1;
		dia.add(Calendar.DATE, dias);
		System.out.println(dia.getTime());
		return dia;
	}

	public void iniciarDatas() {
		dataInicio = new Date(this.getDayAgo(60).getTimeInMillis());
		dataFim = new Date(Calendar.getInstance().getTimeInMillis());
	}

	public List<Projeto> getTodosProjetos() {
		List<Projeto> retorno = null;

		ProjetoDAO projetoDAO = new ProjetoDAO();
		// retorno = projetoDAO.getLista();
		if (cliente == null || cliente.getIdCliente() == null
				|| cliente.getIdCliente().intValue() == 0) {
			retorno = new ArrayList<Projeto>();
		} else {
			if (cliente.getProjetos().isEmpty()) {
				retorno = new ArrayList<Projeto>();
				Projeto p = new Projeto();
				p.setCodProj(0);
				p.setProjeto("Cliente nao tem projetos");
				retorno.add(p);
			} else {
				retorno = cliente.getProjetos();
				System.out.println("ID: "+cliente.getIdCliente()+" cli: " + cliente.getFantasia().toString());
				System.out.println("Quantidade Projetos: " + cliente.getProjetos().size());
			}
		}
		return retorno;
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
