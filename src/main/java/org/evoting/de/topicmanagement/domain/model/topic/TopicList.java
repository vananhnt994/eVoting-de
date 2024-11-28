package org.evoting.de.votemanagement.domain.model.topic;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class TopicList {
    @Id
    private String id;
    private String cityName; // Corresponds to landkreisName
    @ManyToMany
    private Set<Topic> topics; // Beziehung zu Topics
    // Constructor
    public TopicList(String id, String postalCode, String districtName) {
        this.id = id;
        this.cityName = districtName;
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

    public String getDistrictName() {
        return cityName;
    }

    public void setDistrictName(String districtName) {
        this.cityName = districtName;
    }

}