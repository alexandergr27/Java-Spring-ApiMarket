package com.apimarket.connection;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import static org.hibernate.cfg.Environment.*;

@Configuration
@PropertySource("classpath:api.properties")
@EnableTransactionManagement
@ComponentScans(value = {@ComponentScan("com.apimarket.data"), @ComponentScan("com.apimarket.service") })
public class ApiConfiguration {
	@Autowired
	private Environment env;
	
	@Bean
	public LocalSessionFactoryBean getSessionFactory() {
		LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
		
		Properties property = new Properties();
		property.put(DRIVER, env.getProperty("mysql.driver"));
		property.put(URL, env.getProperty("mysql.url"));
		property.put(USER, env.getProperty("mysql.user"));
		property.put(PASS, env.getProperty("mysql.password"));
		
		property.put(SHOW_SQL, env.getProperty("hibernate.show_sql"));
		property.put(HBM2DDL_AUTO, env.getProperty("hibernate.hbm2ddl.auto"));
		
		property.put(C3P0_MIN_SIZE, env.getProperty("hibernate.c3p0.min_size"));
		property.put(C3P0_MAX_SIZE, env.getProperty("hibernate.c3p0.max_size"));
		property.put(C3P0_ACQUIRE_INCREMENT, env.getProperty("hibernate.c3p0.acquire_increment"));
		property.put(C3P0_TIMEOUT, env.getProperty("hibernate.c3p0.timeout"));
		property.put(C3P0_MAX_STATEMENTS, env.getProperty("hibernate.c3p0.max_statements"));
		
		factoryBean.setHibernateProperties(property);
		factoryBean.setPackagesToScan("com.apimarket.model");
		
		return factoryBean;
	}
	
	@Bean
	public HibernateTransactionManager transactionManager() {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(getSessionFactory().getObject());
		
		return transactionManager;
	}
	
}
