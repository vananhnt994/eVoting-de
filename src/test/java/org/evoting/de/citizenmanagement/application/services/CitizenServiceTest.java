package org.evoting.de.citizenmanagement.application.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.evoting.de.citizenmanagement.application.dto.CitizenDto;
import org.evoting.de.citizenmanagement.domain.events.CitizenLoggedInEvent;
import org.evoting.de.citizenmanagement.domain.events.CitizenRegisteredEvent;
import org.evoting.de.citizenmanagement.domain.model.citizen.Citizen;
import org.evoting.de.citizenmanagement.domain.repository.CitizenRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.ApplicationEventPublisher;

public class CitizenServiceTest {

    @Mock
    private CitizenRepository citizenRepository;

    @Mock
    public ApplicationEventPublisher eventPublisher;

    @InjectMocks
    private CitizenService citizenService;

    private CitizenDto citizenDto;
    private Citizen citizen;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        citizenDto = new CitizenDto("test@example.com", "Password123!", "Some Address");

        citizen = new Citizen();
        citizen.setEmail(citizenDto.getEmail());
        citizen.setPassword(citizenDto.getPassword());
        citizen.setAddress(citizenDto.getAddress());
    }

    public void testCreateCitizen_Success() throws Exception {

        when(citizenRepository.findByEmail(citizen.getEmail())).thenReturn(null);
        when(citizenRepository.save(any(Citizen.class))).thenReturn(citizen);

        Citizen result = citizenService.createCitizen(citizenDto);

        assertNotNull(result);
        assertEquals(citizen.getEmail(), result.getEmail());
        verify(eventPublisher).publishEvent(any(CitizenRegisteredEvent.class));
    }

    @Test
    public void testCreateCitizen_EmailAlreadyRegistered() {
        when(citizenRepository.findByEmail(citizen.getEmail())).thenReturn(citizen);

        Exception exception = assertThrows(Exception.class, () -> {
            citizenService.createCitizen(citizenDto);
        });

        assertEquals("E-Mail bereits registriert", exception.getMessage());
        verify(eventPublisher, never()).publishEvent(any(CitizenRegisteredEvent.class));
    }

    public void testLogin_Success() {
        when(citizenRepository.findByEmail(citizen.getEmail())).thenReturn(citizen);

        boolean result = citizenService.login(citizenDto);

        assertTrue(result);
        verify(eventPublisher).publishEvent(any(CitizenLoggedInEvent.class));
    }

    public void testLogin_InvalidCredentials() {
        when(citizenRepository.findByEmail(citizen.getEmail())).thenReturn(null);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            citizenService.login(citizenDto);
        });

        assertEquals("Invalid credentials", exception.getMessage());

        // Test with wrong password
        citizen.setPassword("WrongPassword!");

        boolean result = citizenService.login(new CitizenDto("test@example.com", "WrongPassword!", "Some Address"));

        assertFalse(result);
    }

    @Test
    public void testValidateCredentials_ValidInput() {
        boolean isValid = citizenService.validateCredentials("test@example.com", "Password123!");

        assertTrue(isValid);
    }

    @Test
    public void testValidateCredentials_InvalidEmail() {
        boolean isValid = citizenService.validateCredentials("invalid-email", "Password123!");

        assertFalse(isValid);
    }

    @Test
    public void testValidateCredentials_InvalidPassword() {
        boolean isValid = citizenService.validateCredentials("test@example.com", "short");

        assertFalse(isValid);
    }
}