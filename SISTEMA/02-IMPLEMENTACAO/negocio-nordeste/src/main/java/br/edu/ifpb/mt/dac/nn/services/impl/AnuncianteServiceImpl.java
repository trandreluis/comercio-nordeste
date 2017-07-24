package br.edu.ifpb.mt.dac.nn.services.impl;

import java.util.List;

import javax.inject.Inject;

import br.edu.ifpb.mt.dac.nn.dao.AnuncianteDAO;
import br.edu.ifpb.mt.dac.nn.enumerations.NivelAnunciante;
import br.edu.ifpb.mt.dac.nn.exceptions.NegocioNordesteException;
import br.edu.ifpb.mt.dac.nn.model.Anunciante;
import br.edu.ifpb.mt.dac.nn.model.Anuncio;
import br.edu.ifpb.mt.dac.nn.services.AnuncianteService;
import br.edu.ifpb.mt.dac.nn.util.jpa.Transactional;

public class AnuncianteServiceImpl extends GenericServiceImpl<Anunciante, Long> implements AnuncianteService {

	private static final long serialVersionUID = 1865434577L;

	public AnuncianteServiceImpl() {
	}

	@Inject
	public AnuncianteServiceImpl(AnuncianteDAO anuncianteDAO) {
		this.dao = anuncianteDAO;
	}

	@Override
	@Transactional
	public void salvar(Anunciante entidade) throws NegocioNordesteException {
		try {
			AnuncianteDAO anuncianteDAO = (AnuncianteDAO) this.dao;
			anuncianteDAO.salvar(entidade);
		} catch (Exception e) {
			throw new NegocioNordesteException("Ocorreu um erro ao tentar salvar o anunciante.");
		}
	}

	@Override
	@Transactional
	public Anunciante atualizar(Anunciante entidade) throws NegocioNordesteException {
		try {
			AnuncianteDAO anuncianteDAO = (AnuncianteDAO) this.dao;
			return anuncianteDAO.atualizar(entidade);
		} catch (Exception e) {
			throw new NegocioNordesteException("Ocorreu um erro ao tentar atualizar o anunciante.");
		}
	}

	@Override
	public List<Anunciante> buscarPorNome(String nome) throws NegocioNordesteException {
		try {
			AnuncianteDAO anuncianteDAO = (AnuncianteDAO) this.dao;
			List<Anunciante> anunciantes = anuncianteDAO.buscarPorNome(nome);
			return anunciantes;
		} catch (Exception e) {
			throw new NegocioNordesteException("Ocorreu um erro ao buscar o anunciante pelo nome.");
		}
	}

	@Override
	public Anunciante buscarPorEmail(String email) throws NegocioNordesteException {
		try {
			AnuncianteDAO anuncianteDAO = (AnuncianteDAO) this.dao;
			Anunciante anunciante = anuncianteDAO.buscarPorEmail(email);
			return anunciante;
		} catch (Exception e) {
			throw new NegocioNordesteException("Ocorreu um erro ao buscar o anunciante pelo e-mail.");
		}
	}

	@Override
	public Anunciante buscarPorUsername(String username) throws NegocioNordesteException {
		try {
			AnuncianteDAO anuncianteDAO = (AnuncianteDAO) this.dao;
			Anunciante anunciante = anuncianteDAO.buscarPorUsername(username);
			return anunciante;
		} catch (Exception e) {
			throw new NegocioNordesteException("Ocorreu um erro ao buscar o anunciante pelo username.");
		}
	}

	@Override
	public List<Anunciante> buscarPorNivel(NivelAnunciante nivel) throws NegocioNordesteException {
		AnuncianteDAO anuncianteDAO = (AnuncianteDAO) this.dao;
		List<Anunciante> anunciantes = null;
		try {
			anunciantes = anuncianteDAO.buscarPorNivel(nivel);
		} catch (NegocioNordesteException e) {
			throw new NegocioNordesteException("Já existe um anunciante com este username cadastrado");
		}
		return anunciantes;
	}

	@Override
	public Anunciante buscarPorAnuncio(Anuncio anuncio) throws NegocioNordesteException {
		try {
			AnuncianteDAO anuncianteDAO = (AnuncianteDAO) this.dao;
			Anunciante anunciante = anuncianteDAO.buscarPorAnuncio(anuncio);
			return anunciante;
		} catch (Exception e) {
			throw new NegocioNordesteException("Ocorreu um erro ao buscar o anunciante pelo anuncio.");
		}
	}

}