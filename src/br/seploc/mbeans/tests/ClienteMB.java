/**
 * 
 */
package br.seploc.mbeans.tests;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.seploc.dao.BairroDAO;
import br.seploc.dao.CidadeDAO;
import br.seploc.dao.ClienteDAO;
import br.seploc.dao.EntregaDAO;
import br.seploc.pojos.Bairro;
import br.seploc.pojos.Cidade;
import br.seploc.pojos.Cliente;
import br.seploc.pojos.Entrega;
import br.seploc.pojos.FoneCliente;

/**
 * @author Igor
 *
 */
public class ClienteMB {

	private Cliente cliente;
	private FoneCliente foneCliente;
	private ClienteDAO clienteDAO;
	private Entrega entregaPadrao;
	private Integer opDocCliente;
	private String filtroUF;
	private String filtroLocalEntrega;
	private String filtroCidade;
	private Integer codCidade;
	private Cidade cidadeEscolhida;
	private String localidade;
	//VARIAVEIS SUGGESTION BOX BAIRROS
	private long ultimaConsultaBairro;
	private List<Bairro> listaBairros;
	private int codBairro;
	private int codCidadeAnterior = -1;
	
	
	//METODOS AUXILIARES
	public List<Cidade> getTodasCidades() {

		CidadeDAO cidadeDAO = new CidadeDAO();
		List<Cidade> retorno = cidadeDAO.getLista();

		return retorno;
	}
	public List<Entrega> getLocaisEntrega() {

		EntregaDAO entregaDAO = new EntregaDAO();
		List<Entrega> retorno = entregaDAO.getLista();

		return retorno;
	}
	
	public List<Bairro> complementoBairro(Object event) {
		long today = 0;
		BairroDAO bairroDAO = new BairroDAO();
		if (ultimaConsultaBairro == 0 || codCidade != codCidadeAnterior) {
			ultimaConsultaBairro = Calendar.getInstance().getTimeInMillis();
			codCidadeAnterior = codCidade;
			today = ultimaConsultaBairro;
			listaBairros = bairroDAO.recuperaBairrosPorCidade(codCidade);
			System.out.println("setou tempo cidade");
		}else{
			today = Calendar.getInstance().getTimeInMillis();
		}
		long diff = today - ultimaConsultaBairro;
		System.out.println(diff + " valor bairro: " + ((diff / 1000) / 60));
		if (!(((diff / 1000) / 60) < 5)) {
			listaBairros = bairroDAO.recuperaBairrosPorCidade(codCidade);
			codCidadeAnterior = codCidade;
			ultimaConsultaBairro = Calendar.getInstance().getTimeInMillis();
		}
		String prefixo = event.toString().toLowerCase();
		List<Bairro> retorno = new ArrayList<Bairro>();
		if(listaBairros == null){
			return retorno;
		}
		for (Bairro e : listaBairros) {
			if (e.getNome().toLowerCase().startsWith(prefixo)) {
				retorno.add(e);
				continue;
			}
		}
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
	 * @return the entregaPadrao
	 */
	public Entrega getEntregaPadrao() {
		return entregaPadrao;
	}
	/**
	 * @param entregaPadrao the entregaPadrao to set
	 */
	public void setEntregaPadrao(Entrega entregaPadrao) {
		this.entregaPadrao = entregaPadrao;
	}
	/**
	 * @return the filtroLocalEntrega
	 */
	public String getFiltroLocalEntrega() {
		return filtroLocalEntrega;
	}
	/**
	 * @param filtroLocalEntrega the filtroLocalEntrega to set
	 */
	public void setFiltroLocalEntrega(String filtroLocalEntrega) {
		this.filtroLocalEntrega = filtroLocalEntrega;
	}
	/**
	 * @param localidade the localidade to set
	 */
	public void setLocalidade(String localidade) {
		this.localidade = localidade;
		this.cliente.setBairro("");
	}
	/**
	 * @return the cidadeEscolhida
	 */
	public Cidade getCidadeEscolhida() {
		return cidadeEscolhida;
	}

	/**
	 * @return the codBairro
	 */
	public int getCodBairro() {
		return codBairro;
	}

	/**
	 * @param codBairro the codBairro to set
	 */
	public void setCodBairro(int codBairro) {
		this.codBairro = codBairro;
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
