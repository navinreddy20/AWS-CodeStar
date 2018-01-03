package com.aws.codestar.projecttemplates.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.aws.codestar.projecttemplates.controller.HelloWorldController;
import com.aws.codestar.projecttemplates.model.Alien;

/**
 * Spring configuration for sample application.
 */
@Configuration
@ComponentScan({ "com.aws.codestar.projecttemplates.configuration" })
@PropertySource("classpath:application.properties")
@EnableTransactionManagement
public class ApplicationConfig {

    /**
     * Retrieved from properties file.
     */
    @Value("${HelloWorld.SiteName}")
    private String siteName;

    @Bean
    public HelloWorldController helloWorld() {
        return new HelloWorldController(this.siteName);
    }

    /**
     * Required to inject properties using the 'Value' annotation.
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
    
  @Bean(name = "dataSource")
  public DataSource getDataSource() {
      BasicDataSource dataSource = new BasicDataSource();
      dataSource.setDriverClassName("com.mysql.jdbc.Driver");
     dataSource.setUrl("jdbc:mysql://localhost:3306/datajava");
     //dataSource.setUrl("jdbc:mysql://datajava.cksi1sdzwfpw.us-east-2.rds.amazonaws.com:3306/datajava");
      dataSource.setUsername("root");
     // dataSource.setPassword("abcd1234");
      dataSource.setPassword("0");
      return dataSource;
  }
  
  private Properties getHibernateProperties() {
  	Properties properties = new Properties();
  	properties.put("hibernate.show_sql", "true");
  	properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
  	properties.put("hibernate.hbm2ddl.auto", "create");
  	return properties;
  }

  @Autowired
  @Bean(name = "sessionFactory")
  public SessionFactory getSessionFactory(DataSource dataSource) {
  	LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
  	sessionBuilder.addProperties(getHibernateProperties());
  	sessionBuilder.addAnnotatedClasses(Alien.class);
  	
  	return sessionBuilder.buildSessionFactory();
  }
  
	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);

		return transactionManager;
	}
  
	
    

}
