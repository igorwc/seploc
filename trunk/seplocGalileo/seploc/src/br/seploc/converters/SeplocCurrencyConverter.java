package br.seploc.converters;

import java.text.DecimalFormat;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

public class SeplocCurrencyConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String newValue) {
		if(newValue == null || newValue.trim().equals("")) return new Double(0);
		char[] strSaida = new char[newValue.length()];
		int i = 0;
		for (Character c : newValue.toCharArray()) {
			if (c == '.') {
				continue;
			}
			if (c == ',') {
				strSaida[i] = '.';
				i++;
			}
			if (Character.isDigit(c) ) {
				strSaida[i] = c;
				i++;
			}
		}
		return Double.parseDouble(new String(strSaida).trim());
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		 Double d = ( Double)value;
		 System.out.println(d);
		 DecimalFormat threeDec = new DecimalFormat("#,##0.00");
		 String shortString = (threeDec.format(d));
		 System.out.println(shortString);
		 return shortString;
	}

}
