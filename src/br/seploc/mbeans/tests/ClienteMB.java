/**
 * 
 */
package br.seploc.mbeans.tests;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import br.seploc.dao.BairroDAO;
import br.seploc.dao.CidadeDAO;
import br.seploc.dao.ClienteDAO;
import br.seploc.dao.EntregaDAO;
import br.seploc.dao.PapelDAO;
import br.seploc.pojos.Bairro;
import br.seploc.pojos.Cidade;
import br.seploc.pojos.Cliente;
import br.seploc.pojos.Entrega;
import br.seploc.pojos.FoneCliente;
import br.seploc.pojos.Papel;

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
	private Papel papelPadrao;
	private String filtroPapel;
	//VARIAVEIS SUGGESTION BOX BAIRROS
	private long ultimaConsultaBairro;
	private List<Bairro> listaBairros;
	private int codBairro;
	private int codCidadeAnterior = -1;
	private boolean erroDoc;
	private boolean erroRazao;
	

	//METODOS NEGOCIO
	public void cadastrar() {
		try {
			if (foneCliente != null) {
				cliente.setFoneCliente(foneCliente);
			}
			if(cidadeEscolhida != null){
				cliente.setCidade(cidadeEscolhida.getNome());
				cliente.setEstado(cidadeEscolhida.getUf().getSigla());
			}
			if(cliente.getFantasia() == null || cliente.getFantasia().trim().equals("")){
				cliente.setFantasia(cliente.getRazao());
			}
			if(entregaPadrao!= null){
				cliente.setEntregaPadrao(entregaPadrao);
			}
			if(papelPadrao != null){
				cliente.setPapelPadrao(papelPadrao);
			}
			clienteDAO.adiciona(cliente);
			limpar();
			addGlobalMessage("Inclusão feita com sucesso!");

		} catch (Exception e) {
			addGlobalMessage(e.getMessage());
			e.printStackTrace();
		}
	}
	
	//METODOS AUXILIARES
	public static void addGlobalMessage(String message) {
		FacesMessage facesMessage = new FacesMessage(message);
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	}
	
	public void limpar() {
		cliente = new Cliente();
		foneCliente = new FoneCliente();
		cidadeEscolhida = new Cidade();
		entregaPadrao = new Entrega();
		papelPadrao = new Papel();
		// FacesContext.getCurrentInstance().renderResponse();
		System.out.println("Limpar Cliente");
	}
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
	
	public List<Papel> getListaPapeis() {

		PapelDAO papelDAO  = new PapelDAO();
		List<Papel> retorno = papelDAO.getLista();

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
	 * @return the papelPadrao
	 */
	public Papel getPapelPadrao() {
		return papelPadrao;
	}
	/**
	 * @param papelPadrao the papelPadrao to set
	 */
	public void setPapelPadrao(Papel papelPadrao) {
		this.papelPadrao = papelPadrao;
	}
	/**
	 * @return the filtroPapel
	 */
	public String getFiltroPapel() {
		return filtroPapel;
	}
	/**
	 * @param filtroPapel the filtroPapel to set
	 */
	public void setFiltroPapel(String filtroPapel) {
		this.filtroPapel = filtroPapel;
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
	
	/**
	 * @return the erroDoc
	 */
	public boolean isErroDoc() {
		return erroDoc;
	}

	/**
	 * @param erroDoc the erroDoc to set
	 */
	public void setErroDoc(boolean erroDoc) {
		this.erroDoc = erroDoc;
		System.out.println("setou erroDoc:" + erroDoc);
	}

	public void validaErroDoc() {
		this.erroDoc = false;
	}
	public void validaErroRazao() {
		this.erroRazao = false;
	}
	/**
	 * @return the erroRazao
	 */
	public boolean isErroRazao() {
		return erroRazao;
	}

	/**
	 * @param erroRazao the erroRazao to set
	 */
	public void setErroRazao(boolean erroRazao) {
		this.erroRazao = erroRazao;
	}
	public ClienteMB() {
		cliente = new Cliente();
		foneCliente = new FoneCliente();
		clienteDAO = new ClienteDAO();
		opDocCliente = 1;
		erroDoc = false;
		erroRazao = false;
	}
	
	
}
