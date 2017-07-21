package br.edu.ifpb.mt.dac.nn.beans.anunciante;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.mt.dac.nn.beans.AbstractBean;
import br.edu.ifpb.mt.dac.nn.exceptions.NegocioNordesteException;
import br.edu.ifpb.mt.dac.nn.model.Anunciante;
import br.edu.ifpb.mt.dac.nn.services.AnuncianteService;
import br.edu.ifpb.mt.dac.nn.util.jsf.JSFUtils;
import br.edu.ifpb.mt.dac.nn.util.mensagens.MessageUtils;

@Named
@ViewScoped
public class AnuncianteCadastroBean extends AbstractBean implements Serializable {

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
				System.out.println("ATUALIZADO");
				anuncianteService.atualizar(anunciante);
				MessageUtils.messageSucess("Perfil atualizado!");
			} else {
				System.out.println("SALVO");
				anuncianteService.salvar(anunciante);
				MessageUtils.messageSucess("Cadastro realizado!");
				MessageUtils.messageSucess(anunciante.toString());
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