package lt.pap.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.Table;

import org.springframework.context.i18n.LocaleContextHolder;


@Entity
@Table(schema = "pap", name = "parts")
public class Part implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1362525895835990400L;

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  private Long id;

  @ElementCollection(fetch=FetchType.EAGER)
  @CollectionTable(schema = "pap", name = "parts_translated")
  @MapKeyJoinColumn(name = "locale")
  private Map<String, PartTranslated> translated = new HashMap<String, PartTranslated>();

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Map<String, PartTranslated> getTranslated() {
    return this.translated;
  }

  public void setTranslated(Map<String, PartTranslated> localized) {
    this.translated = localized;
  }
  
  private PartTranslated getPartTranslated(){
    Locale locale = LocaleContextHolder.getLocale();
    return translated.get(locale.getLanguage());
  }
  
  public String getName() {
    return getPartTranslated().getName();
  }
  
  public String getDescription() {
    return getPartTranslated().getDescription();
  }

  public void setName(String name) {
  }

  public void setDescription(String description) {
  }
  
  
}
