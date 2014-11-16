package br.com.samsung.controle.listener;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import br.com.samsung.mb.LoginMB;

@SuppressWarnings("serial")
public class LoginPhaseListener implements PhaseListener {

	@Override
	public void afterPhase(PhaseEvent evento) {
		// M�todo em que ocorre a valida��ode Usu�rio logado
		//Vari�vel com acesso a �rvore de componentes
		FacesContext context = evento.getFacesContext();
		//Verifica��o de acesso a p�gina de login da aplica��o
		String  pagina = context.getViewRoot().getViewId();
		if(pagina.equals("/index.xhtml") || pagina.equals("/index.jsf")){
			return;
		}
		
		//Carregando o objeto loginBean da sess�o
		LoginMB loginBean = context.getApplication().evaluateExpressionGet(context, "#{loginMB}", LoginMB.class);
		
		//Valida��o de autentica��o
		if(!loginBean.isLogado()){
			//Carga do objeto de navega��o
			NavigationHandler handler = context.getApplication().getNavigationHandler();
			//redirecionamento para a tela de login
			handler.handleNavigation(context, null, "index?faces-redirect=true");
			
			context.renderResponse();
		}
	}

	@Override
	public void beforePhase(PhaseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public PhaseId getPhaseId() {
		//Interceptar requisi��es da View
		return PhaseId.RESTORE_VIEW;
	}

}
