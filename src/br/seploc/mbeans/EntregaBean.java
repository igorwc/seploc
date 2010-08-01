package br.seploc.mbeans;

import java.util.List;

import br.seploc.dao.EntregaDAO;
import br.seploc.pojos.Entrega;


public class EntregaBean {

	private Entrega entrega;
	private EntregaDAO entregaDAO;
	
	
	public EntregaBean() {	
		entrega = new Entrega();
		entregaDAO = new EntregaDAO();
	}
	public Entrega getEntrega() {
		return entrega;
	}
	public void setEntrega (Entrega e) {
		this.entrega = e;
	}
	public EntregaDAO getEntregaDAO() {
		return entregaDAO;
	}
	public void setEntregaDAO(EntregaDAO entregaDAO) {
		this.entregaDAO = entregaDAO;
	}
	
	public void cadastra(){
		try{
		entregaDAO.adiciona(entrega);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public List<Entrega> getLista() {
		return entregaDAO.getLista();
	}
}
