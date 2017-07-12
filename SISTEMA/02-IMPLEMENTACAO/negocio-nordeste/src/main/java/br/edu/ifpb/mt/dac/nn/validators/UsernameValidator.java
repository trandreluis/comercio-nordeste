package br.edu.ifpb.mt.dac.nn.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator(value = "usernameValidator")
public class UsernameValidator implements Validator {

	private Pattern padrao;
	private Matcher matcher;

	private static final String PADRAO_USERNAME = "^(?=.{5,15}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$";
	
	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

		String username = (String) value;

		this.padrao = Pattern.compile(PADRAO_USERNAME);
		this.matcher = this.padrao.matcher(username);

		if (!matcher.matches()) {
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_WARN, "Username inválido!",
					"O username deve ter entre 5 e 15 caracters e deve iniciar e terminar com um caractere alfabético ou alfa numérico."));
		}
		
	}

}