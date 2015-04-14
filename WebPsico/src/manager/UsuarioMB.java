package manager;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import modelo.Usuario;
import persistence.UsuarioDao;


@ManagedBean
@ViewScoped
public class UsuarioMB {

	String s;
	Usuario usuarioSelecionado;
	List<Usuario> listaUsuario;
	
	public UsuarioMB() {
		
	}

	

	public String getS() {
		return s;
	}



	public void setS(String s) {
		this.s = s;
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
	
	public List<Usuario> listaUsuarios() {
		listaUsuario = new UsuarioDao().findByLoginOrNomeActives(s);
		return listaUsuario;
	}
	
}
