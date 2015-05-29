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

	@Inject
	private List<Paciente> listaPacientes;
	@Inject
	private Paciente pacienteSelecionado;
	@Inject
	private List<Evolucao> listaEvolucoes;
	@Inject
	private Evolucao evolucao;
	
	public EvolucaoMB() {
	}

	
	
}
