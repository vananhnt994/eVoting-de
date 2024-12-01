package org.evoting.topicmanagement.application.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.evoting.topicmanagement.application.services.TopicService;
import org.evoting.topicmanagement.domain.model.topic.Topic;

import java.util.List;

@RestController
@RequestMapping("/api/topics")
@CrossOrigin(origins = "http://localhost:3000")
public class TopicController {

    private final TopicService topicService;

    @Autowired
    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @GetMapping(value = "/topics", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Topic> getTopicsByAddress(@RequestParam String address) throws Exception {
        return topicService.getTopicsByAddress(address);

    }
}