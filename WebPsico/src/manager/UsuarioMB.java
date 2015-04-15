package manager;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import modelo.Usuario;
import persistence.UsuarioDao;

@ManagedBean
@RequestScoped
public class UsuarioMB {

	Usuario usuarioSelecionado;
	List<Usuario> listaUsuario = new ArrayList<Usuario>();

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

}
