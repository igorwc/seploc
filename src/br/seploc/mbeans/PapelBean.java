package br.seploc.mbeans;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.HttpServletResponse;

import br.seploc.dao.PapelDAO;
import br.seploc.pojos.Papel;

public class PapelBean {

	private Papel papel;
	private PapelDAO papelDAO;
	private int sucesso;

	public PapelBean() {
		papel = new Papel();
		papelDAO = new PapelDAO();
		sucesso = 0;
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
		if (papel.getCodPapel() == null || papel.getCodPapel() == 0) {
			papelDAO.adiciona(papel);
			addGlobalMessage("Cadastro com sucesso!");
		} else {
			Papel temp;
			temp = papelDAO.recupera(papel.getCodPapel());
			if (temp != null) {
				temp.setNome(papel.getNome());
				temp.setImpColor(papel.getImpColor());
				temp.setImpMono(papel.getImpMono());
				temp.setImpShade(papel.getImpShade());
				papelDAO.altera(temp);
				addGlobalMessage("Atualização feita com sucesso!");
				// FacesMessage message = new
				// FacesMessage("Adicionado com sucesso!");
				// message.setSeverity(FacesMessage.SEVERITY_INFO);
				// message.setSummary("Nome Inválido");
				// throw new ValidatorException(message);
			}

		}
		papel = new Papel();
	}

	public static void addGlobalMessage(String message) {
		FacesMessage facesMessage = new FacesMessage(message);
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	}

	public int getSucesso() {
		return sucesso;
	}

	public void setSucesso(int sucesso) {
		this.sucesso = sucesso;
	}

	public void apaga() {
		System.out.println("apaga papel");
		try {
			papelDAO.remove(papel.getCodPapel());
			addGlobalMessage("Papel excluído com sucesso!");
		} catch (Exception e) {
			addGlobalMessage(e.getMessage());
			System.err.println("Lançou exceção");
		}
		papel = new Papel();
	}

	public void editar() {
		System.out.println("edita papel");
		try {
			papel = papelDAO.recupera(papel.getCodPapel());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Papel> getLista() {
		return papelDAO.getLista();
	}

	public void limpar() {
		System.out.println("limpa papel");
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
			message.setSummary("Nome Inválido");
			throw new ValidatorException(message);
		}
		System.out.println("nome: \"" + nome + "\"");
		if (nome.length() < 2 || nome.length() > 20) {
			FacesMessage message = new FacesMessage(
					"O nome deve ter entre 2 e 20 caracteres");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			message.setSummary("O nome deve ter entre 2 e 20 caracteres");
			throw new ValidatorException(message);
		}

		if (m.matches()) {
			FacesMessage message = new FacesMessage("O nome só tem espaços");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			// message.setSummary("Nome Inválido");
			throw new ValidatorException(message);
		}

		// if(!luhnCheck(cardNumber)) {
		// FacesMessage message
		// = com.corejsf.util.Messages.getMessage(
		// “com.corejsf.messages”, “badLuhnCheck”, null);
		// message.setSeverity(FacesMessage.SEVERITY_ERROR);
		// throw new ValidatorException(message);
		// }
	}
}
