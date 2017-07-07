package br.edu.ifpb.mt.dac.nn.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.edu.ifpb.mt.dac.nn.model.Anunciante;
import br.edu.ifpb.mt.dac.nn.service.AnuncianteService;
import br.edu.ifpb.mt.dac.nn.service.impl.AnuncianteServiceImpl;

@ManagedBean
@ViewScoped
public class AnuncianteCadastroBean {

	private AnuncianteService anuncianteService;
	private Anunciante anunciante;

	public AnuncianteCadastroBean() {
		this.anunciante = new Anunciante();
		this.anuncianteService = new AnuncianteServiceImpl();
	}

	public void cadastrar() {
		anuncianteService.salvar(anunciante);
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