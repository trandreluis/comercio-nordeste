package br.edu.ifpb.mt.dac.nn.beans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.edu.ifpb.mt.dac.nn.exceptions.NegocioNordesteException;
import br.edu.ifpb.mt.dac.nn.model.Anunciante;
import br.edu.ifpb.mt.dac.nn.services.AnuncianteService;
import br.edu.ifpb.mt.dac.nn.services.impl.AnuncianteServiceImpl;
import br.edu.ifpb.mt.dac.nn.util.jsf.JSFUtils;
import br.edu.ifpb.mt.dac.nn.util.mensagens.MessageUtils;

@ManagedBean
@ViewScoped
public class AnuncianteCadastroBean implements Serializable {

	private static final long serialVersionUID = 1587624597465L;
	private AnuncianteService anuncianteService;
	private Anunciante anunciante;

	public AnuncianteCadastroBean() {
		this.anunciante = new Anunciante();
		this.anuncianteService = new AnuncianteServiceImpl();
	}

	public void cadastrar() {
		try{
			anuncianteService.salvar(anunciante);			
			MessageUtils.messageSucess("Cadastrado realizado com sucesso!");
			JSFUtils.rederTo("home.xhtml");
			JSFUtils.setParam("anunciante", anunciante);
		} catch(NegocioNordesteException e) {
			MessageUtils.messageError(e.getMessage());
		}
	}

	public void cancelar() {
		JSFUtils.rederTo("busca.xhtml");
	}

	public Anunciante getAnunciante() {
		return anunciante;
	}

	public void setAnunciante(Anunciante anunciante) {
		this.anunciante = anunciante;
	}

}