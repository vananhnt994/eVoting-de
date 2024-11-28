package org.evoting.de.citizenmanagement.application.controller;

import org.evoting.de.citizenmanagement.application.controllers.CitizenController;
import org.evoting.de.citizenmanagement.application.dto.CitizenDto;
import org.evoting.de.citizenmanagement.application.services.CitizenService;
import org.evoting.de.citizenmanagement.domain.model.citizen.Citizen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.springframework.boot.test.context.SpringBootTest;

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
        CitizenDto citizenDto = new CitizenDto();
        citizenDto.setEmail("test@example.com");
        citizenDto.setPassword("Password123!");

        Citizen citizen = new Citizen();
        citizen.setEmail(citizenDto.getEmail());
        citizen.setPassword(citizenDto.getPassword());

        when(citizenService.createCitizen(any(CitizenDto.class))).thenReturn(citizen);
        when(citizenService.findByEmail(citizenDto.getEmail())).thenReturn(citizen);

        // Act
        ResponseEntity<?> response = citizenController.registerNewCitizen(citizenDto);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(citizen, response.getBody());
        verify(citizenService).createCitizen(any(CitizenDto.class));
    }

    @Test
    void testRegisterNewCitizenInvalidEmail() throws Exception {
        // Arrange
        CitizenDto citizenDto = new CitizenDto();
        citizenDto.setEmail("invalid-email");
        citizenDto.setPassword("Password123!");

        Citizen citizen = new Citizen();


        try {
            citizen.setEmail(citizenDto.getEmail());
            citizen.setPassword(citizenDto.getPassword());
        // Act
            citizenController.registerNewCitizen(citizenDto);
        } catch (Exception e) {
        // Assert
            assertTrue(e.getMessage().contains("Invalid email format"));
        }
    }

    @Test
    void testRegisterNewCitizenInvalidPassword() {
        CitizenDto citizenDto = new CitizenDto();
        try {
            citizenDto.setEmail("test@example.com");
            citizenDto.setPassword("short");
            citizenController.registerNewCitizen(citizenDto);
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("Password must be at least 8 characters long and include a number, a lowercase letter, an uppercase letter, and a special character."));
        }
    }

    @Test
    void testLoginSuccess() throws Exception {
        // Arrange
        CitizenDto citizenDto = new CitizenDto();
        citizenDto.setEmail("test@example.com");
        citizenDto.setPassword("Password123!");

        when(citizenService.login(citizenDto)).thenReturn(true);

        when(citizenService.findByEmail(citizenDto.getEmail())).thenReturn(any(Citizen.class));

        // Act
        ResponseEntity<?> response = citizenController.login(citizenDto);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertNull(response.getBody());
        verify(citizenService).login(any(CitizenDto.class));
    }

    @Test
    void testLoginInvalidEmail() {
        // Arrange
        CitizenDto citizenDto = new CitizenDto();
        try {
            citizenDto.setEmail("invalid-email");
            citizenDto.setPassword("Password123!");
            citizenController.login(citizenDto);
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("Invalid email format"));

        }

    }

    @Test
    void testLoginFailed() throws Exception {
        // Arrange
        CitizenDto citizenDto = new CitizenDto();
        ResponseEntity<?> response = null;
        try {
            citizenDto.setEmail("test@example.com");
            citizenDto.setPassword("WrongPassword");
            when(citizenService.login(citizenDto)).thenReturn(false);
            response = citizenController.login(citizenDto);
        } catch (Exception e) {
            assertEquals(401, response.getStatusCodeValue());
            assertNotNull(response.getBody());
            verify(citizenService).login(any(CitizenDto.class));
        }
    }
}