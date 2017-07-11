package br.edu.ifpb.mt.dac.nn.services.impl;

import java.util.List;

import br.edu.ifpb.mt.dac.nn.dao.AnuncianteDAO;
import br.edu.ifpb.mt.dac.nn.dao.impl.AnuncianteDaoImpl;
import br.edu.ifpb.mt.dac.nn.enumerations.NivelAnunciante;
import br.edu.ifpb.mt.dac.nn.exceptions.NegocioNordesteException;
import br.edu.ifpb.mt.dac.nn.model.Anunciante;
import br.edu.ifpb.mt.dac.nn.model.Anuncio;
import br.edu.ifpb.mt.dac.nn.services.AnuncianteService;

public class AnuncianteServiceImpl extends GenericServiceImpl<Anunciante, Long> implements AnuncianteService {

	public AnuncianteServiceImpl() {
		this.dao = new AnuncianteDaoImpl();
	}

	public static void main(String[] args) {

		@SuppressWarnings("unused")
		AnuncianteServiceImpl d = new AnuncianteServiceImpl();

	}

	@Override
	public void salvar(Anunciante entidade) throws NegocioNordesteException {
		AnuncianteDAO anuncianteDAO = (AnuncianteDAO) this.dao;
		Anunciante anuncianteEmail = anuncianteDAO.buscarPorEmail(entidade.getEmail());
		Anunciante anuncianteNomeUsuario = anuncianteDAO.buscarPorNomeUsuario(entidade.getNomeUsuario());

		if (anuncianteEmail != null) {
			throw new NegocioNordesteException("Já existe um anunciante com este e-mail cadastrado.");
		}

		else if (anuncianteNomeUsuario != null) {
			throw new NegocioNordesteException("Já existe um anunciante com este username cadastrado");
		}

		else {
			dao.salvar(entidade);
		}
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
	public Anunciante buscarPorNomeUsuario(String nomeUsuario) {
		AnuncianteDAO anuncianteDAO = (AnuncianteDAO) this.dao;
		Anunciante anunciante = anuncianteDAO.buscarPorNomeUsuario(nomeUsuario);

		return anunciante;
	}

	@Override
	public List<Anunciante> buscarPorNivel(NivelAnunciante nivel) {
		AnuncianteDAO anuncianteDAO = (AnuncianteDAO) this.dao;
		List<Anunciante> anunciantes = anuncianteDAO.buscarPorNivel(nivel);

		return anunciantes;
	}

	@Override
	public Anunciante buscarPorAnuncio(Anuncio anuncio) {
		AnuncianteDAO anuncianteDAO = (AnuncianteDAO) this.dao;
		Anunciante anunciante = anuncianteDAO.buscarPorAnuncio(anuncio);

		return anunciante;
	}

}