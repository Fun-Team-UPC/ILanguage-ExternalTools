package com.edu.upc.ilanguageexternaltool.query.projections;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExternalToolHistoryViewRepository extends JpaRepository<ExternalToolHistoryView, String> {
//    @Query(value = "SELECT * FROM externaltool.external_tool_history_view WHERE 'external_tool_history_id' = (SELECT MAX(external_tool_history_id) FROM externaltool.external_tool_history_view WHERE external_tool_history_id = externatlToolId)", nativeQuery = true)
//    Optional<ExternalToolHistoryView> getLastByExternalToolId(@Param("externatlToolId")String externatlToolId);
//
//    @Query(value = "SELECT * FROM external_tool_history_view WHERE external_tool_id = :externalToolId ORDER BY created_at", nativeQuery = true)
//    List<ExternalToolHistoryView> getHistoryByExternalToolId(@Param("externatlToolId")String externatlToolId);
    @Query("SELECT sh FROM ExternalToolHistoryView sh where sh.externalToolId=?1")
    public List<ExternalToolHistoryView> getExternalToolHistoryById(String externalToolId);
}
