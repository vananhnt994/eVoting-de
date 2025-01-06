package org.evoting.topicmanagement.domain.model.topic;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
public class Topic {
    @Id
    private String id;
    private String topicDescription;
    private Boolean active;
    private LocalDateTime startVoting;
    private LocalDateTime endVoting;

    @ManyToMany(mappedBy = "topics")
    private Set<TopicList> topicLists; // Beziehung zur TopicList
    // Constructor
    public Topic(String id, String topicDescription, Boolean active, LocalDateTime startVoting, LocalDateTime endVoting) {
        this.id = id;
        this.topicDescription = topicDescription;
        this.active = active;
        this.startVoting = startVoting;
        this.endVoting = endVoting;
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
    public Boolean getActive() {
        return active;
    }
    public void setActive(Boolean active) {
        this.active = active;
    }
    public LocalDateTime getStartVoting() {
        return startVoting;
    }
    public void setStartVoting(LocalDateTime startVoting) {
        this.startVoting = startVoting;
    }
    public LocalDateTime getEndVoting() {
        return endVoting;

    }
    public void setEndVoting(LocalDateTime endVoting) {
        this.endVoting = endVoting;
    }

}