package com.edu.upc.ilanguageexternaltool.command.application.dtos.response;

import lombok.Value;

public class GetExternalToolResponse {
    @Value
    public class EditExternalToolResponse {
        private String externalToolId;
        private String name;
        private String description;
        private String resource;
    }
}
