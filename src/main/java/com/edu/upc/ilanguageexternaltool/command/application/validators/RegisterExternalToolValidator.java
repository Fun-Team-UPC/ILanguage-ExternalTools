package com.edu.upc.ilanguageexternaltool.command.application.validators;

import com.edu.upc.ilanguageexternaltool.command.application.dtos.request.RegisterExternalToolRequest;
import com.edu.upc.ilanguageexternaltool.command.infra.ExternalToolName;
import com.edu.upc.ilanguageexternaltool.command.infra.ExternalToolNameRepository;
import org.springframework.stereotype.Component;
import pe.com.ilanguage.common.application.Notification;

import java.util.Optional;

@Component
public class RegisterExternalToolValidator {
    private final ExternalToolNameRepository externalToolNameRepository;

    public RegisterExternalToolValidator(ExternalToolNameRepository externalToolNameRepository) {
        this.externalToolNameRepository = externalToolNameRepository;
    }

    public Notification validate(RegisterExternalToolRequest registerExternalToolValidator)
    {
        Notification notification = new Notification();
        String name = registerExternalToolValidator.getName().trim();
        if (name.isEmpty()) {
            notification.addError("Customer firstname is required");
        }
        String description = registerExternalToolValidator.getDescription().trim();
        if (description.isEmpty()) {
            notification.addError("Customer lastname is required");
        }
        String resource = registerExternalToolValidator.getResource().trim();
        if (resource.isEmpty()) {
            notification.addError("Customer dni is required");
        }
        if (notification.hasErrors()) {
            return notification;
        }
        Optional<ExternalToolName> externalToolNameOptional = externalToolNameRepository.findById(name);
        if (externalToolNameOptional.isPresent()) {
            notification.addError("External Tool Name is taken");
        }
        return notification;
    }
}
