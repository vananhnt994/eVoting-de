package org.evoting.citizenmanagement.application.domain.model;

import org.evoting.citizenmanagement.domain.model.citizen.Citizen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CitizenTest {

    private Citizen citizen;

    @BeforeEach
    void setUp() {
        citizen = new Citizen();
    }

    @Test
    void testSetAndGetId() {
        Long id = 1L;
        citizen.setId(id);
        assertEquals(id, citizen.getId());
    }

    @Test
    void testSetAndGetEmail() {
        String email = "test@example.com";
        citizen.setEmail(email);
        assertEquals(email, citizen.getEmail());
    }

    @Test
    void testSetAndGetPassword() {
        String password = "Password123!";
        citizen.setPassword(password);

        // Überprüfen Sie, ob das Passwort gehasht wurde
        assertNotEquals(password, citizen.getPassword());

        // Überprüfen Sie, ob das eingegebene Passwort korrekt ist
        assertTrue(citizen.login(password));

        // Überprüfen Sie, dass ein falsches Passwort nicht akzeptiert wird
        assertFalse(citizen.login("wrongPassword"));
    }

    @Test
    void testSetAndGetLastName() {
        String lastName = "Doe";
        citizen.setLastName(lastName);
        assertEquals(lastName, citizen.getLastname());
    }

    @Test
    void testSetAndGetFirstName() {
        String firstName = "John";
        citizen.setFirstName(firstName);
        assertEquals(firstName, citizen.getFirstName());
    }

    @Test
    void testSetAndGetAddress() {
        String address = "123 Main St";
        citizen.setAddress(address);
        assertEquals(address, citizen.getAddress());
    }

    @Test
    void testSetAndGetBirthDate() {
        LocalDate birthDate = LocalDate.of(1990, 1, 1);
        citizen.setBirthDate(birthDate);
        assertEquals(birthDate, citizen.getBirthDate());
    }
}