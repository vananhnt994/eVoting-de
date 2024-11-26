package org.evoting.de.user.application.services;


import org.evoting.de.user.application.dto.CitizenDto;
import org.evoting.de.user.domain.events.CitizenLoggedInEvent;
import org.evoting.de.user.domain.events.CitizenRegisteredEvent;
import org.evoting.de.user.domain.model.citizen.Citizen;
import org.evoting.de.user.domain.repository.CitizenRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public Citizen findByEmail(String email) {
        return citizenRepository.findByEmail(email);
    }

    public CitizenRegisteredEvent createCitizen(CitizenDto citizenDto) throws Exception {

        Citizen citizen = new Citizen();
        citizen.setEmail(citizenDto.getEmail());
        citizen.setPassword(citizenDto.getPassword());
        citizen.setAddress(citizenDto.getAddress());
        if (citizenRepository != null && findByEmail(citizen.getEmail()) != null) {
            throw new Exception("E-Mail bereits registriert");
        }
        assert citizenRepository != null;
        citizenRepository.save(citizen);
        return new CitizenRegisteredEvent(citizen.getId(), citizenDto.getEmail(), citizenDto.getAddress());
    }

    public CitizenLoggedInEvent login(CitizenDto citizenDto) {
        if (validateCredentials(citizenDto.getEmail(), citizenDto.getPassword())) { // Beispielmethode zur Validierung
            Citizen citizen = findByEmail(citizenDto.getEmail()); // Beispielmethode zum Finden des Benutzers

            // Ereignis erstellen und zurückgeben
            return new CitizenLoggedInEvent(citizenDto.getEmail(),citizenDto.getPassword());
        } else {
            throw new IllegalArgumentException("Invalid credentials");
        }
    }

    public boolean validateCredentials(String email, String password) {
        return Pattern.matches(EMAIL_REGEX, email) && Pattern.matches(PASSWORD_REGEX, password);
    }
}
