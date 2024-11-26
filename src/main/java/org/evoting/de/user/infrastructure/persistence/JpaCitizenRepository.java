package org.evoting.de.user.infrastructure.persistence;

import jakarta.persistence.EntityManager;
import org.evoting.de.user.domain.model.citizen.Citizen;
import org.evoting.de.user.domain.repository.CitizenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public abstract class JpaCitizenRepository implements CitizenRepository {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Citizen findByEmail(String email) {
        return entityManager.find(Citizen.class, email);
    }

    @Override
    public Citizen save(Citizen citizen) {
        entityManager.persist(citizen);
        return citizen;
    }

    @Override
    public void delete(Citizen citizen) {
        entityManager.remove(citizen);
    }
}