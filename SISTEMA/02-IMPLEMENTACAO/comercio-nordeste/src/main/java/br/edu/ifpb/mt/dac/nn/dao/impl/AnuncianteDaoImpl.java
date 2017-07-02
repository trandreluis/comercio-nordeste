package br.edu.ifpb.mt.dac.nn.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.edu.ifpb.mt.dac.nn.dao.AnuncianteDAO;
import br.edu.ifpb.mt.dac.nn.enumerations.NivelAnunciante;
import br.edu.ifpb.mt.dac.nn.model.Anunciante;
import br.edu.ifpb.mt.dac.nn.model.Anuncio;

public class AnuncianteDaoImpl extends GenericDaoImpl<Anunciante, Long> implements AnuncianteDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<Anunciante> buscarPorNome(String nome) {
		EntityManager em = getEntityManager();
		List<Anunciante> resultado = null;
		try {
			Query query = em.createNamedQuery("Anunciante.buscarPorNome");
			query.setParameter("nome", "%" + nome + "%");
			resultado = query.getResultList();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
		} finally {
			em.close();
		}
		return resultado;
	}

	@Override
	public Anunciante buscarPorEmail(String email) {
		EntityManager em = getEntityManager();
		Anunciante resultado = null;
		try {
			TypedQuery<Anunciante> query = em.createNamedQuery("Funcionario.findByCPF", Anunciante.class);
			query.setParameter("email", email);
			resultado = query.getSingleResult();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
		} finally {
			em.close();
		}
		return resultado;
	}

	@Override
	public Anunciante buscarPorNomeUsuario(String nomeUsuario) {
		EntityManager em = getEntityManager();
		Anunciante resultado = null;
		try {
			TypedQuery<Anunciante> query = em.createNamedQuery("Anunciante.buscarPorNomeUsuario", Anunciante.class);
			query.setParameter("nomeUsuario", nomeUsuario);
			resultado = query.getSingleResult();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
		} finally {
			em.close();
		}
		return resultado;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Anunciante> buscarPorNivel(NivelAnunciante nivel) {
		EntityManager em = getEntityManager();
		List<Anunciante> resultado = null;
		try {
			Query query = em.createNamedQuery("Anunciante.buscarPorNivel");
			query.setParameter("nivel", "%" + nivel.toString() + "%");
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
	public Anunciante buscarPorAnuncio(Anuncio anuncio) {
		return null;
	}

}