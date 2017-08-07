package br.edu.ifpb.mt.dac.nn.beans.anuncio;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.mt.dac.nn.beans.AbstractBean;
import br.edu.ifpb.mt.dac.nn.model.Anunciante;
import br.edu.ifpb.mt.dac.nn.model.Anuncio;
import br.edu.ifpb.mt.dac.nn.model.Conta;
import br.edu.ifpb.mt.dac.nn.services.AnuncianteService;
import br.edu.ifpb.mt.dac.nn.services.AnuncioService;
import br.edu.ifpb.mt.dac.nn.services.ContaService;
import br.edu.ifpb.mt.dac.nn.util.jsf.JSFUtils;
import br.edu.ifpb.mt.dac.nn.util.mensagens.MessageUtils;

@Named
@ViewScoped
public class AnuncioBean extends AbstractBean implements Serializable {

	private static final long serialVersionUID = 158327433427465L;
	@Inject
	private ContaService contaService;
	@Inject
	private AnuncianteService anuncianteService;
	@Inject
	private AnuncioService anuncioService;
	private Conta conta;
	private Anunciante anunciante;

	public void preRenderView() {
		try {
			conta = contaService.buscarPorUsername(getUsernameUsuarioLogado());
			anunciante = conta.getAnunciante();
		} catch (Exception e) {
			e.printStackTrace();
			MessageUtils.messageSucess("Erro no pre-render-view");
		}
	}

	public void excluirAnuncio(Anuncio anuncio) {
		try {
			anuncioService.deletar(anuncio);
			if (anuncioService.buscarPorID(anuncio.getId()) == null) {
				MessageUtils.messageSucess("Anúncio " + anuncio.getTitulo() + " excluído.");
				JSFUtils.rederTo("anuncios.xhtml");
			} else {
				MessageUtils.messageError("Erro ao excluir anúncio " + anuncio.getTitulo() + ".");
			}
		} catch (Exception e) {
			MessageUtils.messageError("Erro ao excluir anúncio " + anuncio.getTitulo() + ".");
		}
	}
	
	public void visualizarAnuncio(Anuncio anuncio) {
		if (anuncioService.buscarPorID(anuncio.getId()) != null) {
			JSFUtils.setParam("anuncio", anuncio);
			JSFUtils.rederTo("visualizar_anuncio.xhtml");
		} else {
			MessageUtils.messageError("Erro ao visualizar anúncio.");
		}
	}

	public void editarAnuncio(Anuncio anuncio) {
		if (anuncioService.buscarPorID(anuncio.getId()) != null) {
			JSFUtils.setParam("anuncio", anuncio);
			JSFUtils.rederTo("editar_anuncio.xhtml");
		} else {
			MessageUtils.messageError("Erro ao editar anúncio.");
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

}