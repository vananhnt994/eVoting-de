package org.evoting.topicmanagement.application.controllers;

import org.evoting.topicmanagement.application.services.TopicService;
import org.evoting.topicmanagement.domain.model.topic.Topic;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class TopicControllerTest {

    @Mock
    private TopicService topicService;

    @InjectMocks
    private TopicController topicController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(topicController).build();
    }

    @Test
    void testGetTopicsByAddress_Success() throws Exception {
        // Arrange
        String address = "123 Main St";
        List<Topic> topics = Arrays.asList(
                new Topic("1", "Description for Topic 1"),
                new Topic("2", "Description for Topic 2")
        );

        when(topicService.getTopicsByAddress(address)).thenReturn(topics);

        // Act & Assert
        mockMvc.perform(get("/api/topics/topics")
                        .param("address", address)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[0].topicDescription").value("Description for Topic 1"))
                .andExpect(jsonPath("$[1].id").value("2"))
                .andExpect(jsonPath("$[1].topicDescription").value("Description for Topic 2"));
    }

    @Test
    void testGetTopicsByAddress_NotFound() throws Exception {
        // Arrange
        String address = "nonexistent address";

        when(topicService.getTopicsByAddress(anyString())).thenThrow(new RuntimeException("No topics found"));

        // Act & Assert
        mockMvc.perform(get("/api/topics/topics")
                        .param("address", address)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());
    }
}