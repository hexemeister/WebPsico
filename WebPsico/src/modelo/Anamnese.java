package modelo;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import persistence.EntidadeBase;

@Entity
public class Anamnese  implements Serializable, EntidadeBase{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@JoinColumn
	private Usuario psicologa;

	@ManyToOne(cascade = CascadeType.ALL, optional = true)
	private Paciente paciente;

	private String resumo;
	private String queixa;
	private String sintoma;
	private String tratamento;
	private String medicamento;
	private String cirurgia;
	private String acidente;
	private String vidaPessoal;
	private String familia;
	private String examePsico;
	private String atitude;
	private String humor;
	private String expansao;
	private String retrato;
	private String negacao;
	private String linguagem;
	private String conciente;
	private String hipotese;
	private String escola;
	private String irescola;
	private String gravidez;
	private String alergia;
	private String saude;
	private String alimentacao;
	private String sono;
	private String mania;
	private String obs;

	public Anamnese() {
	}
	
	public Anamnese(Integer id, Usuario psicologa, Paciente paciente,
			String resumo, String queixa, String sintoma, String tratamento,
			String medicamento, String cirurgia, String acidente,
			String vidaPessoal, String familia, String examePsico,
			String atitude, String humor, String expansao, String retrato,
			String negacao, String linguagem, String conciente,
			String hipotese, String escola, String irescola, String gravidez,
			String alergia, String saude, String alimentacao, String sono,
			String mania, String obs) {
		super();
		this.id = id;
		this.psicologa = psicologa;
		this.paciente = paciente;
		this.resumo = resumo;
		this.queixa = queixa;
		this.sintoma = sintoma;
		this.tratamento = tratamento;
		this.medicamento = medicamento;
		this.cirurgia = cirurgia;
		this.acidente = acidente;
		this.vidaPessoal = vidaPessoal;
		this.familia = familia;
		this.examePsico = examePsico;
		this.atitude = atitude;
		this.humor = humor;
		this.expansao = expansao;
		this.retrato = retrato;
		this.negacao = negacao;
		this.linguagem = linguagem;
		this.conciente = conciente;
		this.hipotese = hipotese;
		this.escola = escola;
		this.irescola = irescola;
		this.gravidez = gravidez;
		this.alergia = alergia;
		this.saude = saude;
		this.alimentacao = alimentacao;
		this.sono = sono;
		this.mania = mania;
		this.obs = obs;
	}

	@Override
	public String toString() {
		return "Anamnese [id=" + id + ", psicologa=" + psicologa
				+ ", paciente=" + paciente + ", resumo=" + resumo + ", queixa="
				+ queixa + ", sintoma=" + sintoma + ", tratamento="
				+ tratamento + ", medicamento=" + medicamento + ", cirurgia="
				+ cirurgia + ", acidente=" + acidente + ", vidaPessoal="
				+ vidaPessoal + ", familia=" + familia + ", examePsico="
				+ examePsico + ", atitude=" + atitude + ", humor=" + humor
				+ ", expansao=" + expansao + ", retrato=" + retrato
				+ ", negacao=" + negacao + ", linguagem=" + linguagem
				+ ", conciente=" + conciente + ", hipotese=" + hipotese
				+ ", escola=" + escola + ", irescola=" + irescola
				+ ", gravidez=" + gravidez + ", alergia=" + alergia
				+ ", saude=" + saude + ", alimentacao=" + alimentacao
				+ ", sono=" + sono + ", mania=" + mania + ", obs=" + obs + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Usuario getPsicologa() {
		return psicologa;
	}

	public void setPsicologa(Usuario psicologa) {
		this.psicologa = psicologa;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public String getResumo() {
		return resumo;
	}

	public void setResumo(String resumo) {
		this.resumo = resumo;
	}

	public String getQueixa() {
		return queixa;
	}

	public void setQueixa(String queixa) {
		this.queixa = queixa;
	}

	public String getSintoma() {
		return sintoma;
	}

	public void setSintoma(String sintoma) {
		this.sintoma = sintoma;
	}

	public String getTratamento() {
		return tratamento;
	}

	public void setTratamento(String tratamento) {
		this.tratamento = tratamento;
	}

	public String getMedicamento() {
		return medicamento;
	}

	public void setMedicamento(String medicamento) {
		this.medicamento = medicamento;
	}

	public String getCirurgia() {
		return cirurgia;
	}

	public void setCirurgia(String cirurgia) {
		this.cirurgia = cirurgia;
	}

	public String getAcidente() {
		return acidente;
	}

	public void setAcidente(String acidente) {
		this.acidente = acidente;
	}

	public String getVidaPessoal() {
		return vidaPessoal;
	}

	public void setVidaPessoal(String vidaPessoal) {
		this.vidaPessoal = vidaPessoal;
	}

	public String getFamilia() {
		return familia;
	}

	public void setFamilia(String familia) {
		this.familia = familia;
	}

	public String getExamePsico() {
		return examePsico;
	}

	public void setExamePsico(String examePsico) {
		this.examePsico = examePsico;
	}

	public String getAtitude() {
		return atitude;
	}

	public void setAtitude(String atitude) {
		this.atitude = atitude;
	}

	public String getHumor() {
		return humor;
	}

	public void setHumor(String humor) {
		this.humor = humor;
	}

	public String getExpansao() {
		return expansao;
	}

	public void setExpansao(String expansao) {
		this.expansao = expansao;
	}

	public String getRetrato() {
		return retrato;
	}

	public void setRetrato(String retrato) {
		this.retrato = retrato;
	}

	public String getNegacao() {
		return negacao;
	}

	public void setNegacao(String negacao) {
		this.negacao = negacao;
	}

	public String getLinguagem() {
		return linguagem;
	}

	public void setLinguagem(String linguagem) {
		this.linguagem = linguagem;
	}

	public String getConciente() {
		return conciente;
	}

	public void setConciente(String conciente) {
		this.conciente = conciente;
	}

	public String getHipotese() {
		return hipotese;
	}

	public void setHipotese(String hipotese) {
		this.hipotese = hipotese;
	}

	public String getEscola() {
		return escola;
	}

	public void setEscola(String escola) {
		this.escola = escola;
	}

	public String getIrescola() {
		return irescola;
	}

	public void setIrescola(String irescola) {
		this.irescola = irescola;
	}

	public String getGravidez() {
		return gravidez;
	}

	public void setGravidez(String gravidez) {
		this.gravidez = gravidez;
	}

	public String getAlergia() {
		return alergia;
	}

	public void setAlergia(String alergia) {
		this.alergia = alergia;
	}

	public String getSaude() {
		return saude;
	}

	public void setSaude(String saude) {
		this.saude = saude;
	}

	public String getAlimentacao() {
		return alimentacao;
	}

	public void setAlimentacao(String alimentacao) {
		this.alimentacao = alimentacao;
	}

	public String getSono() {
		return sono;
	}

	public void setSono(String sono) {
		this.sono = sono;
	}

	public String getMania() {
		return mania;
	}

	public void setMania(String mania) {
		this.mania = mania;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

}
