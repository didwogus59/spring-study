package com.example.demo.mySQL;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.*;

@EnableJpaRepositories(
    basePackages = "com.example.demo.mySQL",
    entityManagerFactoryRef = "mySQLManagerFactory",
    transactionManagerRef = "mySQLTransactionManager"
)
@Configuration
@PropertySource("classpath:mySQL.properties")
public class mySQLConfig {

    @Value("${url}")
    private String url;

    @Value("${name}")
    private String name;

    @Value("${password}")
    private String password;

    @Bean
    public DataSource mySQLSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl(url);
        dataSource.setUsername(name);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Qualifier("mySQLEM")
    @Bean
    public LocalContainerEntityManagerFactoryBean mySQLManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(mySQLSource());
        em.setPackagesToScan(new String[] {"com.example.demo.mySQL"});

        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setShowSql(true);
        adapter.setGenerateDdl(true);
        em.setJpaVendorAdapter(adapter);

        HashMap<String, Object> prop = new HashMap<>();
        prop.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        prop.put("hibernate.hbm2ddl.auto", "update");
        prop.put("hibernate.format_sql", true);
        em.setJpaPropertyMap(prop);

        return em;
    }
    

    @Bean
    public PlatformTransactionManager mySQLTransactionManager() {
        JpaTransactionManager manager = new JpaTransactionManager();
        manager.setEntityManagerFactory(mySQLManagerFactory().getObject());
        return manager;
    }

    @Qualifier("myTemplate")
    @Bean
    public JdbcTemplate myTemplate() {
        return new JdbcTemplate(mySQLSource());
    }
}