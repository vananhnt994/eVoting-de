package org.evoting.de.services;
//import com.fasterxml.jackson.*;
import org.evoting.de.entites.Citizen;
import org.evoting.de.repositories.CitizenRepository;

import java.io.File;
import java.io.IOException;
public class CitizenService implements CitizenRepository {
    @Override
    public void registerCitizen(Citizen citizen) {
        // Hier können Sie Logik hinzufügen, um den Bürger zu registrieren,
        // z.B. in einer Datenbank oder einer Liste.
        System.out.println("Bürger registriert: " + citizen.getfamilyName());

        // Beispiel: Speichern in einer Liste (nicht implementiert hier)
        // citizensList.add(citizen);
    }


    /*@Override
    public void saveCitizenToJson(Citizen citizen) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        // Speichern des Bürgerobjekts als JSON-Datei
        objectMapper.writeValue(new File(citizen.getId() + ".json"), citizen);

        System.out.println("Bürger gespeichert in JSON-Datei: " + citizen.getId() + ".json");
    }*/
}
