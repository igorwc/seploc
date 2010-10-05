package br.seploc.mbeans;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

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
import br.seploc.dao.OpcionaisReqServDAO;
import br.seploc.dao.PapelDAO;
import br.seploc.dao.ProjetoDAO;
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
//		entrega = new Entrega();
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
				//setar data de criação da requisição
				java.util.Date data = new java.util.Date();  
				java.sql.Date hoje = new java.sql.Date(data.getTime());  
				reqServico.setData(hoje);				
				//adicionar projeto
				reqServico.setProjeto(projeto);
				//adicionar a entrega
				if (entrega.getCodEntrega() >= 1){
					reqServico.setEntrega(entrega);
					reqServico.setValorEnt(entrega.getPreco());
					reqServico.setValorTotal(reqServico.getValorTotal()+entrega.getPreco());
				}
				//calculos
				

				// adicionar a requisição de serviço
				reqServicoDAO.adiciona(reqServico);					
				
				// recuperar a requisicao
				reqServico = reqServicoDAO.recupera(reqServico.getNumReq());
				
				// adicionar o opcional
				if (quantidadeOpcional >= 1){
					reqServicoDAO.addOpcional(reqServico, opcional, quantidadeOpcional);
					reqServico.setValorTotal(reqServico.getValorTotal()+opcional.getValorItem());
				}
				//adicionar a linha
				if (linhaReqServ.getQuant() >= 1){
					// transformar o nomePapel em Objeto Papel 
					papel = this.converterToPapel(this.nomePapel);
					linhaReqServ.setPapel(papel);
					double valorPapel = 0.0;
					//verificar a cor em uso
					if (linhaReqServ.getImpressao() == "Mono")  
						valorPapel = papel.getImpMono();
					if (linhaReqServ.getImpressao() == "Color") 
						valorPapel = papel.getImpColor();
					else valorPapel = papel.getImpShade();
										
					reqServicoDAO.addLinha(reqServico, linhaReqServ);					
					reqServico.setValorTotal(reqServico.getValorTotal()+valorPapel);
				}		
		
				addGlobalMessage("Inclusão feita com sucesso!");
			} catch (ValidatorException e) {
				addGlobalMessage(e.getMessage());		
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
		this.limparLinhaOpcional();
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
	
	public void limparLinhaOpcional(){
		opcional = new OpcionaisReqServ();
		papel = new Papel();
		nomePapel = "";
		quantidadeOpcional = 0;
		linhaReqServ = new LinhaRequisicao();
	}
	
	public Papel converterToPapel(String nome) throws ValidatorException, Exception{		
		Papel papel = new Papel();
		
		//verificar se o nome do papel informado é válido
		if (this.validatePapeis(nome)) {				
			PapelDAO papelDAO = new PapelDAO();
								
			if (papelDAO.getListaPapelPorNome(nome).size() > 1){			
				throw new Exception("Existe mais de um papel como o mesmo nome!");
			} else {
				for (Papel p : papelDAO.getListaPapelPorNome(nome)){
					papel = p;
				}
			}
		}
		
		return papel;
	}
	
	private boolean validatePapeis(String nomePapel){
		PapelDAO papelDAO = new PapelDAO(); 
		List<String> papeis = papelDAO.getPapeis();
				 
		String nome = nomePapel.toString();
		boolean flag = false;
		for (String s : papeis) {
			if (s.toUpperCase().startsWith(nome.toUpperCase())) {
				flag = true;
			}
		}
		if (!flag) {
			FacesMessage message = new FacesMessage("Nome Papel Inválido");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}		
		
		return flag;
		
	}
	
	public static void addGlobalMessage(String message) {
		FacesMessage facesMessage = new FacesMessage(message);
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	}	
}
