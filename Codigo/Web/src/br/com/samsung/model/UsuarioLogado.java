package br.com.samsung.model;

public class UsuarioLogado {
	private Usuario usuario;
	
	public boolean isLogado(){
		return usuario != null;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
}
