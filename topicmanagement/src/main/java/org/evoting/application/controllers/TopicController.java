package topicmanagement.application.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import topicmanagement.application.services.TopicService;
import topicmanagement.domain.model.topic.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TopicController {

    private final TopicService topicService;
    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

    @Autowired
    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }
    @GetMapping("/topics")
    public ResponseEntity<List<Topic>> getTopicsByAddress(@RequestParam String address) {
        try {
            System.out.println(address);
            List<Topic> topics = topicService.getTopicsByAddress(address);
            System.out.println(topics);
            String json = ow.writeValueAsString(topics);
            System.out.println(json);
            return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON).body(topics); // Rückgabe der Themen mit HTTP 200 OK
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null); // Rückgabe eines Fehlerstatus bei Ausnahme
        }
    }
}