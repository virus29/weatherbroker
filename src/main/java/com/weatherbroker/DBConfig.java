package com.weatherbroker;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jndi.JndiTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;


import javax.naming.NamingException;
import java.util.Properties;

@Configuration
@EnableJpaRepositories
public class DBConfig {


    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory()
            throws NamingException {
        LocalContainerEntityManagerFactoryBean em
                = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        Properties properties = new Properties();
//        properties.setProperty("hibernate.hbm2ddl.auto", "create");
        properties.setProperty("hibernate.show_sql", "true");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        properties.setProperty("hibernate.current_session_context_class", "thread");
        em.setJpaProperties(properties);
        em.setPackagesToScan("com.weatherbroker.repositories", "com.weatherbroker.entity");
        return em;
    }

    @Bean
    public DriverManagerDataSource dataSource() throws NamingException {
        return (DriverManagerDataSource) new JndiTemplate().lookup("java:/MySqlDS");
    }

    @Bean
    JpaTransactionManager transactionManager() throws NamingException {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
