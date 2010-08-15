package br.seploc.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class SeploCPFValidator implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
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
}
