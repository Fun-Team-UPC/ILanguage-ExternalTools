package com.edu.upc.ilanguageexternaltool;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ILanguageExternalToolApplication {
    public static void main(String[] args) {
        SpringApplication.run(ILanguageExternalToolApplication.class, args);
    }
}
