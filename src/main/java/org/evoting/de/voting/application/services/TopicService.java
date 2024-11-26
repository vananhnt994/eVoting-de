package org.evoting.de.voting.application.services;

import org.evoting.de.voting.domain.model.topic.Topic;
import org.evoting.de.voting.domain.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicService {

    private final TopicRepository topicRepository; // Inject the repository

    @Autowired
    public TopicService(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }
    public List<Topic> getTopicsByAddress(String address) throws Exception {
        List<Topic> topics = topicRepository.findByCityName(address); // Verwende das Repository zur Abfrage der Themen
        if (topics.isEmpty()) {
            throw new Exception("No topics found for address: " + address);
        }
        return topics;
    }
}