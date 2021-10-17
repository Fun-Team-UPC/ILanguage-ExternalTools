package com.edu.upc.ilanguageexternaltool.config;

import com.edu.upc.ilanguageexternaltool.command.domain.ExternalTool;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.modelling.command.Repository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AxonConfig {

    @Bean
    public Repository<ExternalTool> eventSourcingRepository(EventStore eventStore) {
        return EventSourcingRepository.builder(ExternalTool.class)
            .eventStore(eventStore)
            .build();
    }
}
