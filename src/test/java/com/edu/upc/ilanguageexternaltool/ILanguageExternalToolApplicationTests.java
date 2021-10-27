package com.edu.upc.ilanguageexternaltool;

import com.edu.upc.ilanguageexternaltool.command.application.services.ExternalToolApplicationService;
import com.edu.upc.ilanguageexternaltool.command.domain.ExternalTool;
import com.edu.upc.ilanguageexternaltool.command.infra.ExternalToolName;
import com.edu.upc.ilanguageexternaltool.command.infra.ExternalToolNameRepository;
import contracts.events.ExternalToolRegistered;
import org.assertj.core.api.AssertionsForClassTypes;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.Instant;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.axonframework.modelling.command.AggregateLifecycle.apply;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class ILanguageExternalToolApplicationTests {
    @MockBean
    private ExternalToolNameRepository externalToolNameRepository;

    private ExternalToolApplicationService externalToolApplicationService;

    @BeforeEach()
    public void SetUp(){
        //fixture = new AggregateTestFixture<ExternalTool>(ExternalTool.class);
    }

    @Test
    @DisplayName("Get external tool by name with valid name then return true")
    public void whenGetExternalToolByNameWithValidNameThenReturnsSubscription(){
        //Arrange
        String name = "FullPass";
        ExternalToolName externalToolName  = new ExternalToolName();
        externalToolName.setName(name);
        externalToolName.setExternalToolId("HDUD5FSDFDSFGJ");
        when(externalToolNameRepository.findByName(name)).thenReturn(Optional.of(externalToolName));
        Optional<ExternalToolName> foundExternalTool = externalToolNameRepository.findByName(name);
        AssertionsForClassTypes.assertThat(foundExternalTool.get().getName()).isEqualTo(externalToolName.getName());
    }


    @Test
    @DisplayName("Get external tool by name with valid name then return true")
    public void ssd(){
        //https://docs.axoniq.io/reference-guide/v/3.3/part-ii-domain-logic/testing
        /*fixture.given()
                .when(new RegisterSubscription("sddsadsadsad","sadasd",5,100))
                .expectSuccessfulHandlerExecution()
                .expectReturnValue(new RegisterSubscriptionRes("sddsadsadsad","sadasd",5,100));
               // .expectNoEvents(new SubscriptionRegistered("sddsadsadsad","sadasd", 5,100, Instant.now()));*/
        apply(new ExternalToolRegistered("sddsadsadsad","sadasd","sd","100", Instant.now()));
        assertEquals(1, AggregateLifecycle.isLive());

    }
}
