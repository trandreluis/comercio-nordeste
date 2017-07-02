package br.edu.ifpb.mt.dac.nn.dao;

import java.util.List;

import br.edu.ifpb.mt.dac.nn.model.Anunciante;
import br.edu.ifpb.mt.dac.nn.model.Anuncio;

public interface AnuncioDAO extends GenericDAO<Anuncio, Long> {

	/*
	 * Falta declarar possiveis excecoes nos metodos
	 */
	
	List<Anuncio> buscarPorTitulo(String titulo);

	List<Anuncio> buscarPorDescricao(String descricao);
	
	List<Anuncio> buscarPorPrecoMaximo(Double precoMaximo);
	
	List<Anuncio> buscarPorAnunciante(Anuncio anuncio, Anunciante anunciante);
	
}