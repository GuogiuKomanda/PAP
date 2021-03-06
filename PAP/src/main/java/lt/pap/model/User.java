package lt.pap.model;

import java.time.YearMonth;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;

import lt.pap.converter.YearMonthConverter;

@Entity
@Table(schema = "pap", name = "user")
public class User extends AbstractEntity {

	private static final long serialVersionUID = -5132176331654116607L;

	@Column(name = "name", nullable = false, length = 40)
	private String name;

	@Column(name = "testdate", nullable = false)
	@Convert(converter = YearMonthConverter.class)
	private YearMonth date;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public YearMonth getDate() {
		return date;
	}

	public void setDate(YearMonth date) {
		this.date = date;
	}

	@Override
	public boolean equals(Object obj) {
		return (obj != null && obj instanceof User && ((User) obj).getId().equals(getId()));
	}

}
