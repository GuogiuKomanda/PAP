package lt.prg.craft.bean.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import lt.prg.craft.Utils;
import lt.prg.craft.db.DBUtils;
import lt.prg.craft.model.Subjektas;

@FacesConverter(value="subjektasConverter")
public class SubjektasConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		for(Subjektas subjektas : DBUtils.getSubjektasList()){
			String s = Utils.getSubjektasString(subjektas);
			if(s.equals(value)){
				return subjektas;
			}
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object object) {
		Subjektas subjektas = (Subjektas) object;
		return Utils.getSubjektasString(subjektas);
	}
}
