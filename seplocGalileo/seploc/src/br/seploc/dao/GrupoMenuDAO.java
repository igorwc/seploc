package br.seploc.dao;

import java.util.List;

import javax.persistence.Query;

import br.seploc.dao.exceptions.RecordNotFound;
import br.seploc.pojos.Grupo;
import br.seploc.pojos.GrupoMenu;
import br.seploc.pojos.GrupoMenuPK;
import br.seploc.pojos.Menu;
import br.seploc.util.GenericDAO;

public class GrupoMenuDAO extends GenericDAO<GrupoMenu, GrupoMenuPK>{

	@Override
	public void adiciona(GrupoMenu t) {
		em.getTransaction().begin();
		em.merge(t);
		em.getTransaction().commit();		
	}

	@Override
	public GrupoMenu altera(GrupoMenu t) {
		em.getTransaction().begin();
		em.merge(t);
		em.getTransaction().commit();
		return t;
	}

	@Override
	public GrupoMenu recupera(GrupoMenuPK id) {
		GrupoMenu grupoMenu = em.find(GrupoMenu.class, id);
		return grupoMenu;
	}

	public GrupoMenu recupera(Integer MenuId, Integer GrupoId) {
		GrupoMenuPK grupoMenuPK = new GrupoMenuPK(MenuId, GrupoId);
		GrupoMenu grupoMenu = em.find(GrupoMenu.class, grupoMenuPK);
		return grupoMenu;
	}
	
	public GrupoMenu recupera(Menu menu, Grupo grupo) {
		return recupera(menu.getCodMenu(), grupo.getCodGrupo());
	}
	
	@Override
	public GrupoMenu remove(GrupoMenuPK id) throws Exception {
		em.getTransaction().begin();
		GrupoMenu grupoMenu = em.find(GrupoMenu.class, id);
		if(grupoMenu == null){
			em.getTransaction().rollback();
			throw new RecordNotFound("Associa��o Inexistente");
		}
		
		em.remove(grupoMenu);
		em.getTransaction().commit();

		return grupoMenu;
	}
	
	
	public GrupoMenu remove(Integer MenuId, Integer GrupoId) throws Exception {
		em.getTransaction().begin();
		GrupoMenuPK grupoMenuPK = new GrupoMenuPK(MenuId, GrupoId);
		GrupoMenu grupoMenu = remove(grupoMenuPK);
		return grupoMenu;
	}
	
	public GrupoMenu remove(Menu menu, Grupo grupo) throws Exception {
		return remove(menu.getCodMenu(), grupo.getCodGrupo());
	}
	
	public GrupoMenu remove(GrupoMenu grupoMenu) throws Exception {
		return remove(grupoMenu.getMenuId(), grupoMenu.getGrupoId());
	}
	
	@SuppressWarnings("unchecked")
	public List<GrupoMenu> getLista() {
		em.getTransaction().begin();
		Query q = em.createNamedQuery("GrupoMenu.RetornaGruposMenus");
		em.getTransaction().commit();
		return (List<GrupoMenu>) q.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<GrupoMenu> getFilterByMenu(Menu menu) {
		em.getTransaction().begin();
		Query q = em.createNamedQuery("GrupoMenu.RetornaPorMenus").setParameter("MENU", menu.getCodMenu().intValue());
		em.getTransaction().commit();
		return (List<GrupoMenu>) q.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<GrupoMenu> getFilterByGrupo(Grupo grupo) {
		em.getTransaction().begin();
		Query q = em.createNamedQuery("GrupoMenu.RetornaPorGrupo").setParameter("GRUPO", grupo.getCodGrupo().intValue());
		em.getTransaction().commit();
		return (List<GrupoMenu>) q.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<GrupoMenu> getFilterByGrupoMenu(Grupo grupo, Menu menu) {
		em.getTransaction().begin();
		Query q = em.createNamedQuery("GrupoMenu.RetornaPorGrupoMenusPai")
					.setParameter("GRUPO", grupo.getCodGrupo())
					.setParameter("MENU", menu.getCodMenu());
		em.getTransaction().commit();
		return (List<GrupoMenu>) q.getResultList();
	}
	
	@Override
	protected boolean verificaFilhos(GrupoMenuPK id) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void ajustaPojo(GrupoMenu pojo) {
		// TODO Auto-generated method stub
		
	}

}
