package lt.pap.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "pap", name = "make")
public class Make implements Serializable {
    
    /**
     * 
     */
    private static final long serialVersionUID = 6191341332185060364L;

    /**
     * 
     */
    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    
    @Column(name = "make", nullable = false, length = 40)
    private String makeName;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getMakeName() {
      return makeName;
    }

    public void setMakeName(String makeName) {
      this.makeName = makeName;
    }

    @Override
    public String toString()
    {
        return makeName;
    }

    @Override
    public int hashCode() {
      return getId().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
      return (obj != null && obj instanceof Make && ((Make) obj).getId().equals(getId()));
    }
    
    
    
}
