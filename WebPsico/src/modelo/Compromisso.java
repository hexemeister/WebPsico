package modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import persistence.EntidadeBase;

@Entity
public class Compromisso implements Serializable, EntidadeBase {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@OneToOne
	@JoinColumn
	private Usuario psicologa;

	@OneToOne
	@JoinColumn
	private Paciente paciente;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataMarcada;

	@Override
	public Integer getId() {
		return id;
	}

	public Usuario getPsicologa() {
		return psicologa;
	}

	public void setPsicologa(Usuario psicologa) {
		this.psicologa = psicologa;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Date getDataMarcada() {
		return dataMarcada;
	}

	public void setDataMarcada(Date dataMarcada) {
		this.dataMarcada = dataMarcada;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}