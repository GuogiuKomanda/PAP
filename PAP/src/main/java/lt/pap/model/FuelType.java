package lt.pap.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "pap", name = "fueltype")
public class FuelType implements Serializable
{

    /**
     * 
     */
    private static final long serialVersionUID = 5940475859168370706L;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @Column(name = "fuel", nullable = false, length = 40)
    private String fuel;
    public Long getId()
    {
        return id;
    }
    public void setId(Long id)
    {
        this.id = id;
    }
    public String getFuel()
    {
        return fuel;
    }
    public void setFuel(String fuel)
    {
        this.fuel = fuel;
    }
    
    
    
    
}
