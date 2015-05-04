package lt.pap.converter;

import java.time.Year;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class YearConverter implements
        AttributeConverter<Year, String> {

    @Override
    public String convertToDatabaseColumn(Year entityValue) {
        if (entityValue == null)
            return null;
        return entityValue.toString();
    }

    @Override
    public Year convertToEntityAttribute(String databaseValue) {
        if (databaseValue == null)
            return null;
        return Year.parse(databaseValue);
    }
}