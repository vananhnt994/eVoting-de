package org.evoting.citizenmanagement.application.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(basePackages = "org.evoting.citizenmanagement.domain.repository")
@ComponentScan(basePackages = "org.evoting.citizenmanagement")
public class DataSourceConfig {

    @Bean
    @ConfigurationProperties("spring.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .url("jdbc:mysql://localhost:3306/evoting")
                .username("root")
                .password("IloveSoftwaredevelopment")
                .build();
    }

    @Bean
    public HibernatePropertiesCustomizer hibernatePropertiesCustomizer() {
        return hibernateProperties -> {
            hibernateProperties.put("hibernate.hbm2ddl.auto", "update"); // Automatische Schema-Aktualisierung
            hibernateProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect"); // Dialekt für MySQL 5.x
            // Für MySQL 8.x verwende: "org.hibernate.dialect.MySQL8Dialect"
            hibernateProperties.put("hibernate.show_sql", true); // SQL-Abfragen im Log anzeigen
            hibernateProperties.put("hibernate.format_sql", true); // SQL-Abfragen formatieren
        };
    }
}