package manager;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import modelo.Paciente;
import persistence.PacienteDao;

@FacesConverter(value = "pacienteConverter")
public class PacienteConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
		Integer id = Integer.parseInt(string);
		Paciente paciente = (Paciente) new PacienteDao().findById(id);
		return paciente;
	}

	@Override
	public String getAsString(FacesContext fc, UIComponent uic, Object o) {
		Paciente p = (Paciente) o;
		if (p.equals(null)) {
			p.setNome("");
			return p.getNome();
		}
		return p.getId().toString();
	}
}
