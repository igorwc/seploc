package br.seploc.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/*
 * P�gina para gera��o de n�meros de teste: http://www.gerardocumentos.com.br/?pg=gerador-cnpj-valido
 * 
 * Refer�ncia para validador de CNPJ: http://pablonobrega.wordpress.com/2009/08/25/implementando-converter-e-validator-de-cnpj/
 */
public class SeplocCNPJValidator implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		String numeroCNPJ = "";
		if (value == null || value.toString().equals(""))
			return;
		else {
			if (value instanceof String) {
				numeroCNPJ = getDigitsOnly(value.toString());
				System.out.println(numeroCNPJ);
				if (numeroCNPJ.length() != 14) {
					FacesMessage message = new FacesMessage("O CNPJ deve ter 14 digitos.");
					message.setSeverity(FacesMessage.SEVERITY_ERROR);
					throw new ValidatorException(message);
				}
				if (!validaCNPJ(numeroCNPJ) || digitosIguais(numeroCNPJ)) {
					FacesMessage message = new FacesMessage("CNPJ inv�lido.");
					message.setSeverity(FacesMessage.SEVERITY_ERROR);
					throw new ValidatorException(message);

				}
			}
		}
	}
		private boolean digitosIguais(String cpf){
			boolean resultado = true;
			char[] arrayDigitos = cpf.toCharArray();
			char c =  arrayDigitos[1];
			for(char d : arrayDigitos){
				if(d != c){
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

	public static void main(String args[]) {
		SeplocCNPJValidator cc = new SeplocCNPJValidator();
		String teste = "85717763000100";

		System.out.println(cc.validaCNPJ(teste));
		teste = "85714763000100";
		System.out.println(cc.validaCNPJ(teste));
	}
}
