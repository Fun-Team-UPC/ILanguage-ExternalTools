package com.edu.upc.ilanguageexternaltool.command.infra;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ExternalToolNameRepository extends JpaRepository<ExternalToolName, String> {

    //!Query refers to the class (MODEL)
    @Query("SELECT s FROM ExternalToolName s WHERE s.name = ?1")
    public Optional<ExternalToolName> findByName(String name);

    @Query("SELECT s FROM ExternalToolName s WHERE s.description = ?1")
    public Optional<ExternalToolName> findByPrice(String description);

    @Query("SELECT s FROM ExternalToolName s WHERE s.resource = ?1")
    public Optional<ExternalToolName>findByMonthDuration(String resource);

    @Query("SELECT s FROM ExternalToolName s WHERE s.externalToolId = ?1")
    public Optional<ExternalToolName>findExternalToolByExternalToolId(String subscriptionId);

}
