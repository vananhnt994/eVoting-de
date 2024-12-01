package org.evoting.citizenmanagement.domain.model.citizen;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import org.mindrot.jbcrypt.BCrypt;

@Entity
public class Citizen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Eindeutige ID für den Benutzer
    @Email
    @NotNull
    private String email;
    @NotNull
    private String password; // store the hashed pwd
    private String lastName;
    private String firstName;
    private String address;
    private LocalDate birthDate;


    public Citizen() {

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
    public String getLastname() {
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