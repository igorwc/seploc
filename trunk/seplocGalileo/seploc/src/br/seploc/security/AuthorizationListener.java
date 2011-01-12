package br.seploc.security;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpSession;

import br.seploc.pojos.Usuario;

public class AuthorizationListener implements PhaseListener {

	private static final long serialVersionUID = 1L;

	@Override
	public void afterPhase(PhaseEvent event) {
		FacesContext facesContext = event.getFacesContext();
		try {
//			FacesContext facesContext = event.getFacesContext();
			String currentPage = facesContext.getViewRoot().getViewId();

			boolean isLoginPage = (currentPage.lastIndexOf("login.xhtml") > -1)
					|| (currentPage.lastIndexOf("login.jsf") > -1);
			boolean isCssProcess = currentPage.contains("richfaces")
					|| currentPage.contains("a4j/")
					|| currentPage.contains("/css/")
					|| currentPage.contains("/html/");
			HttpSession session = (HttpSession) facesContext
					.getExternalContext().getSession(false);

			Object currentUser = session.getAttribute("usuarioSessao");
			
			 System.out.println((Usuario)currentUser);
			 System.out.println(currentPage);

			if (isCssProcess) {
				return;
			}

			if (!isLoginPage && currentUser == null) {
				NavigationHandler nh = facesContext.getApplication()
						.getNavigationHandler();
				nh.handleNavigation(facesContext, null, "login");
				System.out.println("Mandou pra pagina de login");
			}
		} catch (Exception e) {
			NavigationHandler nh = facesContext.getApplication()
					.getNavigationHandler();
			System.out.println("Aconteceu exceção na classe AuthorizationListener");
			nh.handleNavigation(facesContext, null, "login");
		}

	}

	@Override
	public void beforePhase(PhaseEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

}
