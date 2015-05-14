package manager;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/*//Aqui eu digo que esse cara é um FACES CONVERTER, Antes eu tinha de mapear no facesc-config.xml
 @FacesConverter(value="pesoConverter")
 public class PesoConverter implements Converter {
 //Aqui faremos a conversão de String(82kg) para Inteiro(82)
 public Object getAsObject(FacesContext context, UIComponent component, String value) {
 int retorno = 0;
 if (!(value.equals(""))) {
 String peso = value;
 String somenteNumero = peso.replace("kg", "");
 retorno = Integer.parseInt(somenteNumero);
 }
 return retorno;
 }
 //Aqui faremos o inverso. Conversão de Inteiro(82) para String(82kg)
 public String getAsString(FacesContext context, UIComponent component, Object value) {
 String peso = value.toString();
 peso = peso + "kg";
 return peso;
 }
 }*/
@FacesConverter(value = "cpfConverter")
public class CpfConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		// Aqui faremos a conversão de cpf formatado String( 999.999.999-99 )
		// para String nao formatada( 99999999999 )
		String retorno = null;
		if (!(value.equals(""))) {
			retorno = value;
			String somenteNumero = retorno.trim().replace(".", "")
					.replace("-", "");
			retorno = somenteNumero;
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object obj) {
		// Aqui faremos o inverso. Conversão de String nao formatada(
		// 99999999999 ) para String formatada ( 999.999.999-99 )
		String retorno = obj.toString();
		if (retorno.length() == 11) {
			String somenteNumero = retorno.substring(0, 3) + "." + retorno.substring(3, 6) + "." + retorno.substring(6, 9)+ "-" + retorno.substring(9);
			retorno = somenteNumero;
		} else {
			retorno = "";
		}
		return retorno;
	}
}
