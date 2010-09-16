/**
 * 
 */
package br.seploc.mbeans.tests;

import java.util.List;

import br.seploc.dao.CidadeDAO;
import br.seploc.dao.ClienteDAO;
import br.seploc.pojos.Cidade;
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
	private String filtroUF;
	private String filtroCidade;
	private Integer codCidade;
	private Cidade cidadeEscolhida;
	private String localidade;
	
	
	
	
	//METODOS AUXILIARES
	public List<Cidade> getTodasCidades() {

		CidadeDAO cidadeDAO = new CidadeDAO();
		List<Cidade> retorno = cidadeDAO.getLista();

		return retorno;
	}
	
	
	
	
	
	
	
	
	//SETTERS AND GETTERS
	
	/**
	 * @return the codCidade
	 */
	public Integer getCodCidade() {
		return codCidade;
	}

	/**
	 * @return the localidade
	 */
	public String getLocalidade() {
		return localidade;
	}

	/**
	 * @param localidade the localidade to set
	 */
	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}
	/**
	 * @return the cidadeEscolhida
	 */
	public Cidade getCidadeEscolhida() {
		return cidadeEscolhida;
	}

	/**
	 * @param cidadeEscolhida the cidadeEscolhida to set
	 */
	public void setCidadeEscolhida(Cidade cidadeEscolhida) {
		this.cidadeEscolhida = cidadeEscolhida;
	}

	/**
	 * @param codCidade the codCidade to set
	 * @throws Exception 
	 */
	public void setCodCidade(Integer codCidade) throws Exception {
		this.codCidade = codCidade;
		CidadeDAO cidadeDAO = new CidadeDAO();
		this.cidadeEscolhida = cidadeDAO.recupera(codCidade);
		localidade = cidadeEscolhida.getNome()+" - "+ cidadeEscolhida.getUf().getSigla();
	}
	
	/**
	 * @return the cliente
	 */
	public Cliente getCliente() {
		return cliente;
	}
	/**
	 * @return the filtroUF
	 */
	public String getFiltroUF() {
		return filtroUF;
	}








	/**
	 * @param filtroUF the filtroUF to set
	 */
	public void setFiltroUF(String filtroUF) {
		this.filtroUF = filtroUF;
	}








	/**
	 * @return the filtroCidade
	 */
	public String getFiltroCidade() {
		return filtroCidade;
	}








	/**
	 * @param filtroCidade the filtroCidade to set
	 */
	public void setFiltroCidade(String filtroCidade) {
		this.filtroCidade = filtroCidade;
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
