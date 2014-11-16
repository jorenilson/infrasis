package br.com.samsung.mb;


import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.samsung.dao.UsuarioDao;
import br.com.samsung.model.Usuario;


@SuppressWarnings("serial")
@Named
@RequestScoped
public class LoginMB implements Serializable{
	
	@Inject
	private Usuario usuario;

	@Inject
	private UsuarioDao dao;
	
	@Inject
	private UsuarioLogado usuarioLogado;

	
		
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}




	public String efetuarLogin() {
		try{
			boolean loginValido = dao.existe(this.usuario);
			if(loginValido){
				System.out.println("Encontrou");
				usuarioLogado.setUsuario(this.usuario);
				return "sistema?faces-redirect=true";
			}else{
				System.out.println("Não Encontrou");
				return "index?faces-redirect=true";
			}
		}catch(Exception e){
			System.out.println("Ocorreu um error!");
		}
		return null;		
	}
	
	
	public String sair() {
		this.resetaUsuario();
		return "index?faces-redirect=true"; 
	}
	
	
	public boolean isLogado() {
		return usuarioLogado.isLogado();
	}
	
	
	public void resetaUsuario() {
		usuarioLogado.setUsuario(null);
	}
	
}