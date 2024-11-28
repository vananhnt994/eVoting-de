package org.evoting.application.controllers;

import org.evoting.application.dto.CitizenDto;
import org.evoting.application.services.CitizenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Pattern;
import org.evoting.application.dto.CitizenDto;
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")

public class CitizenController {

    private final CitizenService citizenService;
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    private static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$";

    @Autowired
    public CitizenController(CitizenService citizenService) {
        this.citizenService = citizenService;
    }

    @PostMapping(value = "/signup",consumes={"application/json"})
    public ResponseEntity<?> registerNewCitizen(@RequestBody CitizenDto citizenDto) throws Exception {
        try {
            if (!validateEmail(citizenDto.getEmail())) throwIllegalArgumentException("Invalid email format");
            if(!validatePassword(citizenDto.getPassword())) throwIllegalArgumentException("Password must be at least 8 characters long and include a number, a lowercase letter, an uppercase letter, and a special character.");
            citizenService.createCitizen(citizenDto);
            System.out.println("User " +citizenDto.getEmail() + " ist registriert");
            return ResponseEntity.ok().body(citizenService.findByEmail(citizenDto.getEmail()));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            if(e.getMessage().contains("bereits registriert")) {
                return ResponseEntity.status(400).body(e.getMessage());
            }
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody CitizenDto citizenDto) throws Exception {
        try {
            if (!validateEmail(citizenDto.getEmail())) {
                throw new IllegalArgumentException("Invalid email format");
            }
            boolean result = citizenService.login(citizenDto);
            System.out.println("Login erfolgreich!");
            return ResponseEntity.status(200).body(citizenService.findByEmail(citizenDto.getEmail()));
        } catch (Exception e) {
            System.out.println("Login fehlgeschlagen!");
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }
    public boolean validateEmail(String email) {
        return Pattern.matches(EMAIL_REGEX, email);
    }
    public boolean validatePassword(String password) {
        return Pattern.matches(PASSWORD_REGEX, password);
    }
    public void throwIllegalArgumentException(String exceptionMessage) {
        throw new IllegalArgumentException(exceptionMessage);
    }
}
