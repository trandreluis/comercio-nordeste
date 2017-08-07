package br.edu.ifpb.mt.dac.nn.services.impl;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.PersistenceException;

import br.edu.ifpb.mt.dac.nn.dao.AnuncioDAO;
import br.edu.ifpb.mt.dac.nn.exceptions.NegocioNordesteException;
import br.edu.ifpb.mt.dac.nn.model.Anunciante;
import br.edu.ifpb.mt.dac.nn.model.Anuncio;
import br.edu.ifpb.mt.dac.nn.services.AnuncioService;
import br.edu.ifpb.mt.dac.nn.util.jpa.Transactional;

public class AnuncioServiceImpl extends GenericServiceImpl<Anuncio, Long> implements AnuncioService {

	private static final long serialVersionUID = 18654878732134577L;

	public AnuncioServiceImpl() {
	}

	@Inject
	public AnuncioServiceImpl(AnuncioDAO anuncioDAO) {
		this.dao = anuncioDAO;
	}

	@Override
	@Transactional
	public void salvar(Anuncio entidade) throws NegocioNordesteException {
		try {
			AnuncioDAO anuncioDAO = (AnuncioDAO) this.dao;
			anuncioDAO.salvar(entidade);
		} catch (PersistenceException pe) {
			throw new NegocioNordesteException("Ocorreu um erro ao tentar cadastrar o anúncio.");
		}
	}

	@Override
	@Transactional
	public Anuncio atualizar(Anuncio entidade) throws NegocioNordesteException {
		try {
			AnuncioDAO anuncioDAO = (AnuncioDAO) this.dao;
			return anuncioDAO.atualizar(entidade);
		} catch (PersistenceException pe) {
			throw new NegocioNordesteException("Ocorreu um erro ao tentar atualizar o anúncio.");
		}
	}

	@Override
	@Transactional
	public void deletar(Anuncio entidade) throws NegocioNordesteException {
		try {
			AnuncioDAO anuncioDAO = (AnuncioDAO) this.dao;
			anuncioDAO.deletar(entidade);
		} catch (PersistenceException pe) {
			throw new NegocioNordesteException("Ocorreu um erro ao tentar deletar o anúncio.");
		}
	}

	@Override
	public List<Anuncio> buscarPorTitulo(String titulo) throws NegocioNordesteException {
		AnuncioDAO anuncioDAO = (AnuncioDAO) this.dao;

		try {
			return anuncioDAO.buscarPorTitulo(titulo);
		} catch (PersistenceException pe) {
			throw new NegocioNordesteException("Ocorreu um erro ao tentar buscar o anúncio pelo título.");
		}

	}

	@Override
	public List<Anuncio> buscarPorDescricao(String descricao) throws NegocioNordesteException {
		AnuncioDAO anuncioDAO = (AnuncioDAO) this.dao;

		try {
			return anuncioDAO.buscarPorDescricao(descricao);
		} catch (PersistenceException pe) {
			throw new NegocioNordesteException("Ocorreu um erro ao tentar buscar o anúncio pela descrição.");
		}
	}
	
	@Override
	public List<Anuncio> buscaPorTag(String stringDeBusca) throws NegocioNordesteException {
		AnuncioDAO anuncioDAO = (AnuncioDAO) this.dao;

		try {
			return anuncioDAO.buscarPorDescricao(stringDeBusca);
		} catch (PersistenceException pe) {
			throw new NegocioNordesteException("Ocorreu um erro ao tentar buscar o anúncio pela tag.");
		}
	}

	@Override
	public List<Anuncio> buscarPorPrecoMaximo(Double precoMaximo) throws NegocioNordesteException {
		AnuncioDAO anuncioDAO = (AnuncioDAO) this.dao;
		try {
			return anuncioDAO.buscarPorPrecoMaximo(precoMaximo);
		} catch (PersistenceException pe) {
			throw new NegocioNordesteException("Ocorreu um erro ao tentar buscar o anúncio pelo preço máximo.");
		}
	}

	@Override
	public List<Anuncio> buscarPorAnunciante(Anunciante anunciante) throws NegocioNordesteException {
		AnuncioDAO anuncioDAO = (AnuncioDAO) this.dao;
		try {
			return anuncioDAO.buscarPorAnunciante(anunciante);
		} catch (PersistenceException pe) {
			throw new NegocioNordesteException("Ocorreu um erro ao tentar buscar o anúncio pelo anunciante.");
		}
	}

}