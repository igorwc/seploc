package br.seploc.mbeans;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.validator.ValidatorException;

import br.seploc.dao.BairroDAO;
import br.seploc.dao.CidadeDAO;
import br.seploc.dao.ClienteDAO;
import br.seploc.dao.EntregaDAO;
import br.seploc.dao.PapelDAO;
import br.seploc.dao.OpcionaisReqServDAO;
import br.seploc.pojos.Bairro;
import br.seploc.pojos.Cidade;
import br.seploc.pojos.Cliente;
import br.seploc.pojos.Entrega;
import br.seploc.pojos.Estado;
import br.seploc.pojos.OpcionaisReqServ;

public class AppServiceBean {

	private static List<String> locaisEntrega;
	private static List<String> papeis;
	private static List<String> opcionais;
	private static List<String> nomesCliente;
	private static String cidadeCorrente = "";
	private static List<Cliente> listaClientes;
	private static List<Cliente> listaClientesCadastrados;
	private static int tamanholistaPapel;
	private static int tamanholistaEntrega;
	private static int tamanholistaOpcionais;
	private static int tamanholistaClientes;
	private static boolean isloaded;
	private static long ultimaConsultaCliente;
	private static long ultimaConsultaClientesCadastrados;

	static {
		ultimaConsultaCliente = 0;
		ultimaConsultaClientesCadastrados = 0;
		listaClientesCadastrados = new ArrayList<Cliente>();
		listaClientes = new ArrayList<Cliente>();
	}

	/**
	 * @return the cidadeCorrente
	 */
	public static String getCidadeCorrente() {
		System.out.println("get = " + AppServiceBean.cidadeCorrente);
		return cidadeCorrente;
	}

	/**
	 * @param cidadeCorrente
	 *            the cidadeCorrente to set
	 */
	public static void setCidadeCorrente(String cidadeCorrente) {
		System.out.println("set = " + AppServiceBean.cidadeCorrente);
		AppServiceBean.cidadeCorrente = cidadeCorrente;
	}

	public static int getTamanholistaClientes() {
		return tamanholistaClientes;
	}

	public static void setTamanholistaClientes(int tamanholistaClientes) {
		AppServiceBean.tamanholistaClientes = tamanholistaClientes;
	}

	public static int getTamanholistaOpcionais() {
		return tamanholistaOpcionais;
	}

	public static void setTamanholistaOpcionais(int tamanholistaOpcionais) {
		AppServiceBean.tamanholistaOpcionais = tamanholistaOpcionais;
	}

	public static List<Cidade> getTodasCidades() {

		CidadeDAO cidadeDAO = new CidadeDAO();
		List<Cidade> retorno = cidadeDAO.getLista();

		return retorno;
	}

	public static List<SelectItem> getLocaisEntrega() {
		ArrayList<SelectItem> retorno = new ArrayList<SelectItem>();
		EntregaDAO entregaDAO = new EntregaDAO();
		List<String> entregas = entregaDAO.getLocaisEntrega();
		AppServiceBean.setLocaisEntrega(entregas);
		for (String b : entregas) {
			retorno.add(new SelectItem(b));
		}
		return retorno;
	}

	public static List<SelectItem> getNomesCliente() {
		ArrayList<SelectItem> retorno = new ArrayList<SelectItem>();
		ClienteDAO clienteDAO = new ClienteDAO();
		List<String> clientes = clienteDAO.getNomesCliente();
		AppServiceBean.setNomesCliente(clientes);
		for (String b : clientes) {
			retorno.add(new SelectItem(b));
		}
		return retorno;
	}

	public static List<SelectItem> getPapeis() {
		ArrayList<SelectItem> retorno = new ArrayList<SelectItem>();
		PapelDAO papelDAO = new PapelDAO();
		List<String> papeis = papelDAO.getPapeis();
		if (papeis == null || papeis.isEmpty()) {
			retorno.add(new SelectItem(""));

		}
		AppServiceBean.setPapeis(papeis);

		for (String b : papeis) {
			retorno.add(new SelectItem(b));
		}
		return retorno;
	}

	public static List<SelectItem> getOpcionais() {
		ArrayList<SelectItem> retorno = new ArrayList<SelectItem>();
		OpcionaisReqServDAO opcionaisDAO = new OpcionaisReqServDAO();
		List<String> opcionais = opcionaisDAO.getOpcionais();
		if (opcionais == null || opcionais.isEmpty()) {
			retorno.add(new SelectItem(""));

		}
		AppServiceBean.setOpcionais(opcionais);

		for (String b : opcionais) {
			retorno.add(new SelectItem(b));
		}
		return retorno;
	}

	/**
	 * @param locaisEntrega
	 *            the locaisEntrega to set
	 */
	public static void setLocaisEntrega(List<String> locaisEntrega) {
		AppServiceBean.locaisEntrega = locaisEntrega;
		AppServiceBean.setTamanholistaEntrega(locaisEntrega.size());
	}

	public static void setNomesCliente(List<String> nomesCliente) {
		AppServiceBean.nomesCliente = nomesCliente;
		AppServiceBean.setTamanholistaClientes(nomesCliente.size());
	}

	/**
	 * @return the tamanholistaPapel
	 */
	public static int getTamanholistaPapel() {
		return tamanholistaPapel;
	}

	/**
	 * @param tamanholistaPapel
	 *            the tamanholistaPapel to set
	 */
	public static void setTamanholistaPapel(int tamanholistaPapel) {
		AppServiceBean.tamanholistaPapel = tamanholistaPapel;

	}

	/**
	 * @return the tamanholistaEntrega
	 */
	public static int getTamanholistaEntrega() {
		return tamanholistaEntrega;
	}

	/**
	 * @param tamanholistaEntrega
	 *            the tamanholistaEntrega to set
	 */
	public static void setTamanholistaEntrega(int tamanholistaEntrega) {
		AppServiceBean.tamanholistaEntrega = tamanholistaEntrega;
	}

	/**
	 * @param papeis
	 *            the papeis to set
	 */
	public static void setPapeis(List<String> papeis) {
		AppServiceBean.papeis = papeis;
		AppServiceBean.setTamanholistaPapel(AppServiceBean.papeis.size());
		System.out.println("Setou tamanho " + tamanholistaPapel);
	}

	public static void setOpcionais(List<String> opcionais) {
		AppServiceBean.opcionais = opcionais;
		AppServiceBean
				.setTamanholistaOpcionais(AppServiceBean.opcionais.size());
		System.out.println("Setou tamanho " + tamanholistaOpcionais);
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
	public static void validatePapeis(FacesContext context,
			UIComponent component, Object value) throws ValidatorException {
		String valor = value.toString();
		boolean flag = false;
		for (String s : papeis) {
			if (s.toUpperCase().startsWith(valor.toUpperCase())) {
				flag = true;
			}
		}
		if (!flag) {
			FacesMessage message = new FacesMessage("Nome Papel Inv�lido");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}

	}

	public static List<Entrega> complemento(Object event) {
		String prefixo = event.toString().toLowerCase();
		List<Entrega> retorno = new ArrayList<Entrega>();
		EntregaDAO entregaDAO = new EntregaDAO();
		List<Entrega> lista = entregaDAO.getLista();
		for (Entrega e : lista) {
			if (e.getLocal().toLowerCase().startsWith(prefixo)) {
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
	public static void validateEntrega(FacesContext context,
			UIComponent component, Object value) throws ValidatorException {
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

	public static synchronized List<Cliente> getListaClientes() {
		long today = 0;

		ClienteDAO clienteDAO = new ClienteDAO();
		if (ultimaConsultaCliente == 0) {
			ultimaConsultaCliente = Calendar.getInstance().getTimeInMillis();

			today = ultimaConsultaCliente;
			listaClientes = clienteDAO.getLista();
		} else {
			today = Calendar.getInstance().getTimeInMillis();
		}
		long diff = today - ultimaConsultaCliente;
		if (!(((diff / 1000) / 60) < 5)) {
			listaClientes = clienteDAO.getLista();
			ultimaConsultaCliente = Calendar.getInstance().getTimeInMillis();
		}

		return listaClientes;
	}

	public static synchronized List<Cliente> getListaClientesCadastrados() {
		long today = 0;

		ClienteDAO clienteDAO = new ClienteDAO();
		if (ultimaConsultaClientesCadastrados == 0) {
			ultimaConsultaClientesCadastrados = Calendar.getInstance()
					.getTimeInMillis();

			today = ultimaConsultaClientesCadastrados;
			listaClientesCadastrados = clienteDAO.getListaClientesCadastrados();
		} else {
			today = Calendar.getInstance().getTimeInMillis();
		}
		long diff = today - ultimaConsultaClientesCadastrados;
		if (!(((diff / 1000) / 60) < 5)) {
			listaClientesCadastrados = clienteDAO.getListaClientesCadastrados();
			ultimaConsultaClientesCadastrados = Calendar.getInstance()
					.getTimeInMillis();
		}

		return listaClientesCadastrados;
	}

	public static synchronized void carregaListas() {
		getListaClientesCadastrados();
		getListaClientes();
	}

}
