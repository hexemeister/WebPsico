package modelo;

public enum EstadoCivil {

	SOLTEIRO("Solteiro(a)"), NOIVO("Noivo(a)"), CASADO("Casado(a)"), SEPARADO(
			"Separado(a)"), VIUVO("Viúvo(a)");

	String descricao;

	public String getDescricao() {
		return descricao;
	}

	private EstadoCivil(String descricao) {
		this.descricao = descricao;
	}

}
