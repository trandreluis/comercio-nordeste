package br.edu.ifpb.mt.dac.nn.dao;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import br.edu.ifpb.mt.dac.nn.enumerations.TipoUsuario;
import br.edu.ifpb.mt.dac.nn.model.Anunciante;
import br.edu.ifpb.mt.dac.nn.model.Conta;
import br.edu.ifpb.mt.dac.nn.model.Operador;

public class DaoTest {

	public static void main(String[] args) {

		EntityManagerFactory emf = null;
		EntityManager em = null;
		EntityTransaction tx = null;

		try {
//			emf = Persistence.createEntityManagerFactory("bdnordesteP");
//			em = emf.createEntityManager();
//
//			Operador operador = new Operador(); 
//			
//			Conta conta = new Conta();
//			conta.setEmail("admin");
//			conta.setSenha("admin");
//			conta.setTipo(TipoUsuario.ADMIN);
//			conta.setUsername("admin");
//
//			operador.setConta(conta);
//
//			tx = em.getTransaction();
//			tx.begin();
//
//			em.persist(operador);
//
//			tx.commit();
			
			emf = Persistence.createEntityManagerFactory("bdnordesteP");
			em = emf.createEntityManager();
			
			Conta conta = new Conta();
			
			em.persist(conta);

		} catch (Exception pe) {
			tx.rollback();
			em.close();
		}

	}

}