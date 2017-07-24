package br.edu.ifpb.mt.dac.nn.beans.anunciante;

import java.io.Serializable;
import java.util.Date;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.mt.dac.nn.beans.AbstractBean;
import br.edu.ifpb.mt.dac.nn.model.Anunciante;
import br.edu.ifpb.mt.dac.nn.model.Conta;
import br.edu.ifpb.mt.dac.nn.services.AnuncianteService;
import br.edu.ifpb.mt.dac.nn.services.ContaService;
import br.edu.ifpb.mt.dac.nn.util.mensagens.MessageUtils;

@Named
@ViewScoped
public class AnuncianteEdicaoBean extends AbstractBean implements Serializable {

	private static final long serialVersionUID = 87624577259765L;

	@Inject
	private ContaService contaService;

	@Inject
	private AnuncianteService anuncianteService;

	private Anunciante anunciante;

	private String nomeAntigo;

	private String sobrenomeAntigo;

	private Date dataNascimentoAntiga;

	private Conta conta;

	public void preRenderView() {
		try {
			conta = contaService.buscarPorUsername(getUsernameUsuarioLogado());
			anunciante = conta.getAnunciante();
			nomeAntigo = anunciante.getNome();
			sobrenomeAntigo = anunciante.getSobrenome();
			dataNascimentoAntiga = anunciante.getDataNascimento();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void atualizar() {
		try {
			if (anunciante.getNome().equals(nomeAntigo) && anunciante.getSobrenome().equals(sobrenomeAntigo)
					&& anunciante.getDataNascimento().equals(dataNascimentoAntiga)) {
				MessageUtils.messageWarn("Nenhum dado alterado.");
			} else {
				anuncianteService.atualizar(anunciante);
				MessageUtils.messageSucess("Perfil atualizado.");
			}
		} catch (Exception e) {
			MessageUtils.messageError(e.getMessage());
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