package org.evoting.de.entites;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
@Entity
public class Citizen {
    private @Id
    @GeneratedValue Long id;
    private String email;
    private String password;
    private String lastName;
    private String firstName;
    private String address;
    private Date birthDate;


    public Citizen() {

    }

    // Getters and Setters
    @JsonProperty("id")
    public Long getId() {
        return this.id;
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

    public void setPassword(String password) {
        this.password = password;
    }

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
    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
}