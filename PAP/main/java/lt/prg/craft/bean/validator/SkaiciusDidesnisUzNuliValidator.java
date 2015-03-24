package lt.prg.craft.bean.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("moreThanZero")
public class SkaiciusDidesnisUzNuliValidator implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		Double skaicius = (Double) value;
		if(skaicius <=0){
			throw new ValidatorException(new FacesMessage("Ávestas skaièius turi bûti > 0!"));
		}

	}

}
