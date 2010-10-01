package br.seploc.mbeans;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import br.seploc.pojos.Cliente;
import br.seploc.pojos.LinhaRequisicao;
import br.seploc.pojos.OpcionaisReqServ;
import br.seploc.pojos.Entrega;
import br.seploc.pojos.Papel;
import br.seploc.pojos.Projeto;
import br.seploc.pojos.ReqServicosOpcionais;
import br.seploc.pojos.RequisicaoServico;
import br.seploc.dao.ClienteDAO;
import br.seploc.dao.EntregaDAO;
import br.seploc.dao.LinhaRequisicaoDAO;
import br.seploc.dao.OpcionaisReqServDAO;
import br.seploc.dao.PapelDAO;
import br.seploc.dao.ProjetoDAO;
import br.seploc.dao.ReqServicosOpcionaisDAO;
import br.seploc.dao.RequisicaoServicoDAO;

public class ReqServClienteMB implements Serializable{

	private static final long serialVersionUID = 1L;
	private RequisicaoServico reqServico;
	private RequisicaoServicoDAO reqServicoDAO;
	private Cliente cliente;	
	private OpcionaisReqServ opcional;
	private Entrega entrega;
	private Papel papel;
	private Projeto projeto;
	private LinhaRequisicao linhaReqServ;
	private String nomePapel;
	private String filtroOpcional;
	private String filtroEntrega;	
	private String filtroProjeto;	
	private String filtroCliente;
	private int quantidadeOpcional;
	
	// CONSTRUTOR
	/**
	 * Construtor da classe
	 */
	public ReqServClienteMB(){
		cliente = new Cliente();
		cliente.setIdCliente(0);
		opcional = new OpcionaisReqServ();
		entrega = new Entrega();
		papel = new Papel();
		projeto = new Projeto();
		linhaReqServ = new LinhaRequisicao();
		reqServico = new RequisicaoServico();
		reqServicoDAO = new RequisicaoServicoDAO();
	}

	// GETTERS E SETTERS
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public OpcionaisReqServ getOpcional() {
		return opcional;
	}

	public void setOpcional(OpcionaisReqServ opcional) {
		this.opcional = opcional;
	}

	public Entrega getEntrega() {
		return entrega;
	}

	public void setEntrega(Entrega entrega) {
		this.entrega = entrega;
	}

	public Papel getPapel() {
		return papel;
	}

	public void setPapel(Papel papel) {
		this.papel = papel;
	}

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	public String getFiltroOpcional() {
		return filtroOpcional;
	}

	public void setFiltroOpcional(String filtroOpcional) {
		this.filtroOpcional = filtroOpcional;
	}

	public String getFiltroEntrega() {
		return filtroEntrega;
	}

	public void setFiltroEntrega(String filtroEntrega) {
		this.filtroEntrega = filtroEntrega;
	}

	public String getNomePapel() {
		return nomePapel;
	}

	public void setNomePapel(String filtroPapel) {
		this.nomePapel = filtroPapel;
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

	public int getQuantidadeOpcional() {
		return quantidadeOpcional;
	}

	public void setQuantidadeOpcional(int quantidadeOpcional) {
		this.quantidadeOpcional = quantidadeOpcional;
	}

	public RequisicaoServico getReqServico() {
		return reqServico;
	}

	public void setReqServico(RequisicaoServico reqServico) {
		this.reqServico = reqServico;
	}

	public void setLinhaReqServ(LinhaRequisicao linhaReqServ) {
		this.linhaReqServ = linhaReqServ;
	}

	public LinhaRequisicao getLinhaReqServ() {
		return linhaReqServ;
	}

	public RequisicaoServicoDAO getReqServicoDAO() {
		return reqServicoDAO;
	}

	public void setReqServicoDAO(RequisicaoServicoDAO reqServicoDAO) {
		this.reqServicoDAO = reqServicoDAO;
	}
	
	public List<LinhaRequisicao> getLinhasReqServ(){
		LinhaRequisicaoDAO linhaDAO = new LinhaRequisicaoDAO();
		List<LinhaRequisicao> retorno = linhaDAO.getListaPorReqServ(reqServico);
		
		return retorno;
	}
	
	public List<ReqServicosOpcionais> getOpcionaisReqServ(){
		ReqServicosOpcionaisDAO reqServOpcionaisDAO = new ReqServicosOpcionaisDAO();
		List<ReqServicosOpcionais> retorno = reqServOpcionaisDAO.getListaPorReqServ(reqServico);
		
		return retorno;
	}	
	
	public List<Cliente> getTodosClientes(){
		ClienteDAO clienteDAO = new ClienteDAO();
		List<Cliente> retorno = clienteDAO.getLista();
		
		return retorno;
	}
	
	public List<Papel> getTodosPapeis(){
		PapelDAO papelDAO  = new PapelDAO();
		List<Papel> retorno = papelDAO.getLista();

		return retorno;		
	}
	
	public List<OpcionaisReqServ> getTodosOpcionais(){
		OpcionaisReqServDAO opcionalDAO = new OpcionaisReqServDAO();
		List<OpcionaisReqServ> retorno = opcionalDAO.getLista();
		
		return retorno;
	}
	
	public List<Entrega> getTodasEntregas(){
		EntregaDAO entregaDAO = new EntregaDAO();
		List<Entrega> retorno = entregaDAO.getLista();
		
		return retorno;
	}
	
	public List<Projeto> getTodosProjetos(){
		ProjetoDAO projetoDAO = new ProjetoDAO();
		List<Projeto> retorno = projetoDAO.getListaProjetoPorCliente(cliente);
		
		return retorno;
	}
	
	public List<RequisicaoServico> getLista(){
		List<RequisicaoServico> retorno = reqServicoDAO.getLista();
		return retorno; 
	}
	
	public List<RequisicaoServico> getTodasReqServPorCliente(Cliente cliente){
		List<RequisicaoServico> retorno = reqServicoDAO.getListaPorPorjeto(cliente);
		
		return retorno;
	}
	
	public void cadastrar(){
		if (reqServico.getNumReq() == null || reqServico.getNumReq() == 0){
			try{
				reqServicoDAO.addOpcional(reqServico, opcional, quantidadeOpcional);
				reqServicoDAO.addLinha(reqServico, linhaReqServ);
				reqServicoDAO.adiciona(reqServico);				
				addGlobalMessage("Inclusão feita com sucesso!");
			} catch (Exception e) {
				addGlobalMessage(e.getMessage());
			}
		} else {
			RequisicaoServico temp;
			temp = null;
			try {
				temp = reqServicoDAO.recupera(reqServico.getNumReq());
			} catch (Exception e) {
				addGlobalMessage(e.getMessage());
			}
			if (temp != null) {
				temp.setNumReq(reqServico.getNumReq());
				temp.setData(reqServico.getData());
				temp.setEntrega(reqServico.getEntrega());
				temp.setLinhaRequisicao(reqServico.getLinhaRequisicao());
				temp.setOpcionais(reqServico.getOpcionais());
				temp.setProjeto(reqServico.getProjeto());
				temp.setValorTotal(reqServico.getValorTotal());
			
				try{
					reqServicoDAO.altera(temp);				
					addGlobalMessage("Atualização feita com sucesso!");					
				} catch (Exception e) {
					addGlobalMessage(e.getMessage());
				}				
			}
		}
	}
	
	public void editar(){
		try{
			reqServico = reqServicoDAO.recupera(reqServico.getNumReq());
		} catch (Exception e) {
			e.printStackTrace();
			addGlobalMessage(e.getMessage());
		}	
	}
	
	public void editarLinha(){
		try{
			LinhaRequisicaoDAO linhaDAO = new LinhaRequisicaoDAO();
			linhaReqServ = linhaDAO.recupera(linhaReqServ.getId());
		} catch (Exception e) {
			e.printStackTrace();
			addGlobalMessage(e.getMessage());
		}	
	}	
	
	public void apagar(){
		try{
			reqServicoDAO.remove(reqServico.getNumReq());
			addGlobalMessage("Excluído com sucesso!");
		} catch (Exception e) {
			addGlobalMessage(e.getMessage());
		}
		this.limpar();
	}
	
	public void limpar(){
		cliente = new Cliente();
		cliente.setIdCliente(0);
		opcional = new OpcionaisReqServ();
		entrega = new Entrega();
		papel = new Papel();
		projeto = new Projeto();
		linhaReqServ = new LinhaRequisicao();
		reqServico = new RequisicaoServico();
		reqServicoDAO = new RequisicaoServicoDAO();
	}
	
	public boolean converterToPapel(String nome) throws Exception{
		boolean retorno = false;
		
//		if(nome == null || nome.trim().equals("")) return retorno;
//		
//		PapelDAO papelDAO = new PapelDAO();			
//		Papel papel = new Papel();
//		if (papelDAO.getListaPapelPorNome(nome).size() > 1){
//			throw new Exception("Existe mais de um papel como o mesmo nome!");
//		}
//		AppServiceBean ap = this.getAppBean();
//		if (ap.validatePapeis(context, component, value))
//		for (Papel p : papelDAO.getListaPapelPorNome(value)){
//			retorno = p;
//		}		
		
		return retorno;
	}
	
	private AppServiceBean getAppBean(){
		FacesContext context = FacesContext.getCurrentInstance();
		AppServiceBean appBean = (AppServiceBean) context.getApplication()
        .evaluateExpressionGet(context, "#{appBean}", AppServiceBean.class);
		
		return appBean; 
	}	
	
	public static void addGlobalMessage(String message) {
		FacesMessage facesMessage = new FacesMessage(message);
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	}	
}
