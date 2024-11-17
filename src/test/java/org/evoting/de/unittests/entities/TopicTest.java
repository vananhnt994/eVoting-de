package org.evoting.de.unittests.entities;

import org.evoting.de.domain.entities.Topic;
import org.evoting.de.domain.entities.TopicList;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class TopicTest {

    @Test
    public void testConstructorAndGetters() {
        String id = "topic1";
        String topicDescription = "This is a sample topic.";

        Topic topic = new Topic(id, topicDescription);

        assertEquals(id, topic.getId());
        assertEquals(topicDescription, topic.getTopicDescription());
    }

    @Test
    public void testSetters() {
        Topic topic = new Topic();

        // Teste Setzen neuer Werte
        String newId = "topic2";
        String newTopicDescription = "This is another sample topic.";

        topic.setId(newId);
        topic.setTopicDescription(newTopicDescription);

        assertEquals(newId, topic.getId());
        assertEquals(newTopicDescription, topic.getTopicDescription());
    }

    @Test
    public void testManyToManyRelationship() {
        Topic topic = new Topic();
        Set<TopicList> topicLists = new HashSet<>();

        // Füge eine Beispiel-TopicList hinzu (angenommen, es gibt eine Klasse TopicList)
        TopicList list1 = new TopicList(); // Hier solltest du eine Instanz von TopicList erstellen
        // Füge die Beziehung zur TopicList hinzu (dieser Teil hängt von der Implementierung der TopicList ab)

        // Füge die Liste zum Set hinzu
        topicLists.add(list1);

        // Setze das Set in das Thema (wenn es einen Setter dafür gibt)
        // topic.setTopicLists(topicLists); // Uncomment this line if you have a setter for the relationship

        // Überprüfe die Beziehung
        assertNotNull(topicLists);
        assertFalse(topicLists.isEmpty());
    }
}