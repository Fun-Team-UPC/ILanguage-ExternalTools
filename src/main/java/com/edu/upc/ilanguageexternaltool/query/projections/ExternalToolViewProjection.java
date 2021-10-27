package com.edu.upc.ilanguageexternaltool.query.projections;

import com.edu.upc.ilanguageexternaltool.command.domain.ExternalToolStatus;
import contracts.events.ExternalToolEdited;
import contracts.events.ExternalToolRegistered;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.Timestamp;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Optional;

@Component
public class ExternalToolViewProjection {
    private final ExternalToolViewRepository externalToolViewRepositoy;

    public ExternalToolViewProjection(ExternalToolViewRepository externalToolViewRepositoy) {
        this.externalToolViewRepositoy = externalToolViewRepositoy;
    }

    @EventHandler
    public void on(ExternalToolRegistered event, @Timestamp Instant timestamp) {
        ExternalToolView externalToolView = new ExternalToolView(event.getExternalToolId(), event.getName(), event.getDescription(), event.getResource(), ExternalToolStatus.ACTIVE.toString(), event.getOccurredOn());
        externalToolViewRepositoy.save(externalToolView);
    }

    @EventHandler
    public void on(ExternalToolEdited event, @Timestamp Instant timestamp) {
        ExternalToolView subscriptionView = new ExternalToolView(event.getExternalToolId(), event.getName(), event.getDescription(),event.getResource(), event.getName(), event.getOccurredOn());
        externalToolViewRepositoy.save(subscriptionView);
    }
}
