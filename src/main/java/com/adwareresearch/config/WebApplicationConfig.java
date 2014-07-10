package com.adwareresearch.config;

import java.beans.PropertyVetoException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.adwareresearch.jsf.viewscope.ViewScope;
import com.google.common.base.Preconditions;
import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableTransactionManagement
@PropertySource({ "classpath:persistence-mysql.properties" })
@ComponentScan("com.adwareresearch")
public class WebApplicationConfig {
	
	@Autowired
    private Environment env;

    public WebApplicationConfig() {
        super();
    }
	
	@Bean
    public static CustomScopeConfigurer customScopeConfigurer() {
        Map<String, Object> scopes = new HashMap<>();
        scopes.put("view", new ViewScope());

        CustomScopeConfigurer bean = new CustomScopeConfigurer();
        bean.setScopes(scopes);

        return bean;
    }
	
	@Bean
    public LocalSessionFactoryBean sessionFactory() {
        final LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(restDataSource());
        sessionFactory.setPackagesToScan(new String[] { "com.adwareresearch.domain" });
        sessionFactory.setHibernateProperties(hibernateProperties());

        return sessionFactory;
    }
	
	@Bean
    public DataSource restDataSource() {
		final ComboPooledDataSource dataSource = new ComboPooledDataSource();
		try {
			dataSource.setDriverClass(Preconditions.checkNotNull(env.getProperty("jdbc.driverClassName")));
			dataSource.setUser(Preconditions.checkNotNull(env.getProperty("jdbc.user")));
			dataSource.setJdbcUrl(Preconditions.checkNotNull(env.getProperty("jdbc.url")));
			dataSource.setMinPoolSize(0);
			dataSource.setMaxPoolSize(100);
			dataSource.setAcquireIncrement(50);
			dataSource.setTestConnectionOnCheckout(true);
			dataSource.setMaxStatements(50);
			dataSource.setMaxIdleTime(300);
			dataSource.setPreferredTestQuery(Preconditions.checkNotNull(env.getProperty("hibernate.preferredTestQuery")));
			
		} catch (PropertyVetoException ex) {
			LoggerFactory.getLogger(WebApplicationConfig.class).error("Setting datasource error", ex);        
		}
        return dataSource;
    }
	
	@Bean
    @Autowired
    public HibernateTransactionManager transactionManager(final SessionFactory sessionFactory) {
        final HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory);

        return txManager;
    }
	
	@Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }
	
	final Properties hibernateProperties() {
        final Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
        hibernateProperties.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
        return hibernateProperties;
    }
}
