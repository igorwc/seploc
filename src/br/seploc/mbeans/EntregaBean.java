package br.seploc.mbeans;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

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
			if (entrega.getCodEntrega() == null || entrega.getCodEntrega() == 0) {
				entregaDAO.adiciona(entrega);
				addGlobalMessage("Inclusão feita com sucesso!");
			} else {
				Entrega temp;
				temp = entregaDAO.recupera(entrega.getCodEntrega());
				if (temp != null) {
					temp.setLocal(entrega.getLocal());
					temp.setPreco(entrega.getPreco());
					entregaDAO.altera(temp);
					addGlobalMessage("Atualização feita com sucesso!");
				}
			}
		} catch (Exception e) {
			addGlobalMessage("Não foi possível realizar a operação!");
		}
		entrega = new Entrega();
	}

	public void apagar() {
		try {
			entregaDAO.remove(entrega.getCodEntrega());
		} catch (Exception e) {
			addGlobalMessage(e.getMessage());
		}
		entrega = new Entrega();
	}

	public void editar() {
		try {
			entrega = entregaDAO.recupera(entrega.getCodEntrega());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void limpar() {
		entrega = new Entrega();
	}

	public List<Entrega> getLista() {
		return entregaDAO.getLista();
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
			nome = value.toString().trim();
		else {
			FacesMessage message = new FacesMessage("Nome Inválido");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
		if (nome.length() < 5) { // Rever essa regra, se for Pina por exemplo?
			FacesMessage message = new FacesMessage(
					"O local de entrega deve ter 5 letras no mínimo");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
		if (nome.length() > 20) {
			FacesMessage message = new FacesMessage(
					"O local de entrega deve ter entre 5 e 20 caracteres");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
		if (m.matches()) {
			FacesMessage message = new FacesMessage(
					"O nome do local de entrega só tem espaços");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
	}
}
