package modelo;

public enum Turno {

	MANHA("Manhã"), TARDE("Tarde"), NOITE("Noite");

	String descricao;

	public String getDescricao() {
		return descricao;
	}

	private Turno(String descricao) {
		this.descricao = descricao;
	}
}
