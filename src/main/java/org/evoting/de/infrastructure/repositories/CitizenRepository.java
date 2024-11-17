package org.evoting.de.infrastructure.repositories;

import org.evoting.de.domain.entities.Citizen;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CitizenRepository extends JpaRepository<Citizen, Long> {
    @Query("Select c from Citizen c Where c.email =:email")
    Citizen findByEmail(String email);
}