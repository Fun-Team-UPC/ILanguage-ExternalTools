package com.edu.upc.ilanguageexternaltool.command.infra;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class ExternalToolName {
    @Id
    public String externalToolId;
    public String name;
    public String description;
    public String resource;

    public ExternalToolName() {
    }

    public ExternalToolName(String externalToolId, String name, String description, String resource) {
        this.externalToolId = externalToolId;
        this.name = name;
        this.description = description;
        this.resource = resource;
    }
}
