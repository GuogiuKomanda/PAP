package lt.pap.model;

import java.io.Serializable;
import java.time.YearMonth;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(schema = "pap", name = "user")
public class User implements Serializable {

  private static final long serialVersionUID = -5132176331654116607L;


  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  private Long id;

  @Column(name = "name", nullable = false, length = 40)
  private String name;
  
  @Column(name = "test_date", nullable = false)
 //@DateTimeFormat(pattern = "yyyy-mm")
  private YearMonth date;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public YearMonth getDate() {
    return date;
  }

  public void setDate(YearMonth date) {
    this.date = date;
  }

}
