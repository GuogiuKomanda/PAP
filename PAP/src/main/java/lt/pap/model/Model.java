package lt.pap.model;

import java.io.Serializable;
import java.time.YearMonth;

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
    @JoinColumn(name="modelgroup_id")
    private ModelGroup modelgroup;
    
    @Column(name = "model", nullable = false, length = 40)
    private String modelName;
    
    @Column(name = "from_year", nullable = false)
    private YearMonth from;

    @Column(name = "to_year", nullable = false)
    private YearMonth to;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public ModelGroup getModelgroup()
    {
        return modelgroup;
    }

    public void setModelgroup(ModelGroup modelgroup)
    {
        this.modelgroup = modelgroup;
    }

    public String getModelName()
    {
        return modelName;
    }

    public void setModelName(String modelName)
    {
        this.modelName = modelName;
    }

    public YearMonth getFrom()
    {
        return from;
    }

    public void setFrom(YearMonth from)
    {
        this.from = from;
    }

    public YearMonth getTo()
    {
        return to;
    }

    public void setTo(YearMonth to)
    {
        this.to = to;
    }

   
}
