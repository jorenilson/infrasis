package br.com.samsung.mb;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;

import br.com.samsung.dao.JPAUtil;
import br.com.samsung.dao.UsuarioDao;
import br.com.samsung.model.Usuario;

/**
 * Classe destinada a tratar o Login do usuário no sistema.
 * 
 * @author Jorenilson Lopes
 */
@SessionScoped
@ManagedBean
public class LoginMB{
	
	private Usuario usuario = new Usuario();
	private static boolean usuarioLogado;
	
	
	public static boolean isUsuarioLogado() {
		return usuarioLogado;
	}

	public static void setUsuarioLogado(boolean usuarioLogado) {
		LoginMB.usuarioLogado = usuarioLogado;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
	public String efetuarLogin() {
		UsuarioDao dao = new UsuarioDao();
		boolean loginValido = dao.existe(usuario);
		setUsuarioLogado(loginValido);
		if(loginValido){
			return "sistema?faces-redirect=true";
		}else{
			return "index?faces-redirect=true";
		}
	}
	
	
	public void encarrearSessao(){
		try{
			FacesContext ctx = FacesContext.getCurrentInstance();
			HttpSession sessao = (HttpSession) ctx.getExternalContext().getSession(false);
			sessao.invalidate();
			setUsuarioLogado(false);
			ctx.getExternalContext().redirect("/infrasisjsf/index.jsf");
		}catch(Exception e){
			
		}
	}
}