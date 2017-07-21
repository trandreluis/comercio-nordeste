package br.edu.ifpb.mt.dac.nn.dao;

import java.util.List;

import br.edu.ifpb.mt.dac.nn.enumerations.TipoUsuario;
import br.edu.ifpb.mt.dac.nn.exceptions.NegocioNordesteException;
import br.edu.ifpb.mt.dac.nn.model.Conta;

public interface ContaDAO extends GenericDAO<Conta, Long> {

	List<Conta> buscarPorTipo(TipoUsuario tipo) throws NegocioNordesteException;

	Conta buscarPorEmail(String email);

	Conta buscarPorUsername(String username);

}