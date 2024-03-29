package br.seploc.mbeans;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.faces.component.html.HtmlInputText;

import br.seploc.dao.BairroDAO;
import br.seploc.dao.CidadeDAO;
import br.seploc.dao.EntregaDAO;
import br.seploc.dao.EstadoDAO;
import br.seploc.pojos.Bairro;
import br.seploc.pojos.Cidade;
import br.seploc.pojos.Entrega;
import br.seploc.pojos.Estado;

public class SuggestionboxBean {
	private List<Entrega> lista;
	private Integer codEntrega;
	private Entrega ee;
	// TESTES DE UF-CIDADE-BAIRRO
	private List<Estado> listaUF;
	private List<Cidade> listaCidades;
	private List<Bairro> listaBairros;
	private int codUF;
	private int codUFAnterior;
	private int codCidade;
	private int codCidadeAnterior;
	private int codBairro;
	private HtmlInputText inputBairro;
	private HtmlInputText inputCidade;
	private long ultimaConsulta;
	private long ultimaConsultaCidade;
	private long ultimaConsultaBairro;

	public SuggestionboxBean() {
		lista = new ArrayList<Entrega>();
		EntregaDAO entregaDAO = new EntregaDAO();
		EstadoDAO estadoDAO = new EstadoDAO();
		listaUF = estadoDAO.getLista();
		lista = entregaDAO.getLista();
		ultimaConsulta = Calendar.getInstance().getTimeInMillis();
		codUFAnterior = -1;
	}

	public List<Entrega> complemento(Object event) {

		String prefixo = event.toString().toLowerCase();
		List<Entrega> retorno = new ArrayList<Entrega>();
		for (Entrega e : lista) {
			if (e.getLocal().toLowerCase().startsWith(prefixo)) {
				retorno.add(e);
			}
		}
		return retorno;
	}

	public List<Estado> complementoUF(Object event) {
		long today = Calendar.getInstance().getTimeInMillis();
		long diff = today - ultimaConsulta;
		System.out.println(diff + " valor: " + ((diff / 1000) / 60));
		if (!(((diff / 1000) / 60) < 5)) {
			EstadoDAO estadoDAO = new EstadoDAO();
			listaUF = estadoDAO.getLista();
			ultimaConsulta = Calendar.getInstance().getTimeInMillis();
		}
		String prefixo = event.toString().toLowerCase();
		List<Estado> retorno = new ArrayList<Estado>();
		for (Estado e : listaUF) {
			if (e.getNome().toLowerCase().startsWith(prefixo)) {
				retorno.add(e);
				continue;
			}
			if (e.getSigla().toLowerCase().startsWith(prefixo)) {
				retorno.add(e);
				continue;
			}
		}
		return retorno;
	}

	public List<Cidade> complementoCidade(Object event) {
		long today = 0;
		CidadeDAO cidadeDAO = new CidadeDAO();
		
		if (ultimaConsultaCidade == 0) {
			ultimaConsultaCidade = Calendar.getInstance().getTimeInMillis();
			today = ultimaConsultaCidade;
			listaCidades = cidadeDAO.recuperaCidadesPorEstado(codUF);
			System.out.println("setou tempo cidade");
		}else{
			today = Calendar.getInstance().getTimeInMillis();
		}
		long diff = today - ultimaConsultaCidade;
		System.out.println(diff + " valor Cidade: " + ((diff / 1000) / 60));
		System.out.println(" valor uf: " +codUF+ " valor uf ant: " +codUFAnterior);
		if (!(((diff / 1000) / 60) < 5)) {
			listaCidades = cidadeDAO.recuperaCidadesPorEstado(codUF);
			ultimaConsultaCidade = Calendar.getInstance().getTimeInMillis();
		}
		String prefixo = event.toString().toLowerCase();
		List<Cidade> retorno = new ArrayList<Cidade>();
		if(listaCidades == null){
			return retorno;
		}
		for (Cidade e : listaCidades) {
			if (e.getNome().toLowerCase().startsWith(prefixo)) {
				retorno.add(e);
				continue;
			}
		}
		return retorno;
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
	public void refreshData() {
		EntregaDAO entregaDAO = new EntregaDAO();
		ee = entregaDAO.recupera(codEntrega);
		System.out.println(ee);
	}

	public void refreshUF() {

		System.out.println("Setou codigo UF " + codUF);
	}

	/**
	 * @return the lista
	 */
	public List<Entrega> getLista() {
		return lista;
	}

	/**
	 * @param lista
	 *            the lista to set
	 */
	public void setLista(List<Entrega> lista) {
		this.lista = lista;
	}

	/**
	 * @return the codEntrega
	 */
	public Integer getCodEntrega() {
		return codEntrega;
	}

	/**
	 * @param codEntrega
	 *            the codEntrega to set
	 */
	public void setCodEntrega(Integer codEntrega) {
		this.codEntrega = codEntrega;
		System.out.println("Setou codEntrega " + codEntrega);
	}

	/**
	 * @return the codUF
	 */
	public int getCodUF() {
		return codUF;
	}

	/**
	 * @param codUF
	 *            the codUF to set
	 */
	public void setCodUF(int codUF) {
		if( this.codUF == codUF){
			return;
		}else{
			codUFAnterior = this.codUF;
			this.codUF = codUF;
			ultimaConsultaCidade = 0;
			System.out.println("Setou codigo UF " + this.codUF + " antigo: "
			+ this.codUFAnterior);
		}
//		if (codUFAnterior == -1) {
//			codUFAnterior = -2;
//			this.codUF = codUF;
//			System.out.println("Setou codigo UF " + this.codUF + " antigo: "
//					+ this.codUFAnterior);
//			return;
//		}
//        	ultimaConsultaCidade = 0;
//        	codUFAnterior = this.codUF;
//		
//		this.codUF = codUF;
//		System.out.println("Setou codigo UF " + this.codUF + " antigo: "
//				+ this.codUFAnterior);

	}

	/**
	 * @return the codCidade
	 */
	public int getCodCidade() {
		return codCidade;
	}

	/**
	 * @param codCidade
	 *            the codCidade to set
	 */
	public void setCodCidade(int codCidade) {
//		if (inputCidade.getSubmittedValue()!= null && inputCidade.getSubmittedValue().toString().trim() != ""){
//			inputBairro.getAttributes().remove("disabled");
//			inputBairro.getAttributes().put("disabled", "false");
//		}
		if( this.codCidade == codCidade){
			return;
		}else{
			codCidadeAnterior = this.codCidade;
			this.codCidade = codCidade;
			ultimaConsultaCidade = 0;
			System.out.println("Setou codigo Cidade " + this.codCidade + " antigo: "
			+ this.codCidadeAnterior);
		}
//		this.codCidade = codCidade;
//		System.out.println("Setou codigo cidade " + this.codCidade );
	}

	/**
	 * @return the codBairro
	 */
	public int getCodBairro() {
		return codBairro;
	}

	/**
	 * @param codBairro
	 *            the codBairro to set
	 */
	public void setCodBairro(int codBairro) {
		this.codBairro = codBairro;
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
	 * @return the inputCidade
	 */
	public HtmlInputText getInputCidade() {
		return inputCidade;
	}

	/**
	 * @param inputCidade the inputCidade to set
	 */
	public void setInputCidade(HtmlInputText inputCidade) {
		this.inputCidade = inputCidade;
	}

}
