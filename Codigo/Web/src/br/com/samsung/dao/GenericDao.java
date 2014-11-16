package br.com.samsung.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

public class GenericDao<T> {
	private Class<T> classe;
	
	public GenericDao(Class<T> classe){
		super();
		this.classe = classe;
	}
	
	public void cadastrar(T t){
		EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();
		em.persist(t);
		em.getTransaction().commit();
		em.close();
	}
	
	public void excluir(T t){
		EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();
		em.remove(em.merge(t));
		em.getTransaction().commit();
		em.close();
	}
	
	public void atualizar(T t){
		EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();
		em.merge(t);
		em.getTransaction().commit();
		em.close();
	}

	public List<T> listarTodos(){
		EntityManager em = JPAUtil.getEntityManager();
		CriteriaQuery<T> query = em.getCriteriaBuilder().createQuery(classe);
		query.select(query.from(classe));
		
		List<T> lista = em.createQuery(query).getResultList();
		em.close();
		return lista;
	}
	
	public List<T> listar(){
		EntityManager em = JPAUtil.getEntityManager();
		String jpql = "SELECT o FROM "+classe.getName()+" o";
		Query query = em.createQuery(jpql);
		return query.getResultList();
	}
	
	public T buscarPorId(Integer integer) {
		EntityManager em = JPAUtil.getEntityManager();
		T instancia = em.find(classe, integer);
		em.close();
		return instancia;
	}
	
	public T buscar(Integer codigo) {
		EntityManager em = JPAUtil.getEntityManager();
		return em.getReference(classe, codigo);
	}
}
