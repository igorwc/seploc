package br.seploc.mbeans;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import br.seploc.dao.PapelDAO;
import br.seploc.pojos.Papel;
import br.seploc.util.SIMNAO01;
import br.seploc.util.Utils;

public class PapelMB implements Serializable {

	private static final long serialVersionUID = 1L;
	private Papel papel;
	private PapelDAO papelDAO;
	private String filtroPapel;
	static int quantidade = 0;

	public void cadastra() {
		FacesContext context = FacesContext.getCurrentInstance();
		String msg = "";
		if (papel.getCodPapel() == null || papel.getCodPapel() == 0) {
			if (!existe(papel)) {
				papel.setEhpapel(SIMNAO01.SIM);
				papelDAO.adiciona(papel);
				msg = Utils.getMessageResourceString("messages",
						"mensagens.inclusao.sucesso", null, context.getViewRoot()
								.getLocale());
				papelDAO.adiciona(papel);
				//addGlobalMessage("Inclusao feita com sucesso!");
			} else {
				addGlobalMessage("Nome do papel já existe!");
			}			
		} else {
			Papel temp;
			temp = papelDAO.recupera(papel.getCodPapel());
			if (temp != null) {
				temp.setNome(papel.getNome().trim());
				temp.setImpColor(papel.getImpColor());
				temp.setImpMono(papel.getImpMono());
				temp.setImpShade(papel.getImpShade());
				temp.setEhpapel(SIMNAO01.SIM);
				papelDAO.altera(temp);
				msg = Utils.getMessageResourceString("messages",
						"mensagens.atualizacao.sucesso", null, context.getViewRoot()
								.getLocale());
				addGlobalMessage(msg);
			}

		}
		//papel = new Papel();
		this.limpar();
	}

	public void limpar() {
		papel = new Papel(); 
	}

	/**
	 * M�todo para incluir mensagens globais no formul�rio de cadastro
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
			addGlobalMessage("Papel excluido com sucesso!");
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

	private boolean existe(Papel papel){
		return papelDAO.existe(papel);
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
	public void setFiltroPapel(String filtroPapel) {
		this.filtroPapel = filtroPapel;
	}

	public String getFiltroPapel() {
		return filtroPapel;
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
