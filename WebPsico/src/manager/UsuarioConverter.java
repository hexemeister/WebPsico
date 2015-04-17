package manager;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import modelo.Usuario;
import persistence.UsuarioDao;

@FacesConverter(value = "usuarioConverter")
public class UsuarioConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
		Integer id = Integer.parseInt(string);
		Usuario usuario = (Usuario) new UsuarioDao().FindById(id);
		return usuario;
	}

	@Override
	public String getAsString(FacesContext fc, UIComponent uic, Object o) {
		Usuario u = (Usuario) o;
		return u.getId().toString();
	}

}
