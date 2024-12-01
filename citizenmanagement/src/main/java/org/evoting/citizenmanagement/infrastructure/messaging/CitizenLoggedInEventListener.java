package org.evoting.citizenmanagement.infrastructure.messaging;

import org.evoting.citizenmanagement.domain.events.CitizenLoggedInEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class CitizenLoggedInEventListener {

    private static final Logger logger = LoggerFactory.getLogger(CitizenLoggedInEventListener.class);

    @EventListener
    public void handleCitizenLoggedInEvent(CitizenLoggedInEvent event) {
        logger.info("Bürger angemeldet:");
        logger.info("ID: {}", event.getEmail());

        if (event.getEmail() == null || event.getEmail().isEmpty()) {
            logger.warn("Die E-Mail für den Bürger ist leer.");
        }
    }
}