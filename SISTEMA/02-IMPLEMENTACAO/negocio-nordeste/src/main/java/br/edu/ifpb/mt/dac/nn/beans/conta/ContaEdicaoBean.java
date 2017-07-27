package br.edu.ifpb.mt.dac.nn.beans.conta;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.mt.dac.nn.beans.AbstractBean;
import br.edu.ifpb.mt.dac.nn.beans.LoggedBean;
import br.edu.ifpb.mt.dac.nn.model.Anunciante;
import br.edu.ifpb.mt.dac.nn.model.Conta;
import br.edu.ifpb.mt.dac.nn.services.AnuncianteService;
import br.edu.ifpb.mt.dac.nn.services.ContaService;
import br.edu.ifpb.mt.dac.nn.util.mensagens.MessageUtils;

@Named
@ViewScoped
public class ContaEdicaoBean extends AbstractBean implements Serializable {

	private static final long serialVersionUID = 1309627745772597465L;

	@Inject
	private ContaService contaService;

	@Inject
	private AnuncianteService anuncianteService;

	private Conta conta;

	private Anunciante anunciante;

	private String novaSenha;

	private String confirmacaoNovaSenha;

	public void preRenderView() {
		try {
			conta = contaService.buscarPorUsername(getUsernameUsuarioLogado());
			anunciante = conta.getAnunciante();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void atualizar() {
		try {
			anuncianteService.atualizar(anunciante);
			MessageUtils.messageSucess("Conta atualizada.");
			MessageUtils.messageWarn("Faça login novamente.");
			LoggedBean.logout();
		} catch (Exception e) {
			MessageUtils.messageError(e.getMessage());
		}
	}

	public String minhaConta() {
		return null;
	}

	public boolean camposDeSenhaValidos() {
		MessageUtils.messageSucess("" + novaSenha.length());
		return true;
	}

	public boolean editarSenha() {
		if (novaSenha.length() == 0 && confirmacaoNovaSenha.length() == 0) {
			return false;
		}
		return true;
	}

	public String getNovaSenha() {
		return novaSenha;
	}

	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}

	public String getConfirmacaoNovaSenha() {
		return confirmacaoNovaSenha;
	}

	public void setConfirmacaoNovaSenha(String confirmacaoNovaSenha) {
		this.confirmacaoNovaSenha = confirmacaoNovaSenha;
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