package br.com.samsung.controle.mb;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;

import br.com.samsung.modelo.bean.Usuario;
import br.com.samsung.modelo.dao.JPAUtil;
import br.com.samsung.modelo.dao.UsuarioDao;

/**
 * Classe destinada a tratar o Login do usuário no sistema.
 * 
 * @author Jorenilson Lopes
 */
@SessionScoped
@ManagedBean
public class LoginBean {
	EntityManager em = JPAUtil.getEntityManager();
	private Usuario usuario = new Usuario();
	private boolean usuarioLogado;

	public boolean isUsuarioLogado() {
		return usuarioLogado;
	}

	// Realizar Login
	public String efetuarLogin() {
		UsuarioDao dao = new UsuarioDao(em);
		boolean loginValido = dao.existe(usuario);
		if (loginValido) {
			usuarioLogado = true;
			return "sistema?faces-redirect=true";
		} else {
			usuarioLogado = false;
			return "login";
		}
	}
	
	public String logout(){
		FacesContext contexto = FacesContext.getCurrentInstance();
		HttpSession sessao = (HttpSession)contexto.getExternalContext().getSession(false);
		sessao.invalidate();
		usuario = null;
		return "index";
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}