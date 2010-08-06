package br.seploc.mbeans;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import br.seploc.dao.OpcionaisReqServDAO;
import br.seploc.pojos.OpcionaisReqServ;

public class OpcionalBean {

	private OpcionaisReqServ opcional;
	private OpcionaisReqServDAO opcionalDAO;

	public OpcionalBean() {
		opcional = new OpcionaisReqServ();
		opcionalDAO = new OpcionaisReqServDAO();
	}

	public OpcionaisReqServ getOpcional() {
		return opcional;
	}

	public void setOpcional(OpcionaisReqServ op) {
		this.opcional = op;
	}

	public OpcionaisReqServDAO getOpcionalDAO() {
		return opcionalDAO;
	}

	public void setOpcionalDAO(OpcionaisReqServDAO opcionalDAO) {
		this.opcionalDAO = opcionalDAO;
	}

	public void cadastra() {

		try {
			if (opcional.getCodOpReqServ() == null
					|| opcional.getCodOpReqServ() == 0) {
				opcionalDAO.adiciona(opcional);
				addGlobalMessage("Inclusão feita com sucesso!");
			} else {
				OpcionaisReqServ temp;
				temp = opcionalDAO.recupera(opcional.getCodOpReqServ());
				if (temp != null) {
					temp.setCodOpReqServ(opcional.getCodOpReqServ());
					temp.setNomeItem(opcional.getNomeItem().trim());
					temp.setValorItem(opcional.getValorItem());
					opcionalDAO.altera(temp);
					addGlobalMessage("Atualização feita com sucesso!");
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		opcional = new OpcionaisReqServ();

	}

	public void apagar() {
		try {
			opcionalDAO.remove(opcional.getCodOpReqServ());
			addGlobalMessage("Papel excluído com sucesso!");
		} catch (Exception e) {
			addGlobalMessage(e.getMessage());
		}
		opcional = new OpcionaisReqServ();
	}

	public void editar() {
		try {
			opcional = opcionalDAO.recupera(opcional.getCodOpReqServ());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void limpar() {
		opcional = new OpcionaisReqServ();
	}

	public List<OpcionaisReqServ> getLista() {
		return opcionalDAO.getLista();
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
			nome = value.toString();
		else {
			FacesMessage message = new FacesMessage("Nome Inválido");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
		if (nome.length() < 5) {
			FacesMessage message = new FacesMessage(
					"O nome do Opcional deve ter 5 letras no mínimo");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
		if (nome.length() > 20) {
			FacesMessage message = new FacesMessage(
					"O nome do Opcional deve ter entre 5 e 20 caracteres");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
		if (m.matches()) {
			FacesMessage message = new FacesMessage(
					"O nome do opcional só tem espaços");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
	}
}
