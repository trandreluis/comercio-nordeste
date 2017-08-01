package br.edu.ifpb.mt.dac.nn.services.impl;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import br.edu.ifpb.mt.dac.nn.dao.VisualizacaoDAO;
import br.edu.ifpb.mt.dac.nn.enumerations.TipoVisualizacao;
import br.edu.ifpb.mt.dac.nn.exceptions.NegocioNordesteException;
import br.edu.ifpb.mt.dac.nn.model.Anuncio;
import br.edu.ifpb.mt.dac.nn.model.Visualizacao;
import br.edu.ifpb.mt.dac.nn.services.VisualizacaoService;

public class VisualizacaoServiceImpl extends GenericServiceImpl<Visualizacao, Long> implements VisualizacaoService {

	private static final long serialVersionUID = 186732134577L;

	public VisualizacaoServiceImpl() {
	}

	@Inject
	public VisualizacaoServiceImpl(VisualizacaoDAO visualizacaoDAO) {
		this.dao = visualizacaoDAO;
	}

	@Override
	public void salvar(Visualizacao entidade) throws NegocioNordesteException {
		// TODO Auto-generated method stub
		super.salvar(entidade);
	}
	
	@Override
	public List<Visualizacao> buscarPorData(Date data) throws NegocioNordesteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Visualizacao> buscarPorTipo(TipoVisualizacao tipo) throws NegocioNordesteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Visualizacao> buscarPorAnuncio(Anuncio anuncio) throws NegocioNordesteException {
		// TODO Auto-generated method stub
		return null;
	}

}