package modelo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Telefone implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idTelefone;

	@ManyToMany(mappedBy = "telefones")
	private List<Paciente> pacientes;

	@ManyToMany(mappedBy = "telefones")
	
	private List<Contato> contatos;
	
	private String ddd;
	private String telefone;

	@Enumerated(EnumType.STRING)
	private TipoTelefone tipoTelefone;

	public Telefone() {
	}

	public Telefone(Integer idTelefone, List<Paciente> pacientes, String ddd,
			String telefone, TipoTelefone tipoTelefone) {
		super();
		this.idTelefone = idTelefone;
		this.pacientes = pacientes;
		this.ddd = ddd;
		this.telefone = telefone;
		this.tipoTelefone = tipoTelefone;
	}

	public Telefone(Integer idTelefone, String ddd, String telefone,
			TipoTelefone tipoTelefone) {
		super();
		this.idTelefone = idTelefone;
		this.ddd = ddd;
		this.telefone = telefone;
		this.tipoTelefone = tipoTelefone;
	}

	public Integer getIdTelefone() {
		return idTelefone;
	}

	public void setIdTelefone(Integer idTelefone) {
		this.idTelefone = idTelefone;
	}

	public List<Paciente> getPacientes() {
		return pacientes;
	}

	public void setPacientes(List<Paciente> pacientes) {
		this.pacientes = pacientes;
	}

	public String getDdd() {
		return ddd;
	}

	public void setDdd(String ddd) {
		this.ddd = ddd;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public TipoTelefone getTipoTelefone() {
		return tipoTelefone;
	}

	public void setTipoTelefone(TipoTelefone tipoTelefone) {
		this.tipoTelefone = tipoTelefone;
	}

	@Override
	public String toString() {
		return "Telefone [idTelefone=" + idTelefone + ", pacientes="
				+ pacientes + ", ddd=" + ddd + ", telefone=" + telefone
				+ ", tipoTelefone=" + tipoTelefone + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ddd == null) ? 0 : ddd.hashCode());
		result = prime * result
				+ ((telefone == null) ? 0 : telefone.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Telefone other = (Telefone) obj;
		if (ddd == null) {
			if (other.ddd != null)
				return false;
		} else if (!ddd.equals(other.ddd))
			return false;
		if (telefone == null) {
			if (other.telefone != null)
				return false;
		} else if (!telefone.equals(other.telefone))
			return false;
		return true;
	}

}
