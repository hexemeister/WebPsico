package manager;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import modelo.Usuario;
import Persistence.UsuarioDao;

@ManagedBean(name = "mblogin")
@SessionScoped
public class ManagerBean {

	private Usuario usuario = new Usuario();
	private Usuario logado = new Usuario();

	public ManagerBean() {
	}

	public ManagerBean(Usuario usuario, Usuario logado) {
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
			logado = new UsuarioDao().findByLogin(usuario);
			if (logado != null) {
				fc.addMessage("form1", new FacesMessage("Login com sucesso!!!"));
				return "principal.jsf";
			} else {
				fc.addMessage("form1", new FacesMessage("Tente outra vez!"));
			}
		} catch (Exception e) {
			fc.addMessage("form1", new FacesMessage(e.getMessage()));
			e.printStackTrace();
		}
		return null;
	}

}
