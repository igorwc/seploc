package br.seploc.mbeans;

import java.util.List;

import br.seploc.dao.EntregaDAO;
import br.seploc.pojos.Entrega;
import br.seploc.pojos.Papel;

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

	public void setEntrega(Entrega e) {
		this.entrega = e;
	}

	public EntregaDAO getEntregaDAO() {
		return entregaDAO;
	}

	public void setEntregaDAO(EntregaDAO entregaDAO) {
		this.entregaDAO = entregaDAO;
	}

	public void cadastra() {
		try {
			if (entrega.getCodEntrega() == null || entrega.getCodEntrega() == 0)
				entregaDAO.adiciona(entrega);
			else {
				Entrega temp;
				temp = entregaDAO.recupera(entrega.getCodEntrega());
				if (temp != null) {
					temp.setLocal(entrega.getLocal());
					temp.setPreco(entrega.getPreco());
					entregaDAO.altera(temp);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		entrega = new Entrega();
	}

	public void apagar() {
		try {
			entregaDAO.remove(entrega.getCodEntrega());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		entrega = new Entrega();
	}

	public void editar() {
		try {
			entrega = entregaDAO.recupera(entrega.getCodEntrega());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void limpar() {
		entrega = new Entrega();
	}

	public List<Entrega> getLista() {
		return entregaDAO.getLista();
	}
}
