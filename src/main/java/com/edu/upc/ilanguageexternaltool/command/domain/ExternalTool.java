package com.edu.upc.ilanguageexternaltool.command.domain;

import contracts.commands.EditExternalTool;
import contracts.commands.RegisterExternalTool;
import contracts.events.ExternalToolEdited;
import contracts.events.ExternalToolRegistered;
import lombok.Getter;
import lombok.Setter;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import java.time.Instant;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Getter
@Setter
@Aggregate
public class ExternalTool {
    @AggregateIdentifier
    private String externalToolId;
    private String name;
    private String description;
    private String resource;
    private ExternalToolStatus status;

    public ExternalTool() {
    }

    @CommandHandler
    public ExternalTool(RegisterExternalTool command) {
        Instant now = Instant.now();
        apply(
                new ExternalToolRegistered(
                        command.getExternalToolId(),
                        command.getName(),
                        command.getDescription(),
                        command.getResource(),
                        now
                )
        );
    }

    @CommandHandler
    public void handle(EditExternalTool command){
        Instant now =Instant.now();
        apply(
                new ExternalToolEdited(
                        command.getExternalToolId(),
                        command.getName(),
                        command.getDescription(),
                        command.getResource(),
                        now
                )
        );
    }

    @EventSourcingHandler
    protected void  on(ExternalToolRegistered event) {
        externalToolId = event.getExternalToolId();
        name = event.getName();
        description = event.getDescription();
        resource = event.getResource();
    }

    @EventSourcingHandler
    protected void on(ExternalToolEdited event) {
        name =event.getName();
        description =event.getDescription();
        resource =event.getResource();
    }
}
