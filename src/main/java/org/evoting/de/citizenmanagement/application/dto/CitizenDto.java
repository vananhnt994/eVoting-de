package org.evoting.de.citizenmanagement.application.dto;

import java.time.LocalDate;

public class CitizenDto {
    private Long id; // Optional, kann bei der Registrierung leer sein
    private String email;
    private String password; // Nur für die Registrierung relevant
    private String address;
    private LocalDate birthDate;

    // Konstruktoren
    public CitizenDto() {}

    public CitizenDto(String email, String password, String address) {
        this.email = email;
        this.password = password;
        this.address = address;
    }

    // Getter und Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}