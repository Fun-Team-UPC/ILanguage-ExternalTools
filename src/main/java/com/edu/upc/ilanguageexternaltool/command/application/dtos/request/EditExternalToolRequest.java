package com.edu.upc.ilanguageexternaltool.command.application.dtos.request;

import lombok.Getter;
import lombok.Setter;
import lombok.Value;

@Getter
public class EditExternalToolRequest {
    private @Setter String externalToolId;
    private String name;
    private String description;
    private String resource;
}
