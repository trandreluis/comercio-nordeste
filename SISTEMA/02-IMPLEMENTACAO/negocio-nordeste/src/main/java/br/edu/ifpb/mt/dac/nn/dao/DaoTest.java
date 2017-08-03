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
			emf = Persistence.createEntityManagerFactory("bdnordesteP");
			em = emf.createEntityManager();

			Anunciante anunciante = new Anunciante();
			Conta conta = new Conta();
			conta.setEmail("tr.andreluis@gmail.com");
			conta.setSenha("5994471abb01112afcc18159f6cc74b4f511b99806da59b3caf5a9c173cacfc5");
			conta.setUsername("siqueira");
			conta.setTipo(TipoUsuario.ANUNCIANTE);
			anunciante.setConta(conta);
			anunciante.setDataNascimento(new Date(10, 25, 1996));
			anunciante.setNome("André");
			anunciante.setSobrenome("Luís");
			
			tx = em.getTransaction();
			tx.begin();

			em.persist(anunciante);

			tx.commit();
			
			emf = Persistence.createEntityManagerFactory("bdnordesteP");
			em = emf.createEntityManager();
			
		} catch (Exception pe) {
			tx.rollback();
			em.close();
		}

	}

}