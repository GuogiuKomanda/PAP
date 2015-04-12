package lt.pap.converter;

import java.time.YearMonth;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class YearMonthConverter implements
		AttributeConverter<YearMonth, String> {

	@Override
	public String convertToDatabaseColumn(YearMonth entityValue) {
		if (entityValue == null)
			return null;
		return entityValue.toString();
	}

	@Override
	public YearMonth convertToEntityAttribute(String databaseValue) {
		if (databaseValue == null)
			return null;
		return YearMonth.parse(databaseValue);
	}
}