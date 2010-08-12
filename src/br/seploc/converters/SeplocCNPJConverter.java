package br.seploc.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

public class SeplocCNPJConverter implements Converter {

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
		return value.toString();
	}

}
