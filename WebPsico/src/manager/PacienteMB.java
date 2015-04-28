package manager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import modelo.Paciente;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import persistence.PacienteDao;

@ManagedBean
@RequestScoped
public class PacienteMB implements Serializable{

	private static final long serialVersionUID = 1L;

	Paciente pacienteSelecionado;
	
	List<Paciente> listaPaciente;
	
	public PacienteMB() {
		listaPaciente = new ArrayList<Paciente>(new PacienteDao().findAll());
	}

	public Paciente getPacienteSelecionado() {
		return pacienteSelecionado;
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
        FacesMessage msg = new FacesMessage("Paciente Selecionado", ((Paciente) event.getObject()).getNome());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
 
    public void onRowUnselect(UnselectEvent event) {
    	FacesMessage msg = new FacesMessage("Seleção Limpa", ((Paciente) event.getObject()).getNome());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
	
}
