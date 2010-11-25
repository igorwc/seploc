package br.seploc.mbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
//import java.sql.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import br.seploc.dao.RequisicaoServicoDAO;
import br.seploc.pojos.Cliente;
import br.seploc.pojos.Projeto;
import br.seploc.pojos.RequisicaoServico;

public class ReqServListaMB implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private RequisicaoServico reqServico;
	private RequisicaoServicoDAO reqServicoDAO;
	private int numReqBusca;
	private int numReqVisualizar;
	private int projetoID;
	private int clienteID;
	private final int umDia = 24*60*60*60;
	private Date dataInicio = new Date(Calendar.getInstance().getTimeInMillis()-(umDia*30*1000));
	private Date dataFim = new Date(Calendar.getInstance().getTimeInMillis());
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
	
	public void editar(){
		try{
			reqServico = reqServicoDAO.recupera(reqServico.getNumReq());
		} catch (Exception e) {
			e.printStackTrace();
			addGlobalMessage(e.getMessage());
		}				
	}
	
	public void apagar(){
		try{
			reqServicoDAO.remove(this.reqServico.getNumReq());
			addGlobalMessage("Requisição Excluido!");
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
	
	public void limpar(){
		System.out.println("Limpar campos!");
		this.load();	
	}	
	
	public List<Projeto> getTodosProjetos() {

		List<Projeto> retorno = null;
//		if (cliente == null || cliente.getIdCliente().intValue() == 0) {
//			retorno = new ArrayList<Projeto>();
//		} else {
//			if(cliente.getProjetos().isEmpty()){
//				retorno = new ArrayList<Projeto>();
//				Projeto p = new Projeto();
//				p.setCodProj(0);
//				p.setProjeto("Cliente não tem projetos");
//				retorno.add(p);
//			}else{
//				retorno = cliente.getProjetos();
//			}
//		}
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
