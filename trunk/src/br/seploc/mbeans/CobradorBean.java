package br.seploc.mbeans;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import br.seploc.dao.CobradorDAO;
import br.seploc.pojos.Cobrador;


public class CobradorBean {

	private Cobrador cobrador;
	private CobradorDAO cobradorDAO;
	
	public CobradorBean(){
		cobrador = new Cobrador();
		cobradorDAO = new CobradorDAO();
	}
	
	public Cobrador getCobrador(){
		return cobrador;
	}
	
	public void setCobrador(Cobrador cobrador){
		this.cobrador = cobrador;
	}
	
	public CobradorDAO getCobradorDAO(){
		return cobradorDAO;
	}
	
	public void setCobradorDAO(CobradorDAO cobradorDAO){
		this.cobradorDAO = cobradorDAO;
	}
	
	public void cadastra() {
		
	}
	
	public void edita(){
		
	}
	
	public void apaga(){
		
	}
	
	public void limpa() {
		cobrador =  new Cobrador();
		System.out.println("Limpar Cobrador");
	}	
	
	public List<Cobrador> getLista(){
		return cobradorDAO.getLista();
	}
	
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
		if (nome.length() < 5) {
			FacesMessage message = new FacesMessage("O nome deve ter 5 letras no mínimo");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
		if (nome.length() > 20) {
			FacesMessage message = new FacesMessage(
					"O nome deve ter entre 5 e 20 caracteres");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
		if (m.matches()) {
			FacesMessage message = new FacesMessage(
					"O nome só tem espaços");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
	}	
}
