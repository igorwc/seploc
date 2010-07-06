package br.seploc.dao.test;

import java.util.List;

import br.seploc.dao.MenuDAO;
import br.seploc.pojos.Menu;

public class teste {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MenuDAO dao = new MenuDAO();
		List<Menu> lista = dao.getLista();
		
		for(Menu item : lista){
			System.out.println(item);
		}

	}

}
