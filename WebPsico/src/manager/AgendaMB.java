package manager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import modelo.Compromisso;
import modelo.Paciente;
import modelo.Usuario;

import org.primefaces.event.SelectEvent;

import persistence.AgendaDao;
import persistence.PacienteDao;
import persistence.UsuarioDao;

@Named
@ViewScoped
public class AgendaMB implements Serializable {
	private static final long serialVersionUID = 1L;

	private Usuario psicologaSelecionada;
	private List<Usuario> listaPsicologas;
	private Paciente pacienteSelecionado;
	private List<Paciente> listaPacientes;
	private Date dataInicial = new Date();
	private Date dataFinal = new Date();
	private Compromisso compromisso = new Compromisso();
	private List<Compromisso> agenda;

	@PostConstruct
	public void init() {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		Date inicio = c.getTime();
		Date fim = new Date(inicio.getTime() + TimeUnit.DAYS.toMillis(30));

		agenda = new AgendaDao().buscaPorData(inicio, fim);

		if ((agenda == null) || !(agenda.size() > 0)) {
			agenda = new ArrayList<Compromisso>();
		}
	}

	public Usuario getPsicologaSelecionada() {
		return psicologaSelecionada;
	}

	public void setPsicologaSelecionada(Usuario psicologaSelecionada) {
		this.psicologaSelecionada = psicologaSelecionada;
	}

	public List<Usuario> getListaPsicologas(String query) {
		return listaPsicologas = new UsuarioDao().findAllPsicologas(query);
	}

	public void setListaPsicologas(List<Usuario> listaPsicologas) {
		this.listaPsicologas = listaPsicologas;
	}

	public Paciente getPacienteSelecionado() {
		return pacienteSelecionado;
	}

	public void setPacienteSelecionado(Paciente pacienteSelecionado) {
		this.pacienteSelecionado = pacienteSelecionado;
	}

	public List<Paciente> getListaPacientes(String query) {
		return listaPacientes = new PacienteDao()
				.findAllPacientesAtivosByInitial(query);
	}

	public void setListaPacientes(List<Paciente> listaPacientes) {
		this.listaPacientes = listaPacientes;
	}

	public Compromisso getCompromisso() {
		return compromisso;
	}

	public void setCompromisso(Compromisso compromisso) {
		this.compromisso = compromisso;
	}

	public List<Compromisso> getAgenda() {
		return agenda;
	}

	public void setAgenda(List<Compromisso> agenda) {
		this.agenda = agenda;
	}

	public Date getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Date getDataFinal() {
		Calendar c = Calendar.getInstance();
		c.setTime(dataFinal);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		c.set(Calendar.MILLISECOND, 999);
		dataFinal = c.getTime();
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		Calendar c = Calendar.getInstance();
		c.setTime(dataFinal);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		c.set(Calendar.MILLISECOND, 999);
		dataFinal = c.getTime();
		this.dataFinal = dataFinal;
	}

	public Boolean temConflitoCompromisso(Compromisso c) {
		Date inicio = new Date(c.getDataMarcada().getTime()
				+ TimeUnit.MINUTES.toMillis(-50));
		Date fim = new Date(c.getDataMarcada().getTime()
				+ TimeUnit.MINUTES.toMillis(50));
		try {
			List<Compromisso> lista = new AgendaDao().buscaPorData(inicio, fim);
			if (!lista.isEmpty()) {
				for (Compromisso compromisso : lista) {
					if (compromisso.getPsicologa().equals(c.getPsicologa())
							|| compromisso.getPaciente()
									.equals(c.getPaciente())) {
						return true;
					} else {
						return false;
					}
				}
			} else {
				return false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return true;
		}
		return true;

	}

	public void salvarCompromisso() {
		try {
			compromisso.setPaciente(pacienteSelecionado);
			compromisso.setPsicologa(psicologaSelecionada);
			if (!temConflitoCompromisso(compromisso)) {
				compromisso = new AgendaDao().salvar(compromisso);
				FacesMessage msg = new FacesMessage(
						"Sessão agendada com sucesso!",
						pacienteSelecionado.getNome() + " marcado");
				FacesContext.getCurrentInstance().addMessage(null, msg);
				pacienteSelecionado = new Paciente();
				psicologaSelecionada = new Usuario();
				listaCompromissosPorData();
				compromisso = new Compromisso();
			} else {
				FacesMessage msg = new FacesMessage(
						FacesMessage.SEVERITY_ERROR,
						"Agendamento não realizado", "Horário já alocado");
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void onPsicologaSelect(SelectEvent event) {
		psicologaSelecionada = (Usuario) event.getObject();
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage("Psicólogo Selecionado", psicologaSelecionada
						.getLogin()));
	}

	public void onPacienteSelect(SelectEvent event) {
		pacienteSelecionado = (Paciente) event.getObject();
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage("Paciente Selecionado", pacienteSelecionado
						.getNome()));
	}

	public void removerCompromisso(Compromisso compromisso) {
		new AgendaDao().delete(compromisso);
		agenda.remove(compromisso);
		FacesMessage msg = new FacesMessage("Agendamento Cancelado!",
				compromisso.getPaciente().getNome());
		FacesContext.getCurrentInstance().addMessage(null, msg);
		compromisso = new Compromisso();
	}

	public void listaCompromissosPorData() {
		agenda = new AgendaDao().buscaPorData(dataInicial, dataFinal);
	}

}