package br.edu.ifpb.mt.dac.nn.beans;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.edu.ifpb.mt.dac.nn.util.mensagens.MessageUtils;

@Named
@ViewScoped
public class BuscaBean extends AbstractBean implements Serializable {

	private static final long serialVersionUID = 587624597454565L;

	public void preRenderView() {
		System.out.println("TESTE");
		MessageUtils.messageSucess(getUsuarioLogado());
	}

	public void novo() {

	}

	public void sair() {

	}

}