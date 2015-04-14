import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

import modelo.Usuario;
import persistence.UsuarioDao;


@ManagedBean
@ViewScoped
public class UsuarioMB {

	String resposta;
	List<Usuario> listaUsuario;
	
	public UsuarioMB() {
		// TODO Auto-generated constructor stub
	}

	public String getResposta() {
		return resposta;
	}

	public void setResposta(String resposta) {
		this.resposta = resposta;
	}

	public List<Usuario> getListaUsuario() {
		return listaUsuario;
	}

	public void setListaUsuario(List<Usuario> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}
	
	public List<Usuario> buscaUsuario() {
		return listaUsuario = new UsuarioDao().findByLoginOrNomeActives(resposta);
	}
	
}
