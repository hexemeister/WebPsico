package manager;

import java.io.Serializable;
import java.text.SimpleDateFormat;
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

import persistence.ContatoDao;
import persistence.PacienteDao;
import config.Util;

@ViewScoped
@Named("pacienteMB")
public class PacienteMB implements Serializable {

	private static final long serialVersionUID = 1L;

	private Paciente pacienteSelecionado = new Paciente();
	private Contato contatoSelecionado = new Contato();

	private List<Paciente> listaPaciente;
	private List<Contato> listaContato;
	private List<Paciente> pacientesFiltrados;

	private Boolean desabilitaAbaContato = Boolean.TRUE;

	public PacienteMB() {
	}

	@PostConstruct
	public void init() {
		listaPaciente = new ArrayList<Paciente>(new PacienteDao().findAll());
		listaContato = pacienteSelecionado.getContatos();
	}

	public void atualizarPaciente() {
		new PacienteDao().update(pacienteSelecionado);
		FacesMessage msg = new FacesMessage("Paciente Atualizado",
				pacienteSelecionado.getNome());
		FacesContext.getCurrentInstance().addMessage(null, msg);
		RequestContext context = RequestContext.getCurrentInstance();
		context.update(":pesquisaPaciente:tabelaPaciente");
	}

	public void salvarPaciente() {
		// novo Paciente
		pacienteSelecionado = new PacienteDao()
				.createAndGetId(pacienteSelecionado);
		listaPaciente.add(pacienteSelecionado);
		FacesMessage msg = new FacesMessage("Paciente salvo",
				pacienteSelecionado.getNome());
		FacesContext.getCurrentInstance().addMessage(null, msg);
		RequestContext context = RequestContext.getCurrentInstance();
		context.update(":pesquisaPaciente:tabelaPaciente");
	}

	public void prepararNovoPaciente() {
		listaContato = new ArrayList<Contato>();
		CommandButton comp = (CommandButton) Util
				.findComponent("formPac:atualizarPacBtn");
		comp.setRendered(false);
		comp = (CommandButton) Util.findComponent("formPac:salvarPacBtn");
		comp.setRendered(true);
		CommandButton comp2 = (CommandButton) Util
				.findComponent("formPac:abas:contatoNovoBtn");
		comp2.setDisabled(true);
		FacesMessage msg = new FacesMessage("Seleção Limpa",
				pacienteSelecionado.getNome() == null ? ""
						: pacienteSelecionado.getNome());
		FacesContext.getCurrentInstance().addMessage(null, msg);
		pacienteSelecionado = new Paciente();
		setDesabilitaAbaContato(Boolean.TRUE);
	}

	public void prepararNovoContato() {
		contatoSelecionado = new Contato();
		CommandButton comp = (CommandButton) Util
				.findComponent("formPac:abas:tbContatoAlterarBtn");
		comp.setDisabled(true);
		comp = (CommandButton) Util
				.findComponent("formPac:abas:apagarContatoBtn");
		comp.setDisabled(true);
		RequestContext context = RequestContext.getCurrentInstance();
		context.update(":formPac:abas:tbcontatos");
		context.update("formPac:abas:tb");
		context.update("formContato");
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

	public List<Paciente> getPacientesFiltrados() {
		return pacientesFiltrados;
	}

	public void setPacientesFiltrados(List<Paciente> pacientesFiltrados) {
		this.pacientesFiltrados = pacientesFiltrados;
	}

	public List<Contato> getListaContato() {
		return listaContato;
	}

	public void setListaContato(List<Contato> listaContato) {
		this.listaContato = listaContato;
	}

	public Boolean getDesabilitaAbaContato() {
		return desabilitaAbaContato;
	}

	public void setDesabilitaAbaContato(Boolean desabilitaAbaContato) {
		this.desabilitaAbaContato = desabilitaAbaContato;
	}

	public void criaContato() {
		// novo contato
		if (contatoSelecionado.getId() == null) {
			try {
				contatoSelecionado = new ContatoDao()
						.salvar(contatoSelecionado);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			List<Paciente> lista = new ArrayList<>(
					contatoSelecionado.getPacientes());
			lista.add(pacienteSelecionado);
			contatoSelecionado.setPacientes(lista);

			List<Contato> lista2 = new ArrayList<>(
					pacienteSelecionado.getContatos());
			lista2.add(contatoSelecionado);
			pacienteSelecionado.setContatos(lista2);

			new ContatoDao().update(contatoSelecionado);
			listaContato.add(contatoSelecionado);

			// altera contato
		} else {
			new ContatoDao().update(contatoSelecionado,
					contatoSelecionado.getId());
		}
		FacesMessage msg = new FacesMessage("Contato Salvo",
				contatoSelecionado.getNome());
		FacesContext.getCurrentInstance().addMessage(null, msg);
		RequestContext context = RequestContext.getCurrentInstance();
		context.update(":formPac:abas:tbcontatos");
		context.execute("PF('contatodlg').hide()");
	}

	public void apagaContato() {
		new ContatoDao().delete(contatoSelecionado);
		listaContato.remove(contatoSelecionado);
		FacesMessage msg = new FacesMessage("Contato Apagado",
				contatoSelecionado.getNome());
		FacesContext.getCurrentInstance().addMessage(null, msg);
		CommandButton comp = (CommandButton) Util
				.findComponent("formPac:abas:apagarContatoBtn");
		comp.setDisabled(true);
		contatoSelecionado = new Contato();
		RequestContext context = RequestContext.getCurrentInstance();
		context.update("gerenciaContato");
	}

	public void onDateSelect(SelectEvent event) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		facesContext.addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected",
						format.format(event.getObject())));
	}

	public void onRowSelect(SelectEvent event) {
		listaContato = pacienteSelecionado.getContatos();
		UIComponent comp = Util.findComponent("formPac:salvarPacBtn");
		comp.setRendered(false);
		comp = Util.findComponent("formPac:atualizarPacBtn");
		comp.setRendered(true);
		CommandButton comp2 = (CommandButton) Util
				.findComponent("formPac:abas:contatoNovoBtn");
		comp2.setDisabled(false);
		setDesabilitaAbaContato(Boolean.FALSE);
		RequestContext context = RequestContext.getCurrentInstance();
		context.update("formPac");
		FacesMessage msg = new FacesMessage("Paciente Selecionado",
				((Paciente) event.getObject()).getNome());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onRowUnselect(UnselectEvent event) {
		pacienteSelecionado = new Paciente();
		listaContato = new ArrayList<Contato>();
		UIComponent comp = Util.findComponent("formPac:salvarPacBtn");
		comp.setRendered(true);
		comp = Util.findComponent("formPac:atualizarPacBtn");
		comp.setRendered(false);
		CommandButton comp2 = (CommandButton) Util
				.findComponent("formPac:abas:contatoNovoBtn");
		comp2.setDisabled(true);
		RequestContext context = RequestContext.getCurrentInstance();
		context.update("formPac");
		setDesabilitaAbaContato(Boolean.TRUE);
		FacesMessage msg = new FacesMessage("Seleção Limpa",
				((Paciente) event.getObject()).getNome());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onRowContatoSelect(SelectEvent event) {
		try {
			CommandButton comp = (CommandButton) Util
					.findComponent("formPac:abas:tbContatoAlterarBtn");
			comp.setDisabled(false);
			comp = (CommandButton) Util
					.findComponent("formPac:abas:apagarContatoBtn");
			comp.setDisabled(false);
			RequestContext context = RequestContext.getCurrentInstance();
			context.update("gerenciaContato");
			contatoSelecionado = ((Contato) event.getObject());
			FacesMessage msg = new FacesMessage("Contato Selecionado",
					contatoSelecionado.getNome());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}

	public void onRowContatoUnselect(UnselectEvent event) {
		CommandButton comp = (CommandButton) Util
				.findComponent("formPac:abas:tbContatoAlterarBtn");
		comp.setDisabled(true);
		comp = (CommandButton) Util
				.findComponent("formPac:abas:apagarContatoBtn");
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
