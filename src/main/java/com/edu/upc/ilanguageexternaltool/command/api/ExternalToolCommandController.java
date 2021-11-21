package com.edu.upc.ilanguageexternaltool.command.api;

import com.edu.upc.ilanguageexternaltool.command.application.dtos.request.EditExternalToolRequest;
import com.edu.upc.ilanguageexternaltool.command.application.dtos.request.RegisterExternalToolRequest;
import com.edu.upc.ilanguageexternaltool.command.application.dtos.response.EditExternalToolResponse;
import com.edu.upc.ilanguageexternaltool.command.application.dtos.response.RegisterExternalToolResponse;
import com.edu.upc.ilanguageexternaltool.command.application.services.ExternalToolApplicationService;
import com.edu.upc.ilanguageexternaltool.command.domain.ExternalTool;
import com.edu.upc.ilanguageexternaltool.command.infra.ExternalToolNameRepository;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.command.AggregateNotFoundException;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import pe.com.ilanguage.common.api.ApiController;
import pe.com.ilanguage.common.application.Notification;
import pe.com.ilanguage.common.application.Result;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/externaltool")
//@Tag(name = "ExternalTool")

public class ExternalToolCommandController {

    private static final Logger log = LoggerFactory.getLogger(ExternalToolCommandController.class);
    private final ExternalToolApplicationService externalToolApplicationService;
    private final CommandGateway commandGateway;

    public ExternalToolCommandController(ExternalToolApplicationService externalToolApplicationService, CommandGateway commandGateway) {
        this.externalToolApplicationService = externalToolApplicationService;
        this.commandGateway = commandGateway;
    }

    @PostMapping(path= "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> register(@RequestBody RegisterExternalToolRequest registerExternalToolRequest) {
        try {
            Result<RegisterExternalToolResponse, Notification> result = externalToolApplicationService.register(registerExternalToolRequest);
            if (result.isSuccess()) {
                return ApiController.created(result.getSuccess());
            }
            return ApiController.error(result.getFailure().getErrors());
        } catch(Exception e) {
            return ApiController.serverError();
        }
    }

    @PutMapping("/{externalToolId}")
    public ResponseEntity<Object> edit(@PathVariable("externalToolId") String externalToolId, @RequestBody EditExternalToolRequest editExternalToolRequest) {
        try {
            editExternalToolRequest.setExternalToolId(externalToolId);
            Result<EditExternalToolResponse, Notification> result = externalToolApplicationService.edit(editExternalToolRequest);
            if (result.isSuccess()) {
                return ApiController.ok(result.getSuccess());
            }
            return ApiController.error(result.getFailure().getErrors());
        } catch (AggregateNotFoundException exception) {
            return ApiController.notFound();
        } catch(Exception e) {
            return ApiController.serverError();
        }
    }
}
