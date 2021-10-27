package com.edu.upc.ilanguageexternaltool.query.projections;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExternalToolViewRepository extends JpaRepository<ExternalToolView, String> {
//    Optional<ExternalToolView> getByName(String name);
//
//    @Query(value = "SELECT * FROM externaltool.external_tool_view WHERE external_tool_id <> :externalToolId AND name = :name", nativeQuery = true)
//    Optional<ExternalToolView> getByExternalToolIdAndName(String externalToolId, String name);
    @Query("SELECT s from ExternalToolView s WHERE s.name=?1")
    public ExternalToolView findByName(String externalToolName);

    @Query("SELECT s from ExternalToolView s WHERE s.externalToolId=?1")
    public ExternalToolView getById(String externalToolId);
}
