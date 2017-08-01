package br.edu.ifpb.mt.dac.nn.beans.anuncio;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.mt.dac.nn.beans.AbstractBean;
import br.edu.ifpb.mt.dac.nn.model.Anunciante;
import br.edu.ifpb.mt.dac.nn.model.Anuncio;
import br.edu.ifpb.mt.dac.nn.model.Conta;
import br.edu.ifpb.mt.dac.nn.model.Estado;
import br.edu.ifpb.mt.dac.nn.services.AnuncianteService;
import br.edu.ifpb.mt.dac.nn.services.ContaService;
import br.edu.ifpb.mt.dac.nn.util.json.LocalizacaoUtils;
import br.edu.ifpb.mt.dac.nn.util.mensagens.MessageUtils;

@Named
@ViewScoped
public class AnuncioCadastroBean extends AbstractBean implements Serializable {

	private static final long serialVersionUID = 158327624597465L;

	@Inject
	private ContaService contaService;

	@Inject
	private AnuncianteService anuncianteService;

	private Conta conta;

	private Anunciante anunciante;

	private Anuncio anuncio;

	private List<Estado> estadosNordeste;

	public void preRenderView() {
		try {
			conta = contaService.buscarPorUsername(getUsernameUsuarioLogado());
			anunciante = conta.getAnunciante();
			carregarEstados();
			MessageUtils.messageSucess("Estados carregados.");
		} catch (Exception e) {
			e.printStackTrace();
			MessageUtils.messageSucess("Erro no pre-render-view");
		}
	}

	private void carregarEstados() {
		LocalizacaoUtils localizacao = new LocalizacaoUtils();
		estadosNordeste = localizacao.buscarEstadosDoNordeste();
	}

	public void cadastrar() {
		MessageUtils.messageSucess("Cadastrar()");
	}

	public void uploadImagem() {
		MessageUtils.messageSucess("Olha a imgem!");
	}

	public String cancelar() {
		return null;
	}

	public Anunciante getAnunciante() {
		return anunciante;
	}

	public void setAnunciante(Anunciante anunciante) {
		this.anunciante = anunciante;
	}

	public Anuncio getAnuncio() {
		return anuncio;
	}

	public void setAnuncio(Anuncio anuncio) {
		this.anuncio = anuncio;
	}

	public List<Estado> getEstadosNordeste() {
		return estadosNordeste;
	}

	public void setEstadosNordeste(List<Estado> estados) {
		this.estadosNordeste = estados;
	}

}