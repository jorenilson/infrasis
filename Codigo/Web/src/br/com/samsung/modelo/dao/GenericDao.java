package br.com.samsung.modelo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class GenericDao<T> {
	private EntityManager em;
	private Class<T> classe;
	
	public GenericDao(EntityManager em, Class<T> classe){
		super();
		this.em = em;
		this.classe = classe;
	}
	
	public void adicionar(T t){
		em.persist(t);
	}
	
	public void excluir(T t){
		t = em.merge(t);
		em.remove(t);
	}
	
	public T buscar(Integer codigo){
		return em.getReference(classe, codigo);
	}
	
	public List<T> listar(){
		String jpql = "SELECT o FROM "+classe.getName()+" o";
		Query query = em.createQuery(jpql);
		return query.getResultList();
	}
	
	public void alterar(T t){
		em.merge(t);
	}
	
	
}
