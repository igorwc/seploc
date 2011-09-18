package br.seploc.mbeans;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import br.seploc.dao.PapelDAO;
import br.seploc.pojos.Papel;
import br.seploc.util.SIMNAO01;
import br.seploc.util.Utils;

public class LonaMB implements Serializable {

	private static final long serialVersionUID = 1L;
	private Papel papel;
	private PapelDAO papelDAO;
	private String filtroPapel;
	static int quantidade = 0;

	public void cadastra() {
		FacesContext context = FacesContext.getCurrentInstance();
		String msg = "";
		if (papel.getCodPapel() == null || papel.getCodPapel() == 0) {
			papel.setEhpapel(SIMNAO01.NAO);
			papel.setImpMono(0.0);
			papel.setImpShade(0.0);
			papelDAO.adiciona(papel);
			msg = Utils.getMessageResourceString("messages",
					"mensagens.inclusao.sucesso", null, context.getViewRoot()
							.getLocale());
			addGlobalMessage(msg);
		} else {
			Papel temp;
			temp = papelDAO.recupera(papel.getCodPapel());
			if (temp != null) {
				temp.setNome(papel.getNome().trim());
				temp.setImpColor(papel.getImpColor());
				temp.setImpMono(0.0);
				temp.setImpShade(0.0);
				temp.setEhpapel(SIMNAO01.NAO);
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
	 * Metodo para incluir mensagens globais no formulario de cadastro
	 * 
	 * @param String
	 *            message
	 */
	public static void addGlobalMessage(String message) {
		FacesMessage facesMessage = new FacesMessage(message);
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	}

	public void apaga() {
		FacesContext context = FacesContext.getCurrentInstance();
		String msg = Utils.getMessageResourceString("messages",
				"mensagens.exclusao.sucesso", null, context.getViewRoot()
						.getLocale());
		try {
			papelDAO.remove(papel.getCodPapel());
			addGlobalMessage(msg);
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
	public LonaMB() {
		init();
	}

	public void init(){
		quantidade++;
		papel = new Papel();
		papelDAO = new PapelDAO();
	}
	public void setFiltroPapel(String filtroPapel) {
		this.filtroPapel = filtroPapel;
	}

	public String getFiltroPapel() {
		return filtroPapel;
	}

	// SETTERS AND GETTERS
	public List<Papel> getLista() {
		return papelDAO.getListaLona();
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

}
