package br.edu.ifpb.mt.dac.nn.enumerations;

/**
 * 
 * @author <a href="https://github.com/FranckAJ">Franck Aragão</a>
 *
 */
public enum MessagesType {

	SUCESS("Sucesso! "), ERROR("Erro! "), WARNING("Atenção! ");

	private String message;

	MessagesType(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
