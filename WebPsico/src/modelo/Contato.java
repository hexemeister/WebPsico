package modelo;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Contato extends Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;

	private String religiao;
	private String parentesco;

	public Contato(Integer idPessoa, String nome, String email, String sexo,
			Calendar dataNascimento, String endereco, String cpf,
			Integer idade, String telefone, String naturalidade,
			String nacionalidade, EstadoCivil estadoCivil,
			String grauDeInstrucao, String profissao, String religiao,
			String parentesco) {
		super(idPessoa, nome, email, sexo, dataNascimento, endereco, cpf,
				idade, telefone, naturalidade, nacionalidade, estadoCivil,
				grauDeInstrucao, profissao);
		this.religiao = religiao;
		this.parentesco = parentesco;
	}

}
