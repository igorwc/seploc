package br.seploc.mbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import br.seploc.dao.ClienteDAO;
import br.seploc.dao.ProjetoDAO;
import br.seploc.dao.exceptions.FieldNotNullException;
import br.seploc.dao.pagedqueries.FilteredNameClientesPager;
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
	private String filtroClienteAnterior;	
	private boolean resetaFiltroCliente;
	
	
	private int clienteCurrentPage;
	private int clientePages;	
	private FilteredNameClientesPager clientePager;
	
	// CONSTRUTOR
	/**
	 * Construtor da classe
	 */
	public ProjetoMB(){
		init();
	}
	
	/**
	 * Metodo de inicializa��o
	 */
	public void init(){
		quantidade++;
		projeto = new Projeto();
		cliente = new Cliente();
		cliente.setIdCliente(0);
		projetoDAO = new ProjetoDAO();
		clienteDAO = new ClienteDAO();
		filtroCliente = "";
		filtroClienteAnterior = "";
		clientePager = new FilteredNameClientesPager();
		clientePager.init(10);
		resetaFiltroCliente = false;		
		
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

	public String getFiltroClienteAnterior() {
		return filtroClienteAnterior;
	}

	public void setFiltroClienteAnterior(String filtroClienteAnterior) {
		this.filtroClienteAnterior = filtroClienteAnterior;
	}

	public boolean isResetaFiltroCliente() {
		return resetaFiltroCliente;
	}

	public void setResetaFiltroCliente(boolean resetaFiltroCliente) {
		this.resetaFiltroCliente = resetaFiltroCliente;
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

	public void setClientePages(int clientePages) {
		this.clientePages = clientePages;
	}

	public FilteredNameClientesPager getClientePager() {
		return clientePager;
	}

	public void setClientePager(FilteredNameClientesPager clientePager) {
		this.clientePager = clientePager;
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
	
	public void proximaPaginaCliente() {
		clientePager.proximaPagina();
		clienteCurrentPage++;
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
	
	public void atualizaFiltro() {
		System.out.println(filtroCliente);
	}		
	
	public void resetaFiltro() {
		filtroCliente = "";
		resetaFiltroCliente = true;
	}		

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

	/**
	 * Limpar os Limpar
	 */
	public void limpar() {
		projeto = new Projeto();
		clienteDAO = new ClienteDAO();
	}	
	
	/**
	 * M�todo para incluir mensagens globais no formul�rio de cadastro
	 * 
	 * @param String
	 *            message
	 */
	public static void addGlobalMessage(String message) {
		FacesMessage facesMessage = new FacesMessage(message);
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	}	

}
