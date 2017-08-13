package br.edu.ifpb.mt.dac.nn.beans;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.mt.dac.nn.enumerations.TipoUsuario;
import br.edu.ifpb.mt.dac.nn.model.Anunciante;
import br.edu.ifpb.mt.dac.nn.model.Anuncio;
import br.edu.ifpb.mt.dac.nn.model.Cidade;
import br.edu.ifpb.mt.dac.nn.model.Conta;
import br.edu.ifpb.mt.dac.nn.model.Estado;
import br.edu.ifpb.mt.dac.nn.services.ContaService;
import br.edu.ifpb.mt.dac.nn.util.jsf.JSFUtils;
import br.edu.ifpb.mt.dac.nn.util.json.LocalizacaoUtils;

@Named
@ViewScoped
public class BuscaBean extends AbstractBean implements Serializable {

	private static final long serialVersionUID = 587624597454565L;

	private String textoBotaoNovo;
	private String paginaBotaoNovo = "";
	private String stringDeBusca;
	@Inject
	private ContaService contaService;
	private Conta conta;
	private Anunciante anunciante;
	private List<Estado> estadosNordeste;
	private String idEstadoSelecionado;
	private List<Cidade> cidadesDoEstado;
	private String idCidadeSelecionada;
	private Cidade cidadeSelecionada;
	private Estado estadoSelecionado;
	private boolean buscaOtimizada;

	public void preRenderView() {
		conta = contaService.buscarPorUsername(getUsernameUsuarioLogado());
		if(conta != null) {
			if(conta.getTipo() == TipoUsuario.ADMIN) {
				conta = null;
			}
		}
		carregarEstadosECidades();
		if (conta == null) {
			textoBotaoNovo = "Novo";
			paginaBotaoNovo = "/cadastro_anunciante.xhtml?faces-redirect=true";
		} else {
			anunciante = conta.getAnunciante();
			textoBotaoNovo = "Minha conta";
			paginaBotaoNovo = "/paginas/anunciante/anunciante.xhtml?faces-redirect=true";
		}
	}

	public void buscar() {
		JSFUtils.rederTo("resultado_busca.xhtml");
	}

	public void visualizarAnuncio(Anuncio anuncio) {
		if (anuncio != null) {
			JSFUtils.setParam("anuncio", anuncio);
			JSFUtils.rederTo("/anuncio/visualizar_anuncio.xhtml");
		}
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

	@Override
	public String getUsernameUsuarioLogado() {
		if(conta == null) {
			return "";
		}
		if(conta.getTipo() == TipoUsuario.ADMIN) {
			return "";
		}
		return super.getUsernameUsuarioLogado();
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

	public String getStringDeBusca() {
		return stringDeBusca;
	}

	public void setStringDeBusca(String stringDeBusca) {
		this.stringDeBusca = stringDeBusca;
	}

	public boolean isBuscaOtimizada() {
		return buscaOtimizada;
	}

	public void setBuscaOtimizada(boolean buscaOtimizada) {
		this.buscaOtimizada = buscaOtimizada;
	}

}