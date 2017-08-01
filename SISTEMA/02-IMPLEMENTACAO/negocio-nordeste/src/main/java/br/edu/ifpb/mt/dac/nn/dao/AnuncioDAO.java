package br.edu.ifpb.mt.dac.nn.dao;

import java.util.List;

import br.edu.ifpb.mt.dac.nn.exceptions.NegocioNordesteException;
import br.edu.ifpb.mt.dac.nn.model.Anunciante;
import br.edu.ifpb.mt.dac.nn.model.Anuncio;

public interface AnuncioDAO extends GenericDAO<Anuncio, Long> {

	List<Anuncio> buscarPorTitulo(String titulo) throws NegocioNordesteException;

	List<Anuncio> buscarPorDescricao(String descricao) throws NegocioNordesteException;

	List<Anuncio> buscarPorPrecoMaximo(Double precoMaximo);

	List<Anuncio> buscarPorAnunciante(Anunciante anunciante);

}