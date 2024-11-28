package org.evoting.de.topicmanagement.domain.model.topic;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import org.springframework.boot.test.context.SpringBootTest;

public class TopicListTest {

    @Test
    public void testConstructorAndGetters() {
        String id = "list1";
        String districtName = "Sample District";

        TopicList topicList = new TopicList(id, null, districtName);

        assertEquals(id, topicList.getId());
        assertEquals(districtName, topicList.getDistrictName());
    }

    @Test
    public void testSetters() {
        TopicList topicList = new TopicList();

        // Teste Setzen neuer Werte
        String newId = "list2";
        String newDistrictName = "Another District";

        topicList.setId(newId);
        topicList.setDistrictName(newDistrictName);

        assertEquals(newId, topicList.getId());
        assertEquals(newDistrictName, topicList.getDistrictName());
    }

    @Test
    public void testManyToManyRelationship() {
        TopicList topicList = new TopicList();
        Set<Topic> topics = new HashSet<>();

        // Füge eine Beispiel-Topic hinzu (angenommen, es gibt eine Klasse Topic)
        Topic topic1 = new Topic("topic1", "This is a sample topic.");

        // Füge die Beziehung zu den Topics hinzu
        topics.add(topic1);


        // Überprüfe die Beziehung
        assertNotNull(topics);
        assertFalse(topics.isEmpty());
    }
}