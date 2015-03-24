package lt.prg.craft.bean.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import lt.prg.craft.Utils;
import lt.prg.craft.db.DBUtils;
import lt.prg.craft.model.Vadybininkas;


@FacesConverter(value="vadybininkasConverter")
public class VadybininkasConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		for(Vadybininkas vadybininkas : DBUtils.getVadybininkasList()){
			String s = Utils.getVadybininkasString(vadybininkas);
			if(s.equals(value)){
				return vadybininkas;
			}
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object object) {
		Vadybininkas vadybininkas = (Vadybininkas) object;
		return Utils.getVadybininkasString(vadybininkas);
	}
	

}
