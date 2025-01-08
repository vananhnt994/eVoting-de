package org.evoting.citizenmanagement.domain.model.citizen;

import java.time.LocalDate;

public class CitizenBuilder {
    private String email;
    private String password;
    private String lastName;
    private String firstName;
    private String address;
    private LocalDate birthDate;

    // Private constructor to prevent direct instantiation
    private CitizenBuilder() {
    }

    // Static method to create a builder instance
    public static CitizenBuilder create() {
        return new CitizenBuilder();
    }

    public CitizenBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public CitizenBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public CitizenBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public CitizenBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public CitizenBuilder withAddress(String address) {
        this.address = address;
        return this;
    }

    public CitizenBuilder withBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public Citizen build() {

        // Create and populate Citizen object
        Citizen citizen = new Citizen();
        citizen.setEmail(email);
        citizen.setPassword(password);
        citizen.setLastName(lastName);
        citizen.setFirstName(firstName);
        citizen.setAddress(address);
        citizen.setBirthDate(birthDate);

        return citizen;
    }
}
