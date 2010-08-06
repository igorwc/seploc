package br.seploc.mbeans;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import br.seploc.dao.PapelDAO;
import br.seploc.pojos.Papel;

/**
 * @author Igor
 *
 */
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
		papel = new Papel();
	}

	
	/**
	 * Método para incluir mensagens globais no formulário de cadastro
	 * @param String message
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

	public List<Papel> getLista() {
		return papelDAO.getLista();
	}

	public void limpar() {
		papel = new Papel();
	}

	// Validadores
	/**
	 * @param FacesContext
	 *            context
	 * @param UIComponent
	 *            component
	 * @param Object
	 *            value
	 * @throws ValidatorException
	 * 
	 */
	public void validateNome(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		if (value == null)
			return;
		String nome;
		Pattern pattern = Pattern.compile("^\\s*\\s(\\s)$");
		Matcher m = pattern.matcher(value.toString());
		if (value instanceof String)
			nome = value.toString();
		else {
			FacesMessage message = new FacesMessage("Nome Inválido");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
		if (nome.length() < 2 || nome.length() > 20) {
			FacesMessage message = new FacesMessage(
					"O nome deve ter entre 2 e 20 caracteres");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}

		if (m.matches()) {
			FacesMessage message = new FacesMessage("O nome só tem espaços");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
	}
}
