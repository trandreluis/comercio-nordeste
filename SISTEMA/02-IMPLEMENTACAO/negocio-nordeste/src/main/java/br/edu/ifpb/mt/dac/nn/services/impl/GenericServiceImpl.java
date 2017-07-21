package br.edu.ifpb.mt.dac.nn.services.impl;

import java.io.Serializable;
import java.util.List;

import br.edu.ifpb.mt.dac.nn.dao.GenericDAO;
import br.edu.ifpb.mt.dac.nn.exceptions.NegocioNordesteException;
import br.edu.ifpb.mt.dac.nn.services.GenericService;
import br.edu.ifpb.mt.dac.nn.util.jpa.Transactional;

public class GenericServiceImpl<T, ID> implements GenericService<T, ID>, Serializable {

	private static final long serialVersionUID = 76538538274567236L;

	protected GenericDAO<T, ID> dao;

	@Override
	@Transactional
	public void salvar(T entidade) throws NegocioNordesteException {
		dao.salvar(entidade);
	}

	@Override
	@Transactional
	public T atualizar(T entidade) throws NegocioNordesteException {
		return dao.atualizar(entidade);
	}

	@Override
	@Transactional
	public void deletar(T entidade) throws NegocioNordesteException {
		dao.deletar(entidade);
	}

	@Override
	public T buscarPorID(ID id) {
		return dao.buscarPorID(id);
	}

	@Override
	public List<T> buscarTodos() {
		return dao.buscarTodos();
	}

}