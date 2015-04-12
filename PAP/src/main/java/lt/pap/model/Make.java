package lt.pap.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(schema = "pap", name = "make")
public class Make extends AbstractEntity {

	/**
     * 
     */
	private static final long serialVersionUID = 6191341332185060364L;

	@Column(name = "name", nullable = false, length = 40)
	private String name;

	@OneToMany(mappedBy = "make", fetch=FetchType.EAGER )
	private List<ModelGroup> modelGroupList = new ArrayList<ModelGroup>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ModelGroup> getModelGroupList() {
		return modelGroupList;
	}

	public void setModelGroupList(List<ModelGroup> modelGroupList) {
		this.modelGroupList = modelGroupList;
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public boolean equals(Object obj) {
		return (obj != null && obj instanceof Make && ((Make) obj).getId().equals(getId()));
	}
}
