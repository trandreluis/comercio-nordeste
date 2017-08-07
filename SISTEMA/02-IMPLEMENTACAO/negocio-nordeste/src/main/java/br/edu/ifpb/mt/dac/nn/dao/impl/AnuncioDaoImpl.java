package br.edu.ifpb.mt.dac.nn.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.edu.ifpb.mt.dac.nn.dao.AnuncioDAO;
import br.edu.ifpb.mt.dac.nn.exceptions.NegocioNordesteException;
import br.edu.ifpb.mt.dac.nn.model.Anunciante;
import br.edu.ifpb.mt.dac.nn.model.Anuncio;

public class AnuncioDaoImpl extends GenericDaoImpl<Anuncio, Long> implements AnuncioDAO {

	private static final long serialVersionUID = 167764111111111L;

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
			query.setParameter("descricao", "%" + descricao + "%");
			resultado = query.getResultList();
		} catch (PersistenceException pe) {
			throw new NegocioNordesteException("Erro ao realizar busca pela descrição: " + pe.getMessage());
		}
		return resultado;
	}

	@Override
	public List<Anuncio> buscaPorTag(String stringDeBusca) throws NegocioNordesteException {
		CriteriaBuilder criteria = entityManager.getCriteriaBuilder();
		CriteriaQuery<Anuncio> query = criteria.createQuery(Anuncio.class);
		Root<Anuncio> root = query.from(Anuncio.class);

		List<Predicate> predicates = new ArrayList<Predicate>();

		if (stringDeBusca != null && !"".equals(stringDeBusca)) {
			predicates.add(criteria.like(criteria.lower(root.<String> get("titulo")), "%" + stringDeBusca + "%"));
			predicates.add(criteria.like(criteria.lower(root.<String> get("descricao")), "%" + stringDeBusca + "%"));
		}

		if (predicates.size() > 0) {
			query.where(criteria.or(predicates.toArray(new Predicate[] {})));
		}

		return entityManager.createQuery(query).getResultList();

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