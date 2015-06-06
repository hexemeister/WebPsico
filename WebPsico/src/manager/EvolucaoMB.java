package manager;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import modelo.Evolucao;
import modelo.Paciente;
import modelo.Usuario;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import persistence.PacienteDao;

@Named
@ViewScoped
public class EvolucaoMB {

	@Inject
	LoginMB loginMB;

	Usuario logado;

	private List<Paciente> listaPaciente;
	private Paciente pacienteSelecionado;
	private List<Evolucao> listaEvolucoes;
	private Evolucao evolucao;

	public EvolucaoMB() {
	}

	@PostConstruct
	public void init() {
		logado = loginMB.getLogado();
	}

	public void onRowSelect(SelectEvent event) {
		pacienteSelecionado = (Paciente) event.getObject();
		FacesMessage msg = new FacesMessage("Paciente Selecionado",
				pacienteSelecionado.getNome());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onRowUnselect(UnselectEvent event) {
		pacienteSelecionado = (Paciente) event.getObject();
		FacesMessage msg = new FacesMessage("Seleção Removida",
				pacienteSelecionado.getNome());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public List<Paciente> getListaPaciente() {
		listaPaciente = new PacienteDao().findAllPacientesAtivos();
		return listaPaciente;
	}

	public void setListaPaciente(List<Paciente> listaPaciente) {
		this.listaPaciente = listaPaciente;
	}

	public Paciente getPacienteSelecionado() {
		return pacienteSelecionado;
	}

	public void setPacienteSelecionado(Paciente pacienteSelecionado) {
		this.pacienteSelecionado = pacienteSelecionado;
	}

	public List<Evolucao> getListaEvolucoes() {
		return listaEvolucoes;
	}

	public void setListaEvolucoes(List<Evolucao> listaEvolucoes) {
		this.listaEvolucoes = listaEvolucoes;
	}

	public Evolucao getEvolucao() {
		return evolucao;
	}

	public void setEvolucao(Evolucao evolucao) {
		this.evolucao = evolucao;
	}

}
