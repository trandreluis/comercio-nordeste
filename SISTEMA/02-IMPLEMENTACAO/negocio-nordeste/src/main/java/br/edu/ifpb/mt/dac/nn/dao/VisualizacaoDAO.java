package br.edu.ifpb.mt.dac.nn.dao;

import java.util.Date;
import java.util.List;

import br.edu.ifpb.mt.dac.nn.enumerations.TipoVisualizacao;
import br.edu.ifpb.mt.dac.nn.exceptions.NegocioNordesteException;
import br.edu.ifpb.mt.dac.nn.model.Anuncio;
import br.edu.ifpb.mt.dac.nn.model.Visualizacao;

public interface VisualizacaoDAO extends GenericDAO<Visualizacao, Long> {

	List<Visualizacao> buscarPorData(Date data) throws NegocioNordesteException;

	List<Visualizacao> buscarPorTipo(TipoVisualizacao tipo) throws NegocioNordesteException;

	List<Visualizacao> buscarPorAnuncio(Anuncio anuncio) throws NegocioNordesteException;

}