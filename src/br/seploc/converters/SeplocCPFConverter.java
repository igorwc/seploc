package br.seploc.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

public class SeplocCPFConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String newValue) {
		if (newValue == null || newValue.trim().equals(""))
			return new String("");
		int i = 0;
		String saida = "";
		for (Character c : newValue.toCharArray()) {
			if (Character.isDigit(c)) {
				saida += c.toString();
				i++;
			} else {
				i++;
				continue;
			}
		}
		return saida.trim();
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
//		String input = value.toString();
//		String retorno = input.substring(0, 3) + "." + input.substring(3, 6)
//				+ "." + input.substring(6, 9) + "-" + input.substring(9, 11);
//		System.out.println(value.toString());
		return value.toString();
	}

	public static void main(String args[]) {
		SeplocCPFConverter cc = new SeplocCPFConverter();
		String teste = cc.getAsString(null, null, "03487103486");
		String teste2 = ( cc.getAsObject(null, null, "03487103486")).toString();
		System.out.println(teste);
		System.out.println(teste2);
	}

}
