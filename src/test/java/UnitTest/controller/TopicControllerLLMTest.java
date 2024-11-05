package UnitTest.controller;

import org.evoting.de.controllers.TopicController;
import org.evoting.de.domain.Topic;
import org.evoting.de.services.TopicService;
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

public class TopicControllerLLMTest {

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
        topics.add(new Topic());  // Simulating that there is one topic.

        when(topicService.getTopicsByAddress(address)).thenReturn(topics);

        // Act
        ResponseEntity<List<Topic>> response = topicController.getTopicsByAddress(address);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(topics, response.getBody());
        verify(topicService).getTopicsByAddress(address);
    }

    @Test
    void testGetTopicsByAddress_EmptyList() throws Exception {
        // Arrange
        String address = "123 Main St";
        List<Topic> topics = new ArrayList<>();  // Simulating no topics.

        when(topicService.getTopicsByAddress(address)).thenReturn(topics);

        // Act
        ResponseEntity<List<Topic>> response = topicController.getTopicsByAddress(address);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody().isEmpty(), "The body should be an empty list.");
        verify(topicService).getTopicsByAddress(address);
    }

    @Test
    void testGetTopicsByAddress_Error() throws Exception {
        // Arrange
        String address = "123 Main St";

        // Mock the service to throw an exception when an error occurs
        when(topicService.getTopicsByAddress(anyString())).thenThrow(new RuntimeException("Error fetching topics"));

        // Act
        ResponseEntity<List<Topic>> response = topicController.getTopicsByAddress(address);

        // Assert
        assertEquals(500, response.getStatusCodeValue());
        assertNull(response.getBody(), "Response body should be null when an error occurs.");
        verify(topicService).getTopicsByAddress(address);
    }

    @Test
    void testGetTopicsByAddress_InvalidAddress() throws Exception {
        // Arrange
        String address = "";  // Testing with an empty address.

        // Assuming that an empty address might result in a custom exception (e.g., InvalidAddressException)
        when(topicService.getTopicsByAddress(address)).thenThrow(new IllegalArgumentException("Invalid address"));

        // Act
        ResponseEntity<List<Topic>> response = topicController.getTopicsByAddress(address);

        // Assert
        assertEquals(400, response.getStatusCodeValue());
        assertNull(response.getBody(), "Response body should be null for an invalid address.");
        verify(topicService).getTopicsByAddress(address);
    }
}

