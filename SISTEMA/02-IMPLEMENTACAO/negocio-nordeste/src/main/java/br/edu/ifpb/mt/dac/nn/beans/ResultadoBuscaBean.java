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
import br.edu.ifpb.mt.dac.nn.util.json.LocalizacaoUtils;
import br.edu.ifpb.mt.dac.nn.util.mensagens.MessageUtils;

@Named
@ApplicationScoped
public class ResultadoBuscaBean extends AbstractBean implements Serializable {

	private static final long serialVersionUID = 587624597454565L;

	private String textoBotaoNovo;
	private String paginaBotaoNovo = "";
	private String stringDeBusca;
	private Localizacao localizacao;
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
	private boolean buscaOtimizada;

	public void preRenderView() {
		conta = contaService.buscarPorUsername(getUsernameUsuarioLogado());
		MessageUtils.messageSucess("String de busca: " + stringDeBusca);
		
		resultadoBusca = null;

		try {
			if (buscaOtimizada) {
				
				LocalizacaoUtils util = new LocalizacaoUtils();
				Localizacao localizacao = new Localizacao();
				for (Cidade cidade : cidadesDoEstado) {
					if (cidade.getId().equals(idCidadeSelecionada)) {
						Estado estado = util.buscarEstadoPorCodigo(idEstadoSelecionado);
						localizacao.setCidade(cidade.getNome());
						localizacao.setEstado(estado.getNome());
					}
				}
				
				DistanciaServiceImpl distancia = new DistanciaServiceImpl();
				resultadoBusca = anuncioService.buscaPorTag(stringDeBusca);
				resultadoBusca = distancia.buscaOtimizadaPelaDistancia(localizacao, resultadoBusca);
			} else {
				resultadoBusca = anuncioService.buscaPorTag(stringDeBusca);
			}
		} catch (NegocioNordesteException e) {
			MessageUtils.messageError(e.getMessage());
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

	}
	
	public boolean isBuscaOtimizada() {
		return buscaOtimizada;
	}

	public void setBuscaOtimizada(boolean buscaOtimizada) {
		this.buscaOtimizada = buscaOtimizada;
	}

	public void carregarCidadesDoEstado() {
		LocalizacaoUtils localizacao = new LocalizacaoUtils();
		estadoSelecionado = localizacao.buscarEstadoPorCodigo(idEstadoSelecionado);
		cidadesDoEstado = localizacao.buscarCidadesPorCodigoDoEstado(estadoSelecionado.getId());
	}

	public void carregarEstadosECidades() {
		LocalizacaoUtils localizacao = new LocalizacaoUtils();
		estadosNordeste = localizacao.buscarEstadosDoNordeste();
		if (idEstadoSelecionado == null) {
			cidadesDoEstado = localizacao.buscarCidadesPorCodigoDoEstado("2");
		} else {
			cidadesDoEstado = localizacao.buscarCidadesPorCodigoDoEstado(idEstadoSelecionado);
		}
	}
	
	public String visualizarAnuncio(Anuncio anuncio) {
//		if (anuncio != null) {
		MessageUtils.messageSucess("Renderizando anuncio "+anuncio.getTitulo());
			JSFUtils.setParam("anuncio", anuncio);
			return "/anuncio/visualizar_anuncio.xhtml?faces-redirect=true";
//		}
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

	public Localizacao getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(Localizacao localizacao) {
		this.localizacao = localizacao;
	}

}