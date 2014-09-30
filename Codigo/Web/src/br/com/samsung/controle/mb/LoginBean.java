package br.com.samsung.controle.mb;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;

import br.com.samsung.modelo.bean.Usuario;
import br.com.samsung.modelo.dao.JPAUtil;
import br.com.samsung.modelo.dao.UsuarioDao;


/**
 * Classe destinada a tratar o Login do usuário no sistema.
 * @author Jorenilson Lopes
 */
@SessionScoped
@ManagedBean
public class LoginBean {
	EntityManager em = JPAUtil.getEntityManager();
	private UsuarioDao usuarioDao = new UsuarioDao(em);
	private Usuario usuario = new Usuario();

	/**
	 * Método que irá realizar a validação dos dados informados no formulário.
	 * Se válido, redireciona para a página principal, Se não, mantem na página
	 * de login.
	 * 
	 * @return
	 */
	public String efetuarLogin() {
		boolean loginValido = usuarioDao.existe(usuario);
		if (loginValido) {
			return "sistema.xhtml?faces-redirect=true";
		} else {
			return "index";
		}
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}