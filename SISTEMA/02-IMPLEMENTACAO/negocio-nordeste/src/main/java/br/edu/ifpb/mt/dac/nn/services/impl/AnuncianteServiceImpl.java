package br.edu.ifpb.mt.dac.nn.services.impl;

import java.util.List;

import javax.inject.Inject;

import br.edu.ifpb.mt.dac.nn.dao.AnuncianteDAO;
import br.edu.ifpb.mt.dac.nn.enumerations.NivelAnunciante;
import br.edu.ifpb.mt.dac.nn.exceptions.NegocioNordesteException;
import br.edu.ifpb.mt.dac.nn.model.Anunciante;
import br.edu.ifpb.mt.dac.nn.model.Anuncio;
import br.edu.ifpb.mt.dac.nn.services.AnuncianteService;

public class AnuncianteServiceImpl extends GenericServiceImpl<Anunciante, Long> implements AnuncianteService {

	private static final long serialVersionUID = 1865434577L;

	public AnuncianteServiceImpl() {
	}

	@Inject
	public AnuncianteServiceImpl(AnuncianteDAO anuncianteDAO) {
		this.dao = anuncianteDAO;
	}

	@Override
	public void salvar(Anunciante entidade) throws NegocioNordesteException {
		AnuncianteDAO anuncianteDAO = (AnuncianteDAO) this.dao;
		Anunciante anuncianteEmail = anuncianteDAO.buscarPorEmail(entidade.getEmail());
		Anunciante anuncianteUsername = anuncianteDAO.buscarPorUsername(entidade.getUsername());

		if (anuncianteEmail != null) {
			throw new NegocioNordesteException("Já existe um anunciante com este e-mail cadastrado.");
		}

		else if (anuncianteUsername != null) {
			throw new NegocioNordesteException("Já existe um anunciante com este username cadastrado");
		}

		dao.salvar(entidade);
	}

	@Override
	public Anunciante atualizar(Anunciante entidade) throws NegocioNordesteException {
		AnuncianteDAO anuncianteDAO = (AnuncianteDAO) this.dao;
		Anunciante anuncianteAntigo = anuncianteDAO.buscarPorID(entidade.getId());

		// Se não alterou e-mail nem username, prossegue sem necessidade de
		// comparar com já existentes
		if (anuncianteAntigo.getEmail().equals(entidade.getEmail())
				&& anuncianteAntigo.getUsername().equals(entidade.getUsername())) {
			return anuncianteDAO.atualizar(entidade);
		}

		// Senão busca todos e verifica a disponiblidade do e-mail e username no
		// sistema
		List<Anunciante> anunciantes = anuncianteDAO.buscarTodos();

		for (Anunciante anuncianteCadastrado : anunciantes) {
			if (anuncianteCadastrado.getEmail().equals(entidade.getEmail())) {
				throw new NegocioNordesteException("Já existe um anunciante cadastrado com este e-mail.");
			}
			if (anuncianteCadastrado.getUsername().equals(entidade.getUsername())) {
				throw new NegocioNordesteException("Já existe um anunciante cadastrado com este username.");
			}
		}
		// Caso seja uma atualização válida, prossegue normalmente e retorna a nova instancia atualizada
		return anuncianteDAO.atualizar(entidade);
	}

	@Override
	public List<Anunciante> buscarPorNome(String nome) {
		AnuncianteDAO anuncianteDAO = (AnuncianteDAO) this.dao;
		List<Anunciante> anunciantes = anuncianteDAO.buscarPorNome(nome);

		return anunciantes;
	}

	@Override
	public Anunciante buscarPorEmail(String email) {
		AnuncianteDAO anuncianteDAO = (AnuncianteDAO) this.dao;
		Anunciante anunciante = anuncianteDAO.buscarPorEmail(email);

		return anunciante;
	}

	@Override
	public Anunciante buscarPorUsername(String username) {
		AnuncianteDAO anuncianteDAO = (AnuncianteDAO) this.dao;
		Anunciante anunciante = anuncianteDAO.buscarPorUsername(username);

		return anunciante;
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
	public Anunciante buscarPorAnuncio(Anuncio anuncio) {
		AnuncianteDAO anuncianteDAO = (AnuncianteDAO) this.dao;
		Anunciante anunciante = anuncianteDAO.buscarPorAnuncio(anuncio);

		return anunciante;
	}

}