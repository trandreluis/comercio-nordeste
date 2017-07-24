package br.edu.ifpb.mt.dac.nn.services;

import java.util.List;

import br.edu.ifpb.mt.dac.nn.enumerations.TipoUsuario;
import br.edu.ifpb.mt.dac.nn.exceptions.NegocioNordesteException;
import br.edu.ifpb.mt.dac.nn.model.Conta;

public interface ContaService extends GenericService<Conta, Long> {

	List<Conta> buscarPorTipo(TipoUsuario tipo);

	Conta buscarPorEmail(String email);

	Conta buscarPorUsername(String username);

	String criptografarSenha(String senha) throws NegocioNordesteException;
	
}