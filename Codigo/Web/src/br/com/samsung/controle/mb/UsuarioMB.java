package br.com.samsung.controle.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import javax.persistence.EntityManager;

import br.com.samsung.modelo.bean.Usuario;
import br.com.samsung.modelo.dao.JPAUtil;
import br.com.samsung.modelo.dao.UsuarioDao;

@ViewScoped
@ManagedBean
public class UsuarioMB {
	private Usuario usuario = new Usuario();
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public List<Usuario> listaUsuarios = new ArrayList<Usuario>();

	public List<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}

	@PostConstruct
	public void carregarUsuarios(){
		EntityManager em = JPAUtil.getEntityManager();
		UsuarioDao dao = new UsuarioDao(em);
		listaUsuarios = dao.listar();
		em.close();
	}
	
	
	
	public void excluir(){
		try{
			EntityManager em = JPAUtil.getEntityManager();
			UsuarioDao dao = new UsuarioDao(em);
			em.getTransaction().begin();
			dao.excluir(usuario);
			em.getTransaction().commit();
			em.close();
			carregarUsuarios();
		}catch(Exception e){
		}
	}
	
}
