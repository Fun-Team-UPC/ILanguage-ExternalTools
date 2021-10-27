package com.edu.upc.ilanguageexternaltool.query.projections;

import com.edu.upc.ilanguageexternaltool.command.domain.ExternalToolStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExternalToolHistoryView {
        @Id @GeneratedValue
        private int externalToolHistoryId;
        @Column()
        private String externalToolId;
        @Column(length = 50)
        private String name;
        @Column(length = 50)
        private String description;
        @Column(length = 50)
        private String resource;
        @Column(length = 50)
        private String status;
        private Instant createdAt;
        @Column(nullable = true)
        private Instant updatedAt;

    public ExternalToolHistoryView(String externalToolId, String name, String description, String resource, String status, Instant createdAt) {
        this.externalToolId = externalToolId;
        this.name = name;
        this.description = description;
        this.resource = resource;
        this.status = status;
        this.createdAt = createdAt;
    }

    public ExternalToolHistoryView(ExternalToolHistoryView externalToolHistoryView){
        this.externalToolId =externalToolHistoryView.getExternalToolId();
        this.name =externalToolHistoryView.getName();
        this.description =externalToolHistoryView.getDescription();
        this.resource =externalToolHistoryView.getResource();
        this.status = externalToolHistoryView.getStatus();
        this.createdAt = externalToolHistoryView.getCreatedAt();
    }


}
