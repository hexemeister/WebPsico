package modelo;

public enum Sexo {

	F("Feminino"), M("Masculino");
	
	String descricao;

	public String getDescricao() {
		return descricao;
	}
	
	private Sexo(String descricao) {
		this.descricao = descricao;
	}

}
