package manager;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import modelo.Usuario;
import persistence.UsuarioDao;


@FacesConverter(value="usuarioConverter")
public class UsuarioConverter implements Converter{

	@Override
	 public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
	 UsuarioDao udao= new UsuarioDao();
	 Usuario u = new Usuario();
	 u.setLogin(string);
	 Usuario usuario = (Usuario) udao.findByLogin(u);
	 return usuario;
	 }

	 @Override
	 public String getAsString(FacesContext fc, UIComponent uic, Object o) {
	 Usuario usuario = new Usuario();
	 usuario= (Usuario) o;
	 return usuario.getLogin();
	 }
	
}
