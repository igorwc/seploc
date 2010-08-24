package br.seploc.mbeans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.validator.ValidatorException;

import br.seploc.dao.EntregaDAO;
import br.seploc.dao.PapelDAO;
import br.seploc.pojos.Entrega;

public class AppServiceBean {

	private List<String> locaisEntrega;
	private List<String> papeis;
	private int tamanholistaPapel;
	private int tamanholistaEntrega;

	public List<SelectItem> getLocaisEntrega() {
		ArrayList<SelectItem> retorno = new ArrayList<SelectItem>();
		EntregaDAO entregaDAO = new EntregaDAO();
		List<String> entregas = entregaDAO.getLocaisEntrega();
		this.setLocaisEntrega(entregas);
		for (String b : entregas) {
			retorno.add(new SelectItem(b));
		}
		return retorno;
	}

	public List<SelectItem> getPapeis() {
		ArrayList<SelectItem> retorno = new ArrayList<SelectItem>();
		PapelDAO papelDAO = new PapelDAO();
		List<String> papeis = papelDAO.getPapeis();
		if (papeis == null || papeis.isEmpty()) {
			retorno.add(new SelectItem(""));

		}
		this.setPapeis(papeis);

		for (String b : papeis) {
			retorno.add(new SelectItem(b));
		}
		return retorno;
	}

	/**
	 * @param locaisEntrega
	 *            the locaisEntrega to set
	 */
	public void setLocaisEntrega(List<String> locaisEntrega) {
		this.locaisEntrega = locaisEntrega;
		this.setTamanholistaEntrega(locaisEntrega.size());
	}

	/**
	 * @return the tamanholistaPapel
	 */
	public int getTamanholistaPapel() {
		return tamanholistaPapel;
	}

	/**
	 * @param tamanholistaPapel
	 *            the tamanholistaPapel to set
	 */
	public void setTamanholistaPapel(int tamanholistaPapel) {
		this.tamanholistaPapel = tamanholistaPapel;

	}

	/**
	 * @return the tamanholistaEntrega
	 */
	public int getTamanholistaEntrega() {
		return tamanholistaEntrega;
	}

	/**
	 * @param tamanholistaEntrega
	 *            the tamanholistaEntrega to set
	 */
	public void setTamanholistaEntrega(int tamanholistaEntrega) {
		this.tamanholistaEntrega = tamanholistaEntrega;
	}

	/**
	 * @param papeis
	 *            the papeis to set
	 */
	public void setPapeis(List<String> papeis) {
		this.papeis = papeis;
		this.setTamanholistaPapel(this.papeis.size());
		System.out.println("Setou tamanho " + tamanholistaPapel);
	}

	public static void addGlobalMessage(String message) {
		FacesMessage facesMessage = new FacesMessage(message);
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	}

	/**
	 * @param context
	 * @param component
	 * @param value
	 * @throws ValidatorException
	 */
	public void validatePapeis(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		String valor = value.toString();
		boolean flag = false;
		for (String s : papeis) {
			if (s.toUpperCase().startsWith(valor.toUpperCase())) {
				flag = true;
			}
		}
		if (!flag) {
			FacesMessage message = new FacesMessage("Nome Papel Inválido");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}

	}
	public List<Entrega> complemento(Object event) {
		String prefixo = event.toString().toLowerCase();
		List<Entrega> retorno = new ArrayList<Entrega>();
		EntregaDAO entregaDAO = new EntregaDAO();
		List<Entrega> lista = entregaDAO.getLista();
		for(Entrega e : lista){
		   if(e.getLocal().toLowerCase().startsWith(prefixo)){
			   retorno.add(e);
		   }
		}
		return retorno;
	}
	/**
	 * @param context
	 * @param component
	 * @param value
	 * @throws ValidatorException
	 */
	public void validateEntrega(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		String valor = value.toString();
		boolean flag = false;
		for (String s : locaisEntrega) {
			if (s.toUpperCase().startsWith(valor.toUpperCase())) {
				flag = true;
			}
		}

		if (!flag) {
			FacesMessage message = new FacesMessage("Local de Entrega Inválido");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}

	}
}
