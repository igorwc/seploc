package br.seploc.mbeans;

import java.nio.charset.CodingErrorAction;
import java.util.ArrayList;
import java.util.List;

import br.seploc.dao.EntregaDAO;
import br.seploc.dao.EstadoDAO;
import br.seploc.pojos.Entrega;
import br.seploc.pojos.Estado;

public class SuggestionboxBean {
	private List<Entrega> lista;
    private Integer codEntrega;
    private Entrega ee;
    //TESTES DE UF-CIDADE-BAIRRO
    private List<Estado> listaUF;
    private int codUF;
    private int codCidade;
    private int codBairro;
    
	public SuggestionboxBean() {
		lista = new ArrayList<Entrega>();
		EntregaDAO entregaDAO = new EntregaDAO();
		EstadoDAO estadoDAO = new EstadoDAO();
		listaUF = estadoDAO.getLista();
		lista = entregaDAO.getLista();
	}

	public List<Entrega> complemento(Object event) {
		String prefixo = event.toString().toLowerCase();
		List<Entrega> retorno = new ArrayList<Entrega>();
		for(Entrega e : lista){
		   if(e.getLocal().toLowerCase().startsWith(prefixo)){
			   retorno.add(e);
		   }
		}
		return retorno;
	}
	
	public List<Estado> complementoUF(Object event) {
		String prefixo = event.toString().toLowerCase();
		List<Estado> retorno = new ArrayList<Estado>();
		for(Estado e : listaUF){
		   if(e.getNome().toLowerCase().startsWith(prefixo)){
			   retorno.add(e);
			   continue;
		   }
		   if(e.getSigla().toLowerCase().startsWith(prefixo)){
			   retorno.add(e);
			   continue;
		   }
		}
		return retorno;
	}
	
	public void refreshData(){
		EntregaDAO entregaDAO = new EntregaDAO();
		ee = entregaDAO.recupera(codEntrega);
		System.out.println(ee);
	}
	
	public void refreshUF(){
		
		System.out.println("Setou codigo UF "+ codUF);
	}

	/**
	 * @return the lista
	 */
	public List<Entrega> getLista() {
		return lista;
	}

	/**
	 * @param lista the lista to set
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
	 * @param codEntrega the codEntrega to set
	 */
	public void setCodEntrega(Integer codEntrega) {
		this.codEntrega = codEntrega;
		System.out.println("Setou codEntrega "+codEntrega );
	}

	/**
	 * @return the codUF
	 */
	public int getCodUF() {
		return codUF;
	}

	/**
	 * @param codUF the codUF to set
	 */
	public void setCodUF(int codUF) {
		this.codUF = codUF;
	}

	/**
	 * @return the codCidade
	 */
	public int getCodCidade() {
		return codCidade;
	}

	/**
	 * @param codCidade the codCidade to set
	 */
	public void setCodCidade(int codCidade) {
		this.codCidade = codCidade;
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
	
	
}
