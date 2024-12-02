package org.evoting.citizenmanagement.application.controllers;

import org.evoting.citizenmanagement.application.dto.CitizenDto;
import org.evoting.citizenmanagement.application.services.CitizenService;
import org.evoting.citizenmanagement.domain.model.citizen.Citizen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CitizenControllerTest {

    @InjectMocks
    private CitizenController citizenController;

    @Mock
    private CitizenService citizenService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllCitizens() {
        // Arrange
        Citizen citizen = new Citizen();
        when(citizenService.findAll()).thenReturn(Collections.singletonList(citizen));

        // Act
        var result = citizenController.getAllCitizens();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(citizenService, times(1)).findAll();
    }

    @Test
    void testRegisterNewCitizen_Success() throws Exception {
        // Arrange
        CitizenDto citizenDto = new CitizenDto();
        citizenDto.setEmail("test@example.com");
        citizenDto.setPassword("Password123!");

        when(citizenService.findByEmail(citizenDto.getEmail())).thenReturn(null);
        // Act
        ResponseEntity<?> response = citizenController.registerNewCitizen(citizenDto);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        verify(citizenService, times(1)).createCitizen(any(CitizenDto.class));
    }

    @Test
    void testRegisterNewCitizen_InvalidEmail() {
        // Arrange
        CitizenDto citizenDto = new CitizenDto();
        citizenDto.setEmail("invalid-email");

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            citizenController.registerNewCitizen(citizenDto);
        });

        assertEquals("Invalid email format", exception.getMessage());
    }

    @Test
    void testRegisterNewCitizen_EmailAlreadyRegistered() throws Exception {
        // Arrange
        CitizenDto citizenDto = new CitizenDto();
        citizenDto.setEmail("test@example.com");

        when(citizenService.findByEmail(citizenDto.getEmail())).thenReturn(new Citizen());
        // Act
        ResponseEntity<?> response = citizenController.registerNewCitizen(citizenDto);

        // Assert
        assertEquals(400, response.getStatusCodeValue());
        assertTrue(response.getBody().toString().contains("E-Mail bereits registriert"));
    }

    @Test
    void testLogin_Success() throws Exception {
        // Arrange
        CitizenDto citizenDto = new CitizenDto();
        citizenDto.setEmail("test@example.com");

        when(citizenService.findByEmail(any(String.class))).thenReturn(new Citizen());

        // Act
        ResponseEntity<?> response = citizenController.login(citizenDto);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        verify(citizenService, times(1)).login(any(CitizenDto.class));
    }

    @Test
    void testLogin_InvalidEmail() {
        // Arrange
        CitizenDto citizenDto = new CitizenDto();
        citizenDto.setEmail("invalid-email");

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            citizenController.login(citizenDto);
        });

        assertEquals("Invalid email format", exception.getMessage());
    }

    @Test
    void testLogin_InvalidCredentials() {
        // Arrange
        CitizenDto citizenDto = new CitizenDto();
        citizenDto.setEmail("test@example.com");

        when(citizenService.findByEmail(any(String.class))).thenReturn(null);

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            citizenController.login(citizenDto);
        });

        assertEquals("Invalid credentials", exception.getMessage());
    }
}