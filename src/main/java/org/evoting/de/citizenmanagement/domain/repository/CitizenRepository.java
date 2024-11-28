package org.evoting.de.citizenmanagement.domain.repository;

import org.evoting.de.citizenmanagement.domain.model.citizen.Citizen;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CitizenRepository extends JpaRepository<Citizen, Long> {
    @Query("Select c from Citizen c Where c.email =:email")
    Citizen findByEmail(String email);

    @Modifying
    @Transactional
    @Query("Insert into Citizen (firstName, lastName) VALUES (:firstName, :lastName)")
    void saveCitizen(String firstName, String lastName);
}