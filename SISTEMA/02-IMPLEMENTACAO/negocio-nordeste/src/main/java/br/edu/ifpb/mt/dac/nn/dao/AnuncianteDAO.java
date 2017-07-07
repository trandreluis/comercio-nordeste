package br.edu.ifpb.mt.dac.nn.dao;

import java.util.List;

import br.edu.ifpb.mt.dac.nn.enumerations.NivelAnunciante;
import br.edu.ifpb.mt.dac.nn.model.Anunciante;
import br.edu.ifpb.mt.dac.nn.model.Anuncio;

public interface AnuncianteDAO extends GenericDAO<Anunciante, Long> {

	/*
	 * Falta declarar possiveis excecoes nos metodos
	 */
	
	List<Anunciante> buscarPorNome(String nome);

	Anunciante buscarPorEmail(String email);
	
	Anunciante buscarPorNomeUsuario(String nomeUsuario);
	
	List<Anunciante> buscarPorNivel(NivelAnunciante nivel);
	
	Anunciante buscarPorAnuncio(Anuncio anuncio);
	
}