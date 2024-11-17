package org.evoting.de.domain.entities;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
@Entity
public class VotingResult {
    @Id
    @NotNull
    private String id;
    private String result; // Corresponds to ergebnis
    private String resultDistribution; // Corresponds to ergebnisverteilung
    @Id
    private String topicId; // Corresponds to themaId
    private String resultDescription; // Corresponds to ergebnisBeschreibung

    // Constructor
    public VotingResult(String id, String result, String resultDistribution, String topicId, String resultDescription) {
        this.id = id;
        this.result = result;
        this.resultDistribution = resultDistribution;
        this.topicId = topicId;
        this.resultDescription = resultDescription;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getResultDistribution() {
        return resultDistribution;
    }

    public void setResultDistribution(String resultDistribution) {
        this.resultDistribution = resultDistribution;
    }

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    public String getResultDescription() {
        return resultDescription;
    }

    public void setResultDescription(String resultDescription) {
        this.resultDescription = resultDescription;
    }
}