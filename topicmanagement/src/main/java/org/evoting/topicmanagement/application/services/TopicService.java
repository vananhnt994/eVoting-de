package org.evoting.topicmanagement.application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.evoting.topicmanagement.domain.model.topic.Topic;
import org.evoting.topicmanagement.domain.repository.TopicRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        System.out.println(sortTopicsByEndDate(topics));
        return getActiveTopicsToVote(topics);

    }

    public List<Topic> getActiveTopics(List<Topic> topics) {
        return topics.stream()
                .filter(Topic::getActive)
                .collect(Collectors.toList());

    }

    public List<Topic> getActiveTopicsToVote(List<Topic> topics) {
        return topics.stream()
                .filter(Topic::getActive)
                .filter(topic -> (LocalDateTime.now().isAfter(topic.getStartVoting()) && LocalDateTime.now().isBefore(topic.getEndVoting())))
                .collect(Collectors.toList());
    }

    public Map<LocalDateTime, Long> sortTopicsByEndDate(List<Topic> topics) {
        return topics.stream()
                .collect(Collectors.groupingBy(
                        Topic::getEndVoting,
                        Collectors.counting()));
    }
}