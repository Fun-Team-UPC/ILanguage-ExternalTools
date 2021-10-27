package com.edu.upc.ilanguageexternaltool.command.application.validators;

import com.edu.upc.ilanguageexternaltool.command.application.dtos.request.EditExternalToolRequest;
import com.edu.upc.ilanguageexternaltool.command.domain.ExternalTool;
import com.edu.upc.ilanguageexternaltool.command.infra.ExternalToolName;
import com.edu.upc.ilanguageexternaltool.command.infra.ExternalToolNameRepository;
import org.axonframework.messaging.unitofwork.DefaultUnitOfWork;
import org.axonframework.messaging.unitofwork.UnitOfWork;
import org.axonframework.modelling.command.AggregateNotFoundException;
import org.axonframework.modelling.command.Repository;
import org.springframework.stereotype.Component;
import pe.com.ilanguage.common.application.Notification;
import java.util.Optional;

@Component
public class EditExternalToolValidator {
    private final ExternalToolNameRepository externalToolNameRepository;
    private final Repository<ExternalTool> externalToolRepository;

    public EditExternalToolValidator(ExternalToolNameRepository externalToolNameRepository, Repository<ExternalTool> externalToolRepository) {
        this.externalToolNameRepository = externalToolNameRepository;
        this.externalToolRepository = externalToolRepository;
    }

    public Notification validate(EditExternalToolRequest editExternalToolRequest)
    {
        Notification notification = new Notification();
        String externalToolId = editExternalToolRequest.getExternalToolId().trim();
        if (externalToolId.isEmpty()) {
            notification.addError("External Tool Id is required");
        }
        loadExternalToolAggregate(externalToolId);
        String name = editExternalToolRequest.getName().trim();
        if (name.isEmpty()) {
            notification.addError("External Tool Name is required");
        }
        String description = editExternalToolRequest.getDescription().trim();
        if (description.isEmpty()) {
            notification.addError("External Tool Description is required");
        }
        String resource = editExternalToolRequest.getResource().trim();
        if (resource.isEmpty()) {
            notification.addError("External Tool Resource is required");
        }
        if (notification.hasErrors()) {
            return notification;
        }
        return notification;
    }

    private void loadExternalToolAggregate(String externalToolId) {
        UnitOfWork unitOfWork = null;
        try {
            unitOfWork = DefaultUnitOfWork.startAndGet(null);
            externalToolRepository.load(externalToolId);
            unitOfWork.commit();
        } catch (AggregateNotFoundException ex) {
            unitOfWork.commit();
            throw ex;
        } catch(Exception ex) {
            if (unitOfWork != null) unitOfWork.rollback();
        }
    }
}
