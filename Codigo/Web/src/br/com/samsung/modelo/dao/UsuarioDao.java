package br.com.samsung.modelo.dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.samsung.modelo.bean.Usuario;

public class UsuarioDao {
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
	public boolean existe(Usuario usuario) {
		boolean resultado = false;
		EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();
		Query query = em.createQuery("from Usuario u"
				+ " where u.login = :pLogin and u.senha = :pSenha");

		query.setParameter("pLogin", usuario.getLogin());
		query.setParameter("pSenha", usuario.getSenha());

		resultado = !query.getResultList().isEmpty();
		em.getTransaction().commit();
		em.close();
		return resultado;
	}

	public void inserir(Usuario usuario) {
		dao.adicionar(usuario);
	}
}