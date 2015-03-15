package lt.pap.bean;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.LocaleResolver;

@Component("sessionBean")
@Scope("session")
public class SessionBean implements Serializable {

  private static final long serialVersionUID = 3729328103103002629L;

  @Autowired
  private LocaleResolver localeResolver;

  @Autowired(required = true)
  private HttpServletRequest request;

  @Autowired(required = true)
  private HttpServletResponse response;

  private String localeString;

  private Map<String, Locale> supportedLocales;

  public SessionBean() {
    supportedLocales = new LinkedHashMap<String, Locale>();
    supportedLocales.put("English", Locale.ENGLISH);
    supportedLocales.put("Lietuvi\u0173", new Locale("lt"));

    localeString = Locale.ENGLISH.getLanguage();
  }

  public Map<String, Locale> getSupportedLocales() {
    return supportedLocales;
  }

  public String getLocaleString() {
    return localeString;
  }

  public void setLocaleString(String localeString) {
    this.localeString = localeString;
  }

  public void localeChanged() {
    for (Map.Entry<String, Locale> entry : supportedLocales.entrySet()) {
      if (entry.getValue().toString().equals(localeString)) {
        Locale locale = entry.getValue();

        localeResolver.setLocale(request, response, locale);
        LocaleContextHolder.setLocale(locale, true);

        break;
      }
    }
  }

}
