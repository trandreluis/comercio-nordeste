package br.edu.ifpb.mt.dac.nn.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator(value = "sobrenomeValidator")
public class SobrenomeValidator implements Validator {

	private Pattern padrao;
	private Matcher matcher;
	
	private static final String PADRAO_NOME = "^(([a-zA-Z ]|[é])*)$";
	
	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

		String nome = (String) value;

		this.padrao = Pattern.compile(PADRAO_NOME);
		this.matcher = this.padrao.matcher(nome);

		if (!matcher.matches()) {
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_WARN, "Username inválido!",
					"O sobrenome deve ter somente caracters albfabéticos com ou sem acento e/ou espaços."));
		}				
		
	}

}