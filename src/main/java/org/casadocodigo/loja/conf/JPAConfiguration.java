package org.casadocodigo.loja.conf;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
public class JPAConfiguration {

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(datasource());
		em.setPackagesToScan(new String[]{"org.casadocodigo.loja.model"});
		JpaVendorAdapter vendorA = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorA);
		em.setJpaProperties(addtionalProperties());
		return em;
	}
	private Properties addtionalProperties() {
		Properties p = new Properties();
		p.setProperty("hibernate.hbm2ddl.auto", "update");
		p.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		p.setProperty("hibernate.show_sql", "true");
		return p;
	}
	
	@Bean
	public DataSource datasource(){
		DriverManagerDataSource datasource = new DriverManagerDataSource();
		datasource.setDriverClassName("com.mysql.jdbc.Driver");
		datasource.setUrl("jdbc:mysql://localhost:3306/casadocodigo");
		datasource.setUsername("root");
		datasource.setPassword("root");
		return datasource;
	}
	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory emf){
		JpaTransactionManager tr = new JpaTransactionManager();
		tr.setEntityManagerFactory(emf);
		return tr;
	}
}
