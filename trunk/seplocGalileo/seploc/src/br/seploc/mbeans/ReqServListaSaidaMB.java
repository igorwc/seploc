package br.seploc.mbeans;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import br.seploc.dao.ProjetoDAO;
import br.seploc.dao.RequisicaoServicoDAO;
import br.seploc.pojos.Cliente;
import br.seploc.pojos.Projeto;
import br.seploc.pojos.RequisicaoServico;

public class ReqServListaSaidaMB implements Serializable {
	
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

	// CONTRUTOR
	public ReqServListaSaidaMB(){
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
	}

	public int getProjetoID() {
		return projetoID;
	}

	public void setProjetoID(int projetoID) {
		this.projetoID = projetoID;
	}

	public int getClienteID() {
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

	public String getFiltroProjeto() {
		return filtroProjeto;
	}

	public void setFiltroProjeto(String filtroProjeto) {
		this.filtroProjeto = filtroProjeto;
	}

	public String getFiltroCliente() {
		return filtroCliente;
	}

	public void setFiltroCliente(String filtroCliente) {
		this.filtroCliente = filtroCliente;
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

	public boolean isDatasInvalidas() {
		return datasInvalidas;
	}

	public void setDatasInvalidas(boolean datasInvalidas) {
		this.datasInvalidas = datasInvalidas;
	}

	// METODOS
	
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
