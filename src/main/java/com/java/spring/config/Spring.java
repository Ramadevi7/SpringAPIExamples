package com.java.spring.config;


import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan("com.java.spring")
@EnableWebMvc
@EnableTransactionManagement
public class Spring {
 
	@Bean
	public DataSource dataSource()
	{
		DriverManagerDataSource datasource = new DriverManagerDataSource();
		datasource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		datasource.setUrl("jdbc:oracle:thin:@192.168.10.230:1521:orcl");
		datasource.setUsername("training");
		datasource.setPassword("training");
		return datasource;
	}

	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource)
	{
		return new JdbcTemplate(dataSource);
	}
	

}
