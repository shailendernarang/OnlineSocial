package com.ss.SocialistB.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.ss.SocialistB.model.Blog;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages="com.ss")
public class HibernateConfig {
	
	public DataSource getOracleDataSource(){
		DriverManagerDataSource driverManagerDataSource = 
				new DriverManagerDataSource();
		driverManagerDataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		
		driverManagerDataSource.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
		
		driverManagerDataSource.setUsername("system");
		
		driverManagerDataSource.setPassword("941996");
		
		return driverManagerDataSource;		
	}
	
	public Properties getHibernateProperties() {
		Properties properties=new Properties();
		
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
		
		properties.setProperty("hibernate.show_sql", "true");
		
		properties.setProperty("hibernate.hbm2ddl.auto","update");
		
		return properties;
	}
	@Bean
	public SessionFactory getSessionFactory()
	{
		LocalSessionFactoryBuilder localSessionFactoryBuilder= 
				new LocalSessionFactoryBuilder(getOracleDataSource());
	
		localSessionFactoryBuilder.addProperties(getHibernateProperties());
		
		localSessionFactoryBuilder.addAnnotatedClass(Blog.class);
		
		return localSessionFactoryBuilder.buildSessionFactory();
	}

	
	@Bean
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory)
	{
		HibernateTransactionManager transactionManager=
				new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory);
		
		System.out.println("Data Source Created");
		
		return transactionManager;
	}
	

}