package org.evoting.citizenmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication(exclude={WebMvcAutoConfiguration.class},scanBasePackages = "org.evoting.citizenmanagement")
@CrossOrigin(origins = "http://localhost:3000")
public class CitizenApplication {
    public static void main(String[] args) {
        SpringApplication.run(CitizenApplication.class,args);
    }
}
