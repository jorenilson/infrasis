package br.com.samsung.mb;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.samsung.dao.UsuarioDao;
import br.com.samsung.model.Usuario;

/**
 * Classe destinada a tratar o Login do usuário no sistema.
 * 
 * @author Jorenilson Lopes
 */
@SuppressWarnings("serial")
@RequestScoped
@ManagedBean
public class LoginMB implements Serializable{

	private Usuario usuario = new Usuario();
	private UsuarioDao dao = new UsuarioDao();
	private UsuarioLogado usuarioLogado = new UsuarioLogado();
	
	

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
	public String efetuarLogin() {
		
		boolean loginValido = dao.existe(this.usuario);
		
		if(loginValido) {
			usuarioLogado.setUsuario(usuario);
			return "sistema?faces-redirect=true";
		}else {
			resetaUsuario();
			return "index?faces-redirect=true";
		}
	}
	
	
	
	public void sair(){
		try{
			FacesContext ctx = FacesContext.getCurrentInstance();
			HttpSession sessao = (HttpSession) ctx.getExternalContext().getSession(false);
			sessao.invalidate();
			this.resetaUsuario();
			ctx.getExternalContext().redirect("/index.jsf");
		}catch(Exception e){
			
		}
	}
	
	
	
	public void resetaUsuario() {
		usuarioLogado.setUsuario(null);
	}
	
	
	
	public boolean isLogado() {
		return usuarioLogado.isLogado();
	}
}