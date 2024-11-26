package org.evoting.de.user.domain.events;

import org.evoting.de.application.EventListener;

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