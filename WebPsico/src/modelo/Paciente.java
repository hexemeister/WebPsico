package modelo;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Paciente extends Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;

	private String indicacao; // nome do profissional que indicou a psicologa
	private String profissional; // função do profissional que indicou a psicologa
	
	@Temporal(TemporalType.DATE)
	private Calendar dataInicio; // data da primeira consulta
	private Integer sessao;
	private String frequencia; // preferencia de frequencia de marcação das sessões, meramente informativo
	private Integer horaMarcada; // preferencia de horario para marcação
	private Double preco; // Valor combinado entre o psicologo e o paciente

	public Paciente() {
		super();
	}

	public Paciente(Integer idPessoa, String nome, String email, String sexo,
			Calendar dataNascimento, String endereco, String cpf,
			Integer idade, String telefone, String naturalidade,
			String nacionalidade, EstadoCivil estadoCivil, String grauDeInstrucao,
			String profissao, String indicacao, String profissional,
			Calendar dataInicio, Integer sessao, String frequencia,
			Integer horaMarcada, Double preco) {
		super(idPessoa, nome, email, sexo, dataNascimento, endereco, cpf,
				idade, telefone, naturalidade, nacionalidade, estadoCivil,
				grauDeInstrucao, profissao);
		this.indicacao = indicacao;
		this.profissional = profissional;
		this.dataInicio = dataInicio;
		this.sessao = sessao;
		this.frequencia = frequencia;
		this.horaMarcada = horaMarcada;
		this.preco = preco;
	}

	@Override
	public String toString() {
		return "Paciente [indicacao=" + indicacao + ", profissional="
				+ profissional + ", dataInicio=" + dataInicio + ", sessao="
				+ sessao + ", frequencia=" + frequencia + ", horaMarcada="
				+ horaMarcada + ", preco=" + preco + "]" + super.toString();
	}

	public String getIndicacao() {
		return indicacao;
	}

	public void setIndicacao(String indicacao) {
		this.indicacao = indicacao;
	}

	public String getProfissional() {
		return profissional;
	}

	public void setProfissional(String profissional) {
		this.profissional = profissional;
	}

	public Calendar getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Calendar dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Integer getSessao() {
		return sessao;
	}

	public void setSessao(Integer sessao) {
		this.sessao = sessao;
	}

	public String getFrequencia() {
		return frequencia;
	}

	public void setFrequencia(String frequencia) {
		this.frequencia = frequencia;
	}

	public Integer getHoraMarcada() {
		return horaMarcada;
	}

	public void setHoraMarcada(Integer horaMarcada) {
		this.horaMarcada = horaMarcada;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((dataInicio == null) ? 0 : dataInicio.hashCode());
		result = prime * result
				+ ((frequencia == null) ? 0 : frequencia.hashCode());
		result = prime * result
				+ ((horaMarcada == null) ? 0 : horaMarcada.hashCode());
		result = prime * result
				+ ((indicacao == null) ? 0 : indicacao.hashCode());
		result = prime * result + ((preco == null) ? 0 : preco.hashCode());
		result = prime * result
				+ ((profissional == null) ? 0 : profissional.hashCode());
		result = prime * result + ((sessao == null) ? 0 : sessao.hashCode());
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
		if (frequencia == null) {
			if (other.frequencia != null)
				return false;
		} else if (!frequencia.equals(other.frequencia))
			return false;
		if (horaMarcada == null) {
			if (other.horaMarcada != null)
				return false;
		} else if (!horaMarcada.equals(other.horaMarcada))
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
		if (profissional == null) {
			if (other.profissional != null)
				return false;
		} else if (!profissional.equals(other.profissional))
			return false;
		if (sessao == null) {
			if (other.sessao != null)
				return false;
		} else if (!sessao.equals(other.sessao))
			return false;
		return true;
	}

}