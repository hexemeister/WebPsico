package manager;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.NonUniqueResultException;

import modelo.Usuario;
import persistence.UsuarioDao;

@SessionScoped
@Named("loginMB")
public class LoginMB implements Serializable {

	private static final long serialVersionUID = 1L;

	private Usuario usuario = new Usuario();
	private Usuario logado = new Usuario();

	public LoginMB() {
	}

	public LoginMB(Usuario usuario, Usuario logado) {
		super();
		this.usuario = usuario;
		this.logado = logado;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getLogado() {
		return logado;
	}

	public void setLogado(Usuario logado) {
		this.logado = logado;
	}

	public String logar() {
		FacesContext fc = FacesContext.getCurrentInstance();
		try {
			logado = new UsuarioDao().findByLoginAndSenha(usuario);
			if (logado != null && logado.getDesativado() == false) {
				fc.addMessage("form1", new FacesMessage("Login com sucesso!!!",
						"Bem vindo, " + logado.getLogin()));
				return "principal.jsf";
			} else if (logado != null && logado.getDesativado() == true) {
				fc.addMessage("form1", new FacesMessage(
						FacesMessage.SEVERITY_ERROR,
						"Usuário desativado pelo Administrador!",
						"Usuário desativado pelo Administrador!"));
			} else {
				fc.addMessage("form1", new FacesMessage(
						FacesMessage.SEVERITY_WARN,
						"Login e/ou senha errados!",
						"Login e/ou senha errados!"));
			}
			System.out.println(new FacesMessage().getSummary());
		} catch (NonUniqueResultException e) {
			fc.addMessage(
					"form1",
					new FacesMessage(
							FacesMessage.SEVERITY_FATAL,
							"Existem mais de uma conta com o mesmo login - Fale com o administrador!",
							"Banco de dados comprometido - Mais de uma conta com o mesmo login!"));
		} catch (Exception e) {
			fc.addMessage(
					"form1",
					new FacesMessage(FacesMessage.SEVERITY_FATAL, e
							.getMessage(), e.getMessage()));
			e.printStackTrace();
		}
		return null;
	}

}
