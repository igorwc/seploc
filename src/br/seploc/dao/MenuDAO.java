package br.seploc.dao;

import java.util.List;

import javax.persistence.Query;

import br.seploc.pojos.Menu;
import br.seploc.util.GenericDAO;


public class MenuDAO extends GenericDAO<Menu> {

	@Override
	public void adiciona(Menu t) {
		em.getTransaction().begin();
		em.merge(t);
		em.getTransaction().commit();

	}

	@Override
	public Menu altera(Menu t) {
		em.getTransaction().begin();
		em.merge(t);
		em.getTransaction().commit();
		return t;
	}

	@Override
	public Menu recupera(Integer id) {
		
		return null;
	}

	public Menu recupera(Character id) {
		Menu menu = em.find(Menu.class, id);
		return menu;
	}


	public Menu remove(Character id) throws Exception {
		em.getTransaction().begin();
		Menu menu = em.find(Menu.class, id);
		em.remove(menu);
		em.getTransaction().commit();

		return menu;
	}

	@SuppressWarnings("unchecked")
	public List<Menu> getLista() {
		em.getTransaction().begin();
		Query q = em.createNamedQuery("Menu.RetornaMenus");
		em.getTransaction().commit();
		return (List<Menu>) q.getResultList();
	}

	@Override
	public Menu remove(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
