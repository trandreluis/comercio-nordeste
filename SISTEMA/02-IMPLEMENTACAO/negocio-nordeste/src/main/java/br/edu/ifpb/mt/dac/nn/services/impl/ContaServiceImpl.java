package br.edu.ifpb.mt.dac.nn.services.impl;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.inject.Inject;

import br.edu.ifpb.mt.dac.nn.dao.ContaDAO;
import br.edu.ifpb.mt.dac.nn.enumerations.TipoUsuario;
import br.edu.ifpb.mt.dac.nn.exceptions.NegocioNordesteException;
import br.edu.ifpb.mt.dac.nn.model.Conta;
import br.edu.ifpb.mt.dac.nn.services.ContaService;
import br.edu.ifpb.mt.dac.nn.util.jpa.Transactional;

public class ContaServiceImpl extends GenericServiceImpl<Conta, Long> implements ContaService {

	private static final long serialVersionUID = 18654878732134577L;

	public ContaServiceImpl() {
	}

	@Inject
	public ContaServiceImpl(ContaDAO contaDAO) {
		this.dao = contaDAO;
	}

	@Override
	@Transactional
	public void salvar(Conta entidade) throws NegocioNordesteException {
		ContaDAO contaDAO = (ContaDAO) this.dao;

		Conta contaUsername = contaDAO.buscarPorUsername(entidade.getUsername());
		Conta contaEmail = contaDAO.buscarPorEmail(entidade.getEmail());

		if (contaUsername != null) {
			throw new NegocioNordesteException("Já existe um anunciante com este username cadastrado.");
		}

		if (contaEmail != null) {
			throw new NegocioNordesteException("Já existe um anunciante com este username cadastrado.");
		}

		contaDAO.salvar(entidade);
	}

	@Override
	@Transactional
	public Conta atualizar(Conta entidade) throws NegocioNordesteException {
		ContaDAO contaDAO = (ContaDAO) this.dao;

		// Obtendo a conta antiga
		Conta contaAntiga = contaDAO.buscarPorID(entidade.getId());

		// Verificando se houve alteração no username
		if (!contaAntiga.getUsername().equals(entidade.getUsername())) {
			Conta contaUsername = contaDAO.buscarPorUsername(entidade.getUsername());

			if (contaUsername != null) {
				throw new NegocioNordesteException("Já existe um anunciante com este username cadastrado.");
			}
		}
		
		// Verificando se houve alteração no e-mail
		if (!contaAntiga.getEmail().equals(entidade.getEmail())) {
			Conta contaEmail = contaDAO.buscarPorEmail(entidade.getEmail());

			if (contaEmail != null) {
				throw new NegocioNordesteException("Já existe um anunciante com este username cadastrado.");
			}
		}

		return contaDAO.atualizar(entidade);
	}
	
	@Override
	@Transactional
	public void deletar(Conta entidade) throws NegocioNordesteException {
		ContaDAO contaDAO = (ContaDAO) this.dao;
		
		try{
			contaDAO.deletar(entidade);			
		} catch (Exception e) {
			throw new NegocioNordesteException("Não foi óssível excluir a conta.");
		}
		
	}
	
	@Override
	public List<Conta> buscarPorTipo(TipoUsuario tipo) {

		return null;
	}

	@Override
	public Conta buscarPorEmail(String email) {
		return null;
	}

	@Override
	public Conta buscarPorUsername(String username) {
		ContaDAO contaDAO = (ContaDAO) this.dao;

		return contaDAO.buscarPorUsername(username);
	}

	@Override
	public String criptografarSenha(String senha) throws NegocioNordesteException {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-256");
			md.update(senha.getBytes("UTF-8"));
			byte[] digest = md.digest();
			BigInteger bigInt = new BigInteger(1, digest);
			String senhaCriptografada = bigInt.toString(16);
			return senhaCriptografada;
		} catch (NoSuchAlgorithmException e) {
			throw new NegocioNordesteException("Ocorreu um erro ao critografar a senha!");
		} catch (UnsupportedEncodingException e) {
			throw new NegocioNordesteException("Ocorreu um erro ao critografar a senha!");
		}
	}

}