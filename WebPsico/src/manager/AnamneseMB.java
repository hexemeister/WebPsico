package manager;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import persistence.AnamneseDao;
import persistence.PacienteDao;
import modelo.Anamnese;
import modelo.Paciente;
import modelo.Usuario;

@Named
@ViewScoped
public class AnamneseMB implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	LoginMB loginMB;

	Usuario logado;

	private List<Paciente> listaPaciente;
	private Paciente pacienteSelecionado;
	private Anamnese anamnese;

	@PostConstruct
	public void init() {
		logado = loginMB.getLogado();
	}

	public AnamneseMB() {
	}

	public void onRowSelect(SelectEvent event) {
		pacienteSelecionado = (Paciente) event.getObject();
		FacesMessage msg = new FacesMessage("Paciente Selecionado",
				pacienteSelecionado.getNome());
		if (pacienteSelecionado.getAnamneses().size()>0) {
			for (Anamnese a : pacienteSelecionado.getAnamneses()) {
				if (a.getPsicologa().equals(logado)) {
					anamnese = a;
				}
			}
		} else {
			anamnese = new Anamnese();
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('anamneseTab').select(1)");
	}

	public void onRowUnselect(UnselectEvent event) {
		FacesMessage msg = new FacesMessage("Seleção Removida",
				pacienteSelecionado.getNome());
		pacienteSelecionado = new Paciente();
		anamnese = new Anamnese();
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void salvarAnamnese() {
		anamnese.setPsicologa(logado);
		anamnese.setPaciente(pacienteSelecionado);
		anamnese = new AnamneseDao().update(anamnese);
		FacesMessage msg = new FacesMessage("Anamnese Salva",
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

	public Usuario getLogado() {
		return logado;
	}

	public void setLogado(Usuario logado) {
		this.logado = logado;
	}

	public Anamnese getAnamnese() {
		return anamnese;
	}

	public void setAnamnese(Anamnese anamnese) {
		this.anamnese = anamnese;
	}

}
