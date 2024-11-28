package org.evoting.de.citizenmanagement.application.services;


import org.evoting.de.citizenmanagement.application.dto.CitizenDto;
import org.evoting.de.citizenmanagement.domain.events.CitizenLoggedInEvent;
import org.evoting.de.citizenmanagement.domain.events.CitizenRegisteredEvent;
import org.evoting.de.citizenmanagement.domain.model.citizen.Citizen;
import org.evoting.de.citizenmanagement.domain.repository.CitizenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class CitizenService {
    private final CitizenRepository citizenRepository;
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    private static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$";
    @Autowired
    public CitizenService(CitizenRepository citizenRepository) {
        this.citizenRepository = citizenRepository;
    }
    @Autowired
    private ApplicationEventPublisher eventPublisher;
    public Citizen findByEmail(String email) {
        return citizenRepository.findByEmail(email);
    }

    public Citizen createCitizen(CitizenDto citizenDto) throws Exception {

        Citizen citizen = new Citizen();
        citizen.setEmail(citizenDto.getEmail());
        citizen.setPassword(citizenDto.getPassword());
        citizen.setAddress(citizenDto.getAddress());
        if (citizenRepository != null && findByEmail(citizen.getEmail()) != null) {
            throw new Exception("E-Mail bereits registriert");
        }
        assert citizenRepository != null;
        Citizen savedCitizen = citizenRepository.save(citizen);
        CitizenRegisteredEvent event = new CitizenRegisteredEvent(savedCitizen.getId(), savedCitizen.getEmail(), savedCitizen.getAddress());
        eventPublisher.publishEvent(event);
        return savedCitizen;
    }

    public boolean login(CitizenDto citizenDto) {
            Citizen citizen = citizenRepository.findByEmail(citizenDto.getEmail());
            if (citizen != null && validateCredentials(citizenDto.getEmail(), citizenDto.getPassword())) { // Beispielmethode zur Validierung
                CitizenLoggedInEvent event = new CitizenLoggedInEvent(citizen.getEmail(),citizen.getPassword());
                eventPublisher.publishEvent(event);
                return true;

            } else {
                throw new IllegalArgumentException("Invalid credentials");
            }
    }

    public boolean validateCredentials(String email, String password) {
        return Pattern.matches(EMAIL_REGEX, email) && Pattern.matches(PASSWORD_REGEX, password);
    }
}
