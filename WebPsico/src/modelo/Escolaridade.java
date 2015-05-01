package modelo;

public enum Escolaridade {

	ANALFABETO("Analfabeto(a)"), FUNDAMENTAL_INCOMPLETO(
			"Ensino Fundamental Incompleto"), FUNDAMENTAL_COMPLETO(
			"Ensino Fundamental Completo"), MEDIO_INCOMPLETO("Ensino Médio Incompleto"), MEDIO_COMPLETO(
			"Ensino Médio Completo"), SUPERIOR_INCOMPLETO("Ensino Superior Incompleto"), SUPERIOR_COMPLETO(
			"Ensino Superior Completo");

	String descricao;

	public String getDescricao() {
		return descricao;
	}

	private Escolaridade(String descricao) {
		this.descricao = descricao;
	}

}
