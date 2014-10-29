package br.com.samsung.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.samsung.model.Usuario;

public class UsuarioDao {

	
	public boolean existe(Usuario usuario){
		boolean resultado = false;
		EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();
		
		Query query = em.createQuery("FROM Usuario u "
												+ "WHERE u.login = :pLogin "
												+ "AND u.senha = :pSenha");
		query.setParameter("pLogin", usuario.getLogin());
		query.setParameter("pSenha", usuario.getSenha());
		
		resultado = !query.getResultList().isEmpty();
		
		em.getTransaction().commit();
		em.close();
		
		return resultado;
	}
}
