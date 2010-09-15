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

public class ClienteCB implements Serializable {

	private static final long serialVersionUID = 1L;
	private ClienteMB clienteMB;
	private HtmlInputText inputCNPJ;
	private HtmlInputText inputCPF;
	private HtmlSelectOneRadio selectDocType;
	/**
	 * @return the clienteMB
	 */
	public ClienteMB getClienteMB() {
		return clienteMB;
	}
	/**
	 * @param clienteMB the clienteMB to set
	 */
	public void setClienteMB(ClienteMB clienteMB) {
		this.clienteMB = clienteMB;
	}
	
	
	/**
	 * @return the inputCNPJ
	 */
	public HtmlInputText getInputCNPJ() {
		return inputCNPJ;
	}
	/**
	 * @param inputCNPJ the inputCNPJ to set
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
	 * @param inputCPF the inputCPF to set
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
	 * @param selectDocType the selectDocType to set
	 */
	public void setSelectDocType(HtmlSelectOneRadio selectDocType) {
		this.selectDocType = selectDocType;
	}
	public ClienteMB loadClienteMB(){
		FacesContext context = FacesContext.getCurrentInstance();
		ClienteMB clienteMB  = (ClienteMB) context.getApplication()
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
//			inputCPF.resetValue();
		} else {
			inputCNPJ.setValue("");
//			inputCNPJ.resetValue();
		}
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

//		if (navigationBean.getOpcaoDocId() == 1) {
//			this.getCliente().setCpf("");
//			return;
//		}
//		String numeroCPF = "";
//		if (value == null || value.toString().equals(""))
//			return;
//		else {
//			if (value instanceof String) {
//				numeroCPF = getDigitsOnly(value.toString());
//				System.out.println(numeroCPF);
//				if (numeroCPF.length() != 11) {
//					FacesMessage message = new FacesMessage(
//							"O cpf deve ter 11 digitos.");
//					message.setSeverity(FacesMessage.SEVERITY_ERROR);
//					throw new ValidatorException(message);
//				}
//				if (!validaCPF(numeroCPF) || digitosIguais(numeroCPF)) {
//					FacesMessage message = new FacesMessage("CPF inv�lido.");
//					message.setSeverity(FacesMessage.SEVERITY_ERROR);
//					throw new ValidatorException(message);
//
//				}
//			}
//		}
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
					"N�o existem papeis Cadastrados");
			message.setSeverity(FacesMessage.SEVERITY_INFO);
			System.out.println("Passou por auqi");
			throw new ValidatorException(message);
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
//		Application app = context.getApplication();
//		// NavigationBean navigationBean =
//		// app.getExpressionFactory().createValueExpression(context.getELContext(),"#{navigationBean}",
//		// NavigationBean.class).getValue(arg0);
//		// @SuppressWarnings("deprecation")
//		// NavigationBean navigationBean =
//		// (NavigationBean)app.createValueBinding("#{navigationBean}").getValue(context);
//		String numeroCNPJ = "";
//		ExpressionFactory exprFactory = app.getExpressionFactory();
//		ValueExpression valueExpr = exprFactory.createValueExpression(
//				context.getELContext(), "#{navigationBean}",
//				NavigationBean.class);
//		NavigationBean navigationBean = (NavigationBean) valueExpr
//				.getValue(context.getELContext());
//
//		// this.getCliente().setCpf("");
//
//		if (navigationBean.getOpcaoDocId() == 2) {
//			this.getCliente().setCnpj("");
//			return;
//		}
//		if (value == null || value.toString().equals(""))
//			return;
//		else {
//			if (value instanceof String) {
//				numeroCNPJ = getDigitsOnly(value.toString());
//				System.out.println(numeroCNPJ);
//				if (numeroCNPJ.length() != 14) {
//					FacesMessage message = new FacesMessage(
//							"O CNPJ deve ter 14 digitos.");
//					message.setSeverity(FacesMessage.SEVERITY_ERROR);
//					throw new ValidatorException(message);
//				}
//				if (!validaCNPJ(numeroCNPJ) || digitosIguais(numeroCNPJ)) {
//					FacesMessage message = new FacesMessage("CNPJ inv�lido.");
//					message.setSeverity(FacesMessage.SEVERITY_ERROR);
//					throw new ValidatorException(message);
//
//				}
//			}
//		}

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

		Pattern pattern = Pattern.compile("^\\s*\\s(\\s)$");
		Matcher m = pattern.matcher(value.toString());
		if (value instanceof String)
			nome = value.toString().trim();
		else {
			FacesMessage message = new FacesMessage("Raz�o Social Inv�lida");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
		if (nome.length() < 5) {
			FacesMessage message = new FacesMessage(
					"A Raz�o Social deve ter 5 letras no m�nimo");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
		if (nome.length() >= 60) {
			FacesMessage message = new FacesMessage(
					"A Raz�o Social deve ter entre 5 e 60 caracteres");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
		if (m.matches()) {
			FacesMessage message = new FacesMessage(
					"A Raz�o Social s� tem espa�os");
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
			FacesMessage message = new FacesMessage("Nome Fantasia Inv�lido");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
		if (nome.length() < 5) {
			FacesMessage message = new FacesMessage(
					"O Nome Fantasia deve ter 5 letras no m�nimo");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
		if (nome.length() >= 60) {
			FacesMessage message = new FacesMessage(
					"O Nome Fantasia deve ter entre 5 e 60 caracteres");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
		if (m.matches()) {
			FacesMessage message = new FacesMessage(
					"O Nome Fantasia s� tem espa�os");
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

		Pattern pattern = Pattern
				.compile("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?");
		Matcher m = pattern.matcher(value.toString());
		if (value instanceof String)
			nome = value.toString().trim();
		else {
			FacesMessage message = new FacesMessage("Nome Fantasia Inv�lido");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
		if (!m.matches()) {
			FacesMessage message = new FacesMessage("Email Inv�lido!");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
	}

	/*
	 * 
	 * FUN��ES AUXILIARES
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

		// Recebe os n�meros e realiza a multiplica��o e soma.
		for (int i = 0; i < 11; i++) {
			var[i] = Integer.parseInt("" + cpf.charAt(i));
			if (i < 9)
				soma += (var[i] * --mult);
		}

		// Cria o primeiro d�gito verificador.
		int resto = soma % 11;
		if (resto < 2) {
			var[9] = 0;
		} else {
			var[9] = 11 - resto;
		}

		// Reinicia os valores.
		soma = 0;
		mult = 11;

		// Realiza a multiplica��o e soma do segundo d�gito.
		for (int i = 0; i < 10; i++)
			soma += var[i] * mult--;

		// Cria o segundo d�gito verificador.
		resto = soma % 11;
		if (resto < 2) {
			var[10] = 0;
		} else {
			var[10] = 11 - resto;
		}

		int v1 = Integer.parseInt("" + cpf.charAt(9));
		int v2 = Integer.parseInt("" + cpf.charAt(10));

		// Confere os d�gitos criados com os d�gitados, se forem diferentes
		// informa que o CPF � inv�lido.
		if (v1 != var[9] || v2 != var[10]) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Valida CNPJ do usu�rio.
	 * 
	 * @param cnpj
	 *            String valor com 14 d�gitos
	 */
	public boolean validaCNPJ(String cnpj) {
		if (cnpj == null || cnpj.length() != 14)
			return false;

		try {
			Long.parseLong(cnpj);
		} catch (NumberFormatException e) { // CNPJ n�o possui somente n�meros
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
