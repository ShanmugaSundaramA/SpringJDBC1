package com.sundar.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = "com.sundar")
@EnableTransactionManagement
public class StudentConfig {

	public DataSource getDataSource() {

		DataSource dataSource = new DriverManagerDataSource( "jdbc:mysql://localhost:3306/school", "root" , "@sundarSowmi@0129#" );

		return dataSource;
	}

	@Bean
	public JdbcTemplate jdbcTemplate() {

		JdbcTemplate template = new JdbcTemplate( getDataSource() );

		return template;
	}
	
	@Bean
    public DataSourceTransactionManager transactionManager() {
		 
		DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
    	 
		dataSourceTransactionManager.setDataSource(getDataSource());
		
		return dataSourceTransactionManager;
    }
	

}
