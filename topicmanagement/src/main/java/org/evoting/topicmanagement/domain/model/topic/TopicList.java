package org.evoting.topicmanagement.domain.model.topic;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import java.util.Set;

@Entity
public class TopicList {
    @Id
    private String id;
    private String cityName; // Corresponds to landkreisName
    @ManyToMany
    private Set<Topic> topics; // Beziehung zu Topics
    // Constructor
    public TopicList(String id, String postalCode, String cityName) {
        this.id = id;
        this.cityName = cityName;
    }

    public TopicList() {

    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String districtName) {
        this.cityName = districtName;
    }

}