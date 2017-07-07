package br.edu.ifpb.mt.dac.nn.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DaoTest {
	
	static EntityManagerFactory emf;
	
	static {
		emf = Persistence.createEntityManagerFactory("bdnordeste");
	}
	
	protected EntityManager getEntityManager() {
		return emf.createEntityManager();
	}
	
	public void close() {
		if (emf.isOpen()) {
			emf.close();
		}
	}
	
	public static void main(String[] args) {
		
		@SuppressWarnings("unused")
		DaoTest s = new DaoTest();
		
	}
	
}