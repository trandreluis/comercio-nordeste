package br.edu.ifpb.mt.dac.nn.enumerations;

public enum NivelAnunciante {

	PESSIMO("Péssimo", 1),
	RUIM("Ruim", 2),
	REGULAR("Regular", 3),
	OTIMO("Ótimo", 4),
	EXCELENTE("Excelente", 5);
	
	private String nivel;
	private int nota;
	
	private NivelAnunciante(String state, int nota) {
		this.nivel = state;
		this.nota = nota;
	}

	public String getNivel() {
		return nivel;
	}
	
	public int getNota() {
		return nota;
	}
	
}