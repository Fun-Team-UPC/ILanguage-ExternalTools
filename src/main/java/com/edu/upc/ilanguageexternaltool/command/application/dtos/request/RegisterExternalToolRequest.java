package com.edu.upc.ilanguageexternaltool.command.application.dtos.request;

import com.edu.upc.ilanguageexternaltool.command.domain.ExternalToolStatus;
import lombok.Getter;
import lombok.Value;

@Value
@Getter
public class RegisterExternalToolRequest {
    private String name;
    private String description;
    private String resource;
}
