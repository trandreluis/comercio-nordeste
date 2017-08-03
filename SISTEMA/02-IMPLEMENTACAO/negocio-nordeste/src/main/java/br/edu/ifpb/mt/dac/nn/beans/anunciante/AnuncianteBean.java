package br.edu.ifpb.mt.dac.nn.beans.anunciante;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.mt.dac.nn.beans.AbstractBean;
import br.edu.ifpb.mt.dac.nn.model.Anunciante;
import br.edu.ifpb.mt.dac.nn.model.Conta;
import br.edu.ifpb.mt.dac.nn.services.ContaService;
import br.edu.ifpb.mt.dac.nn.util.mensagens.MessageUtils;

@Named
@ViewScoped
public class AnuncianteBean extends AbstractBean implements Serializable {

	private static final long serialVersionUID = 15876245772597465L;

	@Inject
	private ContaService contaService;

	private Anunciante anunciante;

	private Conta conta;

	public void preRenderView() {
		try {
			conta = contaService.buscarPorUsername(getUsernameUsuarioLogado());
			if(conta == null) {
				MessageUtils.messageSucess("Conta está sendo nula");
			}
			anunciante = conta.getAnunciante();
		} catch (Exception e) {
			e.printStackTrace();
			MessageUtils.messageError("Error no pre-render-view.");
		}
	}

	public String minhaConta() {
		return null;
	}

	public Anunciante getAnunciante() {
		return anunciante;
	}

	public void setAnunciante(Anunciante anunciante) {
		this.anunciante = anunciante;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

}