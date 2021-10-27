package com.edu.upc.ilanguageexternaltool.command.application.services;

import com.edu.upc.ilanguageexternaltool.command.application.dtos.request.EditExternalToolRequest;
import com.edu.upc.ilanguageexternaltool.command.application.dtos.request.RegisterExternalToolRequest;
import com.edu.upc.ilanguageexternaltool.command.application.dtos.response.EditExternalToolResponse;
import com.edu.upc.ilanguageexternaltool.command.application.dtos.response.RegisterExternalToolResponse;
import com.edu.upc.ilanguageexternaltool.command.application.validators.EditExternalToolValidator;
import com.edu.upc.ilanguageexternaltool.command.application.validators.RegisterExternalToolValidator;
import com.edu.upc.ilanguageexternaltool.command.infra.ExternalToolNameRepository;
import contracts.commands.EditExternalTool;
import contracts.commands.RegisterExternalTool;
import lombok.AllArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Component;
import pe.com.ilanguage.common.application.Notification;
import pe.com.ilanguage.common.application.Result;
import pe.com.ilanguage.common.application.ResultType;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Component
public class ExternalToolApplicationService {

    private final RegisterExternalToolValidator registerExternalToolValidator;
    private final EditExternalToolValidator editExternalToolValidator;
    private final CommandGateway commandGateway;
    private final ExternalToolNameRepository externalToolNameRepository;

    public ExternalToolApplicationService(RegisterExternalToolValidator registerExternalToolValidator, EditExternalToolValidator editExternalToolValidator, CommandGateway commandGateway, ExternalToolNameRepository externalToolNameRepository) {
        this.registerExternalToolValidator = registerExternalToolValidator;
        this.editExternalToolValidator = editExternalToolValidator;
        this.commandGateway = commandGateway;
        this.externalToolNameRepository = externalToolNameRepository;
    }
//    public Result<RegisterExternalToolResponse,Notification> getById(RegisterExternalToolRequest registerExternalToolRequest) throws Exception {
//        Notification notification = this.registerExternalToolValidator.validate(registerExternalToolRequest);
//    }

    public Result<RegisterExternalToolResponse, Notification> register(RegisterExternalToolRequest registerExternalToolRequest) throws Exception {
        Notification notification = this.registerExternalToolValidator.validate(registerExternalToolRequest);
        if (notification.hasErrors()) {
            return Result.failure(notification);
        }
        String customerId = UUID.randomUUID().toString();
        RegisterExternalTool registerExternalTool = new RegisterExternalTool(
                customerId,
                registerExternalToolRequest.getName().trim(),
                registerExternalToolRequest.getDescription().trim(),
                registerExternalToolRequest.getResource().trim()
        );
        CompletableFuture<Object> future = commandGateway.send(registerExternalTool);
        CompletableFuture<ResultType> futureResult = future.handle((ok, ex) -> (ex != null) ? ResultType.FAILURE : ResultType.SUCCESS);
        ResultType resultType = futureResult.get();
        if (resultType == ResultType.FAILURE) {
            throw new Exception();
        }
        RegisterExternalToolResponse registerExternalToolResponseDTO = new RegisterExternalToolResponse(
                registerExternalTool.getExternalToolId(),
                registerExternalTool.getName(),
                registerExternalTool.getDescription(),
                registerExternalTool.getResource()
        );
        return Result.success(registerExternalToolResponseDTO);
    }

    public Result<EditExternalToolResponse, Notification> edit(EditExternalToolRequest editExternalToolRequest) throws Exception {
        Notification notification = this.editExternalToolValidator.validate(editExternalToolRequest);
        if (notification.hasErrors()) {
            return Result.failure(notification);
        }
        EditExternalTool editExternalTool = new EditExternalTool(
                editExternalToolRequest.getExternalToolId().trim(),
                editExternalToolRequest.getName().trim(),
                editExternalToolRequest.getDescription().trim(),
                editExternalToolRequest.getResource().trim()
        );
        CompletableFuture<Object> future = commandGateway.send(editExternalTool);
        CompletableFuture<ResultType> futureResult = future.handle((ok, ex) -> (ex != null) ? ResultType.FAILURE : ResultType.SUCCESS);
        ResultType resultType = futureResult.get();
        if (resultType == ResultType.FAILURE) {
            throw new Exception();
        }
        EditExternalToolResponse editExternalToolResponse = new EditExternalToolResponse(
                editExternalTool.getExternalToolId(),
                editExternalTool.getName(),
                editExternalTool.getDescription(),
                editExternalTool.getResource()
        );
        return Result.success(editExternalToolResponse);
    }
}
