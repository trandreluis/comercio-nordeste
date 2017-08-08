package br.edu.ifpb.mt.dac.nn.beans;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.mt.dac.nn.exceptions.NegocioNordesteException;
import br.edu.ifpb.mt.dac.nn.googlewebservice.GoogleWebServicesResponse;
import br.edu.ifpb.mt.dac.nn.model.Anunciante;
import br.edu.ifpb.mt.dac.nn.model.Anuncio;
import br.edu.ifpb.mt.dac.nn.model.Cidade;
import br.edu.ifpb.mt.dac.nn.model.Conta;
import br.edu.ifpb.mt.dac.nn.model.Estado;
import br.edu.ifpb.mt.dac.nn.model.Localizacao;
import br.edu.ifpb.mt.dac.nn.services.AnuncioService;
import br.edu.ifpb.mt.dac.nn.services.ContaService;
import br.edu.ifpb.mt.dac.nn.services.impl.DistanciaServiceImpl;
import br.edu.ifpb.mt.dac.nn.util.jsf.JSFUtils;
import br.edu.ifpb.mt.dac.nn.util.mensagens.MessageUtils;

@Named
@ApplicationScoped
public class ResultadoBuscaBean extends AbstractBean implements Serializable {

	private static final long serialVersionUID = 587624597454565L;

	private String textoBotaoNovo;
	private String paginaBotaoNovo = "";
	private String stringDeBusca;
	@Inject
	private ContaService contaService;
	@Inject
	private AnuncioService anuncioService;
	private Conta conta;
	private Anunciante anunciante;
	private List<Estado> estadosNordeste;
	private String idEstadoSelecionado;
	private List<Cidade> cidadesDoEstado;
	private String idCidadeSelecionada;
	private Cidade cidadeSelecionada;
	private Estado estadoSelecionado;
	private List<Anuncio> resultadoBusca;

	public void preRenderView() {
		conta = contaService.buscarPorUsername(getUsernameUsuarioLogado());
		MessageUtils.messageSucess("String de busca: " + stringDeBusca);
		
		try {
			resultadoBusca = anuncioService.buscaPorTag(stringDeBusca);
		} catch (NegocioNordesteException e) {
			MessageUtils.messageError("Houve um erro ao relizar a busca no banco.");
			e.printStackTrace();
		}
		
		if (conta == null) {
			textoBotaoNovo = "Novo";
			paginaBotaoNovo = "/cadastro_anunciante.xhtml?faces-redirect=true";
		} else {
			anunciante = conta.getAnunciante();
			textoBotaoNovo = "Minha conta";
			paginaBotaoNovo = "/paginas/anunciante/anunciante.xhtml?faces-redirect=true";
		}
		
		//TESTE
		DistanciaServiceImpl distancia = new DistanciaServiceImpl();
		Localizacao localizacao = new Localizacao();
		localizacao.setCidade("Sertânia");
		localizacao.setEstado("Pernambuco");
		
		List<Anuncio> anuncios = anuncioService.buscarTodos();
		
		try {
			distancia.buscaOtimizadaPelaDistancia(localizacao, anuncios);
		} catch (NegocioNordesteException e) {
			MessageUtils.messageError("Deu merda na otimização");
		}
		
		//FIM TESTE
		
	}

	public void visualizarAnuncio(Anuncio anuncio) {
		if (anuncio != null) {
			JSFUtils.setParam("anuncio", anuncio);
			JSFUtils.rederTo("/anuncio/visualizar_anuncio.xhtml");
		}
	}

	public Cidade getCidadeSelecionada() {
		return cidadeSelecionada;
	}

	public void setCidadeSelecionada(Cidade cidadeSelecionada) {
		this.cidadeSelecionada = cidadeSelecionada;
	}

	public Estado getEstadoSelecionado() {
		return estadoSelecionado;
	}

	public void setEstadoSelecionado(Estado estadoSelecionado) {
		this.estadoSelecionado = estadoSelecionado;
	}

	public List<Estado> getEstadosNordeste() {
		return estadosNordeste;
	}

	public void setEstadosNordeste(List<Estado> estadosNordeste) {
		this.estadosNordeste = estadosNordeste;
	}

	public String getIdEstadoSelecionado() {
		return idEstadoSelecionado;
	}

	public void setIdEstadoSelecionado(String idEstadoSelecionado) {
		this.idEstadoSelecionado = idEstadoSelecionado;
	}

	public List<Cidade> getCidadesDoEstado() {
		return cidadesDoEstado;
	}

	public void setCidadesDoEstado(List<Cidade> cidadesDoEstado) {
		this.cidadesDoEstado = cidadesDoEstado;
	}

	public String getIdCidadeSelecionada() {
		return idCidadeSelecionada;
	}

	public void setIdCidadeSelecionada(String idCidadeSelecionada) {
		this.idCidadeSelecionada = idCidadeSelecionada;
	}

	public String novo() {
		return paginaBotaoNovo;
	}

	public boolean isLogged() {
		return !getUsernameUsuarioLogado().equals("");
	}

	public String getTextoBotaoNovo() {
		return textoBotaoNovo;
	}

	public void setTextoBotaoNovo(String textoBotaoNovo) {
		this.textoBotaoNovo = textoBotaoNovo;
	}

	public Conta getConta() {
		return this.conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public Anunciante getAnunciante() {
		return anunciante;
	}

	public void setAnunciante(Anunciante anunciante) {
		this.anunciante = anunciante;
	}

	public List<Anuncio> getResultadoBusca() {
		return resultadoBusca;
	}

	public void setResultadoBusca(List<Anuncio> resultadoBusca) {
		this.resultadoBusca = resultadoBusca;
	}

	public String getStringDeBusca() {
		return stringDeBusca;
	}

	public void setStringDeBusca(String stringDeBusca) {
		this.stringDeBusca = stringDeBusca;
	}

}