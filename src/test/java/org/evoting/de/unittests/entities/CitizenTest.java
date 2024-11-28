package org.evoting.de.unittests.entities;

import org.evoting.de.citizenmanagement.domain.model.citizen.Citizen;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class CitizenTest {

    @Test
    public void testConstructorAndGetters() {
        Citizen citizen = new Citizen();
        Long id = 1L;
        String email = "citizen@example.com";
        String password = "securePassword";
        String lastName = "Doe";
        String firstName = "John";
        String address = "123 Main St";
        LocalDate birthDate = LocalDate.of(1990, 1, 1);

        citizen.setId(id);
        citizen.setEmail(email);
        citizen.setPassword(password); // Passwort wird gehasht
        citizen.setfamilyName(lastName);
        citizen.setFirstName(firstName);
        citizen.setAddress(address);
        citizen.setBirthDate(birthDate);

        assertEquals(id, citizen.getId());
        assertEquals(email, citizen.getEmail());
        assertNotNull(citizen.getPassword()); // Passwort sollte gehasht sein
        assertTrue(citizen.login(password)); // Überprüfe das Passwort
        assertEquals(lastName, citizen.getfamilyName());
        assertEquals(firstName, citizen.getFirstName());
        assertEquals(address, citizen.getAddress());
        assertEquals(birthDate, citizen.getBirthDate());
    }

    @Test
    public void testSetters() {
        Citizen citizen = new Citizen();

        // Teste Setzen neuer Werte
        Long newId = 2L;
        String newEmail = "newCitizen@example.com";
        String newPassword = "newSecurePassword"; // Dies wird auch gehasht
        String newLastName = "Smith";
        String newFirstName = "Jane";
        String newAddress = "456 Elm St";
        LocalDate newBirthDate = LocalDate.of(1995, 5, 5);

        citizen.setId(newId);
        citizen.setEmail(newEmail);
        citizen.setPassword(newPassword); // Passwort wird gehasht
        citizen.setfamilyName(newLastName);
        citizen.setFirstName(newFirstName);
        citizen.setAddress(newAddress);
        citizen.setBirthDate(newBirthDate);

        assertEquals(newId, citizen.getId());
        assertEquals(newEmail, citizen.getEmail());

        // Überprüfe das neue Passwort (gehasht)
        assertTrue(citizen.login(newPassword));

        assertEquals(newLastName, citizen.getfamilyName());
        assertEquals(newFirstName, citizen.getFirstName());
        assertEquals(newAddress, citizen.getAddress());
        assertEquals(newBirthDate, citizen.getBirthDate());
    }

    @Test
    public void testLogin_WithCorrectPassword() {
        Citizen citizen = new Citizen();
        String password = "mySecretPassword";

        // Setze das Passwort (es wird gehasht)
        citizen.setPassword(password);

        // Überprüfe den Login mit dem richtigen Passwort
        assertTrue(citizen.login(password));
    }

    @Test
    public void testLogin_WithIncorrectPassword() {
        Citizen citizen = new Citizen();
        String correctPassword = "mySecretPassword";
        String incorrectPassword = "wrongPassword";

        // Setze das Passwort (es wird gehasht)
        citizen.setPassword(correctPassword);

        // Überprüfe den Login mit dem falschen Passwort
        assertFalse(citizen.login(incorrectPassword));
    }
}