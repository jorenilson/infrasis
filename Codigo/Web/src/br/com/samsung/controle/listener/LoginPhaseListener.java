package br.com.samsung.controle.listener;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import br.com.samsung.mb.LoginMB;

@SuppressWarnings("serial")
public class LoginPhaseListener implements PhaseListener {

	@SuppressWarnings("static-access")
	@Override
	public void afterPhase(PhaseEvent evento) {
		// Método em que ocorre a validaçãode Usuário logado
		//Variável com acesso a árvore de componentes
		FacesContext context = evento.getFacesContext();
		//Verificação de acesso a página de login da aplicação
		String  pagina = context.getViewRoot().getViewId();
		if(pagina.equals("/index.jsf")){
			return;
		}
		
		//Carregando o objeto loginBean da sessão
		LoginMB loginMB = context.getApplication().evaluateExpressionGet(context, "#{loginMB}", LoginMB.class);
		
		//Validação de autenticação
		if(!loginMB.isUsuarioLogado()){
			//Carga do objeto de navegação
			NavigationHandler handler = context.getApplication().getNavigationHandler();
			//redirecionamento para a tela de login
			handler.handleNavigation(context, null, "index.jsf");
			context.renderResponse();
		}
	}

	@Override
	public void beforePhase(PhaseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public PhaseId getPhaseId() {
		//Interceptar requisições da View
		return PhaseId.RESTORE_VIEW;
	}

}
