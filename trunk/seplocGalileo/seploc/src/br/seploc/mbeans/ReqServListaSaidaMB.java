package br.seploc.mbeans;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import br.seploc.dao.CobradorDAO;
import br.seploc.dao.RequisicaoServicoDAO;
import br.seploc.dao.SaidaMotoqueiroDAO;
import br.seploc.pojos.Cobrador;
import br.seploc.pojos.RequisicaoServico;
import br.seploc.pojos.SaidaMotoqueiro;


public class ReqServListaSaidaMB implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private RequisicaoServico reqServico;
	private RequisicaoServicoDAO reqServicoDAO;
	private Cobrador cobrador;	
	private SaidaMotoqueiro saidaMotoqueiro;
	private SaidaMotoqueiroDAO saidaMotoqueiroDAO;		
	private Date dataInicio;	
	private Date dataFim;	
	private String nomeCliente;
	private Integer numReqVisualizar;
	private boolean datasInvalidas = false;

	
	// CONTRUTOR
	public ReqServListaSaidaMB(){
		this.load();
		this.iniciarDatas();
	}
	
	private void load(){
		reqServicoDAO = new RequisicaoServicoDAO();		
		reqServico = new RequisicaoServico();
		saidaMotoqueiroDAO = new SaidaMotoqueiroDAO();		
		nomeCliente = "";
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

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
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
	
	public void apagar(){

	}
	
	public void editar(){
		
	}	
	
	public void cadastrar(){
		if (saidaMotoqueiro.getNumSaida() == null || saidaMotoqueiro.getNumSaida() == 0) {
			try {
				// setar data de criacao da requisicao
				java.util.Date data = new java.util.Date();
				java.sql.Date hoje = new java.sql.Date(data.getTime());
				
				saidaMotoqueiro = new SaidaMotoqueiro();		
				saidaMotoqueiro.setCobrador(cobrador);
				saidaMotoqueiro.setDataCobranca(hoje);
				saidaMotoqueiro.setDescCliente(nomeCliente);
				if(reqServico.getNumReq() != null || reqServico.getNumReq() > 0) {					
					saidaMotoqueiro.setReqServico(reqServico);
				}
				
				this.saidaMotoqueiroDAO.adiciona(saidaMotoqueiro);
				
			} catch (ValidatorException e) {
				addGlobalMessage(e.getMessage());
			} catch (Exception e) {
				addGlobalMessage(e.getMessage());
			}
		} else {
			try {				
				saidaMotoqueiro = saidaMotoqueiroDAO.recuperaPorReq(reqServico.getNumReq());		
				saidaMotoqueiro.setCobrador(cobrador);
				
				this.saidaMotoqueiroDAO.altera(saidaMotoqueiro);
				
			} catch (ValidatorException e) {
				addGlobalMessage(e.getMessage());
			} catch (Exception e) {
				addGlobalMessage(e.getMessage());
			}			
		}
		
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
	
	public List<Cobrador> getTodosCobradores(){
		List<Cobrador> retorno = null;
		
		CobradorDAO cobradorDAO = new CobradorDAO();
		retorno = cobradorDAO.getListaAtivos();
		
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
