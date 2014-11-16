package br.com.samsung.mb;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.samsung.model.Usuario;

@Named
public class UsuarioMB implements Serializable {
	@Inject
	Usuario usuario;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}	
}
