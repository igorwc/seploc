package br.seploc.converters;

import java.text.DecimalFormat;
import java.util.Arrays;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

public class SeplocNumberConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String newValue) {
		boolean flag = false;
		if (newValue == null || newValue.trim().equals(""))
			return new Double(0);
		char[] strSaida = new char[newValue.length()];
		int i = 0;
		for (Character c : newValue.toCharArray()) {
			if (c == '.') {
				if(!flag){
					flag = true;
					strSaida[i] = '.';
					i++;
				}
				continue;
			}
			if (c == ',' && !flag) {
				flag = true;
				strSaida[i] = '.';
				i++;
			}
			if (!Character.isDigit(c)) {
				continue;
			}
			if (Character.isDigit(c)) {
				strSaida[i] = c;
				i++;
			}
		}
		if (i == 0) {
			strSaida = new char[2];
			strSaida[0] = '0';
			strSaida[1] = '.';
		}
		return Double.parseDouble(new String(strSaida).trim());
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		Double d = (Double) value;
		System.out.println("Valor: " + d);
		// DecimalFormat threeDec = new DecimalFormat("#,##0.00");
		// String shortString = (threeDec.format(d));

		String shortString = d.toString();
		shortString = shortString.replace('.', ',');
		System.out.println(shortString);
		return shortString;
	}

}
