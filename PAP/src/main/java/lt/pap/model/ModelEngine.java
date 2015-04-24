package lt.pap.model;

import java.time.YearMonth;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lt.pap.converter.YearMonthConverter;

@Entity
@Table(schema = "pap", name = "modelengine")
public class ModelEngine extends AbstractEntity {
	/**
     * 
     */
	private static final long serialVersionUID = 1783088191174527679L;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "modelid")
	private Model model;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "engineid")
	private Engine engine;

	@Column(name = "fromyear", nullable = false)
	@Convert(converter = YearMonthConverter.class)
	private YearMonth from;

	@Column(name = "toyear", nullable = true)
	@Convert(converter = YearMonthConverter.class)
	private YearMonth to;

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public Engine getEngine() {
		return engine;
	}

	public void setEngine(Engine engine) {
		this.engine = engine;
	}

	public YearMonth getFrom() {
		return from;
	}

	public void setFrom(YearMonth from) {
		this.from = from;
	}

	public YearMonth getTo() {
		return to;
	}

	public void setTo(YearMonth to) {
		this.to = to;
	}

	@Override
	public boolean equals(Object obj) {
		return (obj != null && obj instanceof ModelEngine && ((ModelEngine) obj)
				.getId().equals(getId()));
	}
}
