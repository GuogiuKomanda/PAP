package lt.pap.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(schema = "pap", name = "engine")
public class Engine extends AbstractEntity {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 3795065030996976288L;

	@Column(name = "name", nullable = true, length = 40)
    private String name;
   
    @Column(name = "code", nullable = false, length = 40)
    private String code;   
    
    @Column(name ="kw", nullable = false)
    private Integer kw;
    
    @Column(name ="cc", nullable = false)
    private Integer cc;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="fueltypeid")
    private FuelType fuelType;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getKw() {
		return kw;
	}

	public void setKw(Integer kw) {
		this.kw = kw;
	}

	public Integer getCc() {
		return cc;
	}

	public void setCc(Integer cc) {
		this.cc = cc;
	}

	public FuelType getFuelType() {
		return fuelType;
	}

	public void setFuelType(FuelType fuelType) {
		this.fuelType = fuelType;
	}

	@Override
	public boolean equals(Object obj) {
	      return (obj != null && obj instanceof Engine && ((Engine) obj).getId().equals(getId()));
	}
   
    
}
