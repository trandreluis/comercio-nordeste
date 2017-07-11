package br.edu.ifpb.mt.dac.nn.converters;

import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import br.edu.ifpb.mt.dac.nn.model.Anunciante;
import br.edu.ifpb.mt.dac.nn.services.AnuncianteService;
import br.edu.ifpb.mt.dac.nn.services.impl.AnuncianteServiceImpl;

@RequestScoped
@FacesConverter(forClass=Anunciante.class)
public class AnuncianteConverter implements Converter {

	private AnuncianteService anuncianteService;
	
	public AnuncianteConverter() {
		this.anuncianteService = new AnuncianteServiceImpl();
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if (value == null || value.trim().isEmpty()) {
			return null;
		}
		Long id = 0l;
		try {
			id = Long.parseLong(value);
			return anuncianteService.buscarPorID(id);
		} catch (NumberFormatException e) {
			throw new ConverterException(new FacesMessage(String.format(
					"%s é invalido para o anunciante", id)), e);
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value == null) {
			return null;
		}
		Long id = ((Anunciante) value).getId();
		return (id != null) ? id.toString() : null;
	}

}