package org.evoting.de.unittests.service;

import org.evoting.de.domain.entities.Topic;
import org.evoting.de.infrastructure.repositories.TopicRepository;
import org.evoting.de.services.TopicService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TopicServiceTest {

    @Mock
    private TopicRepository topicRepository;

    @InjectMocks
    private TopicService topicService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetTopicsByAddress_Success() throws Exception {
        String address = "Berlin";
        List<Topic> topics = new ArrayList<>();
        topics.add(new Topic()); // Füge ein Beispiel-Topic hinzu

        when(topicRepository.findByCityName(address)).thenReturn(topics);

        List<Topic> result = topicService.getTopicsByAddress(address);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        verify(topicRepository).findByCityName(address);
    }

    @Test
    public void testGetTopicsByAddress_NoTopicsFound() {
        String address = "Unknown City";

        when(topicRepository.findByCityName(address)).thenReturn(new ArrayList<>());

        Exception exception = assertThrows(Exception.class, () -> {
            topicService.getTopicsByAddress(address);
        });

        assertEquals("No topics found for address: " + address, exception.getMessage());
        verify(topicRepository).findByCityName(address);
    }
}