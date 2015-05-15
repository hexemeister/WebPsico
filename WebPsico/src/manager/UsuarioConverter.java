package manager;

import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import modelo.Usuario;
import persistence.UsuarioDao;

@FacesConverter(value = "usuarioConverter")
public class UsuarioConverter implements Converter {

	@ManagedProperty(value = "#{loginMB.logado")
	Usuario logado;

	public Usuario getLogado() {
		return logado;
	}

	public void setLogado(Usuario logado) {
		this.logado = logado;
	}

	@Override
	public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
		Integer id = Integer.parseInt(string);
		Usuario usuario = (Usuario) new UsuarioDao().findById(id);
		return usuario;
	}

	@Override
	public String getAsString(FacesContext fc, UIComponent uic, Object o) {
		Usuario u = (Usuario) o;
		if (u.equals(null)) {
			u = logado;
		}
		return u.getId().toString();
	}
}
