package br.edu.ifpb.mt.dac.nn.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator(value = "emailValidator")
public class EmailValidator implements Validator {

	private Pattern padrao;
	private Matcher matcher;

	private static final String PADRAO_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

		String email = (String) value;

		this.padrao = Pattern.compile(PADRAO_EMAIL);
		this.matcher = this.padrao.matcher(email);

		if (!matcher.matches()) {
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_WARN, "E-mail inválido!",
					"O e-email informado deve seguir o seguinte padrão email@mail.com ou email@mail.com.br."));
		}

	}

}