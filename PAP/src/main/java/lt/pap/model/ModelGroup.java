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
@Table(schema = "pap", name = "modelgroup")
public class ModelGroup implements Serializable
{
    
    /**
     * 
     */
    private static final long serialVersionUID = -1229686035956680422L;
    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    
    @Column(name = "modelgroup", nullable = false, length = 40)
    private String modelgroup;   
   
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="make_id")
    private Make make;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getModelgroup()
    {
        return modelgroup;
    }

    public void setModelgroup(String modelgroup)
    {
        this.modelgroup = modelgroup;
    }

    public Make getMake()
    {
        return make;
    }

    public void setMake(Make make)
    {
        this.make = make;
    }
    
}
