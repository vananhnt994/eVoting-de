package org.evoting.domain.model.topic;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import java.util.Set;

@Entity
public class Topic {
    @Id
    private String id;
    private String topicDescription;

    @ManyToMany(mappedBy = "topics")
    private Set<TopicList> topicLists; // Beziehung zur TopicList
    // Constructor
    public Topic(String id, String topicDescription) {
        this.id = id;
        this.topicDescription = topicDescription;
    }

    public Topic() {

    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTopicDescription() {
        return topicDescription;
    }

    public void setTopicDescription(String topicDescription) {
        this.topicDescription = topicDescription;
    }
}