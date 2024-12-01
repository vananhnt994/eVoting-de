package org.evoting.citizenmanagement.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.mindrot.jbcrypt.BCrypt;

import java.time.LocalDate;

public class CitizenDto {
    private Long id; // Optional, kann bei der Registrierung leer sein
    private String email;
    private String password; // Nur für die Registrierung relevant
    private String address;
    private String lastName;
    private String firstName;
    private LocalDate birthDate;

    // Konstruktoren
    public CitizenDto() {}

    public CitizenDto(String email, String password, String address, String lastName, String firstName, LocalDate birthDate) {
        this.email = email;
        this.password = password;
        this.address = address;
        this.birthDate = birthDate;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @JsonProperty("password")
    public String getPassword() {
        return password;
    }

    @JsonProperty("lastName")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String name) {
        this.lastName = name;
    }

    @JsonProperty("firstName")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @JsonProperty("address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @JsonProperty("birthDate")
    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    // Custom login method with hashed password check
    public boolean login(String inputPassword) {
        return BCrypt.checkpw(inputPassword, this.password);
    }
    // Hash the password using bcrypt
    public void setPassword(String password) {
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
    }

}