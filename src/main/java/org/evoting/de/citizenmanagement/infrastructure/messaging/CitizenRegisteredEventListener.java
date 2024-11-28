package org.evoting.de.citizenmanagement.infrastructure.messaging;

import org.evoting.de.citizenmanagement.domain.events.CitizenRegisteredEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.context.event.EventListener;

@Component
public class CitizenRegisteredEventListener {
    private static final Logger logger = LoggerFactory.getLogger(CitizenRegisteredEventListener.class);

    @EventListener
    public void handle(CitizenRegisteredEvent event) {
        logger.info("Neuer Bürger registriert:");
        logger.info("ID: {}", event.getUserId());
        logger.info("E-Mail: {}", event.getEmail());
        logger.info("Adresse: {}", event.getAdresse());

        if (event.getAdresse() == null || event.getAdresse().isEmpty()) {
            logger.warn("Die Adresse für den Bürger mit ID {} ist leer.", event.getUserId());
        }
    }
}