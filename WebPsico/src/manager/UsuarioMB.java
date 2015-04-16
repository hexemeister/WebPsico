package manager;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import modelo.Usuario;

import org.primefaces.event.SelectEvent;

import persistence.UsuarioDao;

@ManagedBean
@RequestScoped
public class UsuarioMB {

	Usuario usuarioSelecionado = new Usuario();
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
		this.listaUsuario = new UsuarioDao().findByLoginOrNomeActives(query);
		List<Usuario> sugestoes = new ArrayList<Usuario>();
		for (Usuario usuario : listaUsuario) {
			if (usuario.getLogin().contains(query) || usuario.getNomeCompleto().contains(query)) {
				sugestoes.add(usuario);
			}
		}
		return sugestoes;
	}
	
	public void onItemSelect(SelectEvent event) {
		
			usuarioSelecionado = (Usuario) event.getObject();
			System.out.println(usuarioSelecionado);
//			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Item Selected", usuarioSelecionado.toString()));
	}

}
