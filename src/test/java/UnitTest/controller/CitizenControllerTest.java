package UnitTest.controller;

import org.evoting.de.controllers.CitizenController;
import org.evoting.de.domain.Citizen;
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
    void testRegisterNewCitizen_Success() throws Exception {
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
    void testRegisterNewCitizen_InvalidEmail() {
        // Arrange
        Citizen citizen = new Citizen();
        citizen.setEmail("invalid-email");
        citizen.setPassword("Password123!");

        // Act & Assert
        Exception exception = assertThrows(Exception.class, () -> {
            citizenController.registerNewCitizen(citizen);
        });

        assertTrue(exception.getMessage().contains("Invalid email format"));
    }

    @Test
    void testRegisterNewCitizen_InvalidPassword() {
        // Arrange
        Citizen citizen = new Citizen();
        citizen.setEmail("test@example.com");
        citizen.setPassword("short");

        // Act & Assert
        Exception exception = assertThrows(Exception.class, () -> {
            citizenController.registerNewCitizen(citizen);
        });

        assertTrue(exception.getMessage().contains("Password must be at least 8 characters long and include a number, a lowercase letter, an uppercase letter, and a special character."));
    }

    @Test
    void testLogin_Success() throws Exception {
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
    void testLogin_InvalidEmail() {
        // Arrange
        Citizen citizen = new Citizen();
        citizen.setEmail("invalid-email");
        citizen.setPassword("Password123!");

        // Act & Assert
        Exception exception = assertThrows(Exception.class, () -> {
            citizenController.login(citizen);
        });

        assertTrue(exception.getMessage().contains("Invalid email format"));
    }

    @Test
    void testLogin_Failed() throws Exception {
        // Arrange
        Citizen citizen = new Citizen();
        citizen.setEmail("test@example.com");
        citizen.setPassword("WrongPassword");

        when(citizenService.login(any(String.class), any(String.class))).thenReturn(false);

        // Act & Assert
        ResponseEntity<?> response = citizenController.login(citizen);

        assertEquals(401, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        verify(citizenService).login(any(String.class), any(String.class));
    }
}