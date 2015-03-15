package lt.pap.config;

import java.util.Locale;
import java.util.Properties;

import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"lt.pap.dao"})
@ComponentScan(basePackages = {"lt.pap.dao", "lt.pap.service", "lt.pap.bean","lt.pap.filter"})
@PropertySource("classpath:application.properties")
public class SpringDataConfig {

  @Autowired
  private Environment environment;

  @Bean
  public DataSource dataSource() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setUrl(environment.getProperty("db.url"));
    dataSource.setDriverClassName(environment.getProperty("db.driverClass"));
    dataSource.setUsername(environment.getProperty("db.username"));
    dataSource.setPassword(environment.getProperty("db.password"));
    return dataSource;
  }

  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws NamingException {
    HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
    hibernateJpaVendorAdapter.setDatabase(Database.POSTGRESQL);

    Properties properties = new Properties();


    properties.put("hibernate.dialect", environment.getProperty("hibernate.dialect"));
    properties.put("hibernate.show_sql", environment.getProperty("hibernate.showSQL"));
    properties.put("hibernate.format_sql", environment.getProperty("hibernate.formatSQL"));
    // properties.put("hibernate.hbm2ddl.auto", "create");

    // properties.put("javax.persistence.schema-generation.scripts.action", "create");
    // properties.put("javax.persistence.schema-generation.create-source", "metadata");
    // properties.put("javax.persistence.schema-generation.scripts.create-target", "sql/generated/create-schema.sql");

    LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
    entityManagerFactoryBean.setDataSource(dataSource());
    entityManagerFactoryBean.setPackagesToScan("lt.pap.model");
    entityManagerFactoryBean.setJpaVendorAdapter(hibernateJpaVendorAdapter);
    entityManagerFactoryBean.setJpaProperties(properties);

    return entityManagerFactoryBean;
  }

  @Bean
  public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
    JpaTransactionManager transactionManager = new JpaTransactionManager();
    transactionManager.setEntityManagerFactory(entityManagerFactory);

    return transactionManager;
  }
  
  @Bean
  public MessageSource messageSource() {
      ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
      messageSource.setBasename("lt.pap.translation");
      messageSource.setDefaultEncoding("UTF-8");
      return messageSource;
  }

  @Bean(name = "localeResolver")
  public LocaleResolver localeResolver() {
      SessionLocaleResolver result = new SessionLocaleResolver();
      result.setDefaultLocale(Locale.ENGLISH);
      return result;
  }
}
