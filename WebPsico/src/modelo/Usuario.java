package modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import persistence.EntidadeBase;

@Entity
public class Usuario implements Serializable, EntidadeBase {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(nullable = false, unique = true)
	private String login;
	private String senha;
	private String nomeCompleto;
	private Boolean desativado = false;

	@Enumerated(EnumType.STRING)
	private Perfil perfil;
	private String email;

	public Usuario() {
	}

	public Usuario(Integer id, String login, String senha, String nomeCompleto,
			String email, Perfil perfil) {
		super();
		this.id = id;
		this.login = login;
		this.senha = senha;
		this.nomeCompleto = nomeCompleto;
		this.perfil = perfil;
		this.email = email;
	}

	public Usuario(Integer id, String login, String senha, String nomeCompleto,
			String email, Perfil perfil, Boolean desativado) {
		super();
		this.id = id;
		this.login = login;
		this.senha = senha;
		this.nomeCompleto = nomeCompleto;
		this.desativado = desativado;
		this.perfil = perfil;
		this.email = email;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getDesativado() {
		return desativado;
	}

	public void setDesativado(Boolean desativado) {
		this.desativado = desativado;
	}

	public Boolean temPerfil(Perfil perfil) {
		return this.perfil.equals(perfil);
	}

	public Boolean isAdm() {
		return this.perfil.equals(Perfil.ADMINISTRADOR);
	}

	public Boolean isPsicologa() {
		return this.perfil.equals(Perfil.PSICOLOGA);
	}

	public Boolean isAtendente() {
		return this.perfil.equals(Perfil.ATENDENTE);
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", login=" + login + ", senha=" + senha
				+ ", nomeCompleto=" + nomeCompleto + ", desativado="
				+ desativado + ", perfil=" + perfil + ", email=" + email + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((desativado == null) ? 0 : desativado.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result
				+ ((nomeCompleto == null) ? 0 : nomeCompleto.hashCode());
		result = prime * result + ((perfil == null) ? 0 : perfil.hashCode());
		result = prime * result + ((senha == null) ? 0 : senha.hashCode());
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
		Usuario other = (Usuario) obj;
		if (desativado == null) {
			if (other.desativado != null)
				return false;
		} else if (!desativado.equals(other.desativado))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (nomeCompleto == null) {
			if (other.nomeCompleto != null)
				return false;
		} else if (!nomeCompleto.equals(other.nomeCompleto))
			return false;
		if (perfil != other.perfil)
			return false;
		if (senha == null) {
			if (other.senha != null)
				return false;
		} else if (!senha.equals(other.senha))
			return false;
		return true;
	}

}
