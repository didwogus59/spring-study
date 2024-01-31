package com.example.demo.imageHandling;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.*;

@Configuration
@EnableJpaRepositories(
    basePackages = "com.example.demo.imageHandling.repository",
    entityManagerFactoryRef = "imageManagerFactory",
    transactionManagerRef = "imageTransactionManager"
)
@PropertySource(value = {"classpath:imageDB.properties"})
public class imageDBConfig {

    @Value("${image_url}")
    private String url;

    @Value("${image_name}")
    private String name;

    @Value("${image_password}")
    private String password;

    @Primary
    @Bean
    public DataSource imageSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        System.out.println(url);
        dataSource.setUrl(url);
        dataSource.setUsername(name);
        dataSource.setPassword(password);
        return dataSource;
    }
   
    @Primary
    @Bean
    public LocalContainerEntityManagerFactoryBean imageManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(imageSource());
        em.setPackagesToScan(new String[] {"com.example.demo.imageHandling.entity"});

        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        // adapter.setShowSql(false);
        // adapter.setGenerateDdl(true);
        em.setJpaVendorAdapter(adapter);

        HashMap<String, Object> prop = new HashMap<>();
        prop.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        prop.put("hibernate.hbm2ddl.auto", "update");
        //prop.put("hibernate.format_sql", true);
        em.setJpaPropertyMap(prop);

        return em;
    }
    
    @Primary
    @Bean
    public PlatformTransactionManager imageTransactionManager() {
        JpaTransactionManager manager = new JpaTransactionManager();
        manager.setEntityManagerFactory(imageManagerFactory().getObject());
        return manager;
    }
}