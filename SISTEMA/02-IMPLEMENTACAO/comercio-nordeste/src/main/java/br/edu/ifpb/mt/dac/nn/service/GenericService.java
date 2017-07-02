package br.edu.ifpb.mt.dac.nn.service;

import java.util.List;

public interface GenericService<T, ID> {

	public void salvar(T entidade);

	public T atualizar(T entidade);

	public void deletar(T entidade);

	public T buscarPorID(ID id);

	public List<T> buscarTodos();

}