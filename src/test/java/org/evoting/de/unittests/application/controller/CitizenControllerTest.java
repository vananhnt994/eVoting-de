package org.evoting.de.unittests.application.controller;

import org.evoting.de.application.controllers.CitizenController;
import org.evoting.de.domain.entities.Citizen;
import org.evoting.de.services.CitizenService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CitizenControllerTest {

    @InjectMocks
    private CitizenController citizenController;

    @Mock
    private CitizenService citizenService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterNewCitizenSuccess() throws Exception {
        // Arrange
        Citizen citizen = new Citizen();
        citizen.setEmail("test@example.com");
        citizen.setPassword("Password123!");

        when(citizenService.saveCitizen(any(Citizen.class))).thenReturn(citizen);
        when(citizenService.findByEmail(citizen.getEmail())).thenReturn(citizen);

        // Act
        ResponseEntity<?> response = citizenController.registerNewCitizen(citizen);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(citizen, response.getBody());
        verify(citizenService).saveCitizen(any(Citizen.class));
    }

    @Test
    void testRegisterNewCitizenInvalidEmail() throws Exception {
        // Arrange
        Citizen citizen = new Citizen();

        try {
            citizen.setEmail("invalid-email");
            citizen.setPassword("Password123!");
        // Act
            citizenController.registerNewCitizen(citizen);
        } catch (Exception e) {
        // Assert
            assertTrue(e.getMessage().contains("Invalid email format"));
        }
    }

    @Test
    void testRegisterNewCitizenInvalidPassword() {
        Citizen citizen = new Citizen();
        try {
            citizen.setEmail("test@example.com");
            citizen.setPassword("short");
            citizenController.registerNewCitizen(citizen);
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("Password must be at least 8 characters long and include a number, a lowercase letter, an uppercase letter, and a special character."));
        }
    }

    @Test
    void testLoginSuccess() throws Exception {
        // Arrange
        Citizen citizen = new Citizen();
        citizen.setEmail("test@example.com");
        citizen.setPassword("Password123!");

        when(citizenService.login(any(String.class), any(String.class))).thenReturn(true);

        when(citizenService.findByEmail(citizen.getEmail())).thenReturn(citizen);

        // Act
        ResponseEntity<?> response = citizenController.login(citizen);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(citizen, response.getBody());
        verify(citizenService).login(any(String.class), any(String.class));
    }

    @Test
    void testLoginInvalidEmail() {
        // Arrange
        Citizen citizen = new Citizen();
        try {
            citizen.setEmail("invalid-email");
            citizen.setPassword("Password123!");
            citizenController.login(citizen);
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("Invalid email format"));

        }

    }

    @Test
    void testLoginFailed() throws Exception {
        // Arrange
        Citizen citizen = new Citizen();
        ResponseEntity<?> response = null;
        try {
            citizen.setEmail("test@example.com");
            citizen.setPassword("WrongPassword");
            when(citizenService.login(any(String.class), any(String.class))).thenReturn(false);
            response = citizenController.login(citizen);
        } catch (Exception e) {
            assertEquals(401, response.getStatusCodeValue());
            assertNotNull(response.getBody());
            verify(citizenService).login(any(String.class), any(String.class));
        }
    }
}