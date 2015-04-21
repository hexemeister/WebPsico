package manager;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.NonUniqueResultException;

import modelo.Usuario;
import persistence.UsuarioDao;

@ManagedBean(name = "loginMB")
@SessionScoped
public class LoginMB {

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
				fc.addMessage("form1", new FacesMessage("Login com sucesso!!!"));
//				usuario = new Usuario();
				return "principal.jsf";
			} else if (logado != null && logado.getDesativado() == true) {
				fc.addMessage("form1", new FacesMessage(
						FacesMessage.SEVERITY_ERROR,
						"Usu�rio desativado pelo Administrador!",
						"Usu�rio desativado pelo Administrador!"));
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
			fc.addMessage("form1", new FacesMessage(FacesMessage.SEVERITY_FATAL,e.getMessage(),e.getMessage()));
			e.printStackTrace();
		}
		return null;
	}

}
