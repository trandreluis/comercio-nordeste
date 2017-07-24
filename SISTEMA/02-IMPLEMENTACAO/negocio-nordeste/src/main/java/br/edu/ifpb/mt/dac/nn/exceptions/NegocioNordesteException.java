package br.edu.ifpb.mt.dac.nn.exceptions;

public class NegocioNordesteException extends Exception {

	private static final long serialVersionUID = 135533555L;

	public NegocioNordesteException(String menssagem) {
		super(menssagem);
	}

	public NegocioNordesteException(String menssagem, Throwable motivo) {
		super(menssagem, motivo);
	}
	
}