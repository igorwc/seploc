package br.seploc.mbeans;

import java.util.List;

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
	
	public void cadastra() {
		System.out.println("cadastra papel");
		if (papel.getCodPapel() == null || papel.getCodPapel() == 0)
			papelDAO.adiciona(papel);
		else {
			Papel temp;
			temp = papelDAO.recupera(papel.getCodPapel());
			if (temp != null) {
				temp.setNome(papel.getNome());
				temp.setImpColor(papel.getImpColor());
				temp.setImpMono(papel.getImpMono());
				temp.setImpShade(papel.getImpShade());
				papelDAO.altera(temp);
			}

		}
		papel = new Papel();
	}
	
	public void apaga(){
		System.out.println("apaga papel");
		try {
			papelDAO.remove(papel.getCodPapel());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		papel = new Papel();
	}
	
	public void editar(){
		System.out.println("edita papel");
		try {
			papel =  papelDAO.recupera(papel.getCodPapel());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public List<Papel> getLista() {
		return papelDAO.getLista();
	}

	public void limpar(){
		System.out.println("limpa papel");
		papel = new Papel();
	}
	
}
