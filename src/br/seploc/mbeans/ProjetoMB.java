package br.seploc.mbeans;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import br.seploc.dao.ClienteDAO;
import br.seploc.dao.ProjetoDAO;
import br.seploc.dao.exceptions.FieldNotNullException;
import br.seploc.pojos.Cliente;
import br.seploc.pojos.Projeto;

public class ProjetoMB implements Serializable {

	private static final long serialVersionUID = 1L;
	static int quantidade = 0;
	private Projeto projeto;
	private Cliente cliente;
	private ProjetoDAO projetoDAO;	
	ClienteDAO clienteDAO;
	private String filtroCliente;
	
	// CONSTRUTOR
	/**
	 * Construtor da classe
	 */
	public ProjetoMB(){
		init();
	}
	
	/**
	 * Metodo de inicialização
	 */
	public void init(){
		quantidade++;
		projeto = new Projeto();
		cliente = new Cliente();
		cliente.setIdCliente(0);
		projetoDAO = new ProjetoDAO();
		clienteDAO = new ClienteDAO();
		
	}

	// SETTERS AND GETTERS
	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	public ProjetoDAO getProjetoDAO() {
		return projetoDAO;
	}

	public void setProjetoDAO(ProjetoDAO projetoDAO) {
		this.projetoDAO = projetoDAO;
	}
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getFiltroCliente() {
		return filtroCliente;
	}

	public void setFiltroCliente(String filtroCliente) {
		this.filtroCliente = filtroCliente;
	}

	/**
	 * Recuperar Lista dos Projetos do cliente
	 * @return List<OpcionaisReqServ>
	 */
	public List<Projeto> getLista() {		  
		Cliente c = clienteDAO.recupera(cliente.getIdCliente());
		if (c == null) {
			return null;
		} else if (c.getProjetos().isEmpty() || c.getProjetos().size() == 0) {
			return null;
		} else {
			return c.getProjetos();
		}
	}

	/**
	 * Recupera lista de todos os clientes
	 * @return
	 */
	public List<Cliente> getTodosClientes() {		
		List<Cliente> retorno = this.clienteDAO.getLista();
		return retorno;
	}
	
	public void cadastrar() {
		try {
			if (cliente.getIdCliente() == null || cliente.getIdCliente() == 0) {
				throw new FieldNotNullException(
						"Cliente é Obrigatório para Projeto");
				
			}else{
				cliente = clienteDAO.recupera(cliente.getIdCliente());
				projeto.setCliente(cliente);
			}

			projetoDAO.adiciona(projeto);

			addGlobalMessage("Inclusão feita com sucesso!");
		} catch (Exception e) {
			addGlobalMessage(e.getMessage());
			e.printStackTrace();
		}
		limpar();
	}

	/**
	 * Editar o projeto
	 */
	public void editar() {
		projeto = projetoDAO.recupera(projeto.getCodProj());
	}

	/**
	 * Apagar o projeto
	 */	
	public void apagar() {
		try{
			projetoDAO.remove(projeto.getCodProj());
			addGlobalMessage("Projeto excluído com sucesso!");
		} catch (Exception e) {
			addGlobalMessage(e.getMessage());
		}
		this.limpar();
	}

	/**
	 * Limpar os Limpar
	 */
	public void limpar() {
		projeto = new Projeto();
		projetoDAO = new ProjetoDAO();
		clienteDAO = new ClienteDAO();
	}	
	
	/**
	 * Método para incluir mensagens globais no formulário de cadastro
	 * 
	 * @param String
	 *            message
	 */
	public static void addGlobalMessage(String message) {
		FacesMessage facesMessage = new FacesMessage(message);
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	}	

}
