package modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import persistence.EntidadeBase;

@Entity
public class Evolucao implements Serializable, EntidadeBase,
		Comparable<Evolucao> {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 500)
	private String texto;

	@ManyToOne(cascade = {
			CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH }, optional = true)
	private Paciente paciente;

	private Usuario psicologa;

	@Temporal(TemporalType.TIMESTAMP)
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

	@Override
	public int compareTo(Evolucao o) {
		return this.data.compareTo(o.getData());
	}

}
