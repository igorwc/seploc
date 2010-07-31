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
		System.out.println("criou bean papel");
	}
	public Papel getPapel() {
		System.out.println("get papel");
		return papel;
	}
	public void setPapel(Papel papel) {
		System.out.println("set papel");
		this.papel = papel;
	}
	public PapelDAO getPapelDAO() {
		return papelDAO;
	}
	public void setPapelDAO(PapelDAO papelDAO) {
		this.papelDAO = papelDAO;
	}
	
	public void cadastra(){
		System.out.println("cadastra papel");
		papelDAO.adiciona(papel);
		papel = new Papel();
	}
	public List<Papel> getLista() {
		return papelDAO.getLista();
	}

	
	
}
