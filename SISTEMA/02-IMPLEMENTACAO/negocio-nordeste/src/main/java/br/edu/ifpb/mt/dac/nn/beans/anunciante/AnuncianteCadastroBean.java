package br.edu.ifpb.mt.dac.nn.beans.anunciante;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.mt.dac.nn.beans.AbstractBean;
import br.edu.ifpb.mt.dac.nn.exceptions.NegocioNordesteException;
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

	public void preRenderView() {
		try {
			anunciante = new Anunciante();
			conta = contaService.buscarPorUsername(getUsernameUsuarioLogado());
			if(conta == null){
				MessageUtils.messageSucess("Conta nula");				
			}
			anunciante = conta.getAnunciante();
		} catch(Exception e) {
			e.printStackTrace();
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
		anunciante = new Anunciante();
		return anunciante.getId() != null;
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