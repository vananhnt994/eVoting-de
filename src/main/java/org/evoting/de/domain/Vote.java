package org.evoting.de.domain;

//import java.util.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
public class Vote {
    @Id
    @NotNull
    private String id;
    @Id
    @NotNull
    private String topicId; // Corresponds to themaId
    private boolean votingOptions; // Corresponds to abstimmungsmöglichkeiten
    private LocalDateTime  startDate; // Corresponds to startdatum
    private LocalDateTime  endDate; // Corresponds to enddatum

    // Constructor
    public Vote(String id, String topicId, boolean votingOptions, LocalDateTime startDate, LocalDateTime endDate) {
        this.id = id;
        this.topicId = topicId;
        this.votingOptions = votingOptions;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    public boolean isVotingOptions() {
        return votingOptions;
    }

    public void setVotingOptions(boolean votingOptions) {
        this.votingOptions = votingOptions;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }
}