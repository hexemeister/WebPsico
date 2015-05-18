package modelo;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import br.com.caelum.stella.bean.validation.CPF;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private String email;

	@Enumerated(EnumType.STRING)
	private Sexo sexo;

	@Temporal(TemporalType.DATE)
	private Date dataNascimento;

	@Transient
	private Integer idade;

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval=true, fetch=FetchType.EAGER)
	private Endereco endereco;

	@CPF(formatted = false, message = "CPF invalido")
	private String cpf;

	@Column(length = 10)
	private String telefoneFixo = "";

	@Column(length = 11)
	private String telefoneCelular = "";

	@Enumerated(EnumType.STRING)
	private Uf naturalidade;
	private String nacionalidade = "Brasileiro";

	@Enumerated(EnumType.STRING)
	private EstadoCivil estadoCivil;
	@Enumerated(EnumType.STRING)
	private Escolaridade escolaridade;
	private String profissao;

	private String obs; // observacoes sobre o paciente

	public Pessoa() {
		Endereco e = new Endereco();
		this.setEndereco(e);
	}

	public Pessoa(Integer id, String nome, String email, Sexo sexo,
			Date dataNascimento, Endereco endereco, String cpf,
			String telefoneFixo, String telefoneCelular, Uf naturalidade,
			String nacionalidade, EstadoCivil estadoCivil,
			Escolaridade escolaridade, String profissao, Boolean desativado,
			String obs) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.sexo = sexo;
		this.dataNascimento = dataNascimento;
		this.endereco = endereco;
		this.cpf = cpf;
		this.telefoneFixo = telefoneFixo;
		this.telefoneCelular = telefoneCelular;
		this.naturalidade = naturalidade;
		this.nacionalidade = nacionalidade;
		this.estadoCivil = estadoCivil;
		this.escolaridade = escolaridade;
		this.profissao = profissao;
		this.obs = obs;
	}

	@Override
	public String toString() {
		return "Pessoa [id=" + id + ", nome=" + nome + ", email=" + email
				+ ", sexo=" + sexo + ", dataNascimento=" + dataNascimento
				+ ", endereco=" + endereco + ", cpf=" + cpf + ", telefoneFixo="
				+ telefoneFixo + ", telefoneCelular=" + telefoneCelular
				+ ", naturalidade=" + naturalidade + ", nacionalidade="
				+ nacionalidade + ", estadoCivil=" + estadoCivil
				+ ", escolaridade=" + escolaridade + ", profissao=" + profissao
				+ ", obs=" + obs + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		// dataNascimento.setYear(dataNascimento.getYear() - 1900); olhar no
		// banco
		this.dataNascimento = dataNascimento;
	}

	@SuppressWarnings("finally")
	public Integer getIdade() {
		try {
			Instant instant = Instant.ofEpochMilli(dataNascimento.getTime());
			LocalDateTime localDateTime = LocalDateTime.ofInstant(instant,
					ZoneId.systemDefault());
			idade = Period.between(LocalDate.from(localDateTime),
					LocalDate.now()).getYears();
		} catch (Exception e) {
			idade = null;
		} finally {
			return idade;
		}
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefoneFixo() {
		if (telefoneFixo.equals(null) | telefoneFixo.equals("")) {
			return "";
		} else {
			return "(" + telefoneFixo.substring(0, 2) + ") "
					+ telefoneFixo.substring(2, 6) + "-"
					+ telefoneFixo.substring(6, 10);
		}
	}

	public void setTelefoneFixo(String telefoneFixo) {
		this.telefoneFixo = telefoneFixo;
	}

	public String getTelefoneCelular() {
		if (telefoneCelular.equals(null) | telefoneCelular.equals("")) {
			return "";
		} else {
			return "(" + telefoneCelular.substring(0, 2) + ") "
					+ telefoneCelular.substring(2, 7) + "-"
					+ telefoneCelular.substring(7, 11);
		}
	}

	public void setTelefoneCelular(String telefoneCelular) {
		this.telefoneCelular = telefoneCelular;
	}

	public Uf getNaturalidade() {
		return naturalidade;
	}

	public void setNaturalidade(Uf naturalidade) {
		this.naturalidade = naturalidade;
	}

	public String getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

	public EstadoCivil getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(EstadoCivil estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public Escolaridade getEscolaridade() {
		return escolaridade;
	}

	public void setEscolaridade(Escolaridade escolaridade) {
		this.escolaridade = escolaridade;
	}

	public String getProfissao() {
		return profissao;
	}

	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		result = prime * result
				+ ((dataNascimento == null) ? 0 : dataNascimento.hashCode());

		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result
				+ ((endereco == null) ? 0 : endereco.hashCode());
		result = prime * result
				+ ((escolaridade == null) ? 0 : escolaridade.hashCode());
		result = prime * result
				+ ((estadoCivil == null) ? 0 : estadoCivil.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((nacionalidade == null) ? 0 : nacionalidade.hashCode());
		result = prime * result
				+ ((naturalidade == null) ? 0 : naturalidade.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((obs == null) ? 0 : obs.hashCode());
		result = prime * result
				+ ((profissao == null) ? 0 : profissao.hashCode());
		result = prime * result + ((sexo == null) ? 0 : sexo.hashCode());
		result = prime * result
				+ ((telefoneCelular == null) ? 0 : telefoneCelular.hashCode());
		result = prime * result
				+ ((telefoneFixo == null) ? 0 : telefoneFixo.hashCode());
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
		Pessoa other = (Pessoa) obj;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		if (dataNascimento == null) {
			if (other.dataNascimento != null)
				return false;
		} else if (!dataNascimento.equals(other.dataNascimento))
			return false;

		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (endereco == null) {
			if (other.endereco != null)
				return false;
		} else if (!endereco.equals(other.endereco))
			return false;
		if (escolaridade != other.escolaridade)
			return false;
		if (estadoCivil != other.estadoCivil)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nacionalidade == null) {
			if (other.nacionalidade != null)
				return false;
		} else if (!nacionalidade.equals(other.nacionalidade))
			return false;
		if (naturalidade != other.naturalidade)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (obs == null) {
			if (other.obs != null)
				return false;
		} else if (!obs.equals(other.obs))
			return false;
		if (profissao == null) {
			if (other.profissao != null)
				return false;
		} else if (!profissao.equals(other.profissao))
			return false;
		if (sexo != other.sexo)
			return false;
		if (telefoneCelular == null) {
			if (other.telefoneCelular != null)
				return false;
		} else if (!telefoneCelular.equals(other.telefoneCelular))
			return false;
		if (telefoneFixo == null) {
			if (other.telefoneFixo != null)
				return false;
		} else if (!telefoneFixo.equals(other.telefoneFixo))
			return false;
		return true;
	}

}