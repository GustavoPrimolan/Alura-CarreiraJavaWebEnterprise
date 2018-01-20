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
		
		Usuario usuarioLogado = (Usuario) context.getExternalContext().getSessionMap().get("usuarioLogado");
		NavigationHandler handler = context.getApplication().getNavigationHandler();
		if("/login.xhtml".equals(nomePagina)){
			if(usuarioLogado != null){		
				handler.handleNavigation(context, null, "/livro.xhtml?faces-redirect=true");
				context.getRenderResponse();
			}
			
			return;
		}
		

		//Usuario usuarioLogado = (Usuario) context.getExternalContext().getSessionMap().get("usuarioLogado");
		//System.out.println("Usuario logado: " + usuarioLogado.getEmail());
		
		//NavigationHandler handler = context.getApplication().getNavigationHandler();
		if(usuarioLogado != null){
			return;
		}
		
		//System.out.println("Passou aqui");
		
		
		//REDIRECIONAMENTO PARA O login.xhtml
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
