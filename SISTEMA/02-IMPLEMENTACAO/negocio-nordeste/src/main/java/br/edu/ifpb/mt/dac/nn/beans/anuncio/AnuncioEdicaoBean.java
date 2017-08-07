package br.edu.ifpb.mt.dac.nn.beans.anuncio;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import br.edu.ifpb.mt.dac.nn.beans.AbstractBean;
import br.edu.ifpb.mt.dac.nn.model.Anunciante;
import br.edu.ifpb.mt.dac.nn.model.Anuncio;
import br.edu.ifpb.mt.dac.nn.model.Cidade;
import br.edu.ifpb.mt.dac.nn.model.Conta;
import br.edu.ifpb.mt.dac.nn.model.Estado;
import br.edu.ifpb.mt.dac.nn.model.Localizacao;
import br.edu.ifpb.mt.dac.nn.services.AnuncioService;
import br.edu.ifpb.mt.dac.nn.services.ContaService;
import br.edu.ifpb.mt.dac.nn.util.jsf.JSFUtils;
import br.edu.ifpb.mt.dac.nn.util.json.LocalizacaoUtils;
import br.edu.ifpb.mt.dac.nn.util.mensagens.MessageUtils;

@Named
@ViewScoped
public class AnuncioEdicaoBean extends AbstractBean implements Serializable {

	private static final long serialVersionUID = 158327624597465L;
	@Inject
	private ContaService contaService;
	@Inject
	private AnuncioService anuncioService;
	private Conta conta;
	private Anunciante anunciante;
	private Anuncio anuncio;
	private List<Estado> estadosNordeste;
	private String idEstadoSelecionado;
	private List<Cidade> cidadesDoEstado;
	private String idCidadeSelecionada;
	private Cidade cidadeSelecionada;
	private Estado estadoSelecionado;
	private UploadedFile imagem;
	private boolean editarLocalizacao;

	public void preRenderView() {
		try {
			conta = contaService.buscarPorUsername(getUsernameUsuarioLogado());
			anunciante = conta.getAnunciante();
			anuncio = (Anuncio) JSFUtils.getParam("anuncio");
			carregarEstadosECidades();
		} catch (Exception e) {
			e.printStackTrace();
			MessageUtils.messageSucess("Erro no pre-render-view");
		}
	}

	public void atualizar() {
		try {
			if (editarLocalizacao) {
				LocalizacaoUtils util = new LocalizacaoUtils();
				Localizacao localizacao = new Localizacao();
				for (Cidade cidade : cidadesDoEstado) {
					if (cidade.getId().equals(idCidadeSelecionada)) {
						Estado estado = util.buscarEstadoPorCodigo(idEstadoSelecionado);
						localizacao.setCidade(cidade.getNome());
						localizacao.setEstado(estado.getNome());
					}
				}
				anuncio.setLocalizacao(localizacao);
			}

			anuncioService.atualizar(anuncio);

			if (anuncio.getId() != null) {
				MessageUtils.messageSucess("Anuncio atualizado.");
			} else {
				MessageUtils.messageError("Erro ao atualizar anúncio.");
			}

			JSFUtils.rederTo("anuncios.xhtml");

		} catch (Exception e) {
			if (imagem == null) {
				MessageUtils.messageError("Por favor, selecione uma imagem para o anúncio.");
			} else {
				MessageUtils.messageError("Erro ao atualizar anúncio.");
			}
		}
	}

	public void carregarCidadesDoEstado() {
		LocalizacaoUtils localizacao = new LocalizacaoUtils();
		estadoSelecionado = localizacao.buscarEstadoPorCodigo(idEstadoSelecionado);
		cidadesDoEstado = localizacao.buscarCidadesPorCodigoDoEstado(estadoSelecionado.getId());
	}

	public void carregarEstadosECidades() {
		LocalizacaoUtils localizacao = new LocalizacaoUtils();
		estadosNordeste = localizacao.buscarEstadosDoNordeste();
		cidadesDoEstado = localizacao.buscarCidadesPorCodigoDoEstado(idEstadoSelecionado);
	}

	public void uploadImagem(FileUploadEvent eventoArquivo) {
		try {
			imagem = eventoArquivo.getFile();
			anuncio.setImagem(imagem.getContents());
			MessageUtils.messageSucess("Imagem enviada.");
		} catch (Exception e) {
			MessageUtils.messageError("Imagem inválida.");
		}
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

	public Cidade getCidadeSelecionada() {
		return cidadeSelecionada;
	}

	public void setCidadeSelecionada(Cidade cidadeSelecionada) {
		this.cidadeSelecionada = cidadeSelecionada;
	}

	public Estado getEstadoSelecionado() {
		return estadoSelecionado;
	}

	public void setEstadoSelecionado(Estado estadoSelecionado) {
		this.estadoSelecionado = estadoSelecionado;
	}

	public List<Cidade> getCidadesDoEstado() {
		return cidadesDoEstado;
	}

	public void setCidadesDoEstado(List<Cidade> cidadesDoEstado) {
		this.cidadesDoEstado = cidadesDoEstado;
	}

	public String getIdEstadoSelecionado() {
		return idEstadoSelecionado;
	}

	public void setIdEstadoSelecionado(String idCidadeSelecionada) {
		this.idEstadoSelecionado = idCidadeSelecionada;
	}

	public String getIdCidadeSelecionada() {
		return idCidadeSelecionada;
	}

	public void setIdCidadeSelecionada(String idCidadeSelecionada) {
		this.idCidadeSelecionada = idCidadeSelecionada;
	}

	public UploadedFile getImagem() {
		return imagem;
	}

	public void setImagem(UploadedFile imagem) {
		this.imagem = imagem;
	}

	public boolean isEditarLocalizacao() {
		return editarLocalizacao;
	}

	public void setEditarLocalizacao(boolean editarLocalizacao) {
		this.editarLocalizacao = editarLocalizacao;
	}

}