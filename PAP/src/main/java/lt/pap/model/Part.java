package lt.pap.model;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.Table;

import org.springframework.context.i18n.LocaleContextHolder;

@Entity
@Table(schema = "pap", name = "parts")
public class Part extends AbstractEntity {

	/**
   * 
   */
	private static final long serialVersionUID = 1362525895835990400L;

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(schema = "pap", name = "partstranslated")
	@MapKeyJoinColumn(name = "locale")
	private Map<String, PartTranslated> translated = new HashMap<String, PartTranslated>();

	public Map<String, PartTranslated> getTranslated() {
		return this.translated;
	}

	public void setTranslated(Map<String, PartTranslated> localized) {
		this.translated = localized;
	}

	private PartTranslated getPartTranslated() {
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

	@Override
	public boolean equals(Object obj) {
		return (obj != null && obj instanceof Part && ((Part) obj).getId().equals(getId()));
	}

}
