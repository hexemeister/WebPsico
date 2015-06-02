package config;

public class Main {

	public static void main(String[] args) {
		Util.populaBancoComUsuarios();
		Util.populaBancoComContatos();
		Util.populaBancoComPacientes();
		Util.populaBancoComAnamnese();
		Util.populaBancoComEvolucao();
	}
}
