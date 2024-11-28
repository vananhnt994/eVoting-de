package org.evoting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class},scanBasePackages = "org.evoting")
public class CitizenApplication {
    public static void main(String[] args) {
        SpringApplication.run(CitizenApplication.class,args);
    }
}
