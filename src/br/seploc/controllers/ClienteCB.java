package br.seploc.controllers;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.el.ExpressionFactory;
import javax.el.ValueExpression;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlSelectOneRadio;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import br.seploc.mbeans.AppServiceBean;
import br.seploc.mbeans.NavigationBean;
import br.seploc.mbeans.tests.ClienteMB;
import br.seploc.util.Utils;

public class ClienteCB implements Serializable {

	private static final long serialVersionUID = 1L;
	private ClienteMB clienteMB;
	private HtmlInputText inputCNPJ;
	private HtmlInputText inputCPF;
	private HtmlSelectOneRadio selectDocType;
	private final Integer CNPJ = 1;
	private final Integer CPF = 2;
	private boolean teste;

	// METODOS DE NEGOCIO
	public void cadastrar(){
		if((Integer) selectDocType.getValue() == 1){
			teste = true;
		}
		if (!isClienteInvalido()) {
			clienteMB.cadastrar();
			limpaDoc();
			clienteMB.limpar();
		}
	}
	
	public boolean isClienteInvalido(){
		String msgErro = "Campo(s) obrigatório(s): \n";
		boolean contemErro = false;
		if (((Integer) selectDocType.getValue()) == 1) {
			clienteMB.getCliente().setCnpj(inputCNPJ.getValue().toString());
			clienteMB.getCliente().setCpf(null);
			// inputCPF.resetValue();
		} else {
			clienteMB.getCliente().setCnpj(null);
			clienteMB.getCliente().setCpf(inputCPF.getValue().toString());
		}
		if ((clienteMB.getCliente().getCpf() == null || clienteMB.getCliente()
				.getCpf().trim().equals(""))
				&& (clienteMB.getCliente().getCnpj() == null || clienteMB
						.getCliente().getCnpj().trim().equals(""))) {
				msgErro += "Documento Identificação";
				contemErro = true;
		}
		if (clienteMB.getCliente().getRazao() == null
				|| clienteMB.getCliente().getRazao().trim().equals("")) {
			if(contemErro){
				msgErro += ",Razão Social";
			}else{
				msgErro += "Razão Social";
				contemErro = true;
			}
		}
		if(contemErro){
			addGlobalMessage(msgErro);
		}
		return contemErro;
	}
	//METODOS AUXILIARES
	public static void addGlobalMessage(String message) {
		FacesMessage facesMessage = new FacesMessage(message);
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	}
	
	public ClienteMB loadClienteMB() {
		FacesContext context = FacesContext.getCurrentInstance();
		ClienteMB clienteMB = (ClienteMB) context
				.getApplication()
				.evaluateExpressionGet(context, "#{clienteMB}", ClienteMB.class);
		return clienteMB;
	}

	public ClienteCB() {
		System.out.println("construiu papelCB");
		this.setClienteMB(loadClienteMB());
	}

	public void limpaDoc() {
		clienteMB.getCliente().setCnpj("");
		clienteMB.getCliente().setCpf("");
		System.out.println(selectDocType.getValue().getClass().getName());

		if (((Integer) selectDocType.getValue()) == 1) {
			inputCPF.setValue("");
		} else {
			inputCNPJ.setValue("");
		}
	}

	// SETTTER AND GETTERS
	/**
	 * @return the clienteMB
	 */
	public ClienteMB getClienteMB() {
		return clienteMB;
	}

	/**
	 * @param clienteMB
	 *            the clienteMB to set
	 */
	public void setClienteMB(ClienteMB clienteMB) {
		this.clienteMB = clienteMB;
	}

	/**
	 * @return the teste
	 */
	public boolean isTeste() {
		return teste;
	}

	/**
	 * @param teste the teste to set
	 */
	public void setTeste(boolean teste) {
		this.teste = teste;
	}

	/**
	 * @return the inputCNPJ
	 */
	public HtmlInputText getInputCNPJ() {
		return inputCNPJ;
	}

	/**
	 * @param inputCNPJ
	 *            the inputCNPJ to set
	 */
	public void setInputCNPJ(HtmlInputText inputCNPJ) {
		this.inputCNPJ = inputCNPJ;
	}

	/**
	 * @return the inputCPF
	 */
	public HtmlInputText getInputCPF() {
		return inputCPF;
	}

	/**
	 * @param inputCPF
	 *            the inputCPF to set
	 */
	public void setInputCPF(HtmlInputText inputCPF) {
		this.inputCPF = inputCPF;
	}

	/**
	 * @return the selectDocType
	 */
	public HtmlSelectOneRadio getSelectDocType() {
		return selectDocType;
	}

	/**
	 * @param selectDocType
	 *            the selectDocType to set
	 */
	public void setSelectDocType(HtmlSelectOneRadio selectDocType) {
		this.selectDocType = selectDocType;
	}

	/**
	 * @return the cNPJ
	 */
	public Integer getCNPJ() {
		return CNPJ;
	}

	/**
	 * @return the cPF
	 */
	public Integer getCPF() {
		return CPF;
	}

	/*
	 * VALIDADORES
	 */
	/**
	 * @param context
	 * @param component
	 * @param value
	 * @throws ValidatorException
	 */
	public void validateCPF(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		String errorMsg = "";
		String numeroCPF = "";
		if (value == null || value.toString().equals(""))
			return;
		else {
			if (value instanceof String) {
				numeroCPF = getDigitsOnly(value.toString());
				System.out.println(numeroCPF);
				if (numeroCPF.length() != 11) {
					errorMsg = Utils.getMessageResourceString("messages",
							"cpf.tamanho.invalido", null, context.getViewRoot()
									.getLocale());
					FacesMessage message = new FacesMessage(errorMsg);
					message.setSeverity(FacesMessage.SEVERITY_ERROR);
					throw new ValidatorException(message);
				}
				if (!validaCPF(numeroCPF) || digitosIguais(numeroCPF)) {
					errorMsg = Utils.getMessageResourceString("messages",
							"cpf.invalido", null, context.getViewRoot()
									.getLocale());
					FacesMessage message = new FacesMessage(errorMsg);
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
		String errorMsg = "";
		String numeroCNPJ = "";
		if (value == null || value.toString().equals(""))
			return;
		else {
			if (value instanceof String) {
				numeroCNPJ = getDigitsOnly(value.toString());
				System.out.println(numeroCNPJ);
				if (numeroCNPJ.length() != 14) {
					errorMsg = Utils.getMessageResourceString("messages",
							"cnpj.tamanho.invalido", null, context
									.getViewRoot().getLocale());
					FacesMessage message = new FacesMessage(errorMsg);
					message.setSeverity(FacesMessage.SEVERITY_ERROR);
					throw new ValidatorException(message);
				}
				if (!validaCNPJ(numeroCNPJ) || digitosIguais(numeroCNPJ)) {
					errorMsg = Utils.getMessageResourceString("messages",
							"cnpj.invalido", null, context.getViewRoot()
									.getLocale());
					FacesMessage message = new FacesMessage(errorMsg);
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
	public void validatePapeis(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		Application app = context.getApplication();
		ExpressionFactory exprFactory = app.getExpressionFactory();
		ValueExpression valueExpr = exprFactory.createValueExpression(
				context.getELContext(), "#{appBean}", NavigationBean.class);
		AppServiceBean appBean = (AppServiceBean) valueExpr.getValue(context
				.getELContext());
		System.out.println("Passou por auqi " + appBean.getPapeis().size());
		if (appBean.getPapeis().size() == 1
				&& appBean.getPapeis().get(0).equals("")) {
			FacesMessage message = new FacesMessage(
					"Não existem papeis Cadastrados");
			message.setSeverity(FacesMessage.SEVERITY_INFO);
			System.out.println("Passou por auqi");
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
	public void validateRazaoSocial(FacesContext context,
			UIComponent component, Object value) throws ValidatorException {
		if (value == null)
			return;
		String nome;
		String errorMsg = "";
		Pattern pattern = Pattern.compile("^\\s*\\s(\\s)$");
		Matcher m = pattern.matcher(value.toString());
		if (value instanceof String)
			nome = value.toString().trim();
		else {
			errorMsg = Utils.getMessageResourceString("messages",
					"fantasia.invalido", null, context.getViewRoot()
							.getLocale());
			FacesMessage message = new FacesMessage(errorMsg);
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
		if (nome.length() < 5) {
			errorMsg = Utils.getMessageResourceString("messages",
					"razaosocial.invalido.menor", null, context.getViewRoot()
							.getLocale());
			FacesMessage message = new FacesMessage(errorMsg);
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
		if (nome.length() >= 60) {
			errorMsg = Utils.getMessageResourceString("messages",
					"razaosocial.invalido.maior", null, context.getViewRoot()
							.getLocale());
			FacesMessage message = new FacesMessage(errorMsg);
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
		if (m.matches()) {
			errorMsg = Utils.getMessageResourceString("messages",
					"razaosocial.invalido.espacos", null, context.getViewRoot()
							.getLocale());
			FacesMessage message = new FacesMessage(errorMsg);
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
		String errorMsg = "";

		Pattern pattern = Pattern.compile("^\\s*\\s(\\s)$");
		Matcher m = pattern.matcher(value.toString());
		if (value instanceof String)
			nome = value.toString().trim();
		else {
			errorMsg = Utils.getMessageResourceString("messages",
					"fantasia.invalido", null, context.getViewRoot()
							.getLocale());
			FacesMessage message = new FacesMessage(errorMsg);
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
		if (nome.length() < 5) {
			errorMsg = Utils.getMessageResourceString("messages",
					"fantasia.invalido.menor", null, context.getViewRoot()
							.getLocale());
			FacesMessage message = new FacesMessage(errorMsg);
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
		if (nome.length() >= 60) {
			errorMsg = Utils.getMessageResourceString("messages",
					"fantasia.invalido.maior", null, context.getViewRoot()
							.getLocale());
			FacesMessage message = new FacesMessage(errorMsg);
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
		if (m.matches()) {
			errorMsg = Utils.getMessageResourceString("messages",
					"fantasia.invalido.espacos", null, context.getViewRoot()
							.getLocale());
			FacesMessage message = new FacesMessage(errorMsg);
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
		String nome = "";
		String errorMsg = Utils.getMessageResourceString("messages",
				"email.invalido", null, context.getViewRoot().getLocale());
		Pattern pattern = Pattern
				.compile("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?");
		Matcher m = pattern.matcher(value.toString());
		if (value instanceof String)
			nome = value.toString().trim();
		else {
			FacesMessage message = new FacesMessage(errorMsg);
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
		if (!m.matches()) {
			FacesMessage message = new FacesMessage(errorMsg);
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
	 * Valida CNPJ do cliente.
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
