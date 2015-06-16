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
	private Compromisso compromisso = new Compromisso();
	private List<Compromisso> agenda;

	@PostConstruct
	public void init() {
		Calendar calendar = Calendar.getInstance();
		int firstDay = calendar.getActualMinimum(Calendar.DATE);
		int lastDay = calendar.getActualMaximum(Calendar.DATE);

		calendar.set(Calendar.DATE, firstDay);
		Date inicio = calendar.getTime();

		calendar.set(Calendar.DATE, lastDay);
		Date fim = calendar.getTime();

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

	public Boolean temConflitoCompromisso(Compromisso c) {
		Date inicio = new Date(c.getDataMarcada().getTime()
				+ TimeUnit.HOURS.toMillis(-1));
		Date fim = new Date(c.getDataMarcada().getTime()
				+ TimeUnit.HOURS.toMillis(1));

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
				FacesMessage msg = new FacesMessage("Sessão agendada com sucesso!",
						pacienteSelecionado.getNome() + " marcado");
				FacesContext.getCurrentInstance().addMessage(null, msg);
				pacienteSelecionado = new Paciente();
				psicologaSelecionada = new Usuario();
				compromisso = new Compromisso();
			} else {
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Agendamento não realizado","");
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
				//			if (!agenda.contains(compromisso)) {
				//				agenda.add(compromisso);
				//			} else {
				//				int index = -1;
				//				for (int i = 0; i < agenda.size(); i++) {
				//					if (agenda.get(index).equals(compromisso)) {
				//						index = i;
				//					}
				//				}
				//				if (index >= 0) {
				//					agenda.set(index, compromisso);
				//				}
				//
				//			}
			
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
		FacesMessage msg = new FacesMessage("Agendamento Cancelado!", compromisso.getPaciente().getNome());
		FacesContext.getCurrentInstance().addMessage(null, msg);
		compromisso = new Compromisso();
	}

}