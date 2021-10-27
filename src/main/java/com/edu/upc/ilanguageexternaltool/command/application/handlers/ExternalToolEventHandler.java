package com.edu.upc.ilanguageexternaltool.command.application.handlers;


import com.edu.upc.ilanguageexternaltool.command.infra.ExternalToolName;
import com.edu.upc.ilanguageexternaltool.command.infra.ExternalToolNameRepository;
import contracts.events.ExternalToolEdited;
import contracts.events.ExternalToolRegistered;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@ProcessingGroup("externalToolName")
public class ExternalToolEventHandler {
    private final ExternalToolNameRepository externalToolNameRepository;

    public ExternalToolEventHandler(ExternalToolNameRepository externalToolNameRepository) {this.externalToolNameRepository = externalToolNameRepository;
    }

    @EventHandler
    public void on(ExternalToolRegistered event) {
        ExternalToolName externalToolName = new ExternalToolName();
        externalToolName.setExternalToolId(event.getExternalToolId());
        externalToolName.setName(event.getName());
        externalToolName.setDescription(event.getDescription());
        externalToolName.setResource(event.getResource());
        externalToolNameRepository.save(externalToolName);
    }

    @EventHandler
    public void on(ExternalToolEdited event){
        Optional<ExternalToolName> externalToolName = externalToolNameRepository.findExternalToolByExternalToolId(event.getExternalToolId());
        externalToolName.ifPresent(externalToolNameRepository::delete);
        ExternalToolName newExternalTool =  new ExternalToolName();
        newExternalTool.setExternalToolId(event.getExternalToolId());
        newExternalTool.setName(event.getName());
        newExternalTool.setDescription(event.getDescription());
        newExternalTool.setResource(event.getResource());
        externalToolNameRepository.save(newExternalTool);
    }
}
