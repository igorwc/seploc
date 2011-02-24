package br.seploc.mbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.persistence.NoResultException;
import br.seploc.dao.CobradorDAO;
import br.seploc.dao.RequisicaoServicoDAO;
import br.seploc.dao.SaidaMotoqueiroDAO;
import br.seploc.dao.pagedqueries.FilteredSaidaPager;
import br.seploc.pojos.Cobrador;
import br.seploc.pojos.ReqServicosOpcionais;
import br.seploc.pojos.RequisicaoServico;
import br.seploc.pojos.SaidaMotoqueiro;


public class ReqServListaSaidaMB implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private RequisicaoServico reqServico;
	private RequisicaoServicoDAO reqServicoDAO;
	private Cobrador cobrador;	
	private int numCobrador;
	private SaidaMotoqueiro saidaMotoqueiro;
	private SaidaMotoqueiroDAO saidaMotoqueiroDAO;		
	private Date dataInicio;	
	private Date dataFim;	
	private int numSaidaMoto;
	private Integer numReqVisualizar;
	private boolean datasInvalidas = false;

	private int saiCurrentPage;
	private int saiPages;
	private FilteredSaidaPager saidaPager;
	
	// CONTRUTOR
	public ReqServListaSaidaMB(){
		this.load();
		this.iniciarDatas();
	}
	
	private void load(){
		reqServicoDAO = new RequisicaoServicoDAO();		
		reqServico = new RequisicaoServico();
		saidaMotoqueiroDAO = new SaidaMotoqueiroDAO();	
		saidaMotoqueiro = new SaidaMotoqueiro();
		cobrador = new Cobrador();
		numSaidaMoto = 0;
		numCobrador = -1;
		
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

	public void setNumReqVisualizar(Integer numReqVisualizar) {
		this.numReqVisualizar = numReqVisualizar;
	}

	public Integer getNumReqVisualizar() {
		return numReqVisualizar;
	}

	public Cobrador getCobrador() {
		return cobrador;
	}

	public void setCobrador(Cobrador cobrador) {
		this.cobrador = cobrador;
	}

	public void setSaidaMotoqueiro(SaidaMotoqueiro saidaMotoqueiro) {
		this.saidaMotoqueiro = saidaMotoqueiro;
	}

	public SaidaMotoqueiro getSaidaMotoqueiro() {
		return saidaMotoqueiro;
	}

	public SaidaMotoqueiroDAO getSaidaMotoqueiroDAO() {
		return saidaMotoqueiroDAO;
	}

	public void setSaidaMotoqueiroDAO(SaidaMotoqueiroDAO saidaMotoqueiroDAO) {
		this.saidaMotoqueiroDAO = saidaMotoqueiroDAO;
	}

	public int getNumSaidaMoto() {
		return numSaidaMoto;
	}

	public void setNumSaidaMoto(int numSaida) {
		this.numSaidaMoto = numSaida;
	}

	public void setNumCobrador(int numCobrador) {
		this.numCobrador = numCobrador;
	}

	public int getNumCobrador() {
		return numCobrador;
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

	public boolean isDatasInvalidas() {
		return datasInvalidas;
	}

	public void setDatasInvalidas(boolean datasInvalidas) {
		this.datasInvalidas = datasInvalidas;
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
		saidaMotoqueiro = new SaidaMotoqueiro();		
		this.iniciarDatas();
	}
	
	public void apagar(){
		try {
			
			//verificar se usuario tem permissao de escrita
			if (true) {
				saidaMotoqueiro = saidaMotoqueiroDAO.recupera(numSaidaMoto);
				Integer numSaida = saidaMotoqueiro.getNumSaida();
				saidaMotoqueiroDAO.remove(numSaida);
				addGlobalMessage("Saida '"+ numSaidaMoto +"' Excluido!");
			} else {
				addGlobalMessage("Usuario nao tem permissao para apagar registro!");
			}		
		
		} catch (Exception e) {
			e.printStackTrace();
			addGlobalMessage(e.getMessage());
		}
	}
	
	public void editar(){
		try{
		// setar data de criacao da requisicao
		java.util.Date data = new java.util.Date();
		java.sql.Date hoje = new java.sql.Date(data.getTime());
		java.sql.Time hora = new java.sql.Time(hoje.getTime());
				
		if (cobrador.getCodCobrador() > 1) {

			saidaMotoqueiro = saidaMotoqueiroDAO.recupera(numSaidaMoto);
			saidaMotoqueiro.setDataCobranca(hoje);
			saidaMotoqueiro.setHoraCobranca(hora);
			
			CobradorDAO cobradorDAO = new CobradorDAO();
			cobrador = cobradorDAO.recupera(numCobrador);
			
			saidaMotoqueiro.setCobrador(cobrador);
			saidaMotoqueiroDAO.altera(saidaMotoqueiro);
			
			addGlobalMessage("Saida '"+ numSaidaMoto +"' Alterado!");
		} else {
			addGlobalMessage("Selecione um Motoqueiro!");			
		}
					
		} catch (Exception e) {
			e.printStackTrace();
			addGlobalMessage(e.getMessage());
		}		
	}	
	
	public void pagar(){
		try{
		// setar data de criacao da requisicao
		java.util.Date data = new java.util.Date();
		java.sql.Date hoje = new java.sql.Date(data.getTime());

		saidaMotoqueiro = saidaMotoqueiroDAO.recupera(numSaidaMoto);
		if(saidaMotoqueiro.getDataPagamento() == null){
			saidaMotoqueiro.setDataPagamento(hoje);
			saidaMotoqueiroDAO.altera(saidaMotoqueiro);
			
			addGlobalMessage("Saida '"+ numSaidaMoto +"' Pago!");
		} else {
			addGlobalMessage("Saida '"+ numSaidaMoto +"' já estava Pago!");
		}
		
		this.limpar();
		
		} catch (Exception e) {
			e.printStackTrace();
			addGlobalMessage(e.getMessage());
		}		
	}		
	
	public void cadastrar(){
		boolean existeReqServ = false;
		boolean existeCliente = false;
		boolean existeMoto = false;
		boolean existeReqServSaida = false;
		
		if (saidaMotoqueiro.getNumSaida() == null || saidaMotoqueiro.getNumSaida() == 0) {
			
			try {
				// verificar se o motoqueiro foi selecionado
				if (numCobrador > 0){
					existeMoto = true;
				} 
				
				// existindo Motoqueiro continuar o processo. Do contrario informar ao usuario
				if (existeMoto){
					//verificar se a requisicao ou nome Cliente foi digitado
					if(reqServico.getNumReq() > 0) {
						SaidaMotoqueiro temp;
						try {							
							//verificar se a requisicao ja nao existe na saida
							temp = saidaMotoqueiroDAO.recuperaPorReq(reqServico.getNumReq());
						} catch (NoResultException e){
							temp = null;
						}
						if (temp != null) {
							existeReqServSaida = true;
						} else {
							reqServico = reqServicoDAO.recupera(reqServico.getNumReq());
							saidaMotoqueiro.setReqServico(reqServico);					
							existeReqServ = true;	
							saidaMotoqueiro.setDescCliente(reqServico.getProjeto().getCliente().getFantasia());
						}
					} else {
						if (saidaMotoqueiro.getDescCliente().length() > 0) {					
						saidaMotoqueiro.setDescCliente(saidaMotoqueiro.getDescCliente().toUpperCase());
						existeCliente = true;
						}
					}						
 						
					
					// setar data de criacao da requisicao
					java.util.Date data = new java.util.Date();
					java.sql.Date hoje = new java.sql.Date(data.getTime());
					java.sql.Time hora = new java.sql.Time(hoje.getTime());

					//saidaMotoqueiro = new SaidaMotoqueiro();	
					CobradorDAO cobradorDAO = new CobradorDAO();
					cobrador = cobradorDAO.recupera(numCobrador);
					saidaMotoqueiro.setCobrador(cobrador);
					saidaMotoqueiro.setDataCobranca(hoje);
					saidaMotoqueiro.setHoraCobranca(hora);
					
					// se a requisicao ja existe na saida levantar erro
					if (!existeReqServSaida){
						// para registrar a saida eh necessario ou uma requisicao ou um cliente 
						if ((existeReqServ && !existeCliente)||(!existeReqServ && existeCliente)) {
							
							this.saidaMotoqueiroDAO.adiciona(saidaMotoqueiro);
							addGlobalMessage("Saida registrada com sucesso!");
							
							limpar();
							
							
						} else {
							addGlobalMessage("Informe uma requisição ou um cliente!");
						}						
					} else {
						addGlobalMessage("Ja existe Saida para o numero da requisicao informado!");
					}
				} else {
					addGlobalMessage("Selecione um Motoqueiro!");
				}				
				
			} catch (ValidatorException e) {
				addGlobalMessage(e.getMessage());
			} catch (Exception e) {
				addGlobalMessage(e.getMessage());
			}
		} 		
	}
	
	public List<ReqServicosOpcionais> getGridOpcionais(){
		List<ReqServicosOpcionais> lista = new ArrayList<ReqServicosOpcionais>();
		if (reqServico.getOpcionais() != null) {
			lista = reqServico.getOpcionais();
		}
		
		return lista;
	}	
	
	private Calendar getDayAgo(int dias){
		Calendar dia = Calendar.getInstance();
		//dias atras
		dias = dias * -1;
		dia.add(Calendar.DATE, dias);
		System.out.println(dia.getTime());
		return dia;
	}	
	
	public void iniciarDatas(){
		dataInicio = new Date(this.getDayAgo(5).getTimeInMillis());
		dataFim = new Date(Calendar.getInstance().getTimeInMillis());		
	}
	
	public List<Cobrador> getTodosCobradores(){
		List<Cobrador> retorno = null;
		
		CobradorDAO cobradorDAO = new CobradorDAO();
		retorno = cobradorDAO.getListaAtivos();
		
		return retorno;
	}
	
	public int getReqCurrentPage() {
		return saidaPager.getCurrentPage();
	}

	public void setSaiCurrentPage(int saiCurrentPage) {
		this.saiCurrentPage = saiCurrentPage;
	}

	public int getSaiPages() {
		return saiPages;
	}

	public void setSaiPages(int saiPages) {
		this.saiPages = saiPages;
	}

	public FilteredSaidaPager getReqServPager() {
		return saidaPager;
	}

	public void setReqServPager(FilteredSaidaPager reqServPager) {
		this.saidaPager = reqServPager;
	}
	
	public int getReqServMaxPages() {
		return saidaPager.getMaxPages();
	}

	public void proximaPaginaSaida() {
		saidaPager.proximaPagina();
		saiCurrentPage = saidaPager.getCurrentPage();
	}

	public void paginaAnteriorSaida() {
		saidaPager.paginaAnterior();
		saiCurrentPage--;
	}

	public void primeiraPaginaSaida() {
		saidaPager.setCurrentPage(0);
		saiCurrentPage = 0;
		
	}

	public List<Integer> getReqServPages(){
		List<Integer> retorno = new ArrayList<Integer>();
		int max = saidaPager.getMaxPages();
		for (int i = 1; i <= max;i++){
			retorno.add(i);
		}
		return retorno;
	}
	public void ultimaPaginaSaida() {
		saidaPager.setCurrentPage(saidaPager.getMaxPages());
		saiCurrentPage = saidaPager.getMaxPages();
	}

	public List<SaidaMotoqueiro> listaSaidaInicial(
			int numReqServ, String cliente, Calendar dataIni, Calendar dataFim) {
		List<SaidaMotoqueiro> retorno = null;
		saidaPager = new FilteredSaidaPager();
		saidaPager.setParameters(numReqServ, cliente, dataIni,dataFim);
		saidaPager.init(12);
		retorno = saidaPager.getCurrentResults();
		return retorno;
	}	
	
	public List<SaidaMotoqueiro> filtroSaida(int numReqServ, 
			String cliente, Calendar dataIni, Calendar dataFim){
		List<SaidaMotoqueiro> retorno = null;
		if (saidaPager != null 
				&& cliente == saidaPager.getNomeCliente()
				&& dataIni.getTimeInMillis() == saidaPager.getDataIni().getTimeInMillis()
				&& (dataFim == null || dataFim.getTimeInMillis()== saidaPager.getDataFim().getTimeInMillis())) {
			retorno = saidaPager.getCurrentResults();
		} else {
			saidaPager = new FilteredSaidaPager();
			saidaPager.setParameters(numReqServ, cliente, dataIni,dataFim);
			saidaPager.init(12);
			retorno = saidaPager.getCurrentResults();
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
