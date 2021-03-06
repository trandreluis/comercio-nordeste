package br.edu.ifpb.mt.dac.nn.services;

import java.util.List;

import br.edu.ifpb.mt.dac.nn.exceptions.NegocioNordesteException;

public interface GenericService<T, ID> {

	public void salvar(T entidade) throws NegocioNordesteException;

	public T atualizar(T entidade) throws NegocioNordesteException;

	public void deletar(T entidade) throws NegocioNordesteException;

	public T buscarPorID(ID id);

	public List<T> buscarTodos();

}