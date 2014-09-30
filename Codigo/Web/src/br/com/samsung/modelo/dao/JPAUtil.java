package br.com.samsung.modelo.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("infrasis1");
	
	public static EntityManager getEntityManager(){
		return emf.createEntityManager();
	}
}