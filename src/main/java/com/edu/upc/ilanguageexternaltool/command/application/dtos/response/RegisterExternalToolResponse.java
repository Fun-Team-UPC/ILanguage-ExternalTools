package com.edu.upc.ilanguageexternaltool.command.application.dtos.response;

import com.edu.upc.ilanguageexternaltool.command.domain.ExternalToolStatus;
import lombok.Value;

@Value
public class RegisterExternalToolResponse {
    private String externalToolId;
    private String name;
    private String description;
    private String resource;
}
