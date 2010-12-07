package br.seploc.mbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import br.seploc.dao.ProjetoDAO;
import br.seploc.dao.RequisicaoServicoDAO;
import br.seploc.pojos.Cliente;
import br.seploc.pojos.LinhaRequisicao;
import br.seploc.pojos.Projeto;
import br.seploc.pojos.ReqServicosOpcionais;
import br.seploc.pojos.RequisicaoServico;

public class ReqServListaMB implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private RequisicaoServico reqServico;
	private RequisicaoServicoDAO reqServicoDAO;
	private int numReqBusca;
	private int numReqVisualizar;
	private int projetoID;
	private int clienteID;	
	private Date dataInicio;	
	private Date dataFim;
	private String filtroProjeto;
	private String filtroCliente;
	private Cliente cliente;
	private Projeto projeto;
	private boolean datasInvalidas = false;
	
	
	// CONSTRUTOR
	/**
	 * Construtor da classe
	 */
	public ReqServListaMB() {
		this.load();
		this.iniciarDatas();
	}
	
	private void load(){
		reqServicoDAO = new RequisicaoServicoDAO();		
		reqServico = new RequisicaoServico();
		cliente = new Cliente();
		projeto = new Projeto();
		numReqBusca = 0;
		projetoID = 0;
		clienteID = 0;
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
		try{
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

	public List<RequisicaoServico> getListaReqServ() {
		// setar data de 60 dias atras
		Calendar calendarData = Calendar.getInstance();
		  int numeroDiasParaSubtrair = -60;
		  calendarData.add(Calendar.DATE, numeroDiasParaSubtrair);
		  java.sql.Date dias60 = new java.sql.Date(calendarData.getTimeInMillis());		  
		
		List<RequisicaoServico> retorno = reqServicoDAO.getListaSinceDate(dias60);
		
		return retorno;
	}
	
	public List<ReqServicosOpcionais> getGridOpcionais(){
		List<ReqServicosOpcionais> lista = new ArrayList<ReqServicosOpcionais>();
		if (reqServico.getOpcionais() != null) {
			lista = reqServico.getOpcionais();
		}
		
		return lista;
	}
	
	public String editar(){		
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("numReqServ", reqServico.getNumReq());
		return "reqServ";				
	}
	
	public void apagar(){
		try{
			reqServico = reqServicoDAO.recupera(reqServico.getNumReq());
			Integer numReq = reqServico.getNumReq();
			reqServicoDAO.remove(reqServico.getNumReq());
			addGlobalMessage("Requisição "+String.format("%06d", numReq)+" Excluido!");
		} catch (Exception e) {
			e.printStackTrace();
			addGlobalMessage(e.getMessage());
		}
		
	}
	
	public void imprimir(){
		try{
			reqServico = reqServicoDAO.recupera(reqServico.getNumReq());
		} catch (Exception e) {
			e.printStackTrace();
			addGlobalMessage(e.getMessage());
		}				
	}
	
	public void mostrar(){
		try{
			reqServico = reqServicoDAO.recupera(reqServico.getNumReq());
		} catch (Exception e) {
			e.printStackTrace();
			addGlobalMessage(e.getMessage());
		}		
	}
	
	public void pesquisar(){

	}
	
	public void mostrarMapa(){
		try{
			reqServico = reqServicoDAO.recupera(reqServico.getNumReq());
		} catch (Exception e) {
			e.printStackTrace();
			addGlobalMessage(e.getMessage());
		}	
	}	
	
	public void limpar(){
		System.out.println("Limpar campos!");
		this.load();	
	}	

	public String duplicar(){
		try{
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
			if (reqServico.getOpcionais() != null){
				for (ReqServicosOpcionais ro : reqServico.getOpcionais()){
					reqServicoDAO.addOpcional(temp, ro.getOpcionaisReqServ(), ro.getQuantidade());
				}
			}
			// linhas requisicao
			if (reqServico.getLinhaRequisicao() != null){
				for (LinhaRequisicao l : reqServico.getLinhaRequisicao()){
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
			
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("numReqServ", temp.getNumReq());
		} catch (Exception e) {
			e.printStackTrace();
			addGlobalMessage(e.getMessage());
		}		
		return "reqServ";				
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
		dataInicio = new Date(this.getDayAgo(30).getTimeInMillis());
		dataFim = new Date(Calendar.getInstance().getTimeInMillis());		
	}
	
	public List<Projeto> getTodosProjetos() {
		List<Projeto> retorno = null;

		ProjetoDAO projetoDAO = new ProjetoDAO();
		retorno = projetoDAO.getLista();
		
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
