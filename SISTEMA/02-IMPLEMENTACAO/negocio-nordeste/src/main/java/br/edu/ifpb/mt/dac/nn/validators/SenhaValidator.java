package br.edu.ifpb.mt.dac.nn.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;

@Named
@RequestScoped
@FacesValidator(value = "senhaValidator")
public class SenhaValidator implements Validator {

	private Pattern padrao;
	private Matcher matcher;

	private static final String PADRAO_SENHA = "^(?=.{5,15}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$";

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

		String senha = (String) value;

		this.padrao = Pattern.compile(PADRAO_SENHA);
		this.matcher = this.padrao.matcher(senha);

		if (!matcher.matches() && senha.length() > 0) {
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_WARN, "Senha inválida!",
					"A senha deve ter entre 5 e 15 caracters e deve iniciar e terminar com um caractere alfabético ou alfa numérico."));
		}

	}

}