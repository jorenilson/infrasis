package br.com.samsung.controle.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.com.samsung.modelo.bean.Usuario;
import br.com.samsung.modelo.bean.UsuarioLogado;
import br.com.samsung.modelo.dao.JPAUtil;
import br.com.samsung.modelo.dao.UsuarioDao;

/**
 * Classe destinada a tratar o Login do usuário no sistema.
 * 
 * @author Jorenilson Lopes
 */
@SuppressWarnings("serial")
@SessionScoped
@ManagedBean
public class LoginBean implements Serializable {
	
	EntityManager em = JPAUtil.getEntityManager();
	private Usuario usuario = new Usuario();
	private boolean usuarioLogado;

	public boolean isUsuarioLogado() {
		return usuarioLogado;
	}

	public List<Usuario> usuarioLogin = new ArrayList<Usuario>();
	
	// Realizar Login
	public String efetuarLogin() {
		UsuarioDao dao = new UsuarioDao(em);
		usuarioLogin = dao.verificarUsuario(usuario);
		Object usuarioObj = null;
		usuarioObj = usuarioLogin.get(0).getNome();
		String nomeUsuario = (String)usuarioObj;
		usuario.setNome(nomeUsuario);
		if (usuarioLogin != null || !usuarioLogin.isEmpty()) {
			usuarioLogado = true;
			//criarSessao(usuario);
			return "sistema?faces-redirect=true";
		} else {
			usuarioLogado = false;
			return "index?faces-redirect=true";
		}
	}
	

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public void encarrearSessao(){
		try{
			FacesContext ctx = FacesContext.getCurrentInstance();
			HttpSession sessao = (HttpSession) ctx.getExternalContext().getSession(false);
			sessao.invalidate();
			ctx.getExternalContext().redirect("/infrasisjsf/index.jsf");
		}catch(Exception e){
			
		}
	}
}