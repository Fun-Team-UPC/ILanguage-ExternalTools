package com.edu.upc.ilanguageexternaltool.command.api;

import com.edu.upc.ilanguageexternaltool.command.application.dtos.request.EditExternalToolRequest;
import com.edu.upc.ilanguageexternaltool.command.application.dtos.request.RegisterExternalToolRequest;
import com.edu.upc.ilanguageexternaltool.command.application.dtos.response.EditExternalToolResponse;
import com.edu.upc.ilanguageexternaltool.command.application.dtos.response.RegisterExternalToolResponse;
import com.edu.upc.ilanguageexternaltool.command.application.services.ExternalToolApplicationService;
import com.edu.upc.ilanguageexternaltool.command.infra.ExternalToolNameRepository;
import io.swagger.annotations.Api;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.command.AggregateNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import pe.com.ilanguage.common.api.ApiController;
import pe.com.ilanguage.common.application.Notification;
import pe.com.ilanguage.common.application.Result;

@RestController
@RequestMapping("/externaltool")
@Api(tags = "ExternalTool")
public class ExternalToolCommandController {

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
