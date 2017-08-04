package br.edu.ifpb.mt.dac.nn.beans;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.mt.dac.nn.exceptions.NegocioNordesteException;
import br.edu.ifpb.mt.dac.nn.model.Anunciante;
import br.edu.ifpb.mt.dac.nn.model.Anuncio;
import br.edu.ifpb.mt.dac.nn.model.Conta;
import br.edu.ifpb.mt.dac.nn.services.AnuncioService;
import br.edu.ifpb.mt.dac.nn.services.ContaService;
import br.edu.ifpb.mt.dac.nn.util.jsf.JSFUtils;
import br.edu.ifpb.mt.dac.nn.util.mensagens.MessageUtils;

@Named
@ViewScoped
public class BuscaBean extends AbstractBean implements Serializable {

	private static final long serialVersionUID = 587624597454565L;

	private String textoBotaoNovo;

	@Inject
	private AnuncioService anuncioService;
	
	private String paginaBotaoNovo = "";
	
	private List<Anuncio> resultadoBusca;

	private String stringDeBusca;
	
	@Inject
	private ContaService contaService;

	private Conta conta;

	private Anunciante anunciante;

	public void preRenderView() {
		conta = contaService.buscarPorUsername(getUsernameUsuarioLogado());
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
		if(stringDeBusca != null) {
			try {
				resultadoBusca = anuncioService.buscarPorTitulo(stringDeBusca);
				List<Anuncio> resultadoBuscaDescricao = anuncioService.buscarPorDescricao(stringDeBusca);
				
				for (Anuncio anuncio : resultadoBuscaDescricao) {
					if(!resultadoBusca.contains(anuncio)) {
						resultadoBusca.add(anuncio);
					}
				}
				
			} catch (NegocioNordesteException e) {
				MessageUtils.messageError("Ocorreu um erro durante a busca.");
			}
		}
	}

	public void visualizarAnuncio(Anuncio anuncio) {
		if(anuncio != null) {
			JSFUtils.setParam("anuncio", anuncio);
			JSFUtils.rederTo("/anuncio/visualizar_anuncio.xhtml");			
		}
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