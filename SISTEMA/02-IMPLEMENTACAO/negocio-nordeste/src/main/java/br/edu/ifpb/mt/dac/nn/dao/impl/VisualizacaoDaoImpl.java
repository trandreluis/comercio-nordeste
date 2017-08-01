package br.edu.ifpb.mt.dac.nn.dao.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.PersistenceException;
import javax.persistence.Query;

import br.edu.ifpb.mt.dac.nn.dao.VisualizacaoDAO;
import br.edu.ifpb.mt.dac.nn.enumerations.TipoVisualizacao;
import br.edu.ifpb.mt.dac.nn.exceptions.NegocioNordesteException;
import br.edu.ifpb.mt.dac.nn.model.Anuncio;
import br.edu.ifpb.mt.dac.nn.model.Visualizacao;

public class VisualizacaoDaoImpl extends GenericDaoImpl<Visualizacao, Long> implements VisualizacaoDAO {

	private static final long serialVersionUID = 1456744543276004444L;

	@SuppressWarnings("unchecked")
	@Override
	public List<Visualizacao> buscarPorData(Date data) throws NegocioNordesteException {
		List<Visualizacao> resultado = null;
		try {
			Query query = entityManager.createNamedQuery("Visualizacao.buscarPorData");
			query.setParameter("data", "%" + data + "%");
			resultado = query.getResultList();
		} catch (PersistenceException pe) {
			throw new NegocioNordesteException("Erro ao realizar busca por data: " + pe.getMessage());
		}
		return resultado;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Visualizacao> buscarPorTipo(TipoVisualizacao tipo) throws NegocioNordesteException {
		List<Visualizacao> resultado = null;
		try {
			Query query = entityManager.createNamedQuery("Visualizacao.buscarPorTipo");
			query.setParameter("tipo", "%" + tipo.toString() + "%");
			resultado = query.getResultList();
		} catch (PersistenceException pe) {
			throw new NegocioNordesteException("Erro ao realizar busca por tipo: " + pe.getMessage());
		}
		return resultado;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Visualizacao> buscarPorAnuncio(Anuncio anuncio) throws NegocioNordesteException {
		List<Visualizacao> resultado = null;
		try {
			Query query = entityManager.createNamedQuery("Visualizacao.buscarPorData");
			query.setParameter("anuncio", "%" + anuncio + "%");
			resultado = query.getResultList();
		} catch (PersistenceException pe) {
			throw new NegocioNordesteException("Erro ao realizar busca por anuncio: " + pe.getMessage());
		}
		return resultado;
	}

}