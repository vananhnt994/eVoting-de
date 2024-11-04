package org.evoting.de.controllers;

import org.evoting.de.Response.SignupResponse;
import org.evoting.de.entites.Citizen;
import org.evoting.de.services.CitizenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class CitizenController {

    private final CitizenService citizenService;

    @Autowired
    public CitizenController(CitizenService citizenService) {
        this.citizenService = citizenService;
    }

    @PostMapping(value = "/signup",consumes={"application/json"})
    public ResponseEntity<?> registerNewCitizen(@RequestBody Citizen citizen) throws Exception {
        try {
            citizenService.saveCitizen(citizen);
            System.out.println("User " +citizen.getEmail() + " ist registriert");
            return ResponseEntity.ok().body(new SignupResponse(citizen.getId(), "Signup erfolgreich!"));
        } catch (Exception e) {
            if(e.getMessage().contains("bereits registriert")) {
                return ResponseEntity.status(400).body("E-mail ist bereit vorhanden");
            }
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public String login(@RequestBody Citizen citizen) throws Exception {
        boolean success = citizenService.login(citizen.getEmail(), citizen.getPassword());
        if (success) {
            return "Login erfolgreich!";
        } else {
            return "Login fehlgeschlagen!";
        }
    }


}
