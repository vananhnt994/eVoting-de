package org.evoting.de.services;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import org.evoting.de.domain.Citizen;
import org.evoting.de.repositories.CitizenRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CitizenService {
    ObjectMapper objectMapper = new ObjectMapper();
    private final CitizenRepository citizenRepository;

    @Autowired
    public CitizenService(CitizenRepository citizenRepository) {
        this.citizenRepository = citizenRepository;
    }

    public Citizen findByEmail(String email) {
        return citizenRepository.findByEmail(email);
    }
    public Citizen saveCitizen(Citizen citizen) throws Exception {

        if (citizenRepository != null && findByEmail(citizen.getEmail()) != null) {
            throw new Exception("E-Mail bereits registriert");
        }
        System.out.println("Bürger registriert: " + citizen.getfamilyName());
        assert citizenRepository != null;
        citizenRepository.save(citizen);
        return citizen;
    }

    protected List<Citizen> getExistedCitizens() throws JsonProcessingException {
        List<Citizen> citzens = new ArrayList<>();
        try {
            citzens = objectMapper.readValue("CitizenDB.json" , new TypeReference<List<Citizen>>() {});
        } catch (JsonProcessingException e){
            System.out.println(e.getMessage());
        }
        return citzens;
    }

    protected boolean checkIfCitizenExists(Citizen citizen) throws JsonProcessingException {
        List<String> existedEmails = getExistedCitizens().stream().map(c -> citizen.getEmail()).toList();
        System.out.println(existedEmails);
        return existedEmails.contains(citizen.getEmail());

    }
    public boolean login(String email, String password) {
        Citizen citizen = citizenRepository.findByEmail(email);
        if (citizen != null) {
            return citizen.getPassword().equals(password); // In der Praxis: Passwort-Hashing verwenden!
        }
        return false;
    }
}
