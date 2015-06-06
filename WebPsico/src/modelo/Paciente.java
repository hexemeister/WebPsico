package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import persistence.EntidadeBase;

@Entity
public class Paciente extends Pessoa implements Serializable, EntidadeBase {

	private static final long serialVersionUID = 1L;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn
	private Indicacao indicacao; // profissional que indicou a psicologa

	@Temporal(TemporalType.DATE)
	private Date dataInicio = new Date(); // data da primeira consulta

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataUltimaSessao = new Date(); // Última sessão que o paciente
												// esteve
	// presente
	@Enumerated(EnumType.STRING)
	private Turno preferenciaTurno; // preferencia de horario para marcacao
	private Double preco; // Valor combinado entre o psicologo e o paciente por
							// sessão

	private Boolean desativado = false;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name="PACIENTE_ID")
	private List<Anamnese> anamneses;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "paciente")
	@JoinColumn(name="PACIENTE_ID")
	private List<Evolucao> evolucoes = new ArrayList<>();

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Contato> contatos = new ArrayList<>();

	public Paciente() {
		Endereco endereco = new Endereco();
		this.setEndereco(endereco);
		Indicacao indicacao = new Indicacao();
		this.setIndicacao(indicacao);
		// Anamnese anamnese = new Anamnese();
		// this.setAnamneses(Arrays.asList(anamnese));
	}

	public Paciente(Indicacao indicacao, Date dataInicio, Date dataUtimaSessao,
			String frequencia, Turno preferenciaTurno, Double preco,
			Boolean desativado) {
		super();
		Endereco endereco = new Endereco();
		this.setEndereco(endereco);
		this.indicacao = indicacao;
		this.dataInicio = dataInicio;
		this.dataUltimaSessao = dataUtimaSessao;
		this.preferenciaTurno = preferenciaTurno;
		this.preco = preco;
		this.desativado = desativado;
	}

	public Paciente(Integer id, String nome, String email, Sexo sexo,
			Date dataNascimento, Endereco endereco, String cpf,
			String telefoneFixo, String telefoneCelular, Uf naturalidade,
			String nacionalidade, EstadoCivil estadoCivil,
			Escolaridade escolaridade, String profissao, Boolean desativado,
			String obs, Indicacao indicacao, Date dataInicio,
			Date dataUltimaSessao, Turno preferenciaTurno, Double preco,
			Boolean desativado2, List<Anamnese> anamneses, List<Evolucao> evolucoes,
			List<Contato> contatos) {
		super(id, nome, email, sexo, dataNascimento, endereco, cpf,
				telefoneFixo, telefoneCelular, naturalidade, nacionalidade,
				estadoCivil, escolaridade, profissao, desativado, obs);
		this.indicacao = indicacao;
		this.dataInicio = dataInicio;
		this.dataUltimaSessao = dataUltimaSessao;
		this.preferenciaTurno = preferenciaTurno;
		this.preco = preco;
		desativado = desativado2;
		this.anamneses = anamneses;
		this.evolucoes = evolucoes;
		this.contatos = contatos;
	}

	@Override
	public String toString() {
		return super.toString() + "Paciente [indicacao=" + indicacao
				+ ", dataInicio=" + dataInicio + ", dataUltimaSessao="
				+ dataUltimaSessao + ", preferenciaTurno=" + preferenciaTurno
				+ ", preco=" + preco + ", desativado=" + desativado
				+ ", anamneses=" + anamneses + ", evolucoes=" + evolucoes
				+ ", contatos=" + contatos + "]";
	}

	public List<Contato> getContatos() {
		return contatos;
	}

	public void setContatos(List<Contato> contatos) {
		this.contatos = contatos;
	}

	public Indicacao getIndicacao() {
		return indicacao;
	}

	public void setIndicacao(Indicacao indicacao) {
		this.indicacao = indicacao;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		// dataInicio.setYear(dataInicio.getYear() - 1900);
		this.dataInicio = dataInicio;
	}

	public Date getDataUltimaSessao() {
		return dataUltimaSessao;
	}

	public void setDataUltimaSessao(Date dataUltimaSessao) {
		// dataUltimaSessao.setYear(dataUltimaSessao.getYear() - 1900);
		this.dataUltimaSessao = dataUltimaSessao;
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

	public Boolean getDesativado() {
		return desativado;
	}

	public void setDesativado(Boolean desativado) {
		this.desativado = desativado;
	}

	public List<Anamnese> getAnamneses() {
		return anamneses;
	}

	public void setAnamneses(List<Anamnese> anamneses) {
		this.anamneses = anamneses;
	}

	public List<Evolucao> getEvolucoes() {
		return evolucoes;
	}

	public void setEvolucoes(List<Evolucao> evolucoes) {
		this.evolucoes = evolucoes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((anamneses == null) ? 0 : anamneses.hashCode());
		result = prime * result
				+ ((contatos == null) ? 0 : contatos.hashCode());
		result = prime * result
				+ ((dataInicio == null) ? 0 : dataInicio.hashCode());
		result = prime
				* result
				+ ((dataUltimaSessao == null) ? 0 : dataUltimaSessao.hashCode());
		result = prime * result
				+ ((desativado == null) ? 0 : desativado.hashCode());
		result = prime * result
				+ ((evolucoes == null) ? 0 : evolucoes.hashCode());
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
		if (anamneses == null) {
			if (other.anamneses != null)
				return false;
		} else if (!anamneses.equals(other.anamneses))
			return false;
		if (contatos == null) {
			if (other.contatos != null)
				return false;
		} else if (!contatos.equals(other.contatos))
			return false;
		if (dataInicio == null) {
			if (other.dataInicio != null)
				return false;
		} else if (!dataInicio.equals(other.dataInicio))
			return false;
		if (dataUltimaSessao == null) {
			if (other.dataUltimaSessao != null)
				return false;
		} else if (!dataUltimaSessao.equals(other.dataUltimaSessao))
			return false;
		if (desativado == null) {
			if (other.desativado != null)
				return false;
		} else if (!desativado.equals(other.desativado))
			return false;
		if (evolucoes == null) {
			if (other.evolucoes != null)
				return false;
		} else if (!evolucoes.equals(other.evolucoes))
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
		private Date dataInicio;
		private Date dataUltimaSessao;
		private Turno preferenciaTurno;
		private Double preco;
		private Boolean desativado;

		private Integer id;
		private String nome;
		private String email;
		private Sexo sexo;
		private Date dataNascimento;
		private Endereco endereco;
		private String cpf;
		private String telefoneFixo;
		private String telefoneCelular;
		private Uf naturalidade;
		private String nacionalidade;
		private EstadoCivil estadoCivil;
		private Escolaridade escolaridade;
		private String profissao;
		private String obs;

		public Builder indicacao(Indicacao indicacao) {
			this.indicacao = indicacao;
			return this;
		}

		public Builder dataInicio(Date dataInicio) {
			this.dataInicio = dataInicio;
			return this;
		}

		public Builder dataUltimaSessao(Date dataUltimaSessao) {
			this.dataUltimaSessao = dataUltimaSessao;
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
			this.id = id;
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

		public Builder dataNascimento(Date dataNascimento) {
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

		public Builder telefoneFixo(String telefoneFixo) {
			this.telefoneFixo = telefoneFixo;
			return this;
		}

		public Builder telefoneCelular(String telefoneCelular) {
			this.telefoneCelular = telefoneCelular;
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
		this.setIndicacao(builder.indicacao);
		this.setDataInicio(builder.dataInicio);
		this.setDataUltimaSessao(builder.dataUltimaSessao);
		this.setPreferenciaTurno(builder.preferenciaTurno);
		this.setPreco(builder.preco);
		this.setDesativado(builder.desativado);

		this.setId(builder.id);
		this.setNome(builder.nome);
		this.setEmail(builder.email);
		this.setSexo(builder.sexo);
		this.setDataNascimento(builder.dataNascimento);
		this.setEndereco(builder.endereco);
		this.setCpf(builder.cpf);
		this.setTelefoneFixo(builder.telefoneFixo);
		this.setTelefoneCelular(builder.telefoneCelular);
		this.setNaturalidade(builder.naturalidade);
		this.setNacionalidade(builder.nacionalidade);
		this.setEstadoCivil(builder.estadoCivil);
		this.setEscolaridade(builder.escolaridade);
		this.setProfissao(builder.profissao);
		this.setObs(builder.obs);
	}
}
