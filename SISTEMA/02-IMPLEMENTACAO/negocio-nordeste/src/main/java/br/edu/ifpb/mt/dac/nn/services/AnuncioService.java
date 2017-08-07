package br.edu.ifpb.mt.dac.nn.services;

import java.util.List;

import br.edu.ifpb.mt.dac.nn.exceptions.NegocioNordesteException;
import br.edu.ifpb.mt.dac.nn.model.Anunciante;
import br.edu.ifpb.mt.dac.nn.model.Anuncio;

public interface AnuncioService extends GenericService<Anuncio, Long> {

	List<Anuncio> buscarPorTitulo(String titulo) throws NegocioNordesteException;

	List<Anuncio> buscarPorDescricao(String descricao) throws NegocioNordesteException;
	
	List<Anuncio> buscaPorTag(String stringDeBusca) throws NegocioNordesteException;

	List<Anuncio> buscarPorPrecoMaximo(Double precoMaximo) throws NegocioNordesteException;

	List<Anuncio> buscarPorAnunciante(Anunciante Anunciante) throws NegocioNordesteException;

}