package br.edu.ifpb.mt.dac.nn.beans.anunciante;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.mt.dac.nn.beans.AbstractBean;
import br.edu.ifpb.mt.dac.nn.enumerations.TipoUsuario;
import br.edu.ifpb.mt.dac.nn.model.Anunciante;
import br.edu.ifpb.mt.dac.nn.model.Conta;
import br.edu.ifpb.mt.dac.nn.services.AnuncianteService;
import br.edu.ifpb.mt.dac.nn.services.ContaService;
import br.edu.ifpb.mt.dac.nn.util.jsf.JSFUtils;
import br.edu.ifpb.mt.dac.nn.util.mensagens.MessageUtils;

@Named
@ViewScoped
public class AnuncianteCadastroBean extends AbstractBean implements Serializable {

	private static final long serialVersionUID = 1587624597465L;

	@Inject
	private AnuncianteService anuncianteService;

	@Inject
	private ContaService contaService;

	private Anunciante anunciante;

	private Conta conta;

	private String confirmacaoSenha;

	public void preRenderView() {
		try {
			if (conta == null) {
				conta = new Conta();
			}
			if (anunciante == null) {
				anunciante = new Anunciante();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void cadastrar() {

		try {
			conta.setTipo(TipoUsuario.ANUNCIANTE);
			if (conta.getSenha().equals(confirmacaoSenha)) {
				String senhaCriptografada = contaService.criptografarSenha(confirmacaoSenha);
				conta.setSenha(senhaCriptografada);
				anunciante.setConta(conta);
				anuncianteService.salvar(anunciante);
				MessageUtils.messageSucess("Cadastro realizado com sucesso!");
				MessageUtils.messageWarn("Realize login para acessar sua conta.");
				JSFUtils.rederTo("paginas/login.xhtml?faces-redirect=true");
			} else {
				MessageUtils.messageWarn("As senhas não conferem!");
			}
		} catch (Exception e) {
			MessageUtils.messageError(e.getMessage());
		}

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

	public String getSenha() {
		return confirmacaoSenha;
	}

	public void setSenha(String senha) {
		this.confirmacaoSenha = senha;
	}

}