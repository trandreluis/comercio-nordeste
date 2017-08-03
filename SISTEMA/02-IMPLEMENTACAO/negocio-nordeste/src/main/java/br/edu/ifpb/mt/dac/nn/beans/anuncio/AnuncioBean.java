package br.edu.ifpb.mt.dac.nn.beans.anuncio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.mt.dac.nn.beans.AbstractBean;
import br.edu.ifpb.mt.dac.nn.model.Anunciante;
import br.edu.ifpb.mt.dac.nn.model.Anuncio;
import br.edu.ifpb.mt.dac.nn.model.Conta;
import br.edu.ifpb.mt.dac.nn.services.AnuncianteService;
import br.edu.ifpb.mt.dac.nn.services.AnuncioService;
import br.edu.ifpb.mt.dac.nn.services.ContaService;
import br.edu.ifpb.mt.dac.nn.util.mensagens.MessageUtils;

@Named
@ViewScoped
public class AnuncioBean extends AbstractBean implements Serializable {

	private static final long serialVersionUID = 158327433427465L;
	@Inject
	private ContaService contaService;
	@Inject
	private AnuncianteService anuncianteService;
	@Inject
	private AnuncioService anuncioService;
	private Conta conta;
	private Anunciante anunciante;

	public void preRenderView() {
		try {
			conta = contaService.buscarPorUsername(getUsernameUsuarioLogado());
			anunciante = conta.getAnunciante();
		} catch (Exception e) {
			e.printStackTrace();
			MessageUtils.messageSucess("Erro no pre-render-view");
		}
	}

	public void excluirAnuncio(Anuncio anuncio) {
		try {
			
			anuncioService.deletar(anuncio);

			if(anuncioService.buscarPorID(anuncio.getId()) == null) {
				MessageUtils.messageSucess("Anúncio "+anuncio.getTitulo()+" excluído.");				
			} else {
				MessageUtils.messageError("Erro ao excluir anúncio "+anuncio.getTitulo()+".");
			}
			
		} catch(Exception e) {
			MessageUtils.messageError("Erro ao excluir anúncio "+anuncio.getTitulo()+".");
		}
	}
	
	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public Anunciante getAnunciante() {
		return anunciante;
	}

	public void setAnunciante(Anunciante anunciante) {
		this.anunciante = anunciante;
	}
	
}