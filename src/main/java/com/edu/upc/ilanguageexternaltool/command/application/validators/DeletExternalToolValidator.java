package com.edu.upc.ilanguageexternaltool.command.application.validators;

import com.edu.upc.ilanguageexternaltool.command.infra.ExternalToolNameRepository;

public class DeletExternalToolValidator {
    private final ExternalToolNameRepository externalToolNameRepository;

    public DeletExternalToolValidator(ExternalToolNameRepository externalToolNameRepository) {
        this.externalToolNameRepository = externalToolNameRepository;
    }


}
