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
public class ExternalToolHistoryViewProjection {
    private final ExternalToolHistoryViewRepository externalToolHistoryViewRepository;

    public ExternalToolHistoryViewProjection(ExternalToolHistoryViewRepository externalToolHistoryViewRepository) {
        this.externalToolHistoryViewRepository = externalToolHistoryViewRepository;
    }

    @EventHandler
    public void on(ExternalToolRegistered event, @Timestamp Instant timestamp) {
        ExternalToolHistoryView externalToolHistoryView = new ExternalToolHistoryView(
                event.getExternalToolId(),
                event.getName(),
                event.getDescription(),
                event.getResource(),
                ExternalToolStatus.ACTIVE.toString(),
                event.getOccurredOn());
        externalToolHistoryViewRepository.save(externalToolHistoryView);
    }

    @EventHandler
    public void on(ExternalToolEdited event, @Timestamp Instant timestamp) {
        ExternalToolHistoryView subscriptionHistoryView = new ExternalToolHistoryView(
                event.getExternalToolId(),
                event.getName(),
                event.getDescription(),
                event.getResource(),
                ExternalToolStatus.ACTIVE.toString(),
                event.getOccurredOn());
        externalToolHistoryViewRepository.save(subscriptionHistoryView);
    }
}
