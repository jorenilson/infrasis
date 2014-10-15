package br.com.samsung.controle.mb;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

import br.com.samsung.modelo.bean.Usuario;
import br.com.samsung.modelo.dao.JPAUtil;
import br.com.samsung.modelo.dao.UsuarioDao;

/**
 * Classe destinada a tratar o Login do usuário no sistema.
 * 
 * @author Jorenilson Lopes
 */
@ViewScoped
@ManagedBean
public class LoginBean {
	EntityManager em = JPAUtil.getEntityManager();
	private Usuario usuario = new Usuario();

	// Realizar Login
	public String efetuarLogin() {
		UsuarioDao dao = new UsuarioDao(em);
		Usuario loginUsuario = new Usuario(); 
				loginUsuario = dao.existe(usuario);
		if (loginUsuario!=null) {
			return "sistema?faces-redirect=true";
		} else {
			return "login";
		}
	}
	

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}