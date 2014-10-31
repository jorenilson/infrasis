package br.com.samsung.mb;

import java.io.Serializable;

import javax.faces.bean.SessionScoped;

import br.com.samsung.model.Usuario;


@SuppressWarnings("serial")
@SessionScoped
public class UsuarioLogado implements Serializable {

	private Usuario usuario = new Usuario();
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public boolean isLogado(){
		return usuario != null;
	}
}
