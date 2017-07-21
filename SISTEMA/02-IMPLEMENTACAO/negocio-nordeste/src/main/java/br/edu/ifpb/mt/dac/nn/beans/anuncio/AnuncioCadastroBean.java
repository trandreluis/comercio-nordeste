package br.edu.ifpb.mt.dac.nn.beans.anuncio;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.mt.dac.nn.beans.AbstractBean;
import br.edu.ifpb.mt.dac.nn.exceptions.NegocioNordesteException;
import br.edu.ifpb.mt.dac.nn.model.Anunciante;
import br.edu.ifpb.mt.dac.nn.model.Anuncio;
import br.edu.ifpb.mt.dac.nn.services.impl.AnuncianteServiceImpl;
import br.edu.ifpb.mt.dac.nn.util.jsf.JSFUtils;
import br.edu.ifpb.mt.dac.nn.util.mensagens.MessageUtils;

@Named
@ViewScoped
public class AnuncioCadastroBean extends AbstractBean implements Serializable {

	private static final long serialVersionUID = 1587624597465L;

	private Anunciante anunciante;
	
	@Inject
	private AnuncianteServiceImpl anuncianteService;

	private Anuncio anuncio;
	
	public void preRenderView() {
		anunciante = (Anunciante) JSFUtils.getParam("anunciante");
		if (anunciante == null) {
			anunciante = new Anunciante();
			anuncio = new Anuncio();
		}
	}

	public void cadastrar() {
		if(anunciante.getId() != null) {
			try {
				anuncianteService.atualizar(anunciante);
			} catch (NegocioNordesteException e) {
				MessageUtils.messageError(e.getMessage());
			}			
		}
		else {
			MessageUtils.messageSucess("Você precisa estar logado para cadastrar um anúncio.");
			JSFUtils.rederTo("busca.xhtml");
		}
	}
	
	public void cancelar() {
		JSFUtils.rederTo("anunciante.xhtml");
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