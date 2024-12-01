package org.evoting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class},scanBasePackages = "org.evoting.topicmanagement")
@CrossOrigin(origins = "http://localhost:3000")
public class TopicApplication {
    public static void main(String[] args) {
        SpringApplication.run(TopicApplication.class,args);
    }
}
