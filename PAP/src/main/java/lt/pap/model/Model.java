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
@Table(schema = "pap", name = "model")
public class Model extends AbstractEntity {
    
 
    private static final long serialVersionUID = -859643164493403394L;
    
    @Column(name = "name", nullable = false, length = 40)
    private String name;
    
    
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="model_group_id")
    private ModelGroup modelGroup;
    
    @Column(name = "from_year", nullable = false)
    @Convert(converter = YearMonthConverter.class)
    private YearMonth from;

    @Column(name = "to_year")
    @Convert(converter = YearMonthConverter.class)
    private YearMonth to;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ModelGroup getModelGroup() {
		return modelGroup;
	}

	public void setModelGroup(ModelGroup modelGroup) {
		this.modelGroup = modelGroup;
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
		return (obj != null && obj instanceof Model && ((Model) obj).getId().equals(getId()));
	}
	
}
