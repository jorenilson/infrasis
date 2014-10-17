package br.com.samsung.modelo.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.samsung.modelo.bean.Usuario;

public class UsuarioDao implements Serializable {
	private final GenericDao<Usuario> dao;
	private final EntityManager em;

	public UsuarioDao(EntityManager em) {
		this.dao = new GenericDao<Usuario>(em, Usuario.class);
		this.em = em;
	}

	/**
	 * Este m�todo ir� fazer a valida��o dos dados informados no formul�rio
	 * de login. Dever� ter como retorno um boolean onde TRUE significa que
	 * a aplica��o obteve �xito no acesso ou  FALSE, n�o obteve �xito.
	 * @param usuario
	 * @return
	 */
	public List<Usuario>verificarUsuario(Usuario usuario) {
		
		EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();
		Query query = em.createQuery("SELECT u FROM Usuario u"
				+ " WHERE u.login = :pLogin and u.senha = :pSenha");

		query.setParameter("pLogin", usuario.getLogin());
		query.setParameter("pSenha", usuario.getSenha());
		em.getTransaction().commit();
		em.close();
		return query.getResultList();
	}

	public void inserir(Usuario usuario) {
		dao.adicionar(usuario);
	}

	public void cadastrar(Usuario t) {
		dao.adicionar(t);
	}

	public void excluir(Usuario t) {
		dao.excluir(t);
	}

	public Usuario buscar(Integer codigo) {
		return dao.buscar(codigo);
	}

	public List<Usuario> listar() {
		return dao.listar();
	}
	
	public void alterar(Usuario usuario){
		dao.alterar(usuario);
	}
}
