package lt.pap.model;

import javax.persistence.Column;
import javax.persistence.Entity;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
