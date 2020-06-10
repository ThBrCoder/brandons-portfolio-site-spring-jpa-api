package com.brandon.portfolio.site.configurations;

import java.beans.PropertyVetoException;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
// @ComponentScan(basePackages="com.brandon.portfolio.site")
@PropertySource("classpath:persistence-h2.properties")
public class DataSourceConfigurations {
	// This will configure the data source that will be referenced and used in
	// AuthSecurityConfiguration.java
	
	// Set up a variable to hold the properties
	@Autowired
	private Environment env; // Will hold data read from properties file
	
	// Set up a logger for diagnostics
	private Logger logger = Logger.getLogger(getClass().getName());


	// Define a bean for our security data source
	@Bean
	public DataSource securityDataSource() {
		
		// Create a connection pool
		ComboPooledDataSource securityDataSource = new ComboPooledDataSource();
		
		// Set the JDBC driver class
		try {
			securityDataSource.setDriverClass(env.getProperty("jdbc.driver"));
		} catch (PropertyVetoException e) {
			throw new RuntimeException(e);
		}

		
		// Set database connection properties
		securityDataSource.setJdbcUrl(env.getProperty("jdbc.url"));
		securityDataSource.setUser(env.getProperty("jdbc.user"));
		securityDataSource.setPassword(env.getProperty("jdbc.password"));
		
		// Set the connection pool properties
		securityDataSource.setInitialPoolSize(getIntProperty("connection.pool.initialPoolSize"));
		securityDataSource.setMinPoolSize(getIntProperty("connection.pool.minPoolSize"));
		securityDataSource.setMaxPoolSize(getIntProperty("connection.pool.maxPoolSize"));
		securityDataSource.setMaxIdleTime(getIntProperty("connection.pool.maxIdleTime"));
		
		
		return securityDataSource;
	}
	
	// Need a helper method
	// Read environment property to convert to int
	private int getIntProperty(String propName) {
		String propVal = env.getProperty(propName);
		
		int intPropVal = Integer.parseInt(propVal);
		return intPropVal;
	}	
}
