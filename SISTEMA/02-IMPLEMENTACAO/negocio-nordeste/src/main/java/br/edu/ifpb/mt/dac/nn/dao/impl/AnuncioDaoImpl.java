package br.edu.ifpb.mt.dac.nn.dao.impl;

import java.util.List;

import javax.persistence.PersistenceException;
import javax.persistence.Query;

import br.edu.ifpb.mt.dac.nn.dao.AnuncioDAO;
import br.edu.ifpb.mt.dac.nn.exceptions.NegocioNordesteException;
import br.edu.ifpb.mt.dac.nn.model.Anunciante;
import br.edu.ifpb.mt.dac.nn.model.Anuncio;

public class AnuncioDaoImpl extends GenericDaoImpl<Anuncio, Long> implements AnuncioDAO {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
	public List<Anuncio> buscarPorTitulo(String titulo) throws NegocioNordesteException {
		List<Anuncio> resultado = null;
		try {
			Query query = entityManager.createNamedQuery("Anuncio.buscarPorTitulo");
			query.setParameter("titulo", "%" + titulo + "%");
			resultado = query.getResultList();
		} catch (PersistenceException pe) {
			throw new NegocioNordesteException("Erro ao realizar busca por título: " + pe.getMessage());
		}
		return resultado;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Anuncio> buscarPorDescricao(String descricao) throws NegocioNordesteException {
		List<Anuncio> resultado = null;
		try {
			Query query = entityManager.createNamedQuery("Anuncio.buscarPorDescricao");
			query.setParameter("titulo", "%" + descricao + "%");
			resultado = query.getResultList();
		} catch (PersistenceException pe) {
			throw new NegocioNordesteException("Erro ao realizar busca pela descrição: " + pe.getMessage());
		}
		return resultado;
	}

	/*
	 * Falta implementar
	 */
	@Override
	public List<Anuncio> buscarPorPrecoMaximo(Double precoMaximo) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * Falta implementar
	 */
	@Override
	public List<Anuncio> buscarPorAnunciante(Anunciante anunciante) {
		// TODO Auto-generated method stub
		return null;
	}

}