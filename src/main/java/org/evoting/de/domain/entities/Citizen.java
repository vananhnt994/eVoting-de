package org.evoting.de.domain.entities;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    // Constructor with required parameters, ensuring password is hashed
    /*public Citizen(String email, String firstName, String lastName, String password, LocalDate birthDate) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        //setPassword(password); // Automatically hash password on setting
        this.birthDate = birthDate;
    }*/
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

    /*public void setPassword(String password) {
        this.password = password;
    }*/

    @JsonProperty("familyName")
    public String getfamilyName() {
        return lastName;
    }

    public void setfamilyName(String name) {
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
    /*public boolean login(String inputPassword) {
        return this.password.equals(inputPassword);
    }*/

    // Custom login method with hashed password check
    public boolean login(String inputPassword) {
        return BCrypt.checkpw(inputPassword, this.password);
    }
    // Hash the password using bcrypt
    public void setPassword(String password) {
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
    }
}