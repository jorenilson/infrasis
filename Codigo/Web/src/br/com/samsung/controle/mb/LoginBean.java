package br.com.samsung.controle.mb;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

import br.com.samsung.modelo.bean.Mensagens;
import br.com.samsung.modelo.bean.Usuario;
import br.com.samsung.modelo.dao.JPAUtil;
import br.com.samsung.modelo.dao.UsuarioDao;


/**
 * Classe destinada a tratar o Login do usuário no sistema.
 * @author Jorenilson Lopes
 */
@SessionScoped
@ManagedBean
public class LoginBean {
	EntityManager em = JPAUtil.getEntityManager();
	private UsuarioDao usuarioDao = new UsuarioDao(em);
	private Usuario usuario = new Usuario();
	private String url = "sistema.jsf";


	public void efetuarLogin() throws IOException {
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		
		boolean loginValido = usuarioDao.verificar(usuario);
		if (loginValido) {
			ec.redirect(url);
		} else {
			exibirMensagem(FacesMessage.SEVERITY_ERROR,"Erro", "Usuário ou Senha inválidos. "
					+ "Verifique os dados informados e tente novamente.");			
		}
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	//Exibir mensagem
	public void exibirMensagem(Severity tipo, String summary, String detail) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(tipo,summary,detail));
	}

}