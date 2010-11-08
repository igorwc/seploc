package br.seploc.mbeans.tests;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import br.seploc.dao.PapelDAO;
import br.seploc.pojos.Papel;

public class PapelMB implements Serializable {

	private static final long serialVersionUID = 1L;
	private Papel papel;
	private PapelDAO papelDAO;
	static int quantidade = 0;

	public void cadastra() {
		if (papel.getCodPapel() == null || papel.getCodPapel() == 0) {
			papelDAO.adiciona(papel);
			addGlobalMessage("Inclusão feita com sucesso!");
		} else {
			Papel temp;
			temp = papelDAO.recupera(papel.getCodPapel());
			if (temp != null) {
				temp.setNome(papel.getNome().trim());
				temp.setImpColor(papel.getImpColor());
				temp.setImpMono(papel.getImpMono());
				temp.setImpShade(papel.getImpShade());
				papelDAO.altera(temp);
				addGlobalMessage("Atualização feita com sucesso!");
			}

		}
		//papel = new Papel();
		this.limpar();
	}

	public void limpar() {
		papel = new Papel();
		System.out.println("Limpar Papel");
	}

	/**
	 * Método para incluir mensagens globais no formulário de cadastro
	 * 
	 * @param String
	 *            message
	 */
	public static void addGlobalMessage(String message) {
		FacesMessage facesMessage = new FacesMessage(message);
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	}

	public void apaga() {
		try {
			papelDAO.remove(papel.getCodPapel());
			addGlobalMessage("Papel excluído com sucesso!");
		} catch (Exception e) {
			addGlobalMessage(e.getMessage());
		}
		papel = new Papel();
	}

	public void editar() {
		try {
			papel = papelDAO.recupera(papel.getCodPapel());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	// CONSTRUTOR
	public PapelMB() {
		init();
	}

	public void init(){
		quantidade++;
		System.out.println("quantidade:  " +quantidade);
		papel = new Papel();
		papelDAO = new PapelDAO();
		System.out.println("\n\n\n\n\n\n\nContrui PapelMB\n\n\n\n\n\n\n\n\n\n\n");
	}
	// SETTERS AND GETTERS
	public List<Papel> getLista() {
		return papelDAO.getLista();
	}

	public Papel getPapel() {
		System.out.println("Get Papel");
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

}
