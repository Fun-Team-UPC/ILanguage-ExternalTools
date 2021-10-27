package com.edu.upc.ilanguageexternaltool;

import com.edu.upc.ilanguageexternaltool.command.domain.ExternalTool;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
class ILanguageExternalToolApplicationTests {

//    @Test
//    void contextLoads() {
//    }
//
//    @Test
//    @DisplayName("When getSessionByTopic With Valid topic Then Returns Session")
//    public void whenGetSessionByTopicAtWithValidTopicAtThenReturnsSession() {
//        // Arrange
//        String topic = "active";
//        ExternalTool externalTool = new ExternalTool();
//        externalTool.setName("Test");
//        externalTool.setDescription("Test");
//        externalTool.setDescription("Test");
//
////        when(sessionRepository.findByTopic(topic))
////                .thenReturn(Optional.of(session));
////
////        // Act
////        Session foundSession = sessionService.getSessionByTopic(topic);
////
////        // Assert
////        assertThat(foundSession.getTopic()).isEqualTo(topic);
//    }
}
