package lt.prg.craft.bean.validator;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("todayOrLater")
public class TodayOrLaterDateValidator implements Validator {
	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		if(value == null)
			return;
        Date date = (Date) value;
        Date today = new Date();

        if (date.before(today)) {
            String message = "Data turi bûti ðiandien arba vëlesnë!";
            throw new ValidatorException(new FacesMessage(message));
        }
	}

}
