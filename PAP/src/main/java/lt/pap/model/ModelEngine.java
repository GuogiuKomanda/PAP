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
@Table(schema = "pap", name = "modelengine")
public class ModelEngine implements Serializable {
  /**
     * 
     */
  private static final long serialVersionUID = 1783088191174527679L;

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "model_id")
  private Model model;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "engine_id")
  private Engine engine;

  @Column(name = "from_year", nullable = false)
  private YearMonth from;

  @Column(name = "to_year", nullable = false)
  private YearMonth to;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Model getModel() {
    return model;
  }

  public void setModel(Model model) {
    this.model = model;
  }

  public Engine getEngine() {
    return engine;
  }

  public void setEngine(Engine engine) {
    this.engine = engine;
  }

  public YearMonth getFrom() {
    return from;
  }

  public void setFrom(YearMonth from) {
    this.from = from;
  }

  public YearMonth getTo() {
    return to;
  }

  public void setTo(YearMonth to) {
    this.to = to;
  }
}
