package com.edu.upc.ilanguageexternaltool.command.domain;

import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class ExternalTool {
    @AggregateIdentifier
    private String externalToolId;
    private String name;
    private String description;
    private String resource;
    private ExternalToolStatus status;

    public ExternalTool() {
    }

}
