package lt.pap.filter;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.LocaleResolver;

//@WebFilter(urlPatterns = {"/*"}, description = "Locale Filter")
@Component("localeFilter")
public class LocaleFilter extends OncePerRequestFilter  {

  @Autowired
  private LocaleResolver localeResolver;

//  /**
//   * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
//   */
//  public void doFilter(ServletRequest pRequest, ServletResponse pResponse, FilterChain pFilterChain) throws IOException, ServletException {
//    if (!(pRequest instanceof HttpServletRequest)) {
//      pFilterChain.doFilter(pRequest, pResponse);
//      return;
//    }
//    Locale locale = localeResolver.resolveLocale((HttpServletRequest) pRequest);
//    LocaleContextHolder.setLocale(locale, true);
//
//    pFilterChain.doFilter(pRequest, pResponse);
//  }
//
//  /**
//   * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
//   */
//  public void init(FilterConfig filterConfig) throws ServletException {
//    WebApplicationContext springContext = WebApplicationContextUtils.getWebApplicationContext(filterConfig.getServletContext());
//    localeResolver = springContext.getBean(LocaleResolver.class);
//  }
//
//  /**
//   * @see javax.servlet.Filter#destroy()
//   */
//  public void destroy() {}

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
  Locale locale = localeResolver.resolveLocale((HttpServletRequest) request);
  LocaleContextHolder.setLocale(locale, true);

  filterChain.doFilter(request, response);
    
  }
}
