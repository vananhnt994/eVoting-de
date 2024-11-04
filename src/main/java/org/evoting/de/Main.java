package org.evoting.de;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@CrossOrigin(origins = "http://localhost:3000")
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class,args);
    }
}