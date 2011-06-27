package br.seploc.util;

import java.sql.Date;
import java.text.MessageFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Utils {
	protected static ClassLoader getCurrentClassLoader(Object defaultObject){

		ClassLoader loader = Thread.currentThread().getContextClassLoader();

		if(loader == null){
			loader = defaultObject.getClass().getClassLoader();
		}

		return loader;
	}

	public static String getMessageResourceString(
							String bundleName, 
							String key, 
							Object params[], 
							Locale locale){

		String text = null;

		ResourceBundle bundle = 
				ResourceBundle.getBundle(bundleName, locale, 
										getCurrentClassLoader(params));

		try{
			text = bundle.getString(key);
		} catch(MissingResourceException e){
			text = "?? key " + key + " not found ??";
		}

		if(params != null){
			MessageFormat mf = new MessageFormat(text, locale);
			text = mf.format(params, new StringBuffer(), null).toString();
		}

		return text;
	}
	
	public static Date getCalcularDataFrente(Calendar data, int dias){		
		data.add(Calendar.DATE, dias);
		java.sql.Date diaParaFrete = new java.sql.Date(data.getTimeInMillis());		  
		
		return diaParaFrete;
	}
	
	public static Date getCalcularDataParaTras(Calendar data, int dias){		
		data.add(Calendar.DATE, dias);
		java.sql.Date diaParaTras = new java.sql.Date(data.getTimeInMillis());		  
		
		return diaParaTras;
	}	
	
	public static Date getDataInicioMesCorrente(){
		Calendar calendar = Calendar.getInstance();		
		int primeiroDia = calendar.getActualMinimum(Calendar.DATE);		
		calendar.set(Calendar.DATE, primeiroDia);		
		Date inicioMes = new Date(calendar.getTimeInMillis()); 
		
		return inicioMes;
	}
	
	public static Date getDataFinalMesCorrente(){
		Calendar calendar = Calendar.getInstance();
		int ultimoDia = calendar.getActualMaximum(Calendar.DATE);
		calendar.set(Calendar.DATE, ultimoDia);
		Date finalMes = new Date(calendar.getTimeInMillis());
		
		return finalMes;
	}
	
	public static Calendar getDayAgo(int dias){
		Calendar dia = Calendar.getInstance();
		//dias atras
		dias = dias * -1;
		dia.add(Calendar.DATE, dias);
		System.out.println(dia.getTime());
		
		return dia;
	}		
}
