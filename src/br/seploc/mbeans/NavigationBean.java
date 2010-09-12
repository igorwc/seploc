package br.seploc.mbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.faces.component.html.HtmlInputText;
import javax.faces.model.SelectItem;

import br.seploc.dao.BairroDAO;
import br.seploc.dao.CidadeDAO;
import br.seploc.pojos.Bairro;
import br.seploc.pojos.Cidade;

public class NavigationBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1027518204952836092L;
	private Integer opcaoDocId;
	private final Integer CNPJ = 1;
	private final Integer CPF = 2;
	private String cidadeCorrenteFormCliente = "";
	private String estadoCorrenteFormCliente = "";
	private List<Cidade> todasCidades;
	private Cidade cidadeEscolhidaFormCliente;
	private Integer codCidade;
	private String localidadeFormCliente;
	
	//VARIAVEIS SUGGESTION BOX BAIRROS
	private long ultimaConsultaBairro;
	private HtmlInputText inputBairro;
	private List<Bairro> listaBairros;
	private int codBairro;
	/**
	 * @return the cNPJ
	 */
	public Integer getCNPJ() {
		return CNPJ;
	}

	/**
	 * @return the cPF
	 */
	public Integer getCPF() {
		return CPF;

	}

	/**
	 * @return the opcaoDocId
	 */
	public Integer getOpcaoDocId() {
		return opcaoDocId;
	}

	/**
	 * @param opcaoDocId
	 *            the opcaoDocId to set
	 */
	public void setOpcaoDocId(Integer opcaoDocId) {
		System.out.println("Setou Valor: " + opcaoDocId);
		this.opcaoDocId = opcaoDocId;
	}

	public void resetarBean() {
		setOpcaoDocId(1);
	}

	public List<Cidade> getTodasCidades() {

		CidadeDAO cidadeDAO = new CidadeDAO();
		List<Cidade> retorno = cidadeDAO.getLista();

		return retorno;
	}

	/**
	 * @return the codCidade
	 */
	public Integer getCodCidade() {
		return codCidade;
	}

	/**
	 * @param codCidade
	 *            the codCidade to set
	 * @throws Exception
	 */
	public void setCodCidade(Integer codCidade) throws Exception {
		this.codCidade = codCidade;
		CidadeDAO cidadeDAO = new CidadeDAO();
		cidadeEscolhidaFormCliente = cidadeDAO.recupera(codCidade);
		localidadeFormCliente = cidadeEscolhidaFormCliente.getNome() +" - "+cidadeEscolhidaFormCliente.getUf().getSigla();
	}

	public List<Bairro> complementoBairro(Object event) {
		long today = 0;
		BairroDAO bairroDAO = new BairroDAO();
		
		if (ultimaConsultaBairro == 0) {
			ultimaConsultaBairro = Calendar.getInstance().getTimeInMillis();
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

	/**
	 * @return the cidadeCorrenteFormCliente
	 */
	public String getCidadeCorrenteFormCliente() {
		return cidadeCorrenteFormCliente;
	}

	/**
	 * @param cidadeCorrenteFormCliente the cidadeCorrenteFormCliente to set
	 */
	public void setCidadeCorrenteFormCliente(String cidadeCorrenteFormCliente) {
		this.cidadeCorrenteFormCliente = cidadeCorrenteFormCliente;
	}

	/**
	 * @return the estadoCorrenteFormCliente
	 */
	public String getEstadoCorrenteFormCliente() {
		return estadoCorrenteFormCliente;
	}

	/**
	 * @param estadoCorrenteFormCliente the estadoCorrenteFormCliente to set
	 */
	public void setEstadoCorrenteFormCliente(String estadoCorrenteFormCliente) {
		this.estadoCorrenteFormCliente = estadoCorrenteFormCliente;
	}

	/**
	 * @return the cidadeEscolhidaFormCliente
	 */
	public Cidade getCidadeEscolhidaFormCliente() {
		return cidadeEscolhidaFormCliente;
	}

	/**
	 * @param cidadeEscolhidaFormCliente the cidadeEscolhidaFormCliente to set
	 */
	public void setCidadeEscolhidaFormCliente(Cidade cidadeEscolhidaFormCliente) {
		this.cidadeEscolhidaFormCliente = cidadeEscolhidaFormCliente;
	}

	/**
	 * @return the inputBairro
	 */
	public HtmlInputText getInputBairro() {
		return inputBairro;
	}

	/**
	 * @param inputBairro the inputBairro to set
	 */
	public void setInputBairro(HtmlInputText inputBairro) {
		this.inputBairro = inputBairro;
	}

	/**
	 * @return the listaBairros
	 */
	public List<Bairro> getListaBairros() {
		return listaBairros;
	}

	/**
	 * @param listaBairros the listaBairros to set
	 */
	public void setListaBairros(List<Bairro> listaBairros) {
		this.listaBairros = listaBairros;
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
	 * @return the localidadeFormCliente
	 */
	public String getLocalidadeFormCliente() {
		return localidadeFormCliente;
	}

	/**
	 * @param localidadeFormCliente the localidadeFormCliente to set
	 */
	public void setLocalidadeFormCliente(String localidadeFormCliente) {
		this.localidadeFormCliente = localidadeFormCliente;
	}
	
}
