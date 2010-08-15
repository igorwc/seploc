package br.seploc.mbeans;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.el.ExpressionFactory;
import javax.el.ValueExpression;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import br.seploc.dao.ClienteDAO;
import br.seploc.pojos.Cliente;

public class ClienteBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Cliente cliente;
	private ClienteDAO clienteDAO;

	public ClienteBean() {
		cliente = new Cliente();
		clienteDAO = new ClienteDAO();
	}

	public void limpaDoc() {
		cliente.setCnpj(null);
		cliente.setCpf(null);
	}

	public Cliente getCliente() {
		// System.out.println("Get Cliente");
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public ClienteDAO getClienteDAO() {
		return clienteDAO;
	}

	public void setClienteDAO(ClienteDAO clienteDAO) {
		this.clienteDAO = clienteDAO;
	}

	public void setCPF(String cpf) {
		cliente.setCpf(cpf);
		cliente.setCnpj(null);
	}

	public String getCPF() {
		return cliente.getCpf();
	}

	public void setCNPJ(String cnpj) {
		cliente.setCpf(null);
		cliente.setCnpj(cnpj);
	}

	public String getCNPJ() {
		return cliente.getCpf();
	}

	public void cadastra() {

	}

	public void edita() {

	}

	public void apaga() {

	}

	public void limpa() {
		cliente = new Cliente();
		System.out.println("Limpar Cliente");
	}

	public static void addGlobalMessage(String message) {
		FacesMessage facesMessage = new FacesMessage(message);
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	}

	/*
	 * Validadores
	 */
	
	/**
	 * @param context
	 * @param component
	 * @param value
	 * @throws ValidatorException
	 */
	public void validateCPF(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		Application app = context.getApplication();
		// NavigationBean navigationBean =
		// app.getExpressionFactory().createValueExpression(context.getELContext(),"#{navigationBean}",
		// NavigationBean.class).getValue(arg0);
		// @SuppressWarnings("deprecation")
		// NavigationBean navigationBean =
		// (NavigationBean)app.createValueBinding("#{navigationBean}").getValue(context);

		ExpressionFactory exprFactory = app.getExpressionFactory();
		ValueExpression valueExpr = exprFactory.createValueExpression(
				context.getELContext(), "#{navigationBean}",
				NavigationBean.class);
		NavigationBean navigationBean = (NavigationBean) valueExpr
				.getValue(context.getELContext());

		if (navigationBean.getOpcaoDocId() == 1) {
			this.getCliente().setCpf("");
			return;
		}
		String numeroCPF = "";
		if (value == null || value.toString().equals(""))
			return;
		else {
			if (value instanceof String) {
				numeroCPF = getDigitsOnly(value.toString());
				System.out.println(numeroCPF);
				if (numeroCPF.length() != 11) {
					FacesMessage message = new FacesMessage(
							"O cpf deve ter 11 digitos.");
					message.setSeverity(FacesMessage.SEVERITY_ERROR);
					throw new ValidatorException(message);
				}
				if (!validaCPF(numeroCPF) || digitosIguais(numeroCPF)) {
					FacesMessage message = new FacesMessage("CPF inválido.");
					message.setSeverity(FacesMessage.SEVERITY_ERROR);
					throw new ValidatorException(message);

				}
			}
		}
	}

	/**
	 * @param context
	 * @param component
	 * @param value
	 * @throws ValidatorException
	 */
	public void validateCNPJ(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		Application app = context.getApplication();
		// NavigationBean navigationBean =
		// app.getExpressionFactory().createValueExpression(context.getELContext(),"#{navigationBean}",
		// NavigationBean.class).getValue(arg0);
		// @SuppressWarnings("deprecation")
		// NavigationBean navigationBean =
		// (NavigationBean)app.createValueBinding("#{navigationBean}").getValue(context);
		String numeroCNPJ = "";
		ExpressionFactory exprFactory = app.getExpressionFactory();
		ValueExpression valueExpr = exprFactory.createValueExpression(
				context.getELContext(), "#{navigationBean}",
				NavigationBean.class);
		NavigationBean navigationBean = (NavigationBean) valueExpr
				.getValue(context.getELContext());

//		this.getCliente().setCpf("");

		if (navigationBean.getOpcaoDocId() == 2) {
			this.getCliente().setCnpj("");
			return;
		}
		if (value == null || value.toString().equals(""))
			return;
		else {
			if (value instanceof String) {
				numeroCNPJ = getDigitsOnly(value.toString());
				System.out.println(numeroCNPJ);
				if (numeroCNPJ.length() != 14) {
					FacesMessage message = new FacesMessage(
							"O CNPJ deve ter 14 digitos.");
					message.setSeverity(FacesMessage.SEVERITY_ERROR);
					throw new ValidatorException(message);
				}
				if (!validaCNPJ(numeroCNPJ) || digitosIguais(numeroCNPJ)) {
					FacesMessage message = new FacesMessage("CNPJ inválido.");
					message.setSeverity(FacesMessage.SEVERITY_ERROR);
					throw new ValidatorException(message);

				}
			}
		}

	}
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
	public void validateRazaoSocial(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		if (value == null)
			return;
		String nome;

		Pattern pattern = Pattern.compile("^\\s*\\s(\\s)$");
		Matcher m = pattern.matcher(value.toString());
		if (value instanceof String)
			nome = value.toString().trim();
		else {
			FacesMessage message = new FacesMessage("Razão Social Inválida");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
		if (nome.length() < 5) {
			FacesMessage message = new FacesMessage("A Razão Social deve ter 5 letras no mínimo");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
		if (nome.length() > 20) {
			FacesMessage message = new FacesMessage(
					"A Razão Social deve ter entre 5 e 20 caracteres");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
		if (m.matches()) {
			FacesMessage message = new FacesMessage(
					"A Razão Social só tem espaços");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
	}
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
	public void validateFantasia(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		if (value == null)
			return;
		String nome;

		Pattern pattern = Pattern.compile("^\\s*\\s(\\s)$");
		Matcher m = pattern.matcher(value.toString());
		if (value instanceof String)
			nome = value.toString().trim();
		else {
			FacesMessage message = new FacesMessage("Nome Fantasia Inválido");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
		if (nome.length() < 5) {
			FacesMessage message = new FacesMessage("O Nome Fantasia deve ter 5 letras no mínimo");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
		if (nome.length() > 20) {
			FacesMessage message = new FacesMessage(
					"O Nome Fantasia deve ter entre 5 e 20 caracteres");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
		if (m.matches()) {
			FacesMessage message = new FacesMessage(
					"O Nome Fantasia só tem espaços");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
	}
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
	public void validateEmail(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		if (value == null)
			return;
		String nome;

		Pattern pattern = Pattern.compile("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?");
		Matcher m = pattern.matcher(value.toString());
		if (value instanceof String)
			nome = value.toString().trim();
		else {
			FacesMessage message = new FacesMessage("Nome Fantasia Inválido");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
		if (!m.matches()) {
			FacesMessage message = new FacesMessage(
					"Email Inválido!");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
	}
	/*
	 * 
	 * FUNÇÕES AUXILIARES
	 */
	/**
	 * @param cpf
	 * @return boolean
	 */
	private boolean digitosIguais(String cpf) {
		boolean resultado = true;
		char[] arrayDigitos = cpf.toCharArray();
		char c = arrayDigitos[1];
		for (char d : arrayDigitos) {
			if (d != c) {
				resultado = false;
				break;
			}
		}

		return resultado;

	}

	/**
	 * @param s
	 * @return
	 */
	private String getDigitsOnly(String s) {
		StringBuffer digitsOnly = new StringBuffer();
		char c;
		for (int i = 0; i < s.length(); i++) {
			c = s.charAt(i);
			if (Character.isDigit(c)) {
				digitsOnly.append(c);
			}
		}
		return digitsOnly.toString();
	}

	/**
	 * @param nrcpf
	 * @return
	 */
	private boolean validaCPF(String nrcpf) {
		String cpf = nrcpf;
		int soma = 0, mult = 11;
		int[] var = new int[11];

		// Recebe os números e realiza a multiplicação e soma.
		for (int i = 0; i < 11; i++) {
			var[i] = Integer.parseInt("" + cpf.charAt(i));
			if (i < 9)
				soma += (var[i] * --mult);
		}

		// Cria o primeiro dígito verificador.
		int resto = soma % 11;
		if (resto < 2) {
			var[9] = 0;
		} else {
			var[9] = 11 - resto;
		}

		// Reinicia os valores.
		soma = 0;
		mult = 11;

		// Realiza a multiplicação e soma do segundo dígito.
		for (int i = 0; i < 10; i++)
			soma += var[i] * mult--;

		// Cria o segundo dígito verificador.
		resto = soma % 11;
		if (resto < 2) {
			var[10] = 0;
		} else {
			var[10] = 11 - resto;
		}

		int v1 = Integer.parseInt("" + cpf.charAt(9));
		int v2 = Integer.parseInt("" + cpf.charAt(10));

		// Confere os dígitos criados com os dígitados, se forem diferentes
		// informa que o CPF é inválido.
		if (v1 != var[9] || v2 != var[10]) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Valida CNPJ do usuário.
	 * 
	 * @param cnpj
	 *            String valor com 14 dígitos
	 */
	public boolean validaCNPJ(String cnpj) {
		if (cnpj == null || cnpj.length() != 14)
			return false;

		try {
			Long.parseLong(cnpj);
		} catch (NumberFormatException e) { // CNPJ não possui somente números
			return false;
		}

		int soma = 0;
		String cnpj_calc = cnpj.substring(0, 12);

		char chr_cnpj[] = cnpj.toCharArray();
		for (int i = 0; i < 4; i++)
			if (chr_cnpj[i] - 48 >= 0 && chr_cnpj[i] - 48 <= 9)
				soma += (chr_cnpj[i] - 48) * (6 - (i + 1));

		for (int i = 0; i < 8; i++)
			if (chr_cnpj[i + 4] - 48 >= 0 && chr_cnpj[i + 4] - 48 <= 9)
				soma += (chr_cnpj[i + 4] - 48) * (10 - (i + 1));

		int dig = 11 - soma % 11;
		cnpj_calc = (new StringBuilder(String.valueOf(cnpj_calc))).append(
				dig != 10 && dig != 11 ? Integer.toString(dig) : "0")
				.toString();
		soma = 0;
		for (int i = 0; i < 5; i++)
			if (chr_cnpj[i] - 48 >= 0 && chr_cnpj[i] - 48 <= 9)
				soma += (chr_cnpj[i] - 48) * (7 - (i + 1));

		for (int i = 0; i < 8; i++)
			if (chr_cnpj[i + 5] - 48 >= 0 && chr_cnpj[i + 5] - 48 <= 9)
				soma += (chr_cnpj[i + 5] - 48) * (10 - (i + 1));

		dig = 11 - soma % 11;
		cnpj_calc = (new StringBuilder(String.valueOf(cnpj_calc))).append(
				dig != 10 && dig != 11 ? Integer.toString(dig) : "0")
				.toString();

		if (!cnpj.equals(cnpj_calc))
			return false;
		else
			return true;
	}
}
