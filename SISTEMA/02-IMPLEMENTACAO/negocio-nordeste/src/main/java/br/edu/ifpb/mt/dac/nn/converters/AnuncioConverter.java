package br.edu.ifpb.mt.dac.nn.converters;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.mt.dac.nn.model.Anunciante;
import br.edu.ifpb.mt.dac.nn.model.Anuncio;
import br.edu.ifpb.mt.dac.nn.services.AnuncioService;

@Named
@RequestScoped
@FacesConverter(forClass = Anuncio.class)
public class AnuncioConverter implements Converter {

	@Inject
	private AnuncioService anuncioService;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null || value.trim().isEmpty()) {
			return null;
		}
		Long id = 0l;
		try {
			id = Long.parseLong(value);
			return anuncioService.buscarPorID(id);
		} catch (NumberFormatException e) {
			throw new ConverterException(new FacesMessage(String.format("%s é invalido para o anunciante", id)), e);
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value == null) {
			return null;
		}
		Long id = ((Anunciante) value).getId();
		return (id != null) ? id.toString() : null;
	}

}