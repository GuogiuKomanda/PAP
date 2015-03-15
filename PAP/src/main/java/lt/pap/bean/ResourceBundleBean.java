package lt.pap.bean;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@SuppressWarnings("rawtypes")
@Component(value = "msg")
public class ResourceBundleBean extends HashMap {

  private static final long serialVersionUID = -2627544396127327736L;

  @Autowired
  private MessageSource messageSource;

  @Override
  public String get(Object key) {
    String message;
    try {
      message = messageSource.getMessage((String) key, null, LocaleContextHolder.getLocale());
    } catch (NoSuchMessageException e) {
      message = "???" + key + "???";
    }
    return message;
  }
}
