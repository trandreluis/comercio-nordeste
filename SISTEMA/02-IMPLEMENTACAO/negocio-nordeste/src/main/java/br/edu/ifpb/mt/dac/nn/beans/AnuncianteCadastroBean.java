package br.edu.ifpb.mt.dac.nn.beans;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.mt.dac.nn.exceptions.NegocioNordesteException;
import br.edu.ifpb.mt.dac.nn.model.Anunciante;
import br.edu.ifpb.mt.dac.nn.services.AnuncianteService;
import br.edu.ifpb.mt.dac.nn.util.jsf.JSFUtils;
import br.edu.ifpb.mt.dac.nn.util.mensagens.MessageUtils;

@Named
@ViewScoped
public class AnuncianteCadastroBean implements Serializable {

	private static final long serialVersionUID = 1587624597465L;

	@Inject
	private AnuncianteService anuncianteService;
	
	private Anunciante anunciante;

	public void preRenderView() {
		anunciante = (Anunciante) JSFUtils.getParam("anunciante");
		if (anunciante == null) {
			anunciante = new Anunciante();
		}
	}

	public void cadastrar() {
		try {
			if (isEdicao()) {
				anuncianteService.atualizar(anunciante);
				MessageUtils.messageSucess("Perfil atualizado!");
			} else {
				anuncianteService.salvar(anunciante);
				MessageUtils.messageSucess("Cadastro realizado!");
			}
			
			JSFUtils.rederTo("anunciante.xhtml");
			JSFUtils.setParam("anunciante", anunciante);

		} catch (NegocioNordesteException nne) {
			MessageUtils.messageSucess(nne.getMessage());
		}
	}

	public void cancelar() {
		JSFUtils.rederTo("busca.xhtml");
	}

	public boolean isEdicao() {
		return anunciante.getId() != null;
	}

	public Anunciante getAnunciante() {
		return anunciante;
	}

	public void setAnunciante(Anunciante anunciante) {
		this.anunciante = anunciante;
	}
	
}