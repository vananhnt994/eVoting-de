package org.evoting.de.entites;

import java.util.Date;

public class Citizen {
    private String id;
    private String email;
    private String password;
    private String lastName;
    private String firstName;
    private String address;
    private Date birthDate;

    // Constructor
    public Citizen(String id, String email, String password, String lastName, String firstName, String address, Date birthDate) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.lastName = lastName;
        this.firstName = firstName;
        this.address = address;
        this.birthDate = birthDate;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getfamilyName() {
        return lastName;
    }

    public void setfamilyName(String name) {
        this.lastName = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
}