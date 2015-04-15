package manager;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import modelo.Usuario;
import persistence.UsuarioDao;

@FacesConverter(value = "usuarioConverter")
public class UsuarioConverter implements Converter {
	Usuario u = new Usuario();

	@Override
	public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
		u.setLogin(string);
		Usuario usuario = (Usuario) new UsuarioDao().findByLogin(u);
		return usuario;
	}

	@Override
	public String getAsString(FacesContext fc, UIComponent uic, Object o) {
		u = (Usuario) o;
		return u.getLogin();
	}

}
