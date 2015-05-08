package modelo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Indicacao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idIndicacao;

	@OneToMany(mappedBy = "indicacao")
	private List<Paciente> pacientes;

	private String nome;
	private String profissao;

	public Indicacao() {
	}

	public Indicacao(Integer idIndicacao, List<Paciente> pacientes,
			String nome, String profissao) {
		super();
		this.idIndicacao = idIndicacao;
		this.pacientes = pacientes;
		this.nome = nome;
		this.profissao = profissao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;

	}

	public String getProfissao() {
		return profissao;
	}

	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}

	@Override
	public String toString() {
		return "Indicacao [idIndicacao=" + idIndicacao + ", pacientes="
				+ pacientes + ", nome=" + nome + ", profissao=" + profissao
				+ "]";
	}

}