package lt.pap.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import lt.pap.model.Make;
import lt.pap.service.MakeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("makeConverter")
@Scope("request")
public class MakeConverter implements Converter {

    @Autowired
    private MakeService makeService;

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        Make make = makeService.findOne(Long.valueOf(value));
        return make;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
        if(value == null)
            return null;
        return ((Make)value).getId().toString();
    }
}
