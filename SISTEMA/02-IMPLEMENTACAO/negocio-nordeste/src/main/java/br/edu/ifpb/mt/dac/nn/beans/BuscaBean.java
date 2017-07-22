package br.edu.ifpb.mt.dac.nn.beans;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.mt.dac.nn.model.Anunciante;
import br.edu.ifpb.mt.dac.nn.model.Conta;
import br.edu.ifpb.mt.dac.nn.services.ContaService;
import br.edu.ifpb.mt.dac.nn.util.mensagens.MessageUtils;

@Named
@ViewScoped
public class BuscaBean extends AbstractBean implements Serializable {

	private static final long serialVersionUID = 587624597454565L;

	private String textoBotaoNovo;
	
	private String paginaBotaoNovo = "";

	@Inject
	private ContaService contaService;

	private Conta conta;

	public void preRenderView() {
		conta = contaService.buscarPorUsername(getUsernameUsuarioLogado());
		if (conta == null) {
			textoBotaoNovo = "Novo";
			paginaBotaoNovo = "/cadastro_anunciante.xhtml?faces-redirect=true";
		} else {
			textoBotaoNovo = "Minha conta";
			paginaBotaoNovo = "/paginas/anunciante/anunciante.xhtml?faces-redirect=true";
		}
	}

	public String novo() {
		return paginaBotaoNovo;
	}

	public void sair() {
		
	}

	public boolean isLogged() {
		return conta != null;
	}

	public String getTextoBotaoNovo() {
		return textoBotaoNovo;
	}

	public void setTextoBotaoNovo(String textoBotaoNovo) {
		this.textoBotaoNovo = textoBotaoNovo;
	}

}