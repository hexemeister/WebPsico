package modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Past;

import persistence.EntidadeBase;

@Entity
public class Evolucao implements Serializable, EntidadeBase {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String texto;

	@ManyToOne(cascade = CascadeType.ALL, optional = true)
	private Paciente paciente;

	private Usuario psicologa;

	@Past
	@Temporal(TemporalType.DATE)
	private Date data;

	public Evolucao() {
	}

	public Evolucao(Integer id, String texto, Paciente paciente,
			Usuario psicologa, Date data) {
		super();
		this.id = id;
		this.texto = texto;
		this.paciente = paciente;
		this.psicologa = psicologa;
		this.data = data;
	}

	@Override
	public String toString() {
		return "Evolucao [id=" + id + ", texto=" + texto + ", paciente="
				+ paciente + ", psicologa=" + psicologa + ", data=" + data
				+ "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Usuario getPsicologa() {
		return psicologa;
	}

	public void setPsicologa(Usuario psicologa) {
		this.psicologa = psicologa;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

}
