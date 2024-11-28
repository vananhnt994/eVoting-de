package org.evoting.de.unittests.service;

import org.evoting.de.citizenmanagement.application.dto.CitizenDto;
import org.evoting.de.citizenmanagement.domain.model.citizen.Citizen;
import org.evoting.de.citizenmanagement.domain.repository.CitizenRepository;
import org.evoting.de.citizenmanagement.application.services.CitizenService;
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
    public void testCreateCitizen_Success() throws Exception {
        CitizenDto citizenDto = new CitizenDto();
        citizenDto.setEmail("new@example.com");
        Citizen citizen = new Citizen();
        citizen.setEmail(citizenDto.getEmail());
        when(citizenRepository.findByEmail(citizenDto.getEmail())).thenReturn(null);

        Citizen savedCitizen = citizenService.createCitizen(citizenDto);

        assertNotNull(savedCitizen);
        assertEquals(citizenDto.getEmail(), savedCitizen.getEmail());
        verify(citizenRepository).save(citizen);
    }

    @Test
    public void testCreateCitizen_EmailAlreadyRegistered() {
        CitizenDto citizenDto = new CitizenDto();
        citizenDto.setEmail("existing@example.com");

        when(citizenRepository.findByEmail(citizenDto.getEmail())).thenReturn(new Citizen());

        Exception exception = assertThrows(Exception.class, () -> {
            citizenService.createCitizen(citizenDto);
        });

        assertEquals("E-Mail bereits registriert", exception.getMessage());
        verify(citizenRepository, never()).save(any(Citizen.class));
    }

    @Test
    public void testLogin_Success() {
        String email = "login@example.com";
        String password = "password123!";
        CitizenDto citizenDto = new CitizenDto();
        citizenDto.setEmail(email);
        citizenDto.setPassword(password);
        Citizen citizen = new Citizen();
        citizen.setEmail(email);
        citizen.setPassword(password);

        when(citizenRepository.findByEmail(email)).thenReturn(citizen);
        System.out.println(citizen.getPassword());

        when(citizenService.login(citizenDto)).thenReturn(true);

        assertTrue(BCrypt.checkpw(password, citizen.getPassword()));
        verify(citizenRepository).findByEmail(email);
    }

    @Test
    public void testLogin_Failure() {
        String email = "wrong@example.com";
        CitizenDto citizenDto = new CitizenDto();
        citizenDto.setEmail(email);
        when(citizenRepository.findByEmail(email)).thenReturn(null);
        when(citizenService.login(citizenDto)).thenReturn(null);
        verify(citizenRepository).findByEmail(email);
    }
}