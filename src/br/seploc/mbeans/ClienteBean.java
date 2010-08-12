package br.seploc.mbeans;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import br.seploc.dao.ClienteDAO;
import br.seploc.pojos.Cliente;

public class ClienteBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Cliente cliente;
	private ClienteDAO clienteDAO;
	
	
	public ClienteBean(){
		cliente = new Cliente();
		clienteDAO = new ClienteDAO();
	}
	
	public void limpaDoc(){
		cliente.setCnpj("");
		cliente.setCpf("");
	}
	public Cliente getCliente() {
//		System.out.println("Get Cliente");
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public ClienteDAO getClienteDAO() {
		return clienteDAO;
	}

	public void setClienteDAO(ClienteDAO clienteDAO) {
		this.clienteDAO = clienteDAO;
	}

	public void cadastra() {
		
	}
	
	public void edita(){
		
	}
	
	public void apaga(){
		
	}
	
	public void limpa() {
		cliente =  new Cliente();
		System.out.println("Limpar Cliente");
	}	
	
	public static void addGlobalMessage(String message) {
		FacesMessage facesMessage = new FacesMessage(message);
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	}
	
}
