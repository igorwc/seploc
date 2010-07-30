package br.seploc.mbeans;

import java.util.List;

import javax.persistence.Query;

import br.seploc.dao.PapelDAO;
import br.seploc.pojos.Papel;

public class PapelBean {

	private Papel papel;
	private PapelDAO papelDAO;
	
	
	public PapelBean() {
		papel = new Papel();
		papelDAO = new PapelDAO();
	}
	public Papel getPapel() {
		return papel;
	}
	public void setPapel(Papel papel) {
		this.papel = papel;
	}
	public PapelDAO getPapelDAO() {
		return papelDAO;
	}
	public void setPapelDAO(PapelDAO papelDAO) {
		this.papelDAO = papelDAO;
	}
	
	public void cadastra(){
		papelDAO.adiciona(papel);
	}
	public List<Papel> getLista() {
		return papelDAO.getLista();
	}

	
	
}
