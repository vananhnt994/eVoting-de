package org.evoting.de.unittests.service;

import org.evoting.de.domain.entities.Citizen;
import org.evoting.de.infrastructure.repositories.CitizenRepository;
import org.evoting.de.services.CitizenService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mindrot.jbcrypt.BCrypt;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CitizenServiceTest {

    @Mock
    private CitizenRepository citizenRepository;

    @InjectMocks
    private CitizenService citizenService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindByEmail() {
        String email = "test@example.com";
        Citizen citizen = new Citizen();
        citizen.setEmail(email);

        when(citizenRepository.findByEmail(email)).thenReturn(citizen);

        Citizen foundCitizen = citizenService.findByEmail(email);

        assertNotNull(foundCitizen);
        assertEquals(email, foundCitizen.getEmail());
        verify(citizenRepository).findByEmail(email);
    }

    @Test
    public void testSaveCitizen_Success() throws Exception {
        Citizen citizen = new Citizen();
        citizen.setEmail("new@example.com");

        when(citizenRepository.findByEmail(citizen.getEmail())).thenReturn(null);

        Citizen savedCitizen = citizenService.saveCitizen(citizen);

        assertNotNull(savedCitizen);
        assertEquals(citizen.getEmail(), savedCitizen.getEmail());
        verify(citizenRepository).save(citizen);
    }

    @Test
    public void testSaveCitizen_EmailAlreadyRegistered() {
        Citizen citizen = new Citizen();
        citizen.setEmail("existing@example.com");

        when(citizenRepository.findByEmail(citizen.getEmail())).thenReturn(new Citizen());

        Exception exception = assertThrows(Exception.class, () -> {
            citizenService.saveCitizen(citizen);
        });

        assertEquals("E-Mail bereits registriert", exception.getMessage());
        verify(citizenRepository, never()).save(any(Citizen.class));
    }

    @Test
    public void testLogin_Success() {
        String email = "login@example.com";
        String password = "password123!";

        Citizen citizen = new Citizen();
        citizen.setEmail(email);
        citizen.setPassword(password);

        when(citizenRepository.findByEmail(email)).thenReturn(citizen);
        System.out.println(citizen.getPassword());

        boolean result = citizenService.login(email, citizen.getPassword());

        assertTrue(BCrypt.checkpw(password, citizen.getPassword()));
        assertTrue(result);
        verify(citizenRepository).findByEmail(email);
    }

    @Test
    public void testLogin_Failure() {
        String email = "wrong@example.com";

        when(citizenRepository.findByEmail(email)).thenReturn(null);

        boolean result = citizenService.login(email, "anyPassword");

        assertFalse(result);
        verify(citizenRepository).findByEmail(email);
    }
}