package manager;

import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import modelo.Evolucao;
import modelo.Paciente;

@Named
@ViewScoped
public class EvolucaoMB {

	private List<Paciente> listaPacientes;
	private Paciente pacienteSelecionado;
	private List<Evolucao> listaEvolucoes;
	private Evolucao evolucao;
	
	public EvolucaoMB() {
	}

	
	
}
