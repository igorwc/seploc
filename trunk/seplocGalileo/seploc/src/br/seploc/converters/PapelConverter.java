package br.seploc.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import br.seploc.dao.PapelDAO;
import br.seploc.pojos.Papel;

public class PapelConverter implements Converter {

		@Override
		public Object getAsObject(FacesContext context, UIComponent component, String value){
			if(value == null || value.trim().equals("")) return new Papel();
			
			PapelDAO papelDAO = new PapelDAO();			
			Papel retorno = new Papel();
			if (papelDAO.getListaPapelPorNome(value).size() > 1){
				System.out.println("Existe mais de um papel como o mesmo nome!");
			}
			for (Papel p : papelDAO.getListaPapelPorNome(value)){
				retorno = p;
			}
			
			return retorno;
		}

		@Override
		public String getAsString(FacesContext context, UIComponent component, Object newValue) {
			String retorno = "";
			if (newValue != null && !"".equals(newValue)) {     
				retorno = ((Papel) newValue).getNome();
			}
			return retorno;
		}
	
}
