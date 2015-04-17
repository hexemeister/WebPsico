package manager;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import modelo.Usuario;

import org.primefaces.event.SelectEvent;

import persistence.UsuarioDao;

@ManagedBean
@ViewScoped
public class UsuarioMB {

	Usuario usuarioSelecionado;
	List<Usuario> listaUsuario;

	public UsuarioMB() {

	}

	public Usuario getUsuarioSelecionado() {
		return usuarioSelecionado;
	}

	public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
		this.usuarioSelecionado = usuarioSelecionado;
	}

	public List<Usuario> getListaUsuario() {
		return listaUsuario;
	}

	public void setListaUsuario(List<Usuario> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}

	public List<Usuario> listaUsuarios(String query) {
		return this.listaUsuario = new UsuarioDao().findByLoginOrNome(query);
	}
	
	public void onItemSelect(SelectEvent event) {
		
			usuarioSelecionado = (Usuario) event.getObject();
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Usuário Selecionado", usuarioSelecionado.toString()));
	}

}
