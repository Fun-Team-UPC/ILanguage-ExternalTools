package com.edu.upc.ilanguageexternaltool.query.projections;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.Instant;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExternalToolView {
    @Id @Column(length=50)
    private String externalToolId;
    @Column(length = 50)
    private String name;
    @Column(length = 50)
    private String description;
    @Column(length = 50)
    private String resource;
    @Column(length = 50)
    private String status;
    @Column(nullable = true)
    private Instant updatedAt;
}
