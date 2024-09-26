package com.multi.datasource.product.config;

import java.util.*;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.*;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.orm.jpa.JpaVendorAdapter;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.multi.product.repository",
        entityManagerFactoryRef = "productEntityMangerFactoryBean",
        transactionManagerRef = "productTransactionManager"
)

public class DataSourceProductConfig {
	

	@Autowired
	private Environment environment;
	
	@Bean(name = "productDataSource")
	@Primary
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUrl(environment.getProperty("second.datasource.url"));
		dataSource.setDriverClassName(environment.getProperty("second.datasource.driver-class-name"));
		dataSource.setUsername(environment.getProperty("second.datasource.username"));
		dataSource.setPassword(environment.getProperty("second.datasource.password"));
		return dataSource;
	}

	@Primary
	@Bean(name = "productEntityMangerFactoryBean")
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
		LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
		bean.setDataSource(dataSource());
		bean.setPackagesToScan("com.multi.product.entity");

		JpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		bean.setJpaVendorAdapter(adapter);

		Map<String, String> props = new HashMap<>();
		props.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		props.put("hibernate.show_sql", "true");
		props.put("hibernate.hbm2ddl.auto", "update");
		bean.setJpaPropertyMap(props);

		return bean;
	}
	
	@Bean(name = "productTransactionManager")
	@Primary
	public PlatformTransactionManager transactionManager() {
		JpaTransactionManager manager = new JpaTransactionManager();
		manager.setEntityManagerFactory(entityManagerFactoryBean().getObject());
		return manager;
	}

}
