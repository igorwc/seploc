package br.seploc.mbeans.tests;

import java.util.List;

import br.seploc.dao.CidadeDAO;
import br.seploc.pojos.Cidade;

public class MBeanTest {

	private String cidadeCorrente = "";
	private List<Cidade> todasCidades;
	private Cidade cidadeEscolhida;
	private Integer codCidade;
	public List<Cidade> getTodasCidades(){
		
		CidadeDAO cidadeDAO = new CidadeDAO();
		List<Cidade> retorno = cidadeDAO.getLista();
		
		
		return retorno;
	}
	/**
	 * @return the cidadeCorrente
	 */
	public String getCidadeCorrente() {
		System.out.println("get = "+ this.cidadeCorrente);
		return cidadeCorrente;
	}
	/**
	 * @param cidadeCorrente the cidadeCorrente to set
	 */
	public void setCidadeCorrente(String cidadeCorrente) {
		System.out.println("set = "+ this.cidadeCorrente);
		this.cidadeCorrente = cidadeCorrente;
	}
	/**
	 * 
	 */
	public MBeanTest() {
		// TODO Auto-generated constructor stub
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
	 * @return the codCidade
	 */
	public Integer getCodCidade() {
		return codCidade;
	}
	/**
	 * @param codCidade the codCidade to set
	 * @throws Exception 
	 */
	public void setCodCidade(Integer codCidade) throws Exception {
		this.codCidade = codCidade;
		CidadeDAO cidadeDAO = new CidadeDAO();
		cidadeEscolhida= cidadeDAO.recupera(codCidade);
	}
	
}
