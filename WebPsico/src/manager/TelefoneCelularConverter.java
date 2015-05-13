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
@FacesConverter(value = "telefoneCelularConverter")
public class TelefoneCelularConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		// Aqui faremos a conversão de telefone formatado String( (99)
		// 99999-9999 ) para String nao formatada( 99999999999 )
		String retorno = null;
		if (!(value.equals(""))) {
			retorno = value;
			String somenteNumero = retorno.trim().replace("(", "")
					.replace(")", "").replace(" ", "").replace("-", "");
			retorno = somenteNumero;
		}

		System.out.println("--------------" + retorno);
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object obj) {
		// Aqui faremos o inverso. Conversão de String nao formatada(
		// 99999999999 ) para String formatada ( (99) 99999-9999 )
		String retorno = obj.toString();
		if (retorno.length() == 11) {
			retorno = "(" + retorno.substring(0, 2) + ") "
					+ retorno.substring(3, 7) + "-" + retorno.substring(8);
		} else {
			retorno = "";
		}
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!" + retorno);
		return retorno;
	}

}
