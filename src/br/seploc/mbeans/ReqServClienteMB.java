package br.seploc.mbeans;

import java.io.Serializable;
import java.util.List;

import br.seploc.pojos.Cliente;
import br.seploc.pojos.OpcionaisReqServ;
import br.seploc.pojos.Entrega;
import br.seploc.pojos.Papel;
import br.seploc.pojos.Projeto;
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
	private String filtroOpcional;
	private String filtroEntrega;
	private String filtroPapel;
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

	public String getFiltroPapel() {
		return filtroPapel;
	}

	public void setFiltroPapel(String filtroPapel) {
		this.filtroPapel = filtroPapel;
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
}
