package br.com.samsung.controle.mb;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;

import br.com.samsung.modelo.bean.Usuario;
import br.com.samsung.modelo.dao.JPAUtil;
import br.com.samsung.modelo.dao.UsuarioDao;


/**
 * Classe destinada a tratar o Login do usu�rio no sistema.
 * @author Jorenilson Lopes
 */
@SessionScoped
@ManagedBean
public class LoginBean {
	EntityManager em = JPAUtil.getEntityManager();
	private UsuarioDao usuarioDao = new UsuarioDao(em);
	private Usuario usuario = new Usuario();

	/**
	 * M�todo que ir� realizar a valida��o dos dados informados no formul�rio.
	 * Se v�lido, redireciona para a p�gina principal, Se n�o, mantem na p�gina
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