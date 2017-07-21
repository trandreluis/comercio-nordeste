package br.edu.ifpb.mt.dac.nn.dao.impl;

import java.util.List;

import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.edu.ifpb.mt.dac.nn.dao.ContaDAO;
import br.edu.ifpb.mt.dac.nn.enumerations.TipoUsuario;
import br.edu.ifpb.mt.dac.nn.exceptions.NegocioNordesteException;
import br.edu.ifpb.mt.dac.nn.model.Conta;

public class ContaDaoImpl extends GenericDaoImpl<Conta, Long> implements ContaDAO {

	private static final long serialVersionUID = 145674444444444L;

	@SuppressWarnings("unchecked")
	@Override
	public List<Conta> buscarPorTipo(TipoUsuario tipo) throws NegocioNordesteException {
		List<Conta> resultado = null;
		try {
			Query query = entityManager.createNamedQuery("Conta.buscarPorTipo");
			query.setParameter("tipo", "%" + tipo.toString() + "%");
			resultado = query.getResultList();
		} catch (PersistenceException pe) {
			throw new NegocioNordesteException("Erro ao realizar busca por título: " + pe.getMessage());
		}
		return resultado;
	}

	@Override
	public Conta buscarPorEmail(String email) {
		Conta resultado = null;
		try {
			TypedQuery<Conta> query = entityManager.createNamedQuery("Conta.buscarPorEmail", Conta.class);
			query.setParameter("email", email);
			resultado = query.getSingleResult();
		} catch (PersistenceException pe) {
			return null;
		}
		return resultado;
	}

	@Override
	public Conta buscarPorUsername(String username) {
		Conta resultado = null;
		try {
			TypedQuery<Conta> query = entityManager.createNamedQuery("Conta.buscarPorUsername", Conta.class);
			query.setParameter("username", username);
			resultado = query.getSingleResult();
		} catch (PersistenceException pe) {
			return null;
		}
		return resultado;
	}

}