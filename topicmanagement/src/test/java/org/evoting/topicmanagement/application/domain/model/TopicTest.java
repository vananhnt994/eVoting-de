package org.evoting.topicmanagement.application.domain.model;

import org.evoting.topicmanagement.domain.model.topic.Topic;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TopicTest {

    @Test
    void testConstructorAndGetters() {
        // Arrange
        String expectedId = "1";
        String expectedDescription = "Sample Topic Description";

        // Act
        Topic topic = new Topic(expectedId, expectedDescription);

        // Assert
        assertEquals(expectedId, topic.getId());
        assertEquals(expectedDescription, topic.getTopicDescription());
    }

    @Test
    void testSetters() {
        // Arrange
        Topic topic = new Topic();
        String newId = "2";
        String newDescription = "Another Topic Description";

        // Act
        topic.setId(newId);
        topic.setTopicDescription(newDescription);

        // Assert
        assertEquals(newId, topic.getId());
        assertEquals(newDescription, topic.getTopicDescription());
    }
}