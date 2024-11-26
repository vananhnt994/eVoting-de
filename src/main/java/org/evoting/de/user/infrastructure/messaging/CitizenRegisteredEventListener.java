package org.evoting.de.user.infrastructure.messaging;

import org.evoting.de.application.EventListener;
import org.evoting.de.user.domain.events.CitizenRegisteredEvent;

public class CitizenRegisteredEventListener implements EventListener<CitizenRegisteredEvent> {
    @Override
    public void handle(CitizenRegisteredEvent event) {
        // Logik zur Verarbeitung des Ereignisses (z.B. Benachrichtigung senden)
        System.out.println("Benutzer registriert: " + event.getEmail());

        // Hier könnten Sie auch andere Aktionen durchführen, z.B. E-Mails versenden oder Logs erstellen.
    }
}