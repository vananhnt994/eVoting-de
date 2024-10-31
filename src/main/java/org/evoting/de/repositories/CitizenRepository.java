package org.evoting.de.repositories;

import org.evoting.de.entites.Citizen;

import java.io.IOException;

public interface CitizenRepository {
    void registerCitizen(Citizen citizen);
    //void saveCitizenToJson(Citizen citizen) throws IOException;
}