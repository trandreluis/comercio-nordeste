package br.edu.ifpb.mt.dac.nn.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import br.edu.ifpb.mt.dac.nn.dao.GenericDAO;

public abstract class GenericDaoImpl<T, ID> implements GenericDAO<T, ID>, Serializable {

	private static final long serialVersionUID = -1421441012226531273L;

	static EntityManagerFactory emf;
	private Class<T> tipo;

	@SuppressWarnings( {"unchecked", "rawtypes"} )
	public GenericDaoImpl() {
		Type tipo = getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) tipo;
		this.tipo = (Class) pt.getActualTypeArguments()[0];
	}
	
	static {
		try {
			emf = Persistence.createEntityManagerFactory("bdnordeste");
		} catch(Throwable t) {
			t.printStackTrace();
			throw t;
		}
	}

	protected EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	public void close() {
		if (emf.isOpen()) {
			emf.close();
		}
	}

	@Override
	public void salvar(T entidade) {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			em.persist(entidade);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
		} finally {
			em.close();
		}
	}
	
	@Override
	public T atualizar(T entidade) {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		T resultado = entidade;
		try {
			resultado = em.merge(entidade);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
		} finally {
			em.close();
		}
		return resultado;
	}
	
	@Override
	public void deletar(T entidade) {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			T produto = em.merge(entidade);
			em.remove(produto);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
		} finally {
			em.close();
		}
	}
	
	@Override
	public T buscarPorID(ID id) {
		EntityManager em = getEntityManager();
		T resultado = null;
		try {
			resultado = em.find(tipo, id);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
		} finally {
			em.close();
		}

		return resultado;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> buscarTodos() {
		EntityManager em = getEntityManager();
		List<T> resultado = null;
		try {
			Query query = em.createQuery("SELECT t FROM " + tipo.getName() + " t");
			resultado = query.getResultList();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
		} finally {
			em.close();
		}
		return resultado;
	}
	
}