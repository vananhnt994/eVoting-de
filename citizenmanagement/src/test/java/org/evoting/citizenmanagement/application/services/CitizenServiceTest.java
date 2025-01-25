package org.evoting.citizenmanagement.application.services;

import io.micrometer.core.instrument.config.validate.Validated;
import org.evoting.citizenmanagement.application.dto.CitizenDto;
import org.evoting.citizenmanagement.domain.events.CitizenLoggedInEvent;
import org.evoting.citizenmanagement.domain.model.citizen.Citizen;
import org.evoting.citizenmanagement.domain.repository.CitizenRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.ApplicationEventPublisher;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CitizenServiceTest {

    @InjectMocks
    private CitizenService citizenService;

    @Mock
    private CitizenRepository citizenRepository;

    @Mock
    private ApplicationEventPublisher eventPublisher;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindByEmail_Success() {
        // Arrange
        String email = "test@example.com";
        Citizen citizen = new Citizen();
        citizen.setEmail(email);

        when(citizenRepository.findByEmail(email)).thenReturn(citizen);

        // Act
        Citizen result = citizenService.findByEmail(email);

        // Assert
        assertNotNull(result);
        assertEquals(email, result.getEmail());
        verify(citizenRepository, times(1)).findByEmail(email);
    }

    @Test
    void testFindAll() {
        // Arrange
        Citizen citizen = new Citizen();
        when(citizenRepository.findAll()).thenReturn(Collections.singletonList(citizen));

        // Act
        var result = citizenService.findAll();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(citizenRepository, times(1)).findAll();
    }

    @Test
    void testCreateCitizen_Success() throws Exception {
        // Arrange
        CitizenDto citizenDto = new CitizenDto();
        citizenDto.setEmail("test@example.com");

        when(citizenRepository.findByEmail(citizenDto.getEmail())).thenReturn(null);

        // Act
        citizenService.createCitizen(citizenDto);

        // Assert
        verify(citizenRepository, times(1)).save(any(Citizen.class));
    }

    @Test
    void testCreateCitizen_EmailAlreadyRegistered() {
        // Arrange
        CitizenDto citizenDto = new CitizenDto();
        citizenDto.setEmail("test@example.com");

        when(citizenRepository.findByEmail(citizenDto.getEmail())).thenReturn(new Citizen());

        // Act & Assert
        Exception exception = assertThrows(Exception.class, () -> {
            citizenService.createCitizen(citizenDto);
        });

        assertEquals("E-Mail bereits registriert", exception.getMessage());
    }

    @Test
    void testLogin_Success() {
        // Arrange
        CitizenDto citizenDto = new CitizenDto();
        citizenDto.setEmail("test@example.com");
        citizenDto.setPassword("Thisisamess2024!");

        Citizen citizen = new Citizen();
        citizen.setEmail("test@example.com");

        when(citizenRepository.findByEmail(any(String.class))).thenReturn(citizen);
        assertTrue(citizenService.validateCredentials(citizenDto.getEmail(), citizenDto.getPassword()));

        // Act
        assertDoesNotThrow(() -> {
            citizenService.login(citizenDto);
        });

        verify(eventPublisher, times(1)).publishEvent(any(CitizenLoggedInEvent.class));
    }

    @Test
    void testLogin_InvalidCredentials() {
        // Arrange
        CitizenDto citizenDto = new CitizenDto();
        citizenDto.setEmail("test@example.com");
        citizenDto.setPassword("WrongPassword");

        when(citizenRepository.findByEmail(citizenDto.getEmail())).thenReturn(any(Citizen.class));

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            citizenService.login(citizenDto);
        });

        assertEquals("Invalid credentials", exception.getMessage());
    }

    @Test
    void testValidateCredentials_ValidInput() {
        String email = "test@example.com";
        String password = "Password123!";

        boolean isValid = citizenService.validateCredentials(email, password);

        assertTrue(isValid);
    }

    @Test
    void testValidateCredentials_InvalidEmail() {
        String email = "invalid-email";
        String password = "Password123!";

        boolean isValid = citizenService.validateCredentials(email, password);

        assertFalse(isValid);
    }

    @Test
    void testValidateCredentials_InvalidPassword() {
        String email = "test@example.com";
        String password = "short";

        boolean isValid = citizenService.validateCredentials(email, password);

        assertFalse(isValid);
    }
}