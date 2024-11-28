package org.evoting.de.unittests.application.controller;

import org.evoting.de.application.controllers.TopicController;
import org.evoting.de.topicmanagement.domain.model.topic.Topic;
import org.evoting.de.topicmanagement.application.services.TopicService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class TopicControllerTest {

    @InjectMocks
    private TopicController topicController;

    @Mock
    private TopicService topicService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetTopicsByAddress_Success() throws Exception {
        // Arrange
        String address = "123 Main St";
        List<Topic> topics = new ArrayList<>();
        topics.add(new Topic());

        when(topicService.getTopicsByAddress(address)).thenReturn(topics);

        // Act
        ResponseEntity<List<Topic>> response = topicController.getTopicsByAddress(address);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(topics, response.getBody());
        verify(topicService).getTopicsByAddress(address);
    }

    @Test
    void testGetTopicsByAddress_Error() throws Exception {
        // Arrange
        String address = "123 Main St";

        when(topicService.getTopicsByAddress(anyString())).thenThrow(new RuntimeException("Error fetching topics"));

        // Act
        ResponseEntity<List<Topic>> response = topicController.getTopicsByAddress(address);

        // Assert
        assertEquals(500, response.getStatusCodeValue());
        assertNull(response.getBody());
        verify(topicService).getTopicsByAddress(address);
    }
}