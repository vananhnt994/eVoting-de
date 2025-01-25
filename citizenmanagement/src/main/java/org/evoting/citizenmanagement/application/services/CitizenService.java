package org.evoting.citizenmanagement.application.services;

import org.evoting.citizenmanagement.application.dto.CitizenDto;
import org.evoting.citizenmanagement.domain.events.CitizenLoggedInEvent;
import org.evoting.citizenmanagement.domain.model.citizen.Citizen;
import org.evoting.citizenmanagement.domain.model.citizen.CitizenBuilder;
import org.evoting.citizenmanagement.domain.repository.CitizenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class CitizenService {

    private final CitizenRepository citizenRepository;
    private final ApplicationEventPublisher eventPublisher;

    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    private static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$";

    @Autowired
    public CitizenService(CitizenRepository citizenRepository, ApplicationEventPublisher eventPublisher) {
        this.citizenRepository = citizenRepository;
        this.eventPublisher = eventPublisher;
    }

    public Citizen findByEmail(String email) {
        return citizenRepository.findByEmail(email);
    }

    public List<Citizen> findAll() {
        return citizenRepository.findAll();
    }


    /*public List<String> findAllEmails(){
        return citizenRepository.findAll()
                .stream()
                .map(Citizen::getEmail)
                .filter(email -> email != null && !email.isEmpty())
                .collect(Collectors.toList());
    }*/

    public void createCitizen(CitizenDto citizenDto) throws Exception {

        // Use the builder to create the Citizen object
        Citizen citizen = CitizenBuilder.create()
            // fill values of citizen object
            .withEmail(citizenDto.getEmail())
            .withPassword(citizenDto.getPassword())
            .withLastName(citizenDto.getLastName())
            .withFirstName(citizenDto.getFirstName())
            .withAddress(citizenDto.getAddress())
            .withBirthDate(citizenDto.getBirthDate())
            .build();

//        if (citizenRepository != null && findByEmail(citizen.getEmail()) != null) {
//            throw new Exception("E-Mail bereits registriert");
//        }
        /*if (Optional.ofNullable(citizenRepository)
                .map(repo -> findByEmail(citizen.getEmail()))
                .filter(email -> email != null)
                .isPresent()) {
            throw new Exception("E-Mail bereits registriert");
        }*/
        Optional.ofNullable(citizen.getEmail())
                .flatMap(email -> Optional.ofNullable(findByEmail(email)))
                .ifPresent(email -> {
                    throw new RuntimeException("E-Mail bereits registriert");
                });


        System.out.println("Bürger registriert: " + citizen.getLastname() + " " + citizen.getFirstName());
        assert citizenRepository != null;
        citizenRepository.save(citizen);
    }

    public void login(CitizenDto citizenDto) {
        Citizen citizen = citizenRepository.findByEmail(citizenDto.getEmail());
        if (citizen != null && validateCredentials(citizenDto.getEmail(), citizenDto.getPassword())) { // Beispielmethode zur Validierung
            CitizenLoggedInEvent event = new CitizenLoggedInEvent(citizen.getEmail(),citizen.getPassword());
            eventPublisher.publishEvent(event);

        } else {
            throw new IllegalArgumentException("Invalid credentials");
        }
    }




    public boolean validateCredentials(String email, String password) {
        return Pattern.matches(EMAIL_REGEX, email) && Pattern.matches(PASSWORD_REGEX, password);
    }
}



