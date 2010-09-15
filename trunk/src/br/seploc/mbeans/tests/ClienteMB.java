/**
 * 
 */
package br.seploc.mbeans.tests;

import br.seploc.dao.ClienteDAO;
import br.seploc.pojos.Cliente;
import br.seploc.pojos.FoneCliente;

/**
 * @author Igor
 *
 */
public class ClienteMB {

	private Cliente cliente;
	private FoneCliente foneCliente;
	private ClienteDAO clienteDAO;
	private Integer opDocCliente;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * @return the cliente
	 */
	public Cliente getCliente() {
		return cliente;
	}
	/**
	 * @param cliente the cliente to set
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	/**
	 * @return the foneCliente
	 */
	public FoneCliente getFoneCliente() {
		return foneCliente;
	}
	/**
	 * @param foneCliente the foneCliente to set
	 */
	public void setFoneCliente(FoneCliente foneCliente) {
		this.foneCliente = foneCliente;
	}
	/**
	 * @return the clienteDAO
	 */
	public ClienteDAO getClienteDAO() {
		return clienteDAO;
	}
	/**
	 * @param clienteDAO the clienteDAO to set
	 */
	public void setClienteDAO(ClienteDAO clienteDAO) {
		this.clienteDAO = clienteDAO;
	}
	/**
	 * @return the opDocCliente
	 */
	public Integer getOpDocCliente() {
		return opDocCliente;
	}
	/**
	 * @param opDocCliente the opDocCliente to set
	 */
	public void setOpDocCliente(Integer opDocCliente) {
		this.opDocCliente = opDocCliente;
	}
	public ClienteMB() {
		cliente = new Cliente();
		foneCliente = new FoneCliente();
		clienteDAO = new ClienteDAO();
		opDocCliente = 1;
	}
	
	
}
