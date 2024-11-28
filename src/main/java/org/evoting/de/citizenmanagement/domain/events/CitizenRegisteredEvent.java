package org.evoting.de.citizenmanagement.domain.events;

public class CitizenRegisteredEvent {
    private final Long id;
    private final String email;
    private final String adresse;

    public CitizenRegisteredEvent(Long id, String email, String adresse) {
        this.id = id;
        this.email = email;
        this.adresse = adresse;
    }

    public Long getUserId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getAdresse() {
        return adresse;
    }

}