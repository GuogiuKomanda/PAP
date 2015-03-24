package lt.pap.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(schema = "pap", name = "engine")
public class Engine implements Serializable {
    
  
    
    /**
     * 
     */
    private static final long serialVersionUID = -7524265174166547947L;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    
    @Column(name = "enginename", nullable = true, length = 40)
    private String enginename;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="fueltype_id")
    private FuelType fueltype;
    
    
    @Column(name = "enginecode", nullable = false, length = 40)
    private String enginecode;
    
    
    @Column(name ="kw", nullable = false)
    private Integer kw;
    
    @Column(name ="cc", nullable = false)
    private Integer cc;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getEnginecode()
    {
        return enginecode;
    }

    public void setEngine(String enginecode)
    {
        this.enginecode = enginecode;
    }

    public Integer getKw()
    {
        return kw;
    }

    public void setKw(Integer kw)
    {
        this.kw = kw;
    }

    public Integer getCc()
    {
        return cc;
    }

    public void setCc(Integer cc)
    {
        this.cc = cc;
    }
   
}
