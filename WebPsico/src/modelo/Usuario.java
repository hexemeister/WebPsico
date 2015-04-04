package modelo;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String login;
	private String senha;
	private String nomeCompleto;

	@Enumerated(EnumType.STRING)
	private Perfil perfil;
	private String email;

	public Usuario() {
		// TODO Auto-generated constructor stub
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

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", login=" + login + ", senha=" + senha
				+ ", nomeCompleto=" + nomeCompleto + ", perfil=" + perfil
				+ ", email=" + email + "]\n";
	}

}
