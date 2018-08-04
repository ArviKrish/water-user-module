package com.water.user.springboot.config;

import java.util.Properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties
public class Configurations {
	
	@ConfigurationProperties(prefix = "myPro")
	@Bean
	public Properties myProperties() {
	    return new Properties();
	}
  
    private String name;
    private String environment;
    
    private DBPorperties dbProps;
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEnvironment() {
		return environment;
	}
	public void setEnvironment(String environment) {
		this.environment = environment;
	}
	public DBPorperties getDbProps() {
		return dbProps;
	}
	public void setDbProps(DBPorperties dbProps) {
		this.dbProps = dbProps;
	}
 
    
 
}
