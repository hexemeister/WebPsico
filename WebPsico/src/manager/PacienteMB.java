package manager;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import persistence.PacienteDao;
import modelo.Paciente;

@ManagedBean
@RequestScoped
public class PacienteMB {

	Paciente pacienteSelecionado;
	
	DataModel<Paciente> listaPaciente;
	
	public PacienteMB() {
		listaPaciente = new ListDataModel<Paciente>(new PacienteDao().findAll());
	}

	public Paciente getPacienteSelecionado() {
		return pacienteSelecionado;
	}

	public void setPacienteSelecionado(Paciente pacienteSelecionado) {
		this.pacienteSelecionado = pacienteSelecionado;
	}

	public DataModel<Paciente> getListaPaciente() {
		return listaPaciente;
	}

	public void setListaPaciente(DataModel<Paciente> listaPaciente) {
		this.listaPaciente = listaPaciente;
	}
	
	
	
}
