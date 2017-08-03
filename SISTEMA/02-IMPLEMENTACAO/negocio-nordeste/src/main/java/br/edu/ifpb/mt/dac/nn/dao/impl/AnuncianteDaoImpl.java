package br.edu.ifpb.mt.dac.nn.dao.impl;

import java.util.List;

import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.edu.ifpb.mt.dac.nn.dao.AnuncianteDAO;
import br.edu.ifpb.mt.dac.nn.model.Anunciante;
import br.edu.ifpb.mt.dac.nn.model.Anuncio;

public class AnuncianteDaoImpl extends GenericDaoImpl<Anunciante, Long> implements AnuncianteDAO {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
	public List<Anunciante> buscarPorNome(String nome) {
		Query query = entityManager.createNamedQuery("Anunciante.buscarPorNome");
		query.setParameter("nome", "%" + nome + "%");
		List<Anunciante> resultado = query.getResultList();
		return resultado;
	}

	@Override
	public Anunciante buscarPorEmail(String email) {
		Anunciante resultado = null;
		try {
			TypedQuery<Anunciante> query = entityManager.createNamedQuery("Anunciante.buscarPorEmail",
					Anunciante.class);
			query.setParameter("email", email);
			resultado = query.getSingleResult();
		} catch (PersistenceException pe) {
			return null;
		}
		return resultado;
	}

	@Override
	public Anunciante buscarPorUsername(String username) {
		Anunciante resultado = null;
		try {
			TypedQuery<Anunciante> query = entityManager.createNamedQuery("Anunciante.buscarPorUsername",
					Anunciante.class);
			query.setParameter("username", username);
			resultado = query.getSingleResult();
		} catch (PersistenceException pe) {
			return null;
		}
		return resultado;
	}

	/*
	 * Falta implementar
	 */
	@Override
	public Anunciante buscarPorAnuncio(Anuncio anuncio) {
		return null;
	}

}