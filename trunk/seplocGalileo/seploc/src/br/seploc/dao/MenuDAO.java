package br.seploc.dao;

import java.util.List;

import javax.persistence.Query;

import br.seploc.dao.exceptions.ParentDeleteException;
import br.seploc.dao.exceptions.RecordNotFound;
import br.seploc.pojos.Menu;
import br.seploc.util.GenericDAO;


public class MenuDAO extends GenericDAO<Menu, Integer> {

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
		Menu menu = em.find(Menu.class, id);
		return menu;
	}

	@Override
	public Menu remove(Integer id) throws Exception {
		em.getTransaction().begin();
		Menu menu = em.find(Menu.class, id);
		if(menu == null){
			em.getTransaction().rollback();
			throw new RecordNotFound("Menu Inexistente");
		}else {

			if (verificaFilhos(id)) {
				em.getTransaction().rollback();
				throw new ParentDeleteException(
						"Menu tem registros depedentes...");
			} else {
				em.remove(menu);
			}
		}
		em.getTransaction().commit();

		return menu;
	}
	
	protected boolean verificaFilhos(Integer id) throws ParentDeleteException {
		Number contagemGrupoMenu = 0;
		
//		Query q = em.createQuery(
//				"SELECT count(gm.grupo) FROM br.seploc.pojos.GrupoMenu gm"
//						+ " where gm.menu.codMenu = :menu").setParameter(
//				"menu", id);
//		contagemGrupoMenu = (Number) q.getSingleResult();

		if (contagemGrupoMenu.intValue() != 0)
			return true;

		return false;
	}

	@SuppressWarnings("unchecked")
	public List<Menu> getLista() {
		em.getTransaction().begin();
		Query q = em.createNamedQuery("Menu.RetornaMenus");
		em.getTransaction().commit();
		return (List<Menu>) q.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Menu> getListaMenuComPai() {
		em.getTransaction().begin();
		Query q = em.createNamedQuery("Menu.RetornaMenusComPai");
		em.getTransaction().commit();
		return (List<Menu>) q.getResultList();
	}	

	@SuppressWarnings("unchecked")
	public List<Menu> getListaMenuSemPai() {
		em.getTransaction().begin();
		Query q = em.createNamedQuery("Menu.RetornaMenusSemPai");
		em.getTransaction().commit();
		return (List<Menu>) q.getResultList();
	}	

	
	@SuppressWarnings("unchecked")
	public List<Menu> getMenusRaiz(){
		em.getTransaction().begin();
		Query q = em.createNamedQuery("Menu.RetornaMenusRaizes");
		em.getTransaction().commit();
		return (List<Menu>) q.getResultList();
	}

	@Override
	protected void ajustaPojo(Menu pojo) {
		// TODO Auto-generated method stub
		
	}
}
