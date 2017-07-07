package br.edu.ifpb.mt.dac.nn.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import br.edu.ifpb.mt.dac.nn.dao.AnuncioDAO;
import br.edu.ifpb.mt.dac.nn.model.Anunciante;
import br.edu.ifpb.mt.dac.nn.model.Anuncio;

public class AnuncioDaoImpl extends GenericDaoImpl<Anuncio, Long> implements AnuncioDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<Anuncio> buscarPorTitulo(String titulo) {
		EntityManager em = getEntityManager();
		List<Anuncio> resultado = null;
		try {
			Query query = em.createNamedQuery("Anuncio.buscarPorTitulo");
			query.setParameter("titulo", "%" + titulo + "%");
			resultado = query.getResultList();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
		} finally {
			em.close();
		}
		return resultado;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Anuncio> buscarPorDescricao(String descricao) {
		EntityManager em = getEntityManager();
		List<Anuncio> resultado = null;
		try {
			Query query = em.createNamedQuery("Anuncio.buscarPorDescricao");
			query.setParameter("titulo", "%" + descricao + "%");
			resultado = query.getResultList();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
		} finally {
			em.close();
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