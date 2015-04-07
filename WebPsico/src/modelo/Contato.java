package modelo;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Entity;

@Entity
public class Contato extends Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;

	private String religiao;
	private String parentesco;

	public Contato() {
		super();
	}

	public Contato(String religiao, String parentesco) {
		super();
		this.religiao = religiao;
		this.parentesco = parentesco;
	}

	

	public Contato(Integer idPessoa, String nome, String email, String sexo,
			Calendar dataNascimento, String endereco, String cpf,
			Integer idade, String telefone, String naturalidade,
			String nacionalidade, EstadoCivil estadoCivil, String grauDeInstrucao,
			String profissao, String religiao, String parentesco) {
		super(idPessoa, nome, email, sexo, dataNascimento, endereco, cpf,
				idade, telefone, naturalidade, nacionalidade, estadoCivil,
				grauDeInstrucao, profissao);
		this.religiao = religiao;
		this.parentesco = parentesco;
	}

	@Override
	public String toString() {
		return "Contato [religiao=" + religiao + ", parentesco=" + parentesco
				+ "]" + super.toString();
	}

	public String getReligiao() {
		return religiao;
	}

	public void setReligiao(String religiao) {
		this.religiao = religiao;
	}

	public String getParentesco() {
		return parentesco;
	}

	public void setParentesco(String parentesco) {
		this.parentesco = parentesco;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((parentesco == null) ? 0 : parentesco.hashCode());
		result = prime * result
				+ ((religiao == null) ? 0 : religiao.hashCode());
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
		Contato other = (Contato) obj;
		if (parentesco == null) {
			if (other.parentesco != null)
				return false;
		} else if (!parentesco.equals(other.parentesco))
			return false;
		if (religiao == null) {
			if (other.religiao != null)
				return false;
		} else if (!religiao.equals(other.religiao))
			return false;
		return true;
	}

}
