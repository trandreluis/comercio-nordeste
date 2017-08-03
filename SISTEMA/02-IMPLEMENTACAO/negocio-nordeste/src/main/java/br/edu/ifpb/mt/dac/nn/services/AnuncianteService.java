package br.edu.ifpb.mt.dac.nn.services;

import java.util.List;

import br.edu.ifpb.mt.dac.nn.exceptions.NegocioNordesteException;
import br.edu.ifpb.mt.dac.nn.model.Anunciante;
import br.edu.ifpb.mt.dac.nn.model.Anuncio;

public interface AnuncianteService extends GenericService<Anunciante, Long> {

	List<Anunciante> buscarPorNome(String nome) throws NegocioNordesteException;

	Anunciante buscarPorEmail(String email) throws NegocioNordesteException;

	Anunciante buscarPorUsername(String username) throws NegocioNordesteException;

	Anunciante buscarPorAnuncio(Anuncio anuncio) throws NegocioNordesteException;

}