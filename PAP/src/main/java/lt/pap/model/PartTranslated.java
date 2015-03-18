package lt.pap.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PartTranslated implements java.io.Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 4407658690804385375L;

  private String name;
  private String description;

  @Column(name = "name", nullable = false, length = 40)
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Column(name = "description", nullable = false, length = 120)
  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
