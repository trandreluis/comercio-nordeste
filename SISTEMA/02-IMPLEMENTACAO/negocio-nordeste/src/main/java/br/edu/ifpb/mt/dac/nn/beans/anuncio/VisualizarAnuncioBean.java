package br.edu.ifpb.mt.dac.nn.beans.anuncio;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.mt.dac.nn.beans.AbstractBean;
import br.edu.ifpb.mt.dac.nn.model.Anunciante;
import br.edu.ifpb.mt.dac.nn.model.Anuncio;
import br.edu.ifpb.mt.dac.nn.model.Conta;
import br.edu.ifpb.mt.dac.nn.services.AnuncioService;
import br.edu.ifpb.mt.dac.nn.services.ContaService;
import br.edu.ifpb.mt.dac.nn.util.jsf.JSFUtils;
import br.edu.ifpb.mt.dac.nn.util.mensagens.MessageUtils;

@Named
@ViewScoped
public class VisualizarAnuncioBean extends AbstractBean implements Serializable {

	private static final long serialVersionUID = 158327623345797465L;
	@Inject
	private ContaService contaService;
	@Inject
	private AnuncioService anuncioService;
	private Conta conta;
	private Anunciante anunciante;
	private Anuncio anuncio;

	public void preRenderView() {
		try {
			conta = contaService.buscarPorUsername(getUsernameUsuarioLogado());
			anunciante = conta.getAnunciante();
			anuncio = (Anuncio) JSFUtils.getParam("anuncio");
		} catch (Exception e) {
			e.printStackTrace();
			MessageUtils.messageSucess("Não foi possível visualizar o anúncio.");
		}
	}

	public Conta getConta() {
		return conta;
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

	public Anuncio getAnuncio() {
		return anuncio;
	}

	public void setAnuncio(Anuncio anuncio) {
		this.anuncio = anuncio;
	}

}