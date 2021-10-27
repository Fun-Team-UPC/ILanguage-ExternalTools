package com.edu.upc.ilanguageexternaltool.command.infra;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExternalToolName {
    @Id
    public String externalToolId;
    public String name;
    public String description;
    public String resource;
}
