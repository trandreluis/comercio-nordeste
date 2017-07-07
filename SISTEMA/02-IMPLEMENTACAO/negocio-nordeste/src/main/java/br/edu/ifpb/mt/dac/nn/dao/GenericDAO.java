package br.edu.ifpb.mt.dac.nn.dao;

import java.util.List;

public interface GenericDAO<T, ID>{
	
	public void salvar(T entidade);
	
	public T atualizar(T entidade);
	
	public void deletar(T entidade);
	
	public T buscarPorID(ID id);
	
	public List<T> buscarTodos();

}