package com.bets.util;



import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityUtil{
	private static EntityManagerFactory emf;
	public static final ThreadLocal<EntityManager> _threadLocal = 
		new ThreadLocal<EntityManager>();
	public static EntityManagerFactory getEntityManagerFactory() { 
	   if (emf == null) {
	         emf = Persistence.createEntityManagerFactory("bets");
	   } 
	      return emf;
	 }
	public static EntityManager getEntityManager() { 
	   EntityManager entityManager = _threadLocal.get();
	   if (entityManager == null) { 
	      entityManager = getEntityManagerFactory().createEntityManager();
	      _threadLocal.set(entityManager);
	   }
	   return entityManager;
	}
}
