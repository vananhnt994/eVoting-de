package org.evoting.topicmanagement.application.services;

import org.evoting.topicmanagement.domain.model.topic.Topic;
import org.evoting.topicmanagement.domain.repository.TopicRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class TopicServiceTest {

    @Mock
    private TopicRepository topicRepository;

    @InjectMocks
    private TopicService topicService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetTopicsByAddress_Success() throws Exception {
        // Arrange
        String address = "123 Main St";
        List<Topic> topics = Arrays.asList(
                new Topic("1", "Description for Topic 1"),
                new Topic("2", "Description for Topic 2")
        );

        when(topicRepository.findByCityName(address)).thenReturn(topics);

        // Act
        List<Topic> result = topicService.getTopicsByAddress(address);

        // Assert
        assertEquals(2, result.size());
        assertEquals("1", result.get(0).getId());
        assertEquals("Description for Topic 1", result.get(0).getTopicDescription());
    }

    @Test
    void testGetTopicsByAddress_NoTopicsFound() {
        // Arrange
        String address = "nonexistent address";

        when(topicRepository.findByCityName(address)).thenReturn(Collections.emptyList());

        // Act & Assert
        Exception exception = assertThrows(Exception.class, () -> {
            topicService.getTopicsByAddress(address);
        });

        assertEquals("No topics found for address: nonexistent address", exception.getMessage());
    }
}