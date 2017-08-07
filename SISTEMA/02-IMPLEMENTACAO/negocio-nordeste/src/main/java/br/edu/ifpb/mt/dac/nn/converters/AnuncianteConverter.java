package br.edu.ifpb.mt.dac.nn.converters;

import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.mt.dac.nn.model.Anunciante;
import br.edu.ifpb.mt.dac.nn.services.AnuncianteService;

@Named
@RequestScoped
@FacesConverter(forClass = Anunciante.class)
public class AnuncianteConverter implements Converter {

	@Inject
	private AnuncianteService anuncianteService;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null || value.trim().isEmpty()) {
			return null;
		}
		Long id = 0l;
		id = Long.parseLong(value);
		return anuncianteService.buscarPorID(id);
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