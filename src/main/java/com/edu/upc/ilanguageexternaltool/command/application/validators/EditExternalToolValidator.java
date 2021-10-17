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
        String name = editExternalToolRequest.getName();
        String description = editExternalToolRequest.getDescription();
        String resource = editExternalToolRequest.getResource();

        //First of all, id should be valid
        Optional<ExternalToolName> existingSubscription = externalToolNameRepository.findSubscriptionBySubscriptionId(editExternalToolRequest.getExternalToolId());
        if(existingSubscription.isEmpty()){
            notification.addError("External Tool with id" + editExternalToolRequest.getExternalToolId() + " does not exists");
            return notification;
        }
        existingSubscription = externalToolNameRepository.findByPrice(editExternalToolRequest.getName());
        if(existingSubscription.isPresent()){
            notification.addError("External Tool with that name already exists");
        }
        existingSubscription = externalToolNameRepository.findByMonthDuration(editExternalToolRequest.getDescription());
        if(existingSubscription.isPresent()){
            notification.addError("External Tool with that description already exists");
        }
        existingSubscription = externalToolNameRepository.findByName(editExternalToolRequest.getResource());
        if(existingSubscription.isPresent()){
            notification.addError("External Tool with that resource already exists");
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
