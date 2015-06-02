package manager;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import modelo.Usuario;

@Named
@ViewScoped
public class AnamneseMB implements Serializable{

	@Inject
	LoginMB loginMB;
	
	Usuario logado;

	private String acidente;
	private String alergia;
	private String alimentacao;
	private String atitude;
	private String cirurgia;
	private String conciente;
	private String escola;
	private String examepsico;
	private String expansao;
	private String familia;
	private String gravidez;
	private String hipotese;
	private String humor;
	private String irescola;
	private String linguagem;
	private String mania;
	private String medicamento;
	private String negacao;
	private String obs;
	private String queixa;
	private String resumo;
	private String retrato;
	private String saude;
	private String sintoma;
	private String sono;
	private String tratamento;
	private String vidapessoal;

	public AnamneseMB() {
		// TODO Auto-generated constructor stub
	}
	
	public void teste() {
		System.out.println("!!!!!!!!!!!!!!!!!" + loginMB.getLogado().getNomeCompleto());
	}
	
	public Usuario getLogado() {
		return logado;
	}

	public void setLogado(Usuario logado) {
		this.logado = logado;
	}

	public String getAcidente() {
		return acidente;
	}

	public void setAcidente(String acidente) {
		this.acidente = acidente;
	}

	public String getAlergia() {
		return alergia;
	}

	public void setAlergia(String alergia) {
		this.alergia = alergia;
	}

	public String getAlimentacao() {
		return alimentacao;
	}

	public void setAlimentacao(String alimentacao) {
		this.alimentacao = alimentacao;
	}

	public String getAtitude() {
		return atitude;
	}

	public void setAtitude(String atitude) {
		this.atitude = atitude;
	}

	public String getCirurgia() {
		return cirurgia;
	}

	public void setCirurgia(String cirurgia) {
		this.cirurgia = cirurgia;
	}

	public String getConciente() {
		return conciente;
	}

	public void setConciente(String conciente) {
		this.conciente = conciente;
	}

	public String getEscola() {
		return escola;
	}

	public void setEscola(String escola) {
		this.escola = escola;
	}

	public String getExamepsico() {
		return examepsico;
	}

	public void setExamepsico(String examepsico) {
		this.examepsico = examepsico;
	}

	public String getExpansao() {
		return expansao;
	}

	public void setExpansao(String expansao) {
		this.expansao = expansao;
	}

	public String getFamilia() {
		return familia;
	}

	public void setFamilia(String familia) {
		this.familia = familia;
	}

	public String getGravidez() {
		return gravidez;
	}

	public void setGravidez(String gravidez) {
		this.gravidez = gravidez;
	}

	public String getHipotese() {
		return hipotese;
	}

	public void setHipotese(String hipotese) {
		this.hipotese = hipotese;
	}

	public String getHumor() {
		return humor;
	}

	public void setHumor(String humor) {
		this.humor = humor;
	}

	public String getIrescola() {
		return irescola;
	}

	public void setIrescola(String irescola) {
		this.irescola = irescola;
	}

	public String getLinguagem() {
		return linguagem;
	}

	public void setLinguagem(String linguagem) {
		this.linguagem = linguagem;
	}

	public String getMania() {
		return mania;
	}

	public void setMania(String mania) {
		this.mania = mania;
	}

	public String getMedicamento() {
		return medicamento;
	}

	public void setMedicamento(String medicamento) {
		this.medicamento = medicamento;
	}

	public String getNegacao() {
		return negacao;
	}

	public void setNegacao(String negacao) {
		this.negacao = negacao;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public String getQueixa() {
		return queixa;
	}

	public void setQueixa(String queixa) {
		this.queixa = queixa;
	}

	public String getResumo() {
		return resumo;
	}

	public void setResumo(String resumo) {
		this.resumo = resumo;
	}

	public String getRetrato() {
		return retrato;
	}

	public void setRetrato(String retrato) {
		this.retrato = retrato;
	}

	public String getSaude() {
		return saude;
	}

	public void setSaude(String saude) {
		this.saude = saude;
	}

	public String getSintoma() {
		return sintoma;
	}

	public void setSintoma(String sintoma) {
		this.sintoma = sintoma;
	}

	public String getSono() {
		return sono;
	}

	public void setSono(String sono) {
		this.sono = sono;
	}

	public String getTratamento() {
		return tratamento;
	}

	public void setTratamento(String tratamento) {
		this.tratamento = tratamento;
	}

	public String getVidapessoal() {
		return vidapessoal;
	}

	public void setVidapessoal(String vidapessoal) {
		this.vidapessoal = vidapessoal;
	}

	

}
