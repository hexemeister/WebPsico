package modelo;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class Contato extends Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;

	private String religiao;
	private String parentesco;

	public Contato() {
		super();
	}

	public Contato(Integer id, String nome, String email, Sexo sexo,
			Calendar dataNascimento, Endereco endereco, String cpf,
			List<Telefone> telefones, Uf naturalidade, String nacionalidade,
			EstadoCivil estadoCivil, Escolaridade escolaridade,
			String profissao, Boolean desativado, String obs, String religiao,
			String parentesco) {
		super(id, nome, email, sexo, dataNascimento, endereco, cpf,
				telefones, naturalidade, nacionalidade, estadoCivil,
				escolaridade, profissao, desativado, obs);
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

	public static class Builder {
		private String religiao;
		private String parentesco;

		private Integer id;
		private String nome;
		private String email;
		private Sexo sexo;
		private Calendar dataNascimento;
		private Endereco endereco;
		private String cpf;
		private List<Telefone> telefones;
		private Uf naturalidade;
		private String nacionalidade;
		private EstadoCivil estadoCivil;
		private Escolaridade escolaridade;
		private String profissao;
		private Boolean desativado;
		private String obs;

		public Builder id(Integer id) {
			this.id= id;
			return this;
		}

		public Builder nome(String nome) {
			this.nome = nome;
			return this;
		}

		public Builder email(String email) {
			this.email = email;
			return this;
		}

		public Builder sexo(Sexo sexo) {
			this.sexo = sexo;
			return this;
		}

		public Builder dataNascimento(Calendar dataNascimento) {
			this.dataNascimento = dataNascimento;
			return this;
		}

		public Builder endereco(Endereco endereco) {
			this.endereco = endereco;
			return this;
		}

		public Builder cpf(String cpf) {
			this.cpf = cpf;
			return this;
		}

		public Builder telefones(List<Telefone> telefones) {
			this.telefones = telefones;
			return this;
		}

		public Builder naturalidade(Uf naturalidade) {
			this.naturalidade = naturalidade;
			return this;
		}

		public Builder nacionalidade(String nacionalidade) {
			this.nacionalidade = nacionalidade;
			return this;
		}

		public Builder estadoCivil(EstadoCivil estadoCivil) {
			this.estadoCivil = estadoCivil;
			return this;
		}

		public Builder escolaridade(Escolaridade escolaridade) {
			this.escolaridade = escolaridade;
			return this;
		}

		public Builder profissao(String profissao) {
			this.profissao = profissao;
			return this;
		}

		public Builder desativado(Boolean desativado) {
			this.desativado = desativado;
			return this;
		}

		public Builder obs(String obs) {
			this.obs = obs;
			return this;
		}

		public Builder religiao(String religiao) {
			this.religiao = religiao;
			return this;
		}

		public Builder parentesco(String parentesco) {
			this.parentesco = parentesco;
			return this;
		}

		public Contato build() {
			return new Contato(this);
		}
	}

	private Contato(Builder builder) {
		this.setId(builder.id);
		this.setNome(builder.nome);
		this.setEmail(builder.email);
		this.setSexo(builder.sexo);
		this.setDataNascimento(builder.dataNascimento);
		this.setEndereco(builder.endereco);
		this.setCpf(builder.cpf);
		this.setTelefones(builder.telefones);
		this.setNaturalidade(builder.naturalidade);
		this.setNacionalidade(builder.nacionalidade);
		this.setEstadoCivil(builder.estadoCivil);
		this.setEscolaridade(builder.escolaridade);
		this.setProfissao(builder.profissao);
		this.setDesativado(builder.desativado);
		this.setObs(builder.obs);

		this.religiao = builder.religiao;
		this.parentesco = builder.parentesco;
	}
}
