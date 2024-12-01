package org.evoting.topicmanagement.application.dto;

import jakarta.persistence.ManyToMany;
import org.evoting.topicmanagement.domain.model.topic.Topic;

import java.util.Set;

public class TopicListDto {
    private String cityName; // Corresponds to landkreisName
    @ManyToMany
    private Set<Topic> topics; // Beziehung zu Topics
    // Constructor
    public TopicListDto(String postalCode, String cityName) {
        this.cityName = cityName;
    }

    public TopicListDto() {

    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String districtName) {
        this.cityName = districtName;
    }

}
