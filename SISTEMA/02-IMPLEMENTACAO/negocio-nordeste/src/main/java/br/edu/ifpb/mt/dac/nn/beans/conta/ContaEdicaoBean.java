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
			if (camposDeSenhaValidos()) {
				String senhaCriptografada = contaService.criptografarSenha(novaSenha);
				anunciante.getConta().setSenha(senhaCriptografada);
				anuncianteService.atualizar(anunciante);
				MessageUtils.messageSucess("Conta atualizada.");
				MessageUtils.messageWarn("Faça login novamente.");
				LoggedBean.logout();
			} else {
				anuncianteService.atualizar(anunciante);
				MessageUtils.messageSucess("Conta atualizada.");
				MessageUtils.messageWarn("Faça login novamente.");
				LoggedBean.logout();
			}
		} catch (

		Exception e) {
			MessageUtils.messageError("Erro no atualizar");
		}
	}

	public String minhaConta() {
		return null;
	}

	public boolean camposDeSenhaValidos() {
		if (novaSenha.length() != 0 || confirmacaoNovaSenha.length() != 0) {
			if (novaSenha.length() != 0 && confirmacaoNovaSenha.length() != 0) {
				if (!novaSenha.equals(confirmacaoNovaSenha)) {
					MessageUtils.messageWarn("As senhas informadas diferem uma da outra.");
					return false;
				} else {
					return true;
				}
			} else {
				MessageUtils.messageWarn("Informe o campo de senha faltante.");
				return false;
			}
		} else {
			return true;
		}
	}

	public boolean editarSenha() {
		if (novaSenha.length() != 0 && confirmacaoNovaSenha.length() != 0) {
			return true;
		}
		return false;
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