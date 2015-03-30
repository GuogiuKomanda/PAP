package lt.pap.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(schema = "pap", name = "fuel_type")
public class FuelType extends AbstractEntity
{

    /**
     * 
     */
    private static final long serialVersionUID = 5940475859168370706L;

    
    @Column(name = "name", nullable = false, length = 40)
    private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object obj) {
		return (obj != null && obj instanceof FuelType && ((FuelType) obj).getId().equals(getId()));
	}
	
	
}
