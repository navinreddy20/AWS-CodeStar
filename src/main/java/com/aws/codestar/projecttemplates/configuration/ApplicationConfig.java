package com.aws.codestar.projecttemplates.configuration;

import java.beans.PropertyVetoException;
import java.util.Properties;

import javax.sql.DataSource;

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
import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * Spring configuration for sample application.
 */
@Configuration
@ComponentScan({ "com.aws.codestar.projecttemplates.configuration","com.aws.codestar.projecttemplates.dao" })
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
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        try {
  		dataSource.setDriverClass("com.mysql.jdbc.Driver");
  	} catch (PropertyVetoException e) {
  		// TODO Auto-generated catch block
  		e.printStackTrace();
  	}
//       dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/datajava");
//        dataSource.setUser("root");
//      dataSource.setPassword("0");
       dataSource.setJdbcUrl("jdbc:mysql://youtube.cksi1sdzwfpw.us-east-2.rds.amazonaws.com:3306/telusko");
        dataSource.setUser("root");
        dataSource.setPassword("abcd1234");
       
        return dataSource;
    }
    
    
    private Properties getHibernateProperties() {
    	Properties properties = new Properties();
    	properties.put("hibernate.show_sql", "true");
    	properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
    	properties.put("hibernate.hbm2ddl.auto", "update");
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
