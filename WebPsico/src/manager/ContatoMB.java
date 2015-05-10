package manager;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import modelo.Contato;
import modelo.Escolaridade;
import modelo.EstadoCivil;
import modelo.Paciente;
import modelo.Sexo;

@ViewScoped
@Named("contatoMB")
public class ContatoMB implements Serializable {

	private static final long serialVersionUID = 1L;

	private Paciente pacienteSelecionado;
	private Contato contatoSelecionado;

	public ContatoMB() {
	contatoSelecionado = (Contato) FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get("contato");		
	}

	public Paciente getPacienteSelecionado() {
		return pacienteSelecionado;
	}

	public void setPacienteSelecionado(Paciente pacienteSelecionado) {
		this.pacienteSelecionado = pacienteSelecionado;
	}

	public Contato getContatoSelecionado() {
		return contatoSelecionado;
	}

	public void setContatoSelecionado(Contato contatoSelecionado) {
		this.contatoSelecionado = contatoSelecionado;
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
}
