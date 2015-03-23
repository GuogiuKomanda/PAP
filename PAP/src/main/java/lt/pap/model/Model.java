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
@Table(schema = "pap", name = "model")
public class Model implements Serializable {
    
 
    private static final long serialVersionUID = -859643164493403394L;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="make_id")
    private Make make_id;
    
    @Column(name = "model", nullable = false, length = 40)
    private String model;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Make getMake_id()
    {
        return make_id;
    }

    public void setMake_id(Make make_id)
    {
        this.make_id = make_id;
    }

    public String getModel()
    {
        return model;
    }

    public void setModel(String model)
    {
        this.model = model;
    } 
    
}