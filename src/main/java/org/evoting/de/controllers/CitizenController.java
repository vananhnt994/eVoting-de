package org.evoting.de.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.evoting.de.entites.Citizen;
import org.springframework.web.bind.annotation.*;

import java.io.DataInput;
import java.io.IOException;

@RestController
@RequestMapping("/api")
public class CitizenController {
    @CrossOrigin(origins = "http://localhost:3001")
    @PostMapping(value = "/signup",consumes={"application/json"})
    public Citizen newCitizen(@RequestBody Citizen citizen) throws IOException {
        System.out.println(citizen.getEmail());
        return citizen;

    }
}
