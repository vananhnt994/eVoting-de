package org.evoting.citizenmanagement.infrastructure.persistence;

import org.evoting.citizenmanagement.domain.model.citizen.Citizen;
import org.evoting.citizenmanagement.domain.repository.CitizenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public abstract class JpaCitizenRepository  {

    private final CitizenRepository citizenRepository;

    @Autowired
    public JpaCitizenRepository(CitizenRepository citizenRepository) {
        this.citizenRepository = citizenRepository;
    }

    public Citizen findByEmail(String email) {
        return citizenRepository.findByEmail(email);
    }

    public Citizen save(Citizen citizen) {
        return citizenRepository.save(citizen);
    }

}