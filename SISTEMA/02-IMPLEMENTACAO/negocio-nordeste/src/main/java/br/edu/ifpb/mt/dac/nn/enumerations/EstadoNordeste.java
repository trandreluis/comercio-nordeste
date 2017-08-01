package br.edu.ifpb.mt.dac.nn.enumerations;

public enum EstadoNordeste {

	MARANHAO("Maranhão"),
	PIAUI("Piauí"),
	CEARA("Ceará"),
	RIO_GRANDE_DO_NORTE("Rio Grande do Norte"),
	PARAIBA("Paraíba"),
	PERNAMBUCO("Pernambuco"),
	ALAGOAS("Alagoas"),
	SERGIPE("Sergipe"),
	BAHIA("Bahia");

	private final String nome;

	private EstadoNordeste(String nome) {
			this.nome = nome;
		}

	public String getNome() {
		return nome;
	}

}