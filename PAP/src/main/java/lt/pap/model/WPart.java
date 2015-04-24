package lt.pap.model;

import java.time.Year;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lt.pap.converter.YearConverter;


@Entity
@Table(schema = "pap", name = "WPart")
public class WPart extends AbstractEntity {
	/**
     * 
     */
	private static final long serialVersionUID = 3544256746775808177L;

	
	@Column(name = "year", nullable = false)
	@Convert(converter = YearConverter.class)
	private Year year;

	@Column(name = "fullcode", nullable = false, length = 40)
	private String fullCode;

	@Column(name = "status", nullable = false, length = 20)
	private String status;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "modelengineid")
	private ModelEngine modelEngine;

	public ModelEngine getModelEngine() {
		return modelEngine;
	}

	public void setModelEngine(ModelEngine modelEngine) {
		this.modelEngine = modelEngine;
	}

	public Year getYear() {
		return year;
	}

	public void setYear(Year year) {
		this.year = year;
	}

	public String getFullCode() {
		return fullCode;
	}

	public void setFullCode(String fullCode) {
		this.fullCode = fullCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public boolean equals(Object obj) {
		return (obj != null && obj instanceof WPart && ((WPart) obj).getId().equals(getId()));
	}
}
