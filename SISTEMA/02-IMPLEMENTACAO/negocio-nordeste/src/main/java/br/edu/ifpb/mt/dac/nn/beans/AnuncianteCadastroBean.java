package br.edu.ifpb.mt.dac.nn.beans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.edu.ifpb.mt.dac.nn.model.Anunciante;
import br.edu.ifpb.mt.dac.nn.service.AnuncianteService;
import br.edu.ifpb.mt.dac.nn.service.impl.AnuncianteServiceImpl;
import br.edu.ifpb.mt.dac.nn.util.MessageUtils;

@ManagedBean
@ViewScoped
public class AnuncianteCadastroBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private AnuncianteService anuncianteService;
	private Anunciante anunciante;

	public AnuncianteCadastroBean() {
		this.anunciante = new Anunciante();
		this.anuncianteService = new AnuncianteServiceImpl();
	}

	public void cadastrar() {
		System.out.println("Chamada");
		anuncianteService.salvar(anunciante);
		MessageUtils.messageSucess("Sucesso!");
	}

	public String cancelar() {
		return "home?faces-redirect=true";
	}

	public Anunciante getAnunciante() {
		return anunciante;
	}

	public void setAnunciante(Anunciante anunciante) {
		this.anunciante = anunciante;
	}

}