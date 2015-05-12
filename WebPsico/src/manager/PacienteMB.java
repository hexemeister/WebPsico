package manager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import modelo.Contato;
import modelo.Escolaridade;
import modelo.EstadoCivil;
import modelo.Paciente;
import modelo.Sexo;
import modelo.Turno;
import modelo.Uf;

import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import persistence.PacienteDao;
import config.Util;

@ViewScoped
@Named("pacienteMB")
public class PacienteMB implements Serializable {

	private static final long serialVersionUID = 1L;

	private Paciente pacienteSelecionado = new Paciente();
	private Contato contatoSelecionado = new Contato();

	List<Paciente> listaPaciente;

	public PacienteMB() {
	}

	@PostConstruct
	public void init() {
		listaPaciente = new ArrayList<Paciente>(new PacienteDao().findAll());
	}

	public void atualizar() {
		new PacienteDao().update(pacienteSelecionado);
	}

	public void salvar() {
		new PacienteDao().create(pacienteSelecionado);
	}

	public void prepararNovoPaciente() {
		CommandButton comp = (CommandButton) Util
				.findComponent("pesquisaPaciente:atualizarPacBtn");
		comp.setRendered(false);
		comp = (CommandButton) Util
				.findComponent("pesquisaPaciente:salvarPacBtn");
		comp.setRendered(true);
		pacienteSelecionado = new Paciente();
	}

	public Paciente getPacienteSelecionado() {
		return pacienteSelecionado;
	}

	public Contato getContatoSelecionado() {
		return contatoSelecionado;
	}

	public void setContatoSelecionado(Contato contatoSelecionado) {
		this.contatoSelecionado = contatoSelecionado;
	}

	public void setPacienteSelecionado(Paciente pacienteSelecionado) {
		this.pacienteSelecionado = pacienteSelecionado;
	}

	public List<Paciente> getListaPaciente() {
		return listaPaciente;
	}

	public void setListaPaciente(List<Paciente> listaPaciente) {
		this.listaPaciente = listaPaciente;
	}

	public void onRowSelect(SelectEvent event) {
		UIComponent comp = Util.findComponent("pesquisaPaciente:salvarPacBtn");
		comp.setRendered(false);
		comp = Util.findComponent("pesquisaPaciente:atualizarPacBtn");
		comp.setRendered(true);
		RequestContext context = RequestContext.getCurrentInstance();
		context.update("pesquisaPaciente");
		FacesMessage msg = new FacesMessage("Paciente Selecionado",
				((Paciente) event.getObject()).getNome());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onRowUnselect(UnselectEvent event) {
		pacienteSelecionado = new Paciente();
		UIComponent comp = Util.findComponent("pesquisaPaciente:salvarPacBtn");
		comp.setRendered(true);
		comp = Util.findComponent("pesquisaPaciente:atualizarPacBtn");
		comp.setRendered(false);
		RequestContext context = RequestContext.getCurrentInstance();
		context.update("pesquisaPaciente");
		FacesMessage msg = new FacesMessage("Seleção Limpa",
				((Paciente) event.getObject()).getNome());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onRowContatoSelect(SelectEvent event) {
		try {
			CommandButton comp = (CommandButton) Util.findComponent("formPac:abas:tbContatoAlterarBtn");
			comp.setDisabled(false);
			RequestContext context = RequestContext.getCurrentInstance();
			context.update("gerenciaContato");
			contatoSelecionado = ((Contato) event.getObject());
			FacesMessage msg = new FacesMessage("Contato Selecionado", contatoSelecionado.getNome());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}

	public void onRowContatoUnselect(UnselectEvent event) {
		CommandButton comp = (CommandButton) Util.findComponent("formPac:abas:tbContatoAlterarBtn");
		comp.setDisabled(true);
		RequestContext context = RequestContext.getCurrentInstance();
		context.update("gerenciaContato");
		contatoSelecionado = new Contato();
		FacesMessage msg = new FacesMessage("Seleção Limpa",
				((Contato) event.getObject()).getNome());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public Uf[] getEstados() {
		return Uf.values();
	}

	public EstadoCivil[] getEstadosCivis() {
		return EstadoCivil.values();
	}

	public Sexo[] getSexos() {
		return Sexo.values();
	}

	public Escolaridade[] getEscolaridades() {
		return Escolaridade.values();
	}

	public Turno[] getTurnos() {
		return Turno.values();
	}

}
