package ru.lanit.hospital.configuration;

import org.hibernate.SessionFactory;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration

@EntityScan("ru.lanit.hospital.model")
@EnableTransactionManagement
@ComponentScan("ru.lanit.hospital")
/*@PropertySource("classpath:application.properties")*/

public class AppConfig {

    private final Properties hibernateProperties() {
        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.show_sql", "sql");
        hibernateProperties.setProperty(
                "hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");

        return hibernateProperties;
    }


    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/hospital?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
        dataSource.setUsername("root");
        dataSource.setPassword("mySQLp@ssw0rd");
        return dataSource;
    }

    @Bean(name = "sessionFactory")
    public SessionFactory sessionFactory() {
        LocalSessionFactoryBuilder sfb = new LocalSessionFactoryBuilder(dataSource());
        sfb.scanPackages("ru.lanit.hospital.model");
        sfb.addProperties(hibernateProperties());
        return sfb.buildSessionFactory();
    }

    @Bean(name = "transactionManager")
    public HibernateTransactionManager getManager() {
        HibernateTransactionManager manager = new HibernateTransactionManager(sessionFactory());
        /* manager.setSessionFactory(sessionFactory());*/
        return manager;
    }


}
