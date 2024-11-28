package org.evoting.de.citizenmanagement;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class},scanBasePackages = "org.evoting.de.citizenmanagement")
public class UserApplication {

}
