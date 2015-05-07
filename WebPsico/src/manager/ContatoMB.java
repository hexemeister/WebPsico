package manager;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import modelo.Contato;
import modelo.Escolaridade;
import modelo.EstadoCivil;
import modelo.Paciente;
import modelo.Sexo;

@ManagedBean
@RequestScoped
public class ContatoMB implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{PacienteMB.pacienteSelecionado}")
	private Paciente pacienteSelecionado;

	@ManagedProperty(value = "#{PacienteMB.contatoSelecionado}")
	private Contato contatoSelecionado;

	public ContatoMB() {
		// TODO Auto-generated constructor stub
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
