package org.evoting.citizenmanagement.application.domain.repositories;

import org.evoting.citizenmanagement.domain.model.citizen.Citizen;
import org.evoting.citizenmanagement.domain.repository.CitizenRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class CitizenRepositoryTest {

    @Autowired
    private CitizenRepository citizenRepository;

    @BeforeEach
    void setUp() {
        // Vorbereitungen für jeden Test, z.B. Einfügen von Testdaten
        Citizen citizen = new Citizen();
        citizen.setEmail("test@example.com");
        citizen.setFirstName("John");
        citizen.setLastName("Doe");
        citizen.setPassword("hashedPassword"); // Setzen Sie hier ein gehashtes Passwort ein
        citizenRepository.save(citizen);
    }

    @Test
    void testFindByEmail_Success() {
        // Act
        Citizen foundCitizen = citizenRepository.findByEmail("test@example.com");

        // Assert
        assertThat(foundCitizen).isNotNull();
        assertThat(foundCitizen.getEmail()).isEqualTo("test@example.com");
        assertThat(foundCitizen.getFirstName()).isEqualTo("John");
        assertThat(foundCitizen.getLastname()).isEqualTo("Doe");
    }

    @Test
    void testFindByEmail_NotFound() {
        // Act
        Citizen foundCitizen = citizenRepository.findByEmail("nonexistent@example.com");

        // Assert
        assertThat(foundCitizen).isNull();
    }

    @Test
    @Rollback(false) // Um zu verhindern, dass der Datensatz nach dem Test gelöscht wird.
    void testSaveCitizen() {
        // Arrange
        Citizen newCitizen = new Citizen();
        newCitizen.setEmail("new@example.com");
        newCitizen.setFirstName("Jane");
        newCitizen.setLastName("Smith");

        // Act
        citizenRepository.save(newCitizen);

        // Assert
        Citizen foundCitizen = citizenRepository.findByEmail("new@example.com");

        assertThat(foundCitizen).isNotNull();
        assertThat(foundCitizen.getFirstName()).isEqualTo("Jane");
    }
}