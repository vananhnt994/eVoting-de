package org.evoting.topicmanagement.domain.model.vote;

//import java.util.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

@Entity
public class Vote {
    @Id
    @NotNull
    private String id;
    @Id
    @NotNull
    private String topicId; // Corresponds to themaId
    private boolean votingOptions; // Corresponds to abstimmungsmöglichkeiten
    private Date  startDate; // Corresponds to startdatum
    private Date endDate; // Corresponds to enddatum

    // Constructor
    public Vote(String id, String topicId, boolean votingOptions, Date startDate, Date endDate) {
        this.id = id;
        this.topicId = topicId;
        this.votingOptions = votingOptions;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Vote() {

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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}