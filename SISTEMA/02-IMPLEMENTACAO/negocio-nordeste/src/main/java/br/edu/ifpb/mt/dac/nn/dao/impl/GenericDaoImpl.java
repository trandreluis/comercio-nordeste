package br.edu.ifpb.mt.dac.nn.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.edu.ifpb.mt.dac.nn.dao.GenericDAO;

public abstract class GenericDaoImpl<T, ID> implements GenericDAO<T, ID>, Serializable {

	private static final long serialVersionUID = 1421441012226531273L;

	@Inject
	protected EntityManager entityManager;

	private Class<T> tipo;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public GenericDaoImpl() {
		Type tipo = getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) tipo;
		this.tipo = (Class) pt.getActualTypeArguments()[0];
	}

	@Override
	public void salvar(T entidade) {
		entityManager.persist(entidade);
	}

	@Override
	public T atualizar(T entidade) {
		T resultado = entityManager.merge(entidade);
		entityManager.flush();
		entityManager.detach(resultado);
		return resultado;
	}

	@Override
	public void deletar(T entidade) {
		T resultado = entityManager.merge(entidade);
		entityManager.remove(resultado);
	}

	@Override
	public T buscarPorID(ID id) {
		T resultado = entityManager.find(tipo, id);
		return resultado;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> buscarTodos() {
		Query query = entityManager.createQuery("SELECT t FROM " + tipo.getName() + " t");
		List<T> resultado = query.getResultList();
		return resultado;
	}

	//protected abstract EntityManager getEM();
}