package br.seploc.mbeans;

import java.util.ArrayList;
import java.util.List;

import br.seploc.dao.EntregaDAO;
import br.seploc.pojos.Entrega;

public class SuggestionboxBean {
	private List<Entrega> lista;
    private Integer codEntrega;
    private Entrega ee;
    
	public SuggestionboxBean() {
		lista = new ArrayList<Entrega>();
		EntregaDAO entregaDAO = new EntregaDAO();
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
	public void refreshData(){
		EntregaDAO entregaDAO = new EntregaDAO();
		ee = entregaDAO.recupera(codEntrega);
		System.out.println(ee);
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
	
}
