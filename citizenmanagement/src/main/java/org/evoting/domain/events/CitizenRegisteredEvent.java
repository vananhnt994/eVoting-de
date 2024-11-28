package org.evoting.domain.events;


public class CitizenRegisteredEvent {
    private final Long id;
    private final String email;
    private final String adresse;

    public CitizenRegisteredEvent(Long id, String email, String adresse) {
        if (id == null || email == null || email.isEmpty() || adresse == null || adresse.isEmpty()) {
            throw new IllegalArgumentException("ID, Email und Adresse dürfen nicht null oder leer sein.");
        }
        this.id = id;
        this.email = email;
        this.adresse = adresse;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getAdresse() {
        return adresse;
    }

}