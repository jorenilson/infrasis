package br.com.samsung.mb;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.com.samsung.model.Usuario;

@SuppressWarnings("serial")
@Named
@SessionScoped
public class UsuarioLogado implements Serializable {
	private Usuario usuario;
	
	public boolean isLogado() {
		return usuario != null;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
