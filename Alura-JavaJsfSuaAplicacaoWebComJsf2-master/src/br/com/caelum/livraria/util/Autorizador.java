package br.com.caelum.livraria.util;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import br.com.caelum.livraria.modelo.Usuario;

public class Autorizador implements PhaseListener{

	/*FASES QUE O PHASE APLICARÁ*/
	
	
	@Override
	public void afterPhase(PhaseEvent event) {
			
		FacesContext context = event.getFacesContext();
		
		String nomePagina = context.getViewRoot().getViewId();
		
		System.out.println(nomePagina);
		
		if("/login.xhtml".equals(nomePagina)){
			return;
		}
		

		Usuario usuarioLogado = (Usuario) context.getExternalContext().getSessionMap().get("usuarioLogado");
		//System.out.println("Usuario logado: " + usuarioLogado.getEmail());
		if(usuarioLogado != null){
			return;
		}
		
		
		//REDIRECIONAMENTO PARA O login.xhtml
		NavigationHandler handler = context.getApplication().getNavigationHandler();
		handler.handleNavigation(context, null, "/login.xhtml?faces-redirect=true");
		//context renderisa a resposta
		context.renderResponse();
		
	}

	@Override
	public void beforePhase(PhaseEvent event) {
		
	}

	
	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

	
	
}
