package lt.pap.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(schema = "pap", name = "model_group")
public class ModelGroup extends AbstractEntity
{
    
    /**
     * 
     */
    private static final long serialVersionUID = -1229686035956680422L;
    
    @Column(name = "name", nullable = false, length = 40)
    private String name;   
   
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="make_id")
    private Make make;
    

    @OneToMany(mappedBy="modelGroup", fetch=FetchType.EAGER)
    private List<Model> modelList = new ArrayList<Model>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Make getMake() {
		return make;
	}

	public void setMake(Make make) {
		this.make = make;
	}

	public List<Model> getModelList() {
		return modelList;
	}

	public void setModelList(List<Model> modelList) {
		this.modelList = modelList;
	}

	@Override
	public boolean equals(Object obj) {
		return (obj != null && obj instanceof ModelGroup && ((ModelGroup) obj).getId().equals(getId()));
	}
}
