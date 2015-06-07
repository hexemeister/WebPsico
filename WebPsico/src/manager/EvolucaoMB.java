package manager;

import java.util.ArrayList;
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

import org.primefaces.context.RequestContext;
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
	private Evolucao evolucaoSelecionada;

	public EvolucaoMB() {
	}

	@PostConstruct
	public void init() {
		logado = loginMB.getLogado();
		listaEvolucoes = new ArrayList<Evolucao>();
	}

	public void onRowSelect(SelectEvent event) {
		pacienteSelecionado = (Paciente) event.getObject();
		FacesMessage msg = new FacesMessage("Paciente Selecionado",
				pacienteSelecionado.getNome());
		FacesContext.getCurrentInstance().addMessage(null, msg);
		if (pacienteSelecionado.getEvolucoes().size()>0) {
			for (Evolucao e : pacienteSelecionado.getEvolucoes()) {
				if (e.getPsicologa().equals(logado)) {
					listaEvolucoes.add(e);
				}
			}
		} else {
			listaEvolucoes = new ArrayList<Evolucao>();
		}
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('evolucaoTabs').select(1)");
		// para depois: dar o focus no editor quando a aba abrir
		evolucaoSelecionada = new Evolucao();
	}

	public void onRowUnselect(UnselectEvent event) {
		pacienteSelecionado = (Paciente) event.getObject();
		FacesMessage msg = new FacesMessage("Seleção Removida",
				pacienteSelecionado.getNome());
		FacesContext.getCurrentInstance().addMessage(null, msg);
		pacienteSelecionado = new Paciente();
		listaEvolucoes = new ArrayList<Evolucao>();
	}

	public void onRowSelectEvolucao(SelectEvent event) {
		evolucaoSelecionada = (Evolucao) event.getObject();
		FacesMessage msg = new FacesMessage("Evolução Selecionada",
				evolucaoSelecionada.getPaciente().getNome());
		FacesContext.getCurrentInstance().addMessage(null, msg);
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('evolucaoTabs').select(1)");
	}

	public void onRowUnselectEvolucao(UnselectEvent event) {
		evolucaoSelecionada = (Evolucao) event.getObject();
		FacesMessage msg = new FacesMessage("Seleção Removida",
				evolucaoSelecionada.getPaciente().getNome());
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

	public Evolucao getEvolucaoSelecionada() {
		return evolucaoSelecionada;
	}

	public void setEvolucaoSelecionada(Evolucao evolucaoSelecionada) {
		this.evolucaoSelecionada = evolucaoSelecionada;
	}

}
