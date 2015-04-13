package modelo;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Paciente extends Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn
	private Indicacao indicacao; // profissional que indicou a psicologa

	@Temporal(TemporalType.DATE)
	private Calendar dataInicio; // data da primeira consulta

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataUtimaSessao; // Última sessão que o paciente esteve
										// presente
	private String frequencia; // preferencia de frequencia de marcacao das
								// sessoes, meramente informativo
	@Enumerated(EnumType.STRING)
	private Turno preferenciaTurno; // preferencia de horario para marcacao
	private Double preco; // Valor combinado entre o psicologo e o paciente por
							// mês

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="PACIENTE_TELEFONE", joinColumns= @JoinColumn(name="ID_PACIENTE"),inverseJoinColumns=@JoinColumn(name="ID_TELEFONE"))
	private List<Telefone> telefones;
	
	public Paciente() {
	}
		
	public Paciente(Integer id, String nome, String email, Sexo sexo,
			Calendar dataNascimento, Endereco endereco, String cpf,
			List<Telefone> telefones, Uf naturalidade, String nacionalidade,
			EstadoCivil estadoCivil, Escolaridade escolaridade,
			String profissao, Boolean desativado, String obs,
			Indicacao indicacao, Calendar dataInicio, Calendar dataUtimaSessao,
			String frequencia, Turno preferenciaTurno, Double preco,
			List<Telefone> telefones2) {
		super(id, nome, email, sexo, dataNascimento, endereco, cpf, telefones,
				naturalidade, nacionalidade, estadoCivil, escolaridade,
				profissao, desativado, obs);
		this.indicacao = indicacao;
		this.dataInicio = dataInicio;
		this.dataUtimaSessao = dataUtimaSessao;
		this.frequencia = frequencia;
		this.preferenciaTurno = preferenciaTurno;
		this.preco = preco;
		telefones = telefones2;
	}



	public Paciente(Integer id, String nome, String email, Sexo sexo,
			Calendar dataNascimento, Endereco endereco, String cpf,
			List<Telefone> telefones, Uf naturalidade, String nacionalidade,
			EstadoCivil estadoCivil, Escolaridade escolaridade,
			String profissao, Boolean desativado, String obs,
			Indicacao indicacao, Calendar dataInicio, Calendar dataUtimaSessao,
			String frequencia, Turno preferenciaTurno, Double preco) {
		super(id, nome, email, sexo, dataNascimento, endereco, cpf,
				telefones, naturalidade, nacionalidade, estadoCivil,
				escolaridade, profissao, desativado, obs);
		this.indicacao = indicacao;
		this.dataInicio = dataInicio;
		this.dataUtimaSessao = dataUtimaSessao;
		this.frequencia = frequencia;
		this.preferenciaTurno = preferenciaTurno;
		this.preco = preco;
	}

	@Override
	public String toString() {
		return "Paciente [indicacao=" + indicacao + ", dataInicio="
				+ dataInicio + ", dataUtimaSessao=" + dataUtimaSessao
				+ ", frequencia=" + frequencia + ", preferenciaTurno="
				+ preferenciaTurno + ", preco=" + preco + "]"
				+ super.toString();
	}

	public Indicacao getIndicacao() {
		return indicacao;
	}

	public void setIndicacao(Indicacao indicacao) {
		this.indicacao = indicacao;
	}

	public Calendar getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Calendar dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Calendar getDataUtimaSessao() {
		return dataUtimaSessao;
	}

	public void setDataUtimaSessao(Calendar dataUtimaSessao) {
		this.dataUtimaSessao = dataUtimaSessao;
	}

	public String getFrequencia() {
		return frequencia;
	}

	public void setFrequencia(String frequencia) {
		this.frequencia = frequencia;
	}

	public Turno getPreferenciaTurno() {
		return preferenciaTurno;
	}

	public void setPreferenciaTurno(Turno preferenciaTurno) {
		this.preferenciaTurno = preferenciaTurno;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public List<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((dataInicio == null) ? 0 : dataInicio.hashCode());
		result = prime * result
				+ ((dataUtimaSessao == null) ? 0 : dataUtimaSessao.hashCode());
		result = prime * result
				+ ((frequencia == null) ? 0 : frequencia.hashCode());
		result = prime * result
				+ ((indicacao == null) ? 0 : indicacao.hashCode());
		result = prime * result + ((preco == null) ? 0 : preco.hashCode());
		result = prime
				* result
				+ ((preferenciaTurno == null) ? 0 : preferenciaTurno.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Paciente other = (Paciente) obj;
		if (dataInicio == null) {
			if (other.dataInicio != null)
				return false;
		} else if (!dataInicio.equals(other.dataInicio))
			return false;
		if (dataUtimaSessao == null) {
			if (other.dataUtimaSessao != null)
				return false;
		} else if (!dataUtimaSessao.equals(other.dataUtimaSessao))
			return false;
		if (frequencia == null) {
			if (other.frequencia != null)
				return false;
		} else if (!frequencia.equals(other.frequencia))
			return false;
		if (indicacao == null) {
			if (other.indicacao != null)
				return false;
		} else if (!indicacao.equals(other.indicacao))
			return false;
		if (preco == null) {
			if (other.preco != null)
				return false;
		} else if (!preco.equals(other.preco))
			return false;
		if (preferenciaTurno != other.preferenciaTurno)
			return false;
		return true;
	}

	public static class Builder {
		private Indicacao indicacao;
		private Calendar dataInicio;
		private Calendar dataUtimaSessao;
		private String frequencia;
		private Turno preferenciaTurno;
		private Double preco;

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

		public Builder indicacao(Indicacao indicacao) {
			this.indicacao = indicacao;
			return this;
		}

		public Builder dataInicio(Calendar dataInicio) {
			this.dataInicio = dataInicio;
			return this;
		}

		public Builder dataUtimaSessao(Calendar dataUtimaSessao) {
			this.dataUtimaSessao = dataUtimaSessao;
			return this;
		}

		public Builder frequencia(String frequencia) {
			this.frequencia = frequencia;
			return this;
		}

		public Builder preferenciaTurno(Turno preferenciaTurno) {
			this.preferenciaTurno = preferenciaTurno;
			return this;
		}

		public Builder preco(Double preco) {
			this.preco = preco;
			return this;
		}

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

		public Paciente build() {
			return new Paciente(this);
		}
	}

	private Paciente(Builder builder) {
		this.indicacao = builder.indicacao;
		this.dataInicio = builder.dataInicio;
		this.dataUtimaSessao = builder.dataUtimaSessao;
		this.frequencia = builder.frequencia;
		this.preferenciaTurno = builder.preferenciaTurno;
		this.preco = builder.preco;

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
	}
}
